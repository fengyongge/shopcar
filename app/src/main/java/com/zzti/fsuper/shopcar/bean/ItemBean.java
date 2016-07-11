package com.zzti.fsuper.shopcar.bean;

/**
 * Created by fengyongge on 2016/7/6 0006.
 */
public class ItemBean {

    public String key; // required
    public String value; // required
    public String note1; // required
    public String note2; // required

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getNote1() {
        return note1;
    }

    public void setNote1(String note1) {
        this.note1 = note1;
    }

    public String getNote2() {
        return note2;
    }

    public void setNote2(String note2) {
        this.note2 = note2;
    }
}
