package com.kq.myfeture;

/**
 * KqFutureCallback
 *
 * @author1 kq
 * @date 2019-11-12
 */
public interface KqFutureCallback<V> {

    /**
     * 成功回调
     * @param result
     */
    void onSuccess(V result);

    /**
     * 失败回调
     * @param t
     */
    void onFailure(Throwable t);
}
