package com.zzti.fsuper.shopcar.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.zzti.fsuper.shopcar.R;
import com.zzti.fsuper.shopcar.bean.CatograyBean;
import java.util.List;

/**
 * Created by fengyongge on 2016/5/24 0024.
 */

public class CatograyAdapter extends BaseAdapter {
    private Context context;
    private List<CatograyBean> list;
    int selection = 0;
    public CatograyAdapter(Context context, List<CatograyBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        final Viewholder viewholder;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.shopcart_left_listview, null);
            viewholder = new Viewholder();
            viewholder.tv_catogray = (TextView) view.findViewById(R.id.tv_catogray);
            viewholder.tv_count = (TextView) view.findViewById(R.id.tv_count);
            view.setTag(viewholder);
        } else {
            viewholder = (Viewholder) view.getTag();
        }

        viewholder.tv_catogray.setText(list.get(position).getKind());
        int count = 0;
        for (int i = 0; i < list.get(position).getList().size(); i++) {
            count += list.get(position).getList().get(i).getNum();
        }
        list.get(position).setCount(count);

        if (count <= 0) {
            viewholder.tv_count.setVisibility(View.INVISIBLE);
        } else {

            viewholder.tv_count.setVisibility(View.VISIBLE);
            viewholder.tv_count.setText(list.get(position).getCount()+"");
        }

        if (position == selection) {
            viewholder.tv_catogray.setBackgroundResource(R.drawable.rec_red_left_stroke);
            viewholder.tv_catogray.setTextColor(context.getResources().getColor(R.color.black));
        } else {

            viewholder.tv_catogray.setBackgroundResource(R.drawable.empty);
            viewholder.tv_catogray.setTextColor(context.getResources().getColor(R.color.gray));
        }
        return view;
    }

    public void setSelection(int selection) {
        this.selection = selection;
    }

    class Viewholder {
        TextView tv_catogray;
        TextView tv_count;
    }

}
