package com.suyonoion.flymeosguide;

/**
 * Created by Suyono on 4/19/2015.
 */
import java.util.ArrayList;
import java.util.List;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.PagerTitleStrip;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;

public class aktiviti_pertama extends Activity {
    private ViewPager viewPager;
    private PagerTitleStrip pagerTitleStrip;
    private MyAdapter adapter;
    private List<View> list;
    private List<String> titlelist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aktiviti_pertama_layout);
        viewPager = (ViewPager) findViewById(R.id.viewrpager);
        pagerTitleStrip = (PagerTitleStrip) findViewById(R.id.pagertitle);
        list = new ArrayList<View>();
        list.add(LayoutInflater.from(this).inflate(R.layout.halaman0, null));
        list.add(LayoutInflater.from(this).inflate(R.layout.halaman1, null));
        list.add(LayoutInflater.from(this).inflate(R.layout.halaman2, null));
        list.add(LayoutInflater.from(this).inflate(R.layout.status2bar, null));
        list.add(LayoutInflater.from(this).inflate(R.layout.status3bar, null));
        list.add(LayoutInflater.from(this).inflate(R.layout.status4bar, null));
        list.add(LayoutInflater.from(this).inflate(R.layout.more, null));
        list.add(LayoutInflater.from(this).inflate(R.layout.more_bonus, null));
        
        titlelist = new ArrayList<String>();
        titlelist.add("Garuda Style");
        titlelist.add("Tiga Jari Style");
        titlelist.add("Anu Style");
        titlelist.add("Dua Baris");
        titlelist.add("Tiga Baris");
        titlelist.add("Empat Baris");
        titlelist.add("Lima Baris");
        titlelist.add("Lainnya");


        adapter = new MyAdapter(titlelist, list);

        viewPager.setAdapter(adapter);
    }

}