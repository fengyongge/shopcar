package com.zzti.fsuper.shopcar;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.flipboard.bottomsheet.BottomSheetLayout;
import com.zzti.fsuper.shopcar.adapter.CatograyAdapter;
import com.zzti.fsuper.shopcar.adapter.GoodsAdapter;
import com.zzti.fsuper.shopcar.adapter.GoodsDetailAdapter;
import com.zzti.fsuper.shopcar.adapter.ProductAdapter;
import com.zzti.fsuper.shopcar.bean.CatograyBean;
import com.zzti.fsuper.shopcar.bean.GoodsBean;
import com.zzti.fsuper.shopcar.bean.ItemBean;
import com.zzti.fsuper.shopcar.view.MyListView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by fengyongge on 2016/5/19 0019.
 */
public class MainActivity extends Activity {
    //控件
    private ListView lv_catogary, lv_good;
    private ImageView iv_logo;
    private TextView tv_car;
    private  TextView tv_count,tv_totle_money;
    Double totleMoney = 0.00;
    private TextView bv_unm;
    private RelativeLayout rl_bottom;
    //分类和商品
    private List<CatograyBean> list = new ArrayList<CatograyBean>();
    private List<GoodsBean> list2 = new ArrayList<GoodsBean>();
    private MyApp myApp;
    private CatograyAdapter catograyAdapter;//分类的adapter
    private GoodsAdapter goodsAdapter;//分类下商品adapter
    ProductAdapter productAdapter;//底部购物车的adapter
    GoodsDetailAdapter goodsDetailAdapter;//套餐详情的adapter
    private static DecimalFormat df;
    private LinearLayout ll_shopcar;
    //底部数据
    private BottomSheetLayout bottomSheetLayout;
    private View bottomSheet;
    private SparseArray<GoodsBean> selectedList;
    //套餐
    private View bottomDetailSheet;
    private List<GoodsBean> list3 = new ArrayList<GoodsBean>();
    private List<GoodsBean> list4 = new ArrayList<GoodsBean>();
    private List<GoodsBean> list5 = new ArrayList<GoodsBean>();

