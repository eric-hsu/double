/*交易项目*/
drop table if exists t_trade_record;
create table t_trade_record(
    id int primary key auto_increment,
    customer_no varchar(50) NOT NULL COMMENT '客户账号',
    customer_totalamount INT  NOT NULL COMMENT '交易金额，以分为单位',
    customer_currency varchar(10)  NOT NULL COMMENT '交易币种，RMB表示人民币',
    customer_orderno varchar(50) NOT NULL COMMENT '订单号',
    customer_asynurl varchar(100) COMMENT '异步地址',
    
    channel varchar(20)  NOT NULL COMMENT '交易渠道，如cmbc表示民生，pab表示平安',
    out_trade_no varchar(50)  NOT NULL COMMENT '系统流水号',
    status tinyint(1)  NOT NULL COMMENT '状态,0失败、1成功、2处理中',
    
    create_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    creator varchar(50) COMMENT '创建人',
    remark varchar(100) COMMENT '备注'
);

/*交易子项*/
drop table if exists t_trade_record_item;
create table t_trade_record_item(
    id int primary key auto_increment,
    record_id varchar(50)  NOT NULL COMMENT '订单记录id',
    item_type varchar(50)  NOT NULL COMMENT '交易类型，0 本行代付，1 跨行代付，2 本行代扣，3 跨行代扣',
    item_status tinyint(1)  NOT NULL COMMENT '状态,0失败、1成功、2处理中',
    item_orderno varchar(50) NOT NULL COMMENT '订单号',
    item_out_trade_no varchar(50)  NOT NULL COMMENT '流水号',
    item_amount INT   NOT NULL COMMENT '交易金额，以分为单位',
    item_currency varchar(10)  NOT NULL COMMENT '交易币种，RMB表示人民币',
    
    pay_bank_code varchar(50) COMMENT '支付行',
    pay_account varchar(50) COMMENT '支付账号',
    pay_account_name varchar(50) COMMENT '支付户名',
    
    receive_bank_code varchar(50) COMMENT '收款行',
    receive_account varchar(50) COMMENT '收款账号',
    receive_account_name varchar(50) COMMENT '收款户名',
   
    channel_status tinyint(1) COMMENT '状态',
    channel_amount INT COMMENT '银行交易金额',
    channel_currency varchar(10) COMMENT '银行交易币种',
    channel_trade_no varchar(50) COMMENT '银行交易流水',
    channel_trade_date datetime COMMENT '银行交易时间',
    channel_description varchar(100) COMMENT '银行相关交易描述',

    create_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    creator varchar(50) COMMENT '创建人',
    remark varchar(100) COMMENT '备注'
)
