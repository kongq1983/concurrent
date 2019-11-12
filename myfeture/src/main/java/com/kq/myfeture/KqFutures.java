package com.kq.myfeture;

import java.util.concurrent.Executor;

/**
 * KqFutures
 *
 * @author kq
 * @date 2019-11-12
 */
public class KqFutures {


    public static <V> void addCallback(
            final KqListenableFuture<V> future,
            final KqFutureCallback<? super V> callback,
            Executor executor) {

        future.addListener(new KqCallbackListener<V>(future, callback), executor);
    }


}
