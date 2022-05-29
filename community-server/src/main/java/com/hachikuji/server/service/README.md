## Spring 事务隔离机制

###  一致性的理解

- 数据库届的能量守恒定律

###  @Transactional 注解

~~~
@Service
Class A {
    @Autowired
    B b;
    @Transactional(propagation = Propagation.XXX)
    public void aMethod {
        //do something
        b.bMethod();
    }
}
@Service
Class B {
    @Transactional(propagation = Propagation.XXX)
    public void bMethod {
       //do something
    }
}
~~~

- Propagation.REQUIRED 开启事务，aMethod 与 bMethod 且属于同个事务，bMethod 回滚 aMethod 也回滚。
- Propagation.REQUIRED_NEW 开启新事务，属于不同个事务，bMethod 回滚 aMethod 会捕捉到也回滚，但 aMethod 回滚 bMethod 不回滚。
  

- Isolation.DEFAULT MySQL 默认采用的 Isolation.REPEATABLE_READ
- Isolation.READ_UNCOMMITTED 最低的隔离级别，可能会导致脏读、幻读或不可重复读
- Isolation.READ_COMMITTED
- Isolation.READ_UNCOMMITTED
- Isolation.REPEATABLE_READ
- Isolation.SERIALIZABLE  最高的隔离级别
