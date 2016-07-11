package com.zzti.fsuper.shopcar.adapter;

import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.zzti.fsuper.shopcar.MainActivity;
import com.zzti.fsuper.shopcar.R;
import com.zzti.fsuper.shopcar.bean.GoodsBean;
import com.zzti.fsuper.shopcar.util.StringUtils;



/**
 * Created by fengyongge on 2016/5/24 0024.
 */

/***
 * 底部购物车
 */
public class ProductAdapter extends BaseAdapter {
    GoodsAdapter goodsAdapter;
    private MainActivity activity;
    private SparseArray<GoodsBean> dataList;
    public ProductAdapter(MainActivity activity, GoodsAdapter goodsAdapter, SparseArray<GoodsBean> dataList) {
        this.goodsAdapter =goodsAdapter;
        this.activity = activity;
        this.dataList = dataList;
    }

    @Override
    public int getCount() {
        return dataList.size();
    }

    @Override
    public Object getItem(int position) {
        return dataList.valueAt(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = convertView;
        final Viewholder viewholder;
        if (view == null) {
            view = LayoutInflater.from(activity).inflate(R.layout.product_item, null);
            viewholder = new Viewholder();
            viewholder.tv_name = (TextView) view.findViewById(R.id.tv_name);
            viewholder.tv_price = (TextView) view.findViewById(R.id.tv_price);
            viewholder.iv_add= (ImageView) view.findViewById(R.id.iv_add);
            viewholder.iv_remove= (ImageView) view.findViewById(R.id.iv_remove);
            viewholder.tv_count= (TextView) view.findViewById(R.id.tv_count);

            view.setTag(viewholder);
        } else {
            viewholder = (Viewholder) view.getTag();
        }


            StringUtils.filtNull(viewholder.tv_name,dataList.valueAt(position).getTitle());//商品名称
            StringUtils.filtNull(viewholder.tv_price,"￥"+dataList.valueAt(position).getPrice());//商品价格
            viewholder.tv_count.setText(String.valueOf(dataList.valueAt(position).getNum()));//商品数量

            viewholder.iv_add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    activity.handlerCarNum(1,dataList.valueAt(position),true);
                    goodsAdapter.notifyDataSetChanged();

                }
            });
            viewholder.iv_remove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    activity.handlerCarNum(0,dataList.valueAt(position),true);
                    goodsAdapter.notifyDataSetChanged();
                }
            });

        return view;
    }

    class Viewholder {
        TextView tv_price;
        TextView tv_name;
        ImageView iv_add,iv_remove;
        TextView tv_count;
    }

}