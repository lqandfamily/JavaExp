package com.lq.exp3.db;

/**
 * 语句执行的条件回调
 */
public interface IWhereCallback <E>{
    boolean where(E item);
}
