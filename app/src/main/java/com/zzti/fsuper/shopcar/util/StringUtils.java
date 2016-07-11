package com.zzti.fsuper.shopcar.util;

import android.widget.TextView;

/**
 * Created by fengyongge on 2016/5/20 0020.
 */
public class StringUtils {

    public static boolean isEmpty(CharSequence cs) {
        return (cs == null) || (cs.length() == 0);
    }

    public static boolean isNotEmpty(CharSequence cs) {
        return !isEmpty(cs);
    }

    public static void filtNull(TextView textView,String s) {
        if (s != null) {
            textView.setText(s);
        } else {
            textView.setText(filtNull(s));
        }
    }


    public static void filtNull(TextView textView,String s1,String s2) {
        if (s1 != null&&s2!=null) {
            textView.setText(s1+" "+s2);
        } else {
            textView.setText(filtNull(s1)+filtNull(s2));
        }

    }

    //判断过滤单个string为null
    public static String filtNull(String s) {
        if (s!=null) {
           return  s;
        } else {
            s="null";
        }
        return  s;
    }


    //判断单个对象不为null
    public static boolean filtObjectNull(Object object) {
        if (object != null) {
           return false;
        } else {
           return true;
        }
    }




}
