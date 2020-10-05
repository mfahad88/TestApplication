package com.example.testapplication.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.testapplication.Dashboard.Home;
import com.example.testapplication.Dashboard.Image;
import com.example.testapplication.Dashboard.ImageBlock;
import com.example.testapplication.Dashboard.Row;
import com.example.testapplication.R;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.List;

public class ImageAdapter extends BaseAdapter {
    List<ImageBlock> list;
    Context context;
    int column=1;
    public ImageAdapter(Context context,List<ImageBlock> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {


        if(view==null){
            if(!list.get(i).getTitle().isEmpty()) {
                view = LayoutInflater.from(context).inflate(R.layout.list_title_row, viewGroup, false);
                TextView txt_title=view.findViewById(R.id.txt_title);
                txt_title.setText(list.get(i).getTitle());
            }else{

            }
        }
        return view;
    }
}
