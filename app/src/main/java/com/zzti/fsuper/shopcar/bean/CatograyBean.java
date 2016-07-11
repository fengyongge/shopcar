package com.zzti.fsuper.shopcar.bean;

import java.util.List;

/**
 * Created by fengyongge on 2016/7/6 0006.
 */
public class CatograyBean {
    private String kind;
    private List<GoodsBean> list;
    private int count;
    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public List<GoodsBean> getList() {
        return list;
    }

    public void setList(List<GoodsBean> list) {
        this.list = list;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "CatograyBean{" +
                "kind='" + kind + '\'' +
                ", list=" + list +
                ", count=" + count +
                '}';
    }
}
