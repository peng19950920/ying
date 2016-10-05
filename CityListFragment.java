package com.pengying.citylist;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 43997119 on 2016/9/29.
 */
public class CityListFragment extends android.app.Fragment implements View.OnClickListener{

    private ListView cityList;
    private CityListViewAdapter adapter;
    private List<CityModel> data = new ArrayList<>();
    private QuickIndexView indexView;
    private TextView index;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.z_fragment_city_list, container, false);
        initData();
        cityList = (ListView)view.findViewById(R.id.city_list);
        indexView = (QuickIndexView)view.findViewById(R.id.index_view);
        index = (TextView)view.findViewById(R.id.index_text);
        adapter = new CityListViewAdapter(getActivity(),data);
        cityList.setAdapter(adapter);
        setListener();
        return view;
    }
    private void initData() {
        String[] strings = {
                "Android", "java", "news", "Baidu", "oberser",
                "mary", "next", "ruby", "money", "lucy", "very", "thunder",
                "object", "lily", "jay", "answer", "layout", "demos", "com",
                "Collect", "ustom", "blog", "round", "redirect", "ground", "gray",
                "blue", "zone", "james", "zhang", "location"
        };
        for (int i = 0;i<strings.length;i++){
            data.add(new CityModel(strings[i]));
        }

    }
    private void setListener(){
        indexView.setOnIndexChangedListener(new QuickIndexView.OnIndexChangedListener() {
            @Override
            public void onIndexChanged(String word) {
                index.setText(word);
                index.setVisibility(View.VISIBLE);
                int position = adapter.getIndexPosition(word);
                if(position>=0){
                    cityList.setSelection(position);
                }
                return;
            }
            @Override
            public void onUp() {
                index.setVisibility(View.GONE);
            }
        });
        cityList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getActivity(),((CityModel)adapter.getItem(position)).getEnName(),Toast.LENGTH_LONG).show();
            }
        });
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){

        }
    }
}