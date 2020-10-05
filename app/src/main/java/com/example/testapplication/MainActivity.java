package com.example.testapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.testapplication.Dashboard.Category;
import com.example.testapplication.Dashboard.ImageBlock;
import com.example.testapplication.Dashboard.ProductGrid;
import com.example.testapplication.Dashboard.Row;
import com.example.testapplication.Dashboard.Template;
import com.example.testapplication.Dashboard.Template_;
import com.example.testapplication.adapter.CarouselHomeAdapter;
import com.example.testapplication.adapter.CategoryAdapter;
import com.example.testapplication.adapter.ProductAdapter;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    String token;
    EnhancedWrapContentViewPager viewPager;
    GridView gridView;
    LinearLayout linear_main;
    ScrollView scrollView;
    private int templateSize=0;
    private int currentPage=0;
    private List<Template_> templates_;
    private boolean isfetching=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager=findViewById(R.id.viewPager);
        gridView=findViewById(R.id.gridView);
        linear_main=findViewById(R.id.linear_main);
        scrollView=findViewById(R.id.scrollView);
        templates_=new ArrayList<>();
        try {
            token="bearer j21e6vnonbld18tq6ah85tloqi8ht8an";


            ApiClient.getInstance().appDashboard(token).enqueue(new Callback<List<Template>>() {
                @Override
                public void onResponse(Call<List<Template>> call, Response<List<Template>> response) {
                    if(response.isSuccessful()){

                        for (final Template template:response.body()) {
                            templates_.addAll(template.getTemplate());

                            CarouselHomeAdapter adapter=new CarouselHomeAdapter(MainActivity.this,template.getSliders(),false);
                            viewPager.setAdapter(adapter);
                            CategoryAdapter categoryAdapter=new CategoryAdapter(MainActivity.this,template.getCategories());
                            gridView.setAdapter(categoryAdapter);
                            int count;
                            if(template.getTemplate().size()>1){
                                count=2;
                            }else{
                                count=1;
                            }

                            for (int i = 0; i < count; i++) {
                                currentPage=i;
                                Template_ template_=template.getTemplate().get(i);
                                if(template_.getRowType().equalsIgnoreCase("image_block")){
                                    try {
//                                        Response<List<ImageBlock>> response_image=ApiClient.getInstance().dashImageBlock(token,template_.getRowValue()).execute();
                                        ApiClient.getInstance().dashImageBlock(token,template_.getRowValue()).enqueue(new Callback<List<ImageBlock>>() {
                                            @Override
                                            public void onResponse(Call<List<ImageBlock>> call, Response<List<ImageBlock>> response) {
                                                for (ImageBlock block:response.body()) {
                                                    TextView textView=new TextView(MainActivity.this);
                                                    textView.setText(block.getTitle());
                                                    linear_main.addView(textView);
                                                    for (final Row row:block.getRows()) {
                                                        int width= ViewGroup.LayoutParams.MATCH_PARENT;
                                                        int height=300;
                                                        if(Integer.parseInt(row.getColumns())==2){
                                                            width=550;
                                                            height=550;
                                                        }else if(Integer.parseInt(row.getColumns())==3){
                                                            width=350;
                                                            height=350;
                                                        }

                                                        final LinearLayout linearLayout=new LinearLayout(MainActivity.this);
                                                        linearLayout.setPadding(15,0,15,0);
                                                        linearLayout.setHorizontalGravity(Gravity.CENTER_HORIZONTAL);
                                                        LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                                                        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
                                                        linearLayout.setLayoutParams(params);
                                                        linear_main.addView(linearLayout);
                                                        for (int j = 0; j < row.getImages().size(); j++) {

                                                            final ImageView imageView=new ImageView(MainActivity.this);

                                                            LinearLayout.LayoutParams layoutParams=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                                                            layoutParams.gravity=Gravity.CENTER;
                                                            layoutParams.height=height;
                                                            layoutParams.width=width;
                                                            imageView.setLayoutParams(layoutParams);
                                                            imageView.setAdjustViewBounds(false);
                                                            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                                                            imageView.setPadding(10,10,10,10);

                                                            final int finalJ = j;
                                                            Picasso.get().load(row.getImages().get(finalJ).getImage()).fetch(new com.squareup.picasso.Callback() {
                                                                @Override
                                                                public void onSuccess() {
                                                                    Picasso.get().load(row.getImages().get(finalJ).getImage()).into(imageView);
                                                                    linearLayout.addView(imageView);

                                                                }

                                                                @Override
                                                                public void onError(Exception e) {

                                                                }
                                                            });

                                                        }


                                                    }
                                                }
                                            }

                                            @Override
                                            public void onFailure(Call<List<ImageBlock>> call, Throwable t) {

                                            }
                                        });

                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                    Log.e("View--->", "onResponse: "+template_.getRowType());

                                }else{
                                    Log.e("View--->", "onResponse: "+template_.getRowType() );

//                                        Response<List<ProductGrid>> gridResponse=ApiClient.getInstance().dashProductGrid(token,template_.getRowValue()).execute();
                                    ApiClient.getInstance().dashProductGrid(token,template_.getRowValue()).enqueue(new Callback<List<ProductGrid>>() {
                                        @Override
                                        public void onResponse(Call<List<ProductGrid>> call, Response<List<ProductGrid>> response) {
                                            for (final ProductGrid grid:response.body()) {
                                                final RelativeLayout relativeLayout=new RelativeLayout(MainActivity.this);
                                                RelativeLayout.LayoutParams layoutParams=new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                                                relativeLayout.setLayoutParams(layoutParams);

                                                Picasso.get().load(grid.getBackground()).fetch(new com.squareup.picasso.Callback() {
                                                    @Override
                                                    public void onSuccess() {
                                                        Picasso.get().load(grid.getBackground()).into(new Target() {
                                                            @Override
                                                            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                                                                Drawable drawable=new BitmapDrawable(bitmap);
                                                                relativeLayout.setBackground(drawable);
                                                                for (int i = 0; i < grid.getProducts().size(); i++) {
                                                                    if(i%2==0){
                                                                        if(Float.parseFloat(grid.getProducts().get(i).getReviewsSummary())>0.0){
                                                                            grid.getProducts().get(i).setRating(true);
                                                                            if(i<grid.getProducts().size()-1) {
                                                                                grid.getProducts().get(i + 1).setRating(true);
                                                                            }
                                                                        }
                                                                    }else{
                                                                        if(Float.parseFloat(grid.getProducts().get(i).getReviewsSummary())>0.0) {
                                                                            grid.getProducts().get(i).setRating(true);
                                                                            if (i >0) {
                                                                                grid.getProducts().get(i - 1).setRating(true);
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                                relativeLayout.setPadding(50,20,50,0);
                                                                MyGridView gridView=new MyGridView(MainActivity.this);
                                                                gridView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                                                                gridView.setNumColumns(2);
                                                                gridView.setHorizontalSpacing(10);
                                                                gridView.setVerticalSpacing(10);
                                                                gridView.setAdapter(new ProductAdapter(MainActivity.this,grid.getProducts()));
                                                                relativeLayout.addView(gridView);
                                                                linear_main.addView(relativeLayout);

                                                                scrollView.setVisibility(View.VISIBLE);

                                                            }

                                                            @Override
                                                            public void onBitmapFailed(Exception e, Drawable errorDrawable) {

                                                            }

                                                            @Override
                                                            public void onPrepareLoad(Drawable placeHolderDrawable) {

                                                            }
                                                        });
                                                    }

                                                    @Override
                                                    public void onError(Exception e) {

                                                    }
                                                });

                                            }
                                        }

                                        @Override
                                        public void onFailure(Call<List<ProductGrid>> call, Throwable t) {

                                        }
                                    });

                                }

                            }
                        }
                    }
                }

                @Override
                public void onFailure(Call<List<Template>> call, Throwable t) {

                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }



        scrollView.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
            @Override
            public void onScrollChanged() {
                if(currentPage<templates_.size()-1) {
                    if (!scrollView.canScrollVertically(1)) {
                        if (!isfetching) {
                            isfetching = true;

                            Toast.makeText(MainActivity.this, "" + templates_.get(currentPage + 1).toString(), Toast.LENGTH_SHORT).show();
                            if (templates_.get(currentPage + 1).getRowType().equalsIgnoreCase("image_block")) {

//                                        Response<List<ImageBlock>> response_image=ApiClient.getInstance().dashImageBlock(token,template_.getRowValue()).execute();
                                ApiClient.getInstance().dashImageBlock(token, templates_.get(currentPage + 1).getRowValue()).enqueue(new Callback<List<ImageBlock>>() {
                                    @Override
                                    public void onResponse(Call<List<ImageBlock>> call, Response<List<ImageBlock>> response) {
                                        currentPage++;
                                        for (ImageBlock block : response.body()) {
                                            TextView textView = new TextView(MainActivity.this);
                                            textView.setText(block.getTitle());
                                            linear_main.addView(textView);
                                            for (final Row row : block.getRows()) {
                                                int width = ViewGroup.LayoutParams.MATCH_PARENT;
                                                int height = 300;
                                                if (Integer.parseInt(row.getColumns()) == 2) {
                                                    width = 550;
                                                    height = 550;
                                                } else if (Integer.parseInt(row.getColumns()) == 3) {
                                                    width = 350;
                                                    height = 350;
                                                }

                                                final LinearLayout linearLayout = new LinearLayout(MainActivity.this);
                                                linearLayout.setPadding(15, 0, 15, 0);
                                                linearLayout.setHorizontalGravity(Gravity.CENTER_HORIZONTAL);
                                                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                                                linearLayout.setOrientation(LinearLayout.HORIZONTAL);
                                                linearLayout.setLayoutParams(params);
                                                linear_main.addView(linearLayout);
                                                isfetching = false;
                                                for (int j = 0; j < row.getImages().size(); j++) {

                                                    final ImageView imageView = new ImageView(MainActivity.this);

                                                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                                                    layoutParams.gravity = Gravity.CENTER;
                                                    layoutParams.height = height;
                                                    layoutParams.width = width;
                                                    imageView.setLayoutParams(layoutParams);
                                                    imageView.setAdjustViewBounds(false);
                                                    imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                                                    imageView.setPadding(10, 10, 10, 10);

                                                    final int finalJ = j;
                                                    Picasso.get().load(row.getImages().get(finalJ).getImage()).fetch(new com.squareup.picasso.Callback() {
                                                        @Override
                                                        public void onSuccess() {
                                                            Picasso.get().load(row.getImages().get(finalJ).getImage()).into(imageView);
                                                            linearLayout.addView(imageView);

                                                        }

                                                        @Override
                                                        public void onError(Exception e) {

                                                        }
                                                    });

                                                }


                                            }
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<List<ImageBlock>> call, Throwable t) {

                                    }
                                });

                            } else {
                                ApiClient.getInstance().dashProductGrid(token, templates_.get(currentPage + 1).getRowValue()).enqueue(new Callback<List<ProductGrid>>() {
                                    @Override
                                    public void onResponse(Call<List<ProductGrid>> call, Response<List<ProductGrid>> response) {
                                        currentPage++;
                                        for (final ProductGrid grid : response.body()) {
                                            final RelativeLayout relativeLayout = new RelativeLayout(MainActivity.this);
                                            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                                            relativeLayout.setLayoutParams(layoutParams);

                                            Picasso.get().load(grid.getBackground()).fetch(new com.squareup.picasso.Callback() {
                                                @Override
                                                public void onSuccess() {
                                                    Picasso.get().load(grid.getBackground()).into(new Target() {
                                                        @Override
                                                        public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                                                            Drawable drawable = new BitmapDrawable(bitmap);
                                                            relativeLayout.setBackground(drawable);
                                                            for (int i = 0; i < grid.getProducts().size(); i++) {
                                                                if (i % 2 == 0) {
                                                                    if (Float.parseFloat(grid.getProducts().get(i).getReviewsSummary()) > 0.0) {
                                                                        grid.getProducts().get(i).setRating(true);
                                                                        if (i < grid.getProducts().size() - 1) {
                                                                            grid.getProducts().get(i + 1).setRating(true);
                                                                        }
                                                                    }
                                                                } else {
                                                                    if (Float.parseFloat(grid.getProducts().get(i).getReviewsSummary()) > 0.0) {
                                                                        grid.getProducts().get(i).setRating(true);
                                                                        if (i > 0) {
                                                                            grid.getProducts().get(i - 1).setRating(true);
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                            relativeLayout.setPadding(50, 20, 50, 0);
                                                            MyGridView gridView = new MyGridView(MainActivity.this);
                                                            gridView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                                                            gridView.setNumColumns(2);
                                                            gridView.setHorizontalSpacing(10);
                                                            gridView.setVerticalSpacing(10);
                                                            gridView.setAdapter(new ProductAdapter(MainActivity.this, grid.getProducts()));
                                                            relativeLayout.addView(gridView);
                                                            linear_main.addView(relativeLayout);
                                                            isfetching = false;
                                                            scrollView.setVisibility(View.VISIBLE);

                                                        }

                                                        @Override
                                                        public void onBitmapFailed(Exception e, Drawable errorDrawable) {

                                                        }

                                                        @Override
                                                        public void onPrepareLoad(Drawable placeHolderDrawable) {

                                                        }
                                                    });
                                                }

                                                @Override
                                                public void onError(Exception e) {

                                                }
                                            });

                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<List<ProductGrid>> call, Throwable t) {

                                    }
                                });
                            }
                        }

                    }
                }
            }
        });

    }



}