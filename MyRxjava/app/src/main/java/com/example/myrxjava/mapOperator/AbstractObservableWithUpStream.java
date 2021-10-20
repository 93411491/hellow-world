package com.example.myrxjava.mapOperator;

import com.example.myrxjava.core.Observable;
import com.example.myrxjava.core.ObservableSource;

/**
 * author : wangzhirui
 * date : 2021/10/14
 * description :
 *      1. 对应装饰器模式抽象装饰类,因为是用该装饰类实现map方法，存在类型转换，需要使用两个两个泛型
 *      2. 之后所有的拓展类均继承本类即可
 */
public abstract class AbstractObservableWithUpStream<T,U> extends Observable<U> {

    //装饰器模式中，抽象装饰类持有了顶层接口的引用,在此基础上进行拓展即可
    protected final ObservableSource<T> source;

    protected AbstractObservableWithUpStream(ObservableSource<T> source) {
        this.source = source;
    }
}
