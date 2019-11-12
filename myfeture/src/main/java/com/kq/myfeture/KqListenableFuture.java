package com.kq.myfeture;

import java.util.concurrent.Executor;
import java.util.concurrent.Future;

/**
 * KqListenableFuture
 *
 * @author kq
 * @date 2019-11-12
 */
public interface KqListenableFuture <V> extends Future<V>  {

    /**
     * 添加监听
     * @param listener
     * @param executor
     */
    void addListener(Runnable listener, Executor executor);

}
