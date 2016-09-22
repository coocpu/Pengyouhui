package com.enrich.pengyouhui.bean;

/**
 * @describe 亲情账户测试实体
 */
public class TestAccountBean {
    /**
     * 交易类型
     * 交易类型 (1 为 存入 2为支出)
     */
    private String transactionType;
    private String transactionAmount;
    private String transactionTime;
    private String depositType;

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public String getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(String transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public String getTransactionTime() {
        return transactionTime;
    }

    public void setTransactionTime(String transactionTime) {
        this.transactionTime = transactionTime;
    }

    public String getDepositType() {
        return depositType;
    }

    public void setDepositType(String depositType) {
        this.depositType = depositType;
    }
}
