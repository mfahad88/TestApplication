package com.example.testapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.testapplication.Dashboard.Slider;
import com.example.testapplication.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CarouselHomeAdapter extends PagerAdapter {
    private Context context;
    private LayoutInflater layoutInflater;
    private List<Slider> list;
    private boolean isFocusable;
    public static final String POSITION="position";
    public static final String IMAGES="images";
    public CarouselHomeAdapter(Context context, List<Slider> bitmap, boolean isFocusable) {
        this.context = context;
        this.list = bitmap;
        this.isFocusable=isFocusable;
    }



    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public int getCount() {
        return list.size();
    }



    @Override
    public Object instantiateItem(ViewGroup container, final int position) {

        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        final View view = layoutInflater.inflate(R.layout.list_carousel_layout, null);
        final ImageView imageView = (ImageView) view.findViewById(R.id.imageView);
        final Slider slider=list.get(position);

        if(slider!=null){
//            Glide.with(context).asBitmap().load(slider.getImage()).transition(BitmapTransitionOptions.withCrossFade(200)).into(imageView);
            Picasso.get().load(slider.getImage()).into(imageView);
        }

        ViewPager vp = (ViewPager) container;
        vp.addView(view, 0);
        return view;

    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

        ViewPager vp = (ViewPager) container;
        View view = (View) object;
        vp.removeView(view);

    }
}
