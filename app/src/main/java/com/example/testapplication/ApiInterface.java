package com.example.testapplication;



import com.example.testapplication.Dashboard.ImageBlock;
import com.example.testapplication.Dashboard.ProductGrid;
import com.example.testapplication.Dashboard.Template;
import com.google.gson.JsonElement;

import java.util.List;
import java.util.Map;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface ApiInterface {





    @GET("index.php/rest/V1/naheed-customapi/appdashboard")
    Call<List<Template>> appDashboard(@Header("Authorization") String token);

    @GET("index.php/rest/V1/naheed-customapi/dashimageblock")
    Call<List<ImageBlock>> dashImageBlock(@Header("Authorization") String token, @Query("id")String id);

    @GET("index.php/rest/V1/naheed-customapi/dashproductgrid")
    Call<List<ProductGrid>> dashProductGrid(@Header("Authorization") String token,@Query("id")String id);




}