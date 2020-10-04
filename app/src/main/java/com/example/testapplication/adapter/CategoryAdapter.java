package com.example.testapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.testapplication.Dashboard.Category;
import com.example.testapplication.MainActivity;
import com.example.testapplication.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CategoryAdapter extends BaseAdapter {
    Context context;
    List<Category> categories;

    public CategoryAdapter(Context context, List<Category> categories) {
        this.context = context;
        this.categories = categories;
    }

    @Override
    public int getCount() {
        return categories.size();
    }

    @Override
    public Object getItem(int i) {
        return categories.get(i);
    }

    @Override
    public long getItemId(int i) {
        return categories.get(i).getCategory();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        if(convertView==null){
            convertView= LayoutInflater.from(context).inflate(R.layout.list_popular_categories_row,viewGroup,false);
        }
        ImageView imageCategory=convertView.findViewById(R.id.imageCategory);
        TextView txt_category=convertView.findViewById(R.id.txt_category);
        Category category=categories.get(position);
        Picasso.get().load(category.getImage()).into(imageCategory);
        txt_category.setText(category.getTitle());
        return convertView;
    }
}
