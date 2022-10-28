package com.kq.future;

import javax.annotation.Nullable;
import java.util.concurrent.Executor;


/**
 * @author kq
 * @date 2022-10-28 9:42
 * @since 2020-0630
 */
public class ExecutionList {

    private boolean executed;

    private RunnableExecutorPair runnables;

    public void add(Runnable runnable, Executor executor) {
        // Fail fast on a null. We throw NPE here because the contract of Executor states that it throws
        // NPE on null listener, so we propagate that contract up into the add method as well.
        checkNotNull(runnable, "Runnable was null.");
        checkNotNull(executor, "Executor was null.");

        // Lock while we check state. We must maintain the lock while adding the new pair so that
        // another thread can't run the list out from under us. We only add to the list if we have not
        // yet started execution.
        synchronized (this) {
            if (!executed) {
                runnables = new ExecutionList.RunnableExecutorPair(runnable, executor, runnables);
                return;
            }
        }
        // Execute the runnable immediately. Because of scheduling this may end up getting called before
        // some of the previously added runnables, but we're OK with that. If we want to change the
        // contract to guarantee ordering among runnables we'd have to modify the logic here to allow
        // it.
        executeListener(runnable, executor);
    }

    public void execute() {
        // Lock while we update our state so the add method above will finish adding any listeners
        // before we start to run them.
        ExecutionList.RunnableExecutorPair list;
        synchronized (this) {
            if (executed) {
                return;
            }
            executed = true;
            list = runnables;
            runnables = null; // allow GC to free listeners even if this stays around for a while.
        }
        // If we succeeded then list holds all the runnables we to execute. The pairs in the stack are
        // in the opposite order from how they were added so we need to reverse the list to fulfill our
        // contract.
        // This is somewhat annoying, but turns out to be very fast in practice. Alternatively, we could
        // drop the contract on the method that enforces this queue like behavior since depending on it
        // is likely to be a bug anyway.

        // N.B. All writes to the list and the next pointers must have happened before the above
        // synchronized block, so we can iterate the list without the lock held here.
        ExecutionList.RunnableExecutorPair reversedList = null;
        while (list != null) {
            ExecutionList.RunnableExecutorPair tmp = list;
            list = list.next;
            tmp.next = reversedList;
            reversedList = tmp;
        }
        while (reversedList != null) {
            executeListener(reversedList.runnable, reversedList.executor);
            reversedList = reversedList.next;
        }
    }

    private static void executeListener(Runnable runnable, Executor executor) {
        try {
            executor.execute(runnable);
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }

    private static final class RunnableExecutorPair {
        final Runnable runnable;
        final Executor executor;
        @Nullable
       ExecutionList.RunnableExecutorPair next;

        RunnableExecutorPair(Runnable runnable, Executor executor, ExecutionList.RunnableExecutorPair next) {
            this.runnable = runnable;
            this.executor = executor;
            this.next = next;
        }
    }

    public static <T> T checkNotNull(T reference, @Nullable Object errorMessage) {
        if (reference == null) {
            throw new NullPointerException(String.valueOf(errorMessage));
        }
        return reference;
    }
}
