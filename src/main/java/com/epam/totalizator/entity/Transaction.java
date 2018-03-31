package com.epam.totalizator.entity;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.util.Objects;

/**
 * Transactions table in database
 *  @author Denis Byhovsky
 */

public class Transaction  {
    private Date date;
    private Time time;
    private String depositType;
    private BigDecimal amount;
    private TransactionType transactionType;

    public Transaction() { }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDepositType() {
        return depositType;
    }

    public void setDepositType(String depositType) {
        this.depositType = depositType;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public String getTransactionType() {
        return transactionType.getValue();
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = TransactionType.valueOf(transactionType.toUpperCase());
    }

    public enum TransactionType {
        DEPOSIT("Deposit"),
        DRAWBACK("Drawback");

        private String value;

        TransactionType(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return Objects.equals(date, that.date) &&
                Objects.equals(time, that.time) &&
                Objects.equals(depositType, that.depositType) &&
                Objects.equals(amount, that.amount) &&
                transactionType == that.transactionType;
    }

    @Override
    public int hashCode() {

        return Objects.hash(date, time, depositType, amount, transactionType);
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "date=" + date +
                ", time=" + time +
                ", depositType='" + depositType + '\'' +
                ", amount=" + amount +
                ", transactionType=" + transactionType +
                '}';
    }
}
