package com.kq.future;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;

import static com.google.common.base.Preconditions.checkState;

/**
 * @author kq
 * @date 2022-10-28 10:09
 * @since 2020-0630
 */
public class Futures {

    public static <V> void addCallback(
            final ListenableFuture<V> future,
            final FutureCallback<? super V> callback,
            Executor executor) {
        checkNotNull(callback);
        Runnable callbackListener =
                new Runnable() {  // callback包装成
                    @Override
                    public void run() {
                        final V value;
                        try {
                            value = getDone(future);
                        } catch (ExecutionException e) {
                            callback.onFailure(e.getCause());
                            return;
                        } catch (RuntimeException e) {
                            callback.onFailure(e);
                            return;
                        } catch (Error e) {
                            callback.onFailure(e);
                            return;
                        }
                        callback.onSuccess(value);
                    }
                };
        future.addListener(callbackListener, executor);
    }


    public static <V> V getDone(Future<V> future) throws ExecutionException {
        /*
         * We throw IllegalStateException, since the call could succeed later. Perhaps we "should" throw
         * IllegalArgumentException, since the call could succeed with a different argument. Those
         * exceptions' docs suggest that either is acceptable. Google's Java Practices page recommends
         * IllegalArgumentException here, in part to keep its recommendation simple: Static methods
         * should throw IllegalStateException only when they use static state.
         *
         *
         * Why do we deviate here? The answer: We want for fluentFuture.getDone() to throw the same
         * exception as Futures.getDone(fluentFuture).
         */
        checkState(future.isDone(), "Future was expected to be done: %s", future);
        return getUninterruptibly(future);
    }

    public static <V> V getUninterruptibly(Future<V> future) throws ExecutionException {
        boolean interrupted = false;
        try {
            while (true) {
                try {
                    return future.get();
                } catch (InterruptedException e) {
                    interrupted = true;
                }
            }
        } finally {
            if (interrupted) {
                Thread.currentThread().interrupt();
            }
        }
    }


    public static <T> T checkNotNull(T reference) {
        if (reference == null) {
            throw new NullPointerException();
        }
        return reference;
    }

}
