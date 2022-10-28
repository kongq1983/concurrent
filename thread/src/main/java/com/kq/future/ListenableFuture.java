package com.kq.future;

import java.util.concurrent.Executor;
import java.util.concurrent.Future;

/**
 * @author kq
 * @date 2022-10-28 9:26
 * @since 2020-0630
 */
public interface ListenableFuture<V> extends Future<V> {

    void addListener(Runnable listener, Executor executor);

}
