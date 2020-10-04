package com.example.testapplication.adapter;

import android.annotation.SuppressLint;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.testapplication.Dashboard.Product;
import com.example.testapplication.R;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.List;

public class ProductAdapter extends BaseAdapter {
    Context context;
    List<Product> products;

    public ProductAdapter(Context context, List<Product> products) {
        this.context = context;
        this.products = products;
    }

    @Override
    public int getCount() {
        return products.size();
    }

    @Override
    public Object getItem(int i) {
        return products.get(i);
    }

    @Override
    public long getItemId(int i) {
        return Long.parseLong(products.get(i).getId());
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view==null){
            view= LayoutInflater.from(context).inflate(R.layout.list_product_row,viewGroup,false);
        }
        final ImageView imageProduct=view.findViewById(R.id.imageProduct);
        ImageView imageView_karachi=view.findViewById(R.id.imageView_karachi);
        TextView txtProduct=view.findViewById(R.id.txtProduct);
        TextView txtPrice=view.findViewById(R.id.txtPrice);
        TextView txtDiscount=view.findViewById(R.id.txtDiscount);
        TextView txtCutPrice=view.findViewById(R.id.txtCutPrice);
        RatingBar ratingBar=view.findViewById(R.id.ratingBar);
        RelativeLayout relativeProduct=view.findViewById(R.id.relativeProduct);


        final Product mProduct = products.get(i);



        if(mProduct!=null){
            if (mProduct.getDiscount()) {
                txtDiscount.setVisibility(View.VISIBLE);
                txtDiscount.setText(mProduct.getDiscountPercent() + "% OFF");

                txtCutPrice.setText(mProduct.getPrice());
                txtCutPrice.setPaintFlags(txtCutPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            }else{
                 txtDiscount.setVisibility(View.GONE);
            }

             txtPrice.setText(mProduct.getFinalPrice());
            txtProduct.setText(mProduct.getName());


            if (mProduct.getKarachiOnly()) {
                imageView_karachi.setVisibility(View.VISIBLE);

            }else{
                 imageView_karachi.setVisibility(View.GONE);
            }


            if(mProduct.isRating()==true){
                ratingBar.setRating(Float.parseFloat(mProduct.getReviewsSummary()));
                ratingBar.setVisibility(Float.parseFloat(mProduct.getReviewsSummary())>0.0?View.VISIBLE:View.INVISIBLE);
            }else{
                ratingBar.setVisibility(View.GONE);
            }

            Picasso.get().load(mProduct.getImage()).fetch(new Callback() {
                @Override
                public void onSuccess() {
                    Picasso.get().load(mProduct.getImage()).into(imageProduct);

                }

                @Override
                public void onError(Exception e) {

                }
            });


        }
        
        return view;
    }
}
