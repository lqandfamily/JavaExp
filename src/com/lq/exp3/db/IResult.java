package com.lq.exp3.db;

/**
 * 执行结果接口
 */
public interface IResult<E> {
    /**
     * 游标下移
     * @return 返回当前游标对应的数据模型对象,到尽头返回null
     */
    E next();

    /**
     * 添加元素到IResult
     * 该方法仅为内部使用，不应该暴露给用户
     */
    void add(E obj);
}
