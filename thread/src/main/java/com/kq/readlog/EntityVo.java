package com.kq.readlog;

/**
 * @author kq
 * @date 2020-12-15 18:24
 * @since 2020-0630
 */
public class EntityVo {

    private String className;
    private String methodName;
    private Integer time = 0;

    private String longName;

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    public String getLongName() {
        return longName;
    }

    public void setLongName(String longName) {
        this.longName = longName;
    }

    @Override
    public String toString() {
        return "EntityVo{" +
                "className='" + className + '\'' +
                ", methodName='" + methodName + '\'' +
                ", time=" + time +
                ", longName='" + longName + '\'' +
                '}';
    }
}
