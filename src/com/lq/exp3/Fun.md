# 货物进销管理系统分析
## 架构

- CRUD
- DAO
- DO
- LOG

## 实现

### CRUD

> CRUD是系统的核心业务，实现

- Service接口多个

  ```
  getAll()
  add()
  del()
  ```

- Service实现



### DAO

> 数据访问层，提供访问数据库的能力，这里以文本文件存储数据

- DAO接口，泛型

  ```
  -- db
  	connect(path,E)
  
  --con
  	sel(E)
  	add(E)
  	upd(E)
  	del(E)
  ```

- TextDao实现类，数据成员.toString()



### DO

> 数据模型

```
Inventory(old/new)
Shipping

```





### LOG

> 日志记录