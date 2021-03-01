package com.kq.concurrent.threadlocal.demo;

/**
 * @author kq
 * @date 2020-07-30 11:25
 * @since 2020-0630
 */
public class LoginService {

    private Integer g50;

    private boolean isWeight;

    public Integer getG50() {
        return g50;
    }

    public void setG50(Integer g50) {
        this.g50 = g50;
    }

    public boolean isWeight() {
        return isWeight;
    }

    public void setWeight(boolean weight) {
        isWeight = weight;
    }

    public void login(Long userId) {

        ThreadLocalUtil.set(userId);

    }


}
