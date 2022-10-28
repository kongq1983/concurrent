package com.kq.future;


import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;



/**
 * @author kq
 * @date 2022-10-28 9:26
 * @since 2020-0630
 */
public class JdkFutureAdapters  {
//public class ListenableFutureTask<V> extends FutureTask<V> implements ListenableFuture<V> {



    public static <V> ListenableFuture<V> listenInPoolThread(Future<V> future) {
        if (future instanceof ListenableFuture) {
            return (ListenableFuture<V>) future;
        }
        return new JdkFutureAdapters.ListenableFutureAdapter<V>(future);
    }

    private static class ListenableFutureAdapter<V> implements ListenableFuture<V> {

//        private static final ThreadFactory threadFactory =
//                new ThreadFactoryBuilder()
//                        .setDaemon(true)
//                        .setNameFormat("ListenableFutureAdapter-thread-%d")
//                        .build();
//        private static final Executor defaultAdapterExecutor = Executors.newCachedThreadPool(threadFactory);
        private static final Executor defaultAdapterExecutor = Executors.newCachedThreadPool();

        private final Executor adapterExecutor;

        private final ExecutionList executionList = new ExecutionList();

        // The delegate future.
        private final Future<V> delegate;

        // This allows us to only start up a thread waiting on the delegate future when the first
        // listener is added.
        private final AtomicBoolean hasListeners = new AtomicBoolean(false);

        ListenableFutureAdapter(Future<V> delegate) {
            this(delegate, defaultAdapterExecutor);
        }

        ListenableFutureAdapter(Future<V> delegate, Executor adapterExecutor) {
            this.delegate = Futures.checkNotNull(delegate);
            this.adapterExecutor = Futures.checkNotNull(adapterExecutor);
        }

        @Override
        public void addListener(Runnable listener, Executor executor) {
            executionList.add(listener, executor);


            if (hasListeners.compareAndSet(false, true)) {
                if (delegate.isDone()) {
                    // If the delegate is already done, run the execution list immediately on the current
                    // thread.
                    executionList.execute();
                    return;
                }

                adapterExecutor.execute(
                        new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    /*
                                     * Threads from our private pool are never interrupted. Threads from a
                                     * user-supplied executor might be, but... what can we do? This is another reason
                                     * to return a proper ListenableFuture instead of using listenInPoolThread.
                                     */
                                    getUninterruptibly(delegate); // 阻塞等待返回
                                } catch (Throwable e) {
                                    // ExecutionException / CancellationException / RuntimeException / Error
                                    // The task is presumably done, run the listeners.
                                }
                                executionList.execute();
                            }
                        });
            }
        }

        @Override
        public boolean cancel(boolean mayInterruptIfRunning) {
            return this.delegate().cancel(mayInterruptIfRunning);
        }

        @Override
        public boolean isCancelled() {
            return delegate.isCancelled();
        }

        @Override
        public boolean isDone() {
            return delegate().isDone();
        }

        @Override
        public V get() throws InterruptedException, ExecutionException {
            return delegate().get();
        }

        @Override
        public V get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
            return delegate().get(timeout, unit);
        }

        protected Future<V> delegate() {
            return delegate;
        }
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

}
