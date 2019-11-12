package com.kq.myfeture;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * KqUninterruptibles
 *
 * @author kq
 * @date 2019-11-12
 */
public class KqUninterruptibles {

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
