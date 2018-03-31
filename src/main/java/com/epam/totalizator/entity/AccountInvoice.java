package com.epam.totalizator.entity;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * Invoices table in database
 *@author Denis Byhovsky
 */

public class AccountInvoice implements java.io.Serializable {

    private int id;
    private String currency;
    private BigDecimal ballance;

    public AccountInvoice(){ }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public BigDecimal getBallance() {
        return ballance;
    }

    public void setBallance(BigDecimal ballance) {
        this.ballance = ballance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountInvoice that = (AccountInvoice) o;
        return id == that.id &&
                Objects.equals(currency, that.currency) &&
                Objects.equals(ballance, that.ballance);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, currency, ballance);
    }

    @Override
    public String toString() {
        return "AccountInvoice{" +
                "id=" + id +
                ", currency='" + currency + '\'' +
                ", ballance=" + ballance +
                '}';
    }
}
