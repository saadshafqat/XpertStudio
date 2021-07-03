package com.example.xpertstudio;

import android.animation.ArgbEvaluator;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;
import java.util.List;

public class fragment_packages extends Fragment {
    ViewPager viewPager;
    Adapter adapter;
    List<Model> models;
    Integer[] colors=null;
    ArgbEvaluator argbEvaluator=new ArgbEvaluator();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

                View v=inflater.inflate(R.layout.fragment_packages,container,false);

            models=new ArrayList<>();
            models.add(new Model(R.drawable.packagesimage1,getString(R.string.package_1), getString(R.string.pkgdesc1)));
            models.add(new Model(R.drawable.packagesimage2,getString(R.string.package_2), getString(R.string.pkgdesc2)));
            models.add(new Model(R.drawable.packagesimage3,getString(R.string.package_3), getString(R.string.pkgdesc3)));
            models.add(new Model(R.drawable.packagesimage4,getString(R.string.package_4), getString(R.string.pkgdesc4)));

            adapter=new Adapter(models,getActivity());
            viewPager=v.findViewById(R.id.viewPager);
            viewPager.setAdapter(adapter);
            viewPager.setPadding(130,0,130,0);

            Integer[] colors_temp={
                    getResources().getColor(R.color.packagescolor1),
        getResources().getColor(R.color.packagescolor2),
        getResources().getColor(R.color.packagescolor3),
                    getResources().getColor(R.color.packagescolor4),
            };
            colors=colors_temp;

            viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                    if(position<(adapter.getCount()-1)&&position<(colors.length-1)){
                        viewPager.setBackgroundColor((Integer) argbEvaluator.evaluate(positionOffset,colors[position],colors[position+1]));
                    }
                    else{
                        viewPager.setBackgroundColor(colors[colors.length-1]);
                    }
                }

                @Override
                public void onPageSelected(int position) {

                }

                @Override
                public void onPageScrollStateChanged(int state) {

                }
            });

        return v;
    }
}
