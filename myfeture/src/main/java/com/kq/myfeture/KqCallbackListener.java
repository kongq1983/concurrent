package com.kq.myfeture;

import com.google.common.base.MoreObjects;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * KqCallbackListener
 *
 * @author kq
 * @date 2019-11-12
 */
public class KqCallbackListener<V> implements Runnable  {

    final Future<V> future;
    final KqFutureCallback<? super V> callback;

    public KqCallbackListener(Future<V> future, KqFutureCallback<? super V> callback) {
        this.future = future;
        this.callback = callback;
    }

    @Override
    public void run() {
        final V value;
        try {
            value = getDone(future);
        } catch (ExecutionException e) {
            callback.onFailure(e.getCause());
            return;
        } catch (RuntimeException | Error e) {
            callback.onFailure(e);
            return;
        }
        callback.onSuccess(value);
    }


    public <V> V getDone(Future<V> future) throws ExecutionException {

        System.out.println("future.isDone="+future.isDone());

        return getUninterruptibly(future);
    }

    public <V> V getUninterruptibly(Future<V> future) throws ExecutionException {
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

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this).addValue(callback).toString();
    }
}
