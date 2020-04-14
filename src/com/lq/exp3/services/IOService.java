package com.lq.exp3.services;

import com.lq.exp3.entity.Inventory;

/**
 * 进出货服务
 */
public interface IOService {
    //操作成功
    int SUCCESS = 1;

    //货物不足
    int LACK = -1;

    //货物编号不存在
    int ITEM_ID_NOT_EXIST = -2;

    //客户编号不存在
    int CUSTOM_ID_NOT_EXIST = -3;

    /**
     * 发货给客户
     * 以'O'开头的事务表示这是一个发货订单，即某一种货物应该发给特定的客户。Item number和Quantity的格式如上面表格定义。Custom编号和上面的
     * Supplier编号一致。处理一条定单记录（以'O'开头的事务）意味着从减少库存记录中相应货物的数量（减少的数量=发货单中的数量），记录发货信息到
     * Shipping.txt中。注意：Inventory.txt中的quantity不应该小于0，如果对于某一种货物，库存的数量小于发货单的数量的话，系统应该停止处理发
     * 货单，并记录出错信息到Errors.txt。如果对于某一种货物有多个发货单，而且库存总量小于这些发货单的总和的话，系统应该按照发货单中的数量从小到
     * 大的有限原则满足客户。也就是说，对于某一种货物如果一个数量Quantity少的发货单没有处理之前，数量Quantity多的发货单永远不会被处理。（这种
     * 处理原则不受发货单记录在Transactions.txt的先后顺序影响）
     *
     * @param itemId   　货物编号
     * @param customId 　客户编号
     * @return 操作结果码
     */
    int outCargo(String itemId, String customId);

    /**
     * 货物入仓
     * 以'R'开头的事务表示这是一个到货单记录，在'R'后面是Item number和它的数量Quanlity。处理一条到货单意味着增加库存中相应货物的数量（增加的
     * 数量=到货单中的数量）。注意：如果在Transactions.txt文件中，到货单出现在发货单之后，到货单中的货物数量可以用来填补发货单中的数量（可以
     * 理解成在Transactions.txt中，优先处理到货单）。
     *
     * @param itemId 　货物编号
     * @return 操作结果码
     */
    int inCargo(String itemId);


    /**
     * 新增一种类型的货物到仓库
     * 'A'开头的事务表示向库存中增加一种新的货物（即这种货物以前库存中没有），在'A'后面是Item number，供应商supplier以及货物的描述descript
     * ion。处理一个新增货物记录意味着向库存中增加一个数量Quantity为0的新的Item。你可以假设在一个Transactions.txt中，新增货物记录总是出现
     * 在第一个到货单之前
     *
     * @param inventory 　货物模型，不包含数量
     * @return 操作结果码
     */
    int newCargo(Inventory inventory);

    /**
     * 删除货物
     * 以'D'开头的事务表示从库存中删除一种货物，在'D'后面是Item号。删除操作总是在所有的事物处理之后才被处理，以保证对于可能出现的同一种货物的发
     * 货单的操作能在删除之前被正确处理。如果要删除的某种货物的库存量Quantity不为0的话，系统应该向Errors.txt记录出错信息。
     *
     * @param itemId 货物编号
     * @return 操作结果码
     */
    int delCargo(String itemId);

}
