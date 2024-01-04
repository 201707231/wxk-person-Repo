package com.entity;

public class Borrow {
    public String bid;
    public String rid;
    public Integer mid;
    public String borrowtime;
    public String returntime;
    public Integer renewtimes;
    public String state;
    public String practicaltime;
    public Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPracticaltime() {
        return practicaltime;
    }

    public void setPracticaltime(String practicaltime) {
        this.practicaltime = practicaltime;
    }



    public String getBid() {
        return bid;
    }

    public void setBid(String bid) {
        this.bid = bid;
    }

    public String getRid() {
        return rid;
    }

    public void setRid(String rid) {
        this.rid = rid;
    }

    public Integer getMid() {
        return mid;
    }

    public void setMid(Integer mid) {
        this.mid = mid;
    }

    public String getBorrowtime() {
        return borrowtime;
    }

    public void setBorrowtime(String borrowtime) {
        this.borrowtime = borrowtime;
    }


    public String getReturntime() {
        return returntime;
    }

    public void setReturntime(String returntime) {
        this.returntime = returntime;
    }

    public Integer getRenewtimes() {
        return renewtimes;
    }

    public void setRenewtimes(Integer renewtimes) {
        this.renewtimes = renewtimes;
    }
}
