package com.epam.totalizator.entity;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Accounts table in database
 *@author Denis Byhovsky
 */

public class Account implements java.io.Serializable{

    private String login;
    private String password;
    private String email;
    private String firstName;
    private String  lastName;
    private AccountRole role;
    private Date createDate;
    private Date birthday;
    private AccountInvoice invoice;
    private String phone;
    private String documentNumb;
    private String documentType;
    private ArrayList<Bet> bets;


    public Account() { }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDocumentNumb() {
        return documentNumb;
    }

    public void setDocumentNumb(String documentNumb) {
        this.documentNumb = documentNumb;
    }

    public String getDocumentType() {
        return documentType;
    }

    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }

    public Date getCreatingDate() {
        return createDate;
    }

    public void setCreatingDate(Date creatingDate) {
        this.createDate = creatingDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public AccountInvoice getInvoice() {
        return invoice;
    }

    public void setInvoice(AccountInvoice invoice) {
        this.invoice = invoice;
    }

    public ArrayList<Bet> getBets() {
        return bets;
    }

    public void setBets(ArrayList<Bet> bets) {
        this.bets = bets;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public void addBet(Bet bet){
        bets.add(0,bet);
    }

    public AccountRole getRole() {
        return role;
    }

    public void setRole(AccountRole role) {
        this.role = role;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Objects.equals(login, account.login) &&
                Objects.equals(password, account.password) &&
                Objects.equals(email, account.email) &&
                Objects.equals(firstName, account.firstName) &&
                Objects.equals(lastName, account.lastName) &&
                role == account.role &&
                Objects.equals(createDate, account.createDate) &&
                Objects.equals(birthday, account.birthday) &&
                Objects.equals(invoice, account.invoice) &&
                Objects.equals(phone, account.phone) &&
                Objects.equals(documentNumb, account.documentNumb) &&
                Objects.equals(documentType, account.documentType) &&
                Objects.equals(bets, account.bets);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login, password, email, firstName, lastName, role, createDate, birthday, invoice, phone, documentNumb, documentType, bets);
    }

    @Override
    public String toString() {
        return "Account{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", role=" + role +
                ", createDate=" + createDate +
                ", birthday=" + birthday +
                ", invoice=" + invoice +
                ", phone='" + phone + '\'' +
                ", documentNumb='" + documentNumb + '\'' +
                ", documentType='" + documentType + '\'' +
                ", bets=" + bets +
                '}';
    }


}
