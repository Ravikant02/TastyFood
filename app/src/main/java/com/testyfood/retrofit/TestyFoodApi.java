package com.testyfood.retrofit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by viswas on 8/28/2017.
 */

public class TestyFoodApi {

    public static TestyFoodService apiServices;
    public static TestyFoodService getServiceInstance() {
        if (apiServices == null) {
            Gson gson = new GsonBuilder().setLenient().create();
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);

            OkHttpClient client = new OkHttpClient()
                    .newBuilder()
                    .writeTimeout(120, TimeUnit.SECONDS)
                    .readTimeout(120, TimeUnit.SECONDS)
                    .connectTimeout(120, TimeUnit.SECONDS)
                    .addInterceptor(logging)
                    .build();
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(ApiUrlConfig.BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
            apiServices = retrofit.create(TestyFoodService.class);
        }
        return apiServices;
    }

}
