package com.epam.totalizator.entity;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.util.Objects;

/**
 * Bets table in database
 *@author Denis Byhovsky
 */

public class Bet implements java.io.Serializable {

private int id;
    private String accountLogin;
    private Event event;
    private BigDecimal coefficient;
    private BigDecimal amountCount;
    private BigDecimal expectedWin;
    private String typeBet;
    private String dateOfMatch;
    private Date betDate;
    private  Time betTime;
    private String currency;
    private int result;

    public Bet(){ }

    public Time getBetTime() {
        return betTime;
    }

    public void setBetTime(Time betTime) {
        this.betTime = betTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAccountLogin() {
        return accountLogin;
    }

    public void setAccountLogin(String accountLogin) {
        this.accountLogin = accountLogin;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public BigDecimal getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(BigDecimal coefficient) {
        this.coefficient = coefficient;
    }

    public BigDecimal getAmountCount() {
        return amountCount;
    }

    public void setAmountCount(BigDecimal amountCount) {
        this.amountCount = amountCount;
    }

    public BigDecimal getExpectedWin() {
        return expectedWin;
    }

    public void setExpectedWin(BigDecimal expectedWin) {
        this.expectedWin = expectedWin;
    }

    public String getTypeBet() {
        return typeBet;
    }

    public void setTypeBet(String typeBet) {
        this.typeBet = typeBet;
    }

    public String getDateOfMatch() {
        return dateOfMatch;
    }

    public void setDateOfMatch(String dateOfMatch) {
        this.dateOfMatch = dateOfMatch;
    }

    public Date getBetDate() {
        return betDate;
    }

    public void setBetDate(Date betDate) {
        this.betDate = betDate;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bet bet = (Bet) o;
        return id == bet.id &&
                result == bet.result &&
                Objects.equals(accountLogin, bet.accountLogin) &&
                Objects.equals(event, bet.event) &&
                Objects.equals(coefficient, bet.coefficient) &&
                Objects.equals(amountCount, bet.amountCount) &&
                Objects.equals(expectedWin, bet.expectedWin) &&
                Objects.equals(typeBet, bet.typeBet) &&
                Objects.equals(dateOfMatch, bet.dateOfMatch) &&
                Objects.equals(betDate, bet.betDate) &&
                Objects.equals(betTime, bet.betTime) &&
                Objects.equals(currency, bet.currency);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, accountLogin, event, coefficient, amountCount, expectedWin, typeBet, dateOfMatch, betDate, betTime, currency, result);
    }

    @Override
    public String toString() {
        return "Bet{" +
                "id=" + id +
                ", accountLogin='" + accountLogin + '\'' +
                ", event=" + event +
                ", coefficient=" + coefficient +
                ", amountCount=" + amountCount +
                ", expectedWin=" + expectedWin +
                ", typeBet='" + typeBet + '\'' +
                ", dateOfMatch='" + dateOfMatch + '\'' +
                ", betDate=" + betDate +
                ", currency='" + currency + '\'' +
                ", result=" + result +
                '}';
    }
}
