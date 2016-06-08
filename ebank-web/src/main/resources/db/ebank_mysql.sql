/*t_customer*/
drop table if exists t_customer;
create table t_customer(
    id int primary key auto_increment,
    customer_no varchar(50) COMMENT '客户账号',
    customer_name varchar(50) COMMENT '客户名称',
    status tinyint(1) not null COMMENT '状态',
    create_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    creator varchar(50) COMMENT '创建人',
    remark varchar(100) COMMENT '备注'
);

/*t_customer_certificate*/
drop table if exists t_customer_certificate;
create table t_customer_certificate(
    id int primary key auto_increment,
    customer_no varchar(50) not null COMMENT '客户账号',
    public_key varchar(2000) not null COMMENT '公钥',
    status tinyint(1) not null COMMENT '状态',
    create_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    creator varchar(50) COMMENT '创建人',
    remark varchar(100) COMMENT '备注'
);