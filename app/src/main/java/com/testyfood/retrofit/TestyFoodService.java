package com.testyfood.retrofit;

import com.testyfood.models.ItemsResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by viswas on 8/28/2017.
 */

public interface TestyFoodService {

    @POST(ApiUrlConfig.GET_FOOD_ITEM_URL)
    @FormUrlEncoded
    Call<ItemsResponse> getFoodItems(@Field("location_id") String location_id);
}
