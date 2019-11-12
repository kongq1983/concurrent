package com.kq.myfeture;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * KqListenableFutureAdapter
 *
 * @author kq
 * @date 2019-11-12
 */
public class KqListenableFutureAdapter<V> implements KqListenableFuture<V> {

    // The delegate future.
    private final Future<V> delegate;
    private final Executor adapterExecutor;

    private final AtomicBoolean hasListeners = new AtomicBoolean(false);

    public KqListenableFutureAdapter(Future<V> delegate, Executor adapterExecutor){
        this.delegate = delegate;
        this.adapterExecutor = adapterExecutor;
    }



    @Override
    public void addListener(Runnable listener, Executor executor) {

    }

    @Override
    public boolean cancel(boolean mayInterruptIfRunning) {
        return false;
    }

    @Override
    public boolean isCancelled() {
        return false;
    }

    @Override
    public boolean isDone() {
        return false;
    }

    @Override
    public V get() throws InterruptedException, ExecutionException {
        return null;
    }

    @Override
    public V get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
        return null;
    }
}