    private Handler mHanlder;
    private ViewGroup anim_mask_layout;//动画层


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myApp = (MyApp) getApplicationContext();
        mHanlder = new Handler(getMainLooper());
        initView();
        initData();
        addListener();
        ll_shopcar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showBottomSheet();
            }
        });
    }

    public void initView() {
        lv_catogary = (ListView) findViewById(R.id.lv_catogary);
        lv_good = (ListView) findViewById(R.id.lv_good);
        tv_car = (TextView) findViewById(R.id.tv_car);
        //底部控件
        rl_bottom = (RelativeLayout) findViewById(R.id.rl_bottom);
        tv_count = (TextView) findViewById(R.id.tv_count);
        bv_unm = (TextView) findViewById(R.id.bv_unm);
        tv_totle_money= (TextView) findViewById(R.id.tv_totle_money);
        ll_shopcar= (LinearLayout) findViewById(R.id.ll_shopcar);
        selectedList = new SparseArray<>();
        df = new DecimalFormat("0.00");
    }

    //填充数据
    private void initData() {
        //商品
        for (int j=30;j<45;j++){
            GoodsBean goodsBean = new GoodsBean();
            goodsBean.setTitle("胡辣汤"+j);
            goodsBean.setProduct_id(j);
            goodsBean.setCategory_id(j);
            goodsBean.setIcon("http://c.hiphotos.baidu.com/image/h%3D200/sign=5992ce78530fd9f9bf175269152cd42b/4ec2d5628535e5dd557b44db74c6a7efce1b625b.jpg");
            goodsBean.setOriginal_price("200");
            goodsBean.setPrice("100");
            list3.add(goodsBean);
        }

        //商品
        for (int j=5;j<10;j++){
            GoodsBean goodsBean = new GoodsBean();
            goodsBean.setTitle("胡辣汤"+j);
            goodsBean.setProduct_id(j);
            goodsBean.setCategory_id(j);
            goodsBean.setIcon("http://e.hiphotos.baidu.com/image/h%3D200/sign=c898bddf19950a7b6a3549c43ad0625c/14ce36d3d539b600be63e95eed50352ac75cb7ae.jpg");
            goodsBean.setOriginal_price("80");
            goodsBean.setPrice("60");
            list4.add(goodsBean);
        }

        //商品
        for (int j=10;j<15;j++){
            GoodsBean goodsBean = new GoodsBean();
            goodsBean.setTitle("胡辣汤"+j);
            goodsBean.setProduct_id(j);
            goodsBean.setCategory_id(j);
            goodsBean.setIcon("http://g.hiphotos.baidu.com/image/pic/item/03087bf40ad162d9ec74553b14dfa9ec8a13cd7a.jpg");
            goodsBean.setOriginal_price("40");
            goodsBean.setPrice("20");
            list5.add(goodsBean);
        }


            CatograyBean catograyBean3 = new CatograyBean();
            catograyBean3.setCount(3);
            catograyBean3.setKind("江湖餐品"+3);
            catograyBean3.setList(list3);
            list.add(catograyBean3);

            CatograyBean catograyBean4 = new CatograyBean();
            catograyBean4.setCount(4);
            catograyBean4.setKind("江湖餐品"+4);
            catograyBean4.setList(list4);
            list.add(catograyBean4);

            CatograyBean catograyBean5 = new CatograyBean();
            catograyBean5.setCount(5);
            catograyBean5.setKind("江湖餐品"+5);
            catograyBean5.setList(list5);
            list.add(catograyBean5);
            bottomSheetLayout = (BottomSheetLayout) findViewById(R.id.bottomSheetLayout);

            //默认值
            list2.clear();
            list2.addAll(list.get(0).getList());

            //分类
            catograyAdapter = new CatograyAdapter(this, list);
            lv_catogary.setAdapter(catograyAdapter);
            catograyAdapter.notifyDataSetChanged();
            //商品
            goodsAdapter = new GoodsAdapter(this, list2, catograyAdapter);
            lv_good.setAdapter(goodsAdapter);
            goodsAdapter.notifyDataSetChanged();

    }


    //添加监听
    private void addListener() {
        lv_catogary.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.i("fyg","list.get(position).getList():"+list.get(position).getList());
                list2.clear();
                list2.addAll(list.get(position).getList());
                catograyAdapter.setSelection(position);
                catograyAdapter.notifyDataSetChanged();
                goodsAdapter.notifyDataSetChanged();


            }
        });
    }





    //创建套餐详情view
    public void showDetailSheet(List<ItemBean> listItem, String mealName){
        bottomDetailSheet = createMealDetailView(listItem,mealName);
        if(bottomSheetLayout.isSheetShowing()){
            bottomSheetLayout.dismissSheet();
        }else {
            if(listItem.size()!=0){
                bottomSheetLayout.showWithSheetView(bottomDetailSheet);
            }
        }
    }

    //查看套餐详情
    private View createMealDetailView(List<ItemBean> listItem, String mealName){
        View view = LayoutInflater.from(this).inflate(R.layout.activity_goods_detail,(ViewGroup) getWindow().getDecorView(),false);
        ListView lv_product = (MyListView) view.findViewById(R.id.lv_product);
        TextView tv_meal = (TextView) view.findViewById(R.id.tv_meal);
        TextView tv_num = (TextView) view.findViewById(R.id.tv_num);
        int count=0;
        for(int i=0;i<listItem.size();i++){
            count = count+Integer.parseInt(listItem.get(i).getNote2());
        }
        tv_meal.setText(mealName);
        tv_num.setText("(共"+count+"件)");
        goodsDetailAdapter = new GoodsDetailAdapter(MainActivity.this,listItem);
        lv_product.setAdapter(goodsDetailAdapter);
        goodsDetailAdapter.notifyDataSetChanged();
        return view;
    }





    //创建购物车view
    private void showBottomSheet(){
        bottomSheet = createBottomSheetView();
        if(bottomSheetLayout.isSheetShowing()){
            bottomSheetLayout.dismissSheet();
        }else {
            if(selectedList.size()!=0){
                bottomSheetLayout.showWithSheetView(bottomSheet);
            }
        }
    }




    //查看购物车布局
    private View createBottomSheetView(){
        View view = LayoutInflater.from(this).inflate(R.layout.layout_bottom_sheet,(ViewGroup) getWindow().getDecorView(),false);
        MyListView lv_product = (MyListView) view.findViewById(R.id.lv_product);
        TextView clear = (TextView) view.findViewById(R.id.clear);
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearCart();
            }
        });
        productAdapter = new ProductAdapter(MainActivity.this,goodsAdapter, selectedList);
        lv_product.setAdapter(productAdapter);
        return view;
    }

    //清空购物车
    public void clearCart(){
        selectedList.clear();
        list2.clear();
        if (list.size() > 0) {
            for (int j=0;j<list.size();j++){
                list.get(j).setCount(0);
                for(int i=0;i<list.get(j).getList().size();i++){
                    list.get(j).getList().get(i).setNum(0);
                }
            }
            list2.addAll(list.get(0).getList());
            catograyAdapter.setSelection(0);
            //刷新不能删
            catograyAdapter.notifyDataSetChanged();
            goodsAdapter.notifyDataSetChanged();
        }
        update(true);
    }


    //根据商品id获取当前商品的采购数量
    public int getSelectedItemCountById(int id){
        GoodsBean temp = selectedList.get(id);
        if(temp==null){
            return 0;
        }
        return temp.getNum();
    }


    public void handlerCarNum(int type, GoodsBean goodsBean, boolean refreshGoodList){
        if (type == 0) {
            GoodsBean temp = selectedList.get(goodsBean.getProduct_id());
            if(temp!=null){
                if(temp.getNum()<2){
                    goodsBean.setNum(0);
                    selectedList.remove(goodsBean.getProduct_id());

                }else{
                    int i =  goodsBean.getNum();
                    goodsBean.setNum(--i);
                }
            }



        } else if (type == 1) {
            GoodsBean temp = selectedList.get(goodsBean.getProduct_id());
            if(temp==null){
                goodsBean.setNum(1);
                selectedList.append(goodsBean.getProduct_id(), goodsBean);
            }else{
                int i= goodsBean.getNum();
                goodsBean.setNum(++i);
            }
        }

        update(refreshGoodList);

    }



    //刷新布局 总价、购买数量等
    private void update(boolean refreshGoodList){
        int size = selectedList.size();
        int count =0;
        for(int i=0;i<size;i++){
            GoodsBean item = selectedList.valueAt(i);
            count += item.getNum();
            totleMoney += item.getNum()*Double.parseDouble(item.getPrice());
        }
        tv_totle_money.setText("￥"+String.valueOf(df.format(totleMoney)));
        totleMoney = 0.00;
        if(count<1){
            bv_unm.setVisibility(View.GONE);
        }else{
            bv_unm.setVisibility(View.VISIBLE);
        }

        bv_unm.setText(String.valueOf(count));

        if(productAdapter!=null){
            productAdapter.notifyDataSetChanged();
        }

        if(goodsAdapter!=null){
            goodsAdapter.notifyDataSetChanged();
        }

        if(catograyAdapter!=null){
            catograyAdapter.notifyDataSetChanged();
        }

        if(bottomSheetLayout.isSheetShowing() && selectedList.size()<1){
            bottomSheetLayout.dismissSheet();
        }
    }


    /**
     * @Description: 创建动画层
     * @param
     * @return void
     * @throws
     */
    private ViewGroup createAnimLayout() {
        ViewGroup rootView = (ViewGroup) this.getWindow().getDecorView();
        LinearLayout animLayout = new LinearLayout(this);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        animLayout.setLayoutParams(lp);
        animLayout.setId(Integer.MAX_VALUE-1);
        animLayout.setBackgroundResource(android.R.color.transparent);
        rootView.addView(animLayout);
        return animLayout;
    }

    private View addViewToAnimLayout(final ViewGroup parent, final View view,
                                     int[] location) {
        int x = location[0];
        int y = location[1];
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        lp.leftMargin = x;
        lp.topMargin = y;
        view.setLayoutParams(lp);
        return view;
    }

    public void setAnim(final View v, int[] startLocation) {
        anim_mask_layout = null;
        anim_mask_layout = createAnimLayout();
        anim_mask_layout.addView(v);//把动画小球添加到动画层
        final View view = addViewToAnimLayout(anim_mask_layout, v, startLocation);
        int[] endLocation = new int[2];// 存储动画结束位置的X、Y坐标
        tv_car.getLocationInWindow(endLocation);
        // 计算位移
        int endX = 0 - startLocation[0] + 40;// 动画位移的X坐标
        int endY = endLocation[1] - startLocation[1];// 动画位移的y坐标

        TranslateAnimation translateAnimationX = new TranslateAnimation(0,endX, 0, 0);
        translateAnimationX.setInterpolator(new LinearInterpolator());
        translateAnimationX.setRepeatCount(0);// 动画重复执行的次数
        translateAnimationX.setFillAfter(true);

        TranslateAnimation translateAnimationY = new TranslateAnimation(0, 0, 0, endY);
        translateAnimationY.setInterpolator(new AccelerateInterpolator());
        translateAnimationY.setRepeatCount(0);// 动画重复执行的次数
        translateAnimationY.setFillAfter(true);

        AnimationSet set = new AnimationSet(false);
        set.setFillAfter(false);
        set.addAnimation(translateAnimationY);
        set.addAnimation(translateAnimationX);
        set.setDuration(800);// 动画的执行时间
        view.startAnimation(set);
        // 动画监听事件
        set.setAnimationListener(new Animation.AnimationListener() {
            // 动画的开始
            @Override
            public void onAnimationStart(Animation animation) {
                v.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                // TODO Auto-generated method stub
            }

            // 动画的结束
            @Override
            public void onAnimationEnd(Animation animation) {
                v.setVisibility(View.GONE);
            }
        });

    }



}
