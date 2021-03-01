package com.kq.concurrent.threadlocal.demo;

import java.util.Date;

/**
 * @author kq
 * @date 2020-07-30 11:17
 * @since 2020-0630
 */
public class LoginDto {

    private Long userId;
    private Date loginTime;


    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    @Override
    public String toString() {
        return "LoginDto{" +
                "userId=" + userId +
                ", loginTime=" + loginTime +
                '}';
    }
}
