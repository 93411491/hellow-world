package com.example.myrxjava.mapOperator;

/**
 * author : wangzhirui
 * date : 2021/10/14
 * description : 用来实现map的类型转换功能
 */
public interface Function<T,R> {
    R apply(T t);
}
