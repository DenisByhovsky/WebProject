package com.epam.totalizator.entity;

import java.util.Objects;

/**
 * Coefficients table in database
 *@author Denis Byhovsky
 */

public class Coefficient  implements java.io.Serializable{

    private Double winFirst;
    private Double winSecond;
    private Double nobody;
    private Double firstOrNobody;
    private Double secondOrNobody;
    private Double firstOrSecond;

    public Double getWinFirst() {
        return winFirst;
    }

    public void setWinFirst(Double winFirst) {
        if(winFirst>0){
            this.winFirst=winFirst;
        }else
        {
            this.winFirst=null;
        }
    }

    public Double getWinSecond() {
        return winSecond;
    }

    public void setWinSecond(Double winSecond) {
        if(winSecond>0){
            this.winSecond=winSecond;
        }else
        {
            this.winSecond=null;
        }
    }

    public Double getNobody() {
        return nobody;
    }

    public void setNobody(Double nobody) {
        if(nobody>0){
            this.nobody=nobody;
        }else
        {
            this.nobody=null;
        }
    }

    public Double getFirstOrNobody() {
        return firstOrNobody;
    }

    public void setFirstOrNobody(Double firstOrNobody) {
        if(firstOrNobody>0){
            this.firstOrNobody=winFirst;
        }else
        {
            this.firstOrNobody=null;
        }
    }

    public Double getSecondOrNobody() {
        return secondOrNobody;
    }

    public void setSecondOrNobody(Double secondOrNobody) {
        if(secondOrNobody>0){
            this.secondOrNobody=secondOrNobody;
        }else
        {
            this.secondOrNobody=null;
        }
    }

    public Double getFirstOrSecond() {
        return firstOrSecond;
    }

    public void setFirstOrSecond(Double firstOrSecond) {
        if(firstOrSecond>0){
            this.firstOrSecond=firstOrSecond;
        }else
        {
            this.firstOrSecond=null;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coefficient that = (Coefficient) o;
        return Objects.equals(winFirst, that.winFirst) &&
                Objects.equals(winSecond, that.winSecond) &&
                Objects.equals(nobody, that.nobody) &&
                Objects.equals(firstOrNobody, that.firstOrNobody) &&
                Objects.equals(secondOrNobody, that.secondOrNobody) &&
                Objects.equals(firstOrSecond, that.firstOrSecond);
    }

    @Override
    public int hashCode() {

        return Objects.hash(winFirst, winSecond, nobody, firstOrNobody, secondOrNobody, firstOrSecond);
    }

    @Override
    public String toString() {
        return "Coefficient{" +
                "winFirst=" + winFirst +
                ", winSecond=" + winSecond +
                ", nobody=" + nobody +
                ", firstOrNobody=" + firstOrNobody +
                ", secondOrNobody=" + secondOrNobody +
                ", firstOrSecond=" + firstOrSecond +
                '}';
    }
}
