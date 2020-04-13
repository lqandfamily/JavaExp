package com.lq.exp3.db;

import java.io.IOException;

/**
 * 数据库语句执行对象的接口
 * CRUD方法
 */
public interface IStatement<E> {
    /**
     * 查询所有
     */
    IResult<E> executeSelAll();

    /**
     * 根据条件选择一个或多个
     *
     * @param where 条件筛选回调
     */

    IResult<E> executeSel( IWhereCallback where);

    /**
     * 新增
     *
     * @param obj 　do对象
     */
    IResult<E> executeAdd(E obj);

    /**
     * 根据条件更新一个或多个
     *
     * @param where 条件筛选回调
     */
    IResult<E> executeUpd(IWhereCallback where);

    /**
     * 根据条件删除一个或多个
     *
     * @param where 条件筛选回调
     */
    IResult<E> executeDel(IWhereCallback where);


}
