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
    IResult executeSelAll(Class<E> eClass) throws IOException;

    /**
     * 根据条件选择一个或多个
     *
     * @param where 条件筛选回调
     */

    IResult<E> executeSel(Class<E> eClass, IWhereCallback where) throws IOException;

    /**
     * 新增
     *
     * @param obj 　do对象
     */
    IResult executeAdd(E obj);

    /**
     * 根据条件更新一个或多个
     *
     * @param where 条件筛选回调
     */
    IResult executeUpd(IWhereCallback where);

    /**
     * 根据条件删除一个或多个
     *
     * @param where 条件筛选回调
     */
    IResult executeDel(IWhereCallback where);


}
