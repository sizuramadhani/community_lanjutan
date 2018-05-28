package com.udakita.komunitas;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.github.florent37.bubbletab.BubbleTab;
import com.udakita.komunitas.adapter.FakeAdapter;
import com.yalantis.euclid.library.EuclidActivity;
import com.yalantis.euclid.library.EuclidListAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends EuclidActivity {


    @BindView(R.id.bubbleTab)
    BubbleTab bubbleTab;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.imgprofile)
    ImageView imgprofile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    //    viewPager.setAdapter(new FakeAdapter(getSupportFragmentManager()));
       FakeAdapter adapter = new FakeAdapter(getSupportFragmentManager(),MainActivity.this,imgprofile);
        viewPager.setAdapter(adapter);
        bubbleTab.setupWithViewPager(viewPager);
    }

    @Override
    protected BaseAdapter getAdapter() {
        Map<String, Object> profileMap;
        List<Map<String, Object>> profilesList = new ArrayList<>();

        int[] avatars = {
                R.drawable.andi,
                R.drawable.mada,
        };
        int[] descripsi = {
                R.string.andi,
                R.string.mada,
        };
        String[] names = getResources().getStringArray(R.array.array_names);

        for (int i = 0; i < avatars.length; i++) {
            profileMap = new HashMap<>();
            profileMap.put(EuclidListAdapter.KEY_AVATAR, avatars[i]);
            profileMap.put(EuclidListAdapter.KEY_NAME, names[i]);
            profileMap.put(EuclidListAdapter.KEY_DESCRIPTION_SHORT, descripsi[i]);
            profileMap.put(EuclidListAdapter.KEY_DESCRIPTION_FULL, getString(R.string.lorem_ipsum_long));
            profilesList.add(profileMap);
        }

        return new EuclidListAdapter(this, R.layout.list_item, profilesList);

    }

//    @OnClick(R.id.imgprofile)
//    public void onViewClicked() {
//        MyProfileFragment fragment =new MyProfileFragment();
//        android.support.v4.app.FragmentManager manager =getSupportFragmentManager();
//        FragmentTransaction transaction =manager.beginTransaction()
//                .replace(R.id.viewPager,fragment);
//        transaction.commit();
//    }
}
