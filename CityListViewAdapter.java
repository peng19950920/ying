package com.pengying.citylist;

/**
 * Created by pengying on 2016/10/5.
 */
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 43997119 on 2016/9/29.
 */

public class CityListViewAdapter extends BaseAdapter {
    private Context context;
    private List<CityModel> data = new ArrayList<>();
    private static final int TYPE_CITY = 0;
    private static final int TYPE_STRING = 1;
    private ArrayList<Object> mData = new ArrayList<>();
    private Map<String,Integer> map = new HashMap<>();

    public CityListViewAdapter(Context context,List<CityModel> data){
        this.context = context;
        this.data = data;
        Collections.sort(this.data);
        for (int i =0;i<this.data.size();i++){
            String word = data.get(i).getEnName().toLowerCase().substring(0, 1).toUpperCase();
            if(i==0) {
                map.put(word,mData.size());
                mData.add(word);
                mData.add(data.get(i));

            } else {
                String preWord = data.get(i-1).getEnName().toLowerCase().substring(0, 1).toUpperCase();
                //判断是否于当前行的word是否相同
                //如果相同, 隐藏
                if(word.equals(preWord)) {
                    mData.add(data.get(i));
                } else {
                    //如果不同, 显示
                    map.put(word,mData.size());
                    mData.add(word);
                    mData.add(data.get(i));
                }
            }
        }
    }
    @Override
    public boolean isEnabled(int position) {
        if (mData.get(position) instanceof String)// 如果是字母索引
            return false;// 表示不能点击
        return super.isEnabled(position);
    }
    @Override
    public int getCount() {
        return mData.size();
    }
    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }
    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public int getItemViewType(int position) {
        if (mData.get(position) instanceof CityModel) {
            return TYPE_CITY;
        } else if (mData.get(position) instanceof String) {
            return TYPE_STRING;
        } else {
            return super.getItemViewType(position);
        }
    }
    @Override
    public int getViewTypeCount() {
        return 2;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        int type = getItemViewType(position);
        ViewHolder1 holder1 = null;
        ViewHolder2 holder2 = null;
        if(convertView == null){
            switch (type){
                case TYPE_CITY:
                    holder1 = new ViewHolder1();
                    convertView = LayoutInflater.from(context).inflate(R.layout.city_item, parent, false);
                    holder1.name = (TextView) convertView.findViewById(R.id.name);
                    convertView.setTag(R.layout.city_item,holder1);
                    break;
                case TYPE_STRING:
                    holder2 = new ViewHolder2();
                    convertView = LayoutInflater.from(context).inflate(R.layout.city_index, parent, false);
                    holder2.index = (TextView) convertView.findViewById(R.id.index);
                    convertView.setTag(R.layout.city_index,holder2);
                    break;
            }
        }else{
            switch (type){
                case TYPE_CITY:
                    holder1 = (ViewHolder1) convertView.getTag(R.layout.city_item);
                    break;
                case TYPE_STRING:
                    holder2 = (ViewHolder2) convertView.getTag(R.layout.city_index);
                    break;
            }
        }

        Object obj = mData.get(position);
        //设置下控件的值
        switch (type){
            case TYPE_CITY:
                CityModel city = (CityModel) obj;
                if(city != null){
                    holder1.name.setText(city.getEnName());
                }
                break;
            case TYPE_STRING:
                String index = (String) obj;
                if(index != null){
                    holder2.index.setText(index);
                }
                break;
        }
        return convertView;
    }

    //两个不同的ViewHolder
    private static class ViewHolder1{
        TextView name;
    }

    private static class ViewHolder2{
        TextView index;
    }
    public int getIndexPosition(String index){
        if (map.containsKey(index)){
            return map.get(index);
        }
        else return  -1;
    }
}