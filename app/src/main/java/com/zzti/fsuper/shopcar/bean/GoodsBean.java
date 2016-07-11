package com.zzti.fsuper.shopcar.bean;

/**
 * Created by fengyongge on 2016/7/6 0006.
 */
public class GoodsBean {

    public int product_id; // required
    public int category_id; // required
    public String title; // required
    public int is_package_status; // required
    public String icon; // required
    public String original_price; // required
    public String price; // required
    public String specification; // required
    public int num; // required
//    public List<ItemBean> package_product_info; // required
    public int cart_num; // required

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getIs_package_status() {
        return is_package_status;
    }

    public void setIs_package_status(int is_package_status) {
        this.is_package_status = is_package_status;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getOriginal_price() {
        return original_price;
    }

    public void setOriginal_price(String original_price) {
        this.original_price = original_price;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }


    public int getCart_num() {
        return cart_num;
    }

    public void setCart_num(int cart_num) {
        this.cart_num = cart_num;
    }

    @Override
    public String toString() {
        return "GoodsBean{" +
                "product_id=" + product_id +
                ", category_id=" + category_id +
                ", title='" + title + '\'' +
                ", is_package_status=" + is_package_status +
                ", icon='" + icon + '\'' +
                ", original_price='" + original_price + '\'' +
                ", price='" + price + '\'' +
                ", specification='" + specification + '\'' +
                ", num=" + num +
                ", cart_num=" + cart_num +
                '}';
    }
}
