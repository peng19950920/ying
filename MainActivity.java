package com.pengying.citylist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    CityListFragment cityListFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cityListFragment = new CityListFragment();
        getFragmentManager().beginTransaction().add(R.id.fragment,cityListFragment).commit();
    }
}
