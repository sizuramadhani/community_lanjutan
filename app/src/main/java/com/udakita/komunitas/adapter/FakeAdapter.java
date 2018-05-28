package com.udakita.komunitas.adapter;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.widget.ImageView;

import com.udakita.komunitas.MainActivity;
import com.udakita.komunitas.R;
import com.udakita.komunitas.fragment.FakeFragment;
import com.udakita.komunitas.fragment.HomeComunityFragment;
import com.udakita.komunitas.fragment.KomunitasFragment;

import butterknife.BindView;

public class FakeAdapter extends FragmentStatePagerAdapter {
    Activity activity;
    ImageView img;
    @BindView(R.id.imgprofile)
    ImageView imgprofile;
FragmentManager fm;
    public FakeAdapter(FragmentManager fm) {
        super(fm);
    }

    public FakeAdapter(FragmentManager fm, MainActivity mainActivity, ImageView imgprofile) {
        super(fm);
        this.fm=fm;
        activity = mainActivity;
        img = imgprofile;
    }

    @Override
    public Fragment getItem(int position) {
        if(position == 0){

            return new HomeComunityFragment();

        }
        else if(position == 1){

            return new KomunitasFragment();

        }else if (position==2){
            return FakeFragment.newInstance();

        }
        return null;

    }

    @Override
    public int getCount() {
        return 3;
    }


//    @Override
//    public boolean isViewFromObject(View view, Object object) {
//        return super.isViewFromObject(view, object);
//    }

//    @Override
//    public Object instantiateItem(ViewGroup container, int position) {
////        LayoutInflater inflater = (LayoutInflater) activity.getSystemService(activity.LAYOUT_INFLATER_SERVICE);
////        View view = inflater.inflate(R.layout.activity_main, container, false);
////        imgprofile.setoncl
////        ((ViewPager) container).addView(view);
////
////
////        return view;
//    }
}
