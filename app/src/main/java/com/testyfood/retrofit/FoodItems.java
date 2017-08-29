package com.testyfood.retrofit;

import org.json.JSONObject;

/**
 * Created by viswas on 8/28/2017.
 */

public class FoodItems {
    public JSONObject items;
    public static FoodItems foodItems;

    public static FoodItems getInstance(){
        if (foodItems==null) return new FoodItems();
        else return foodItems;
    }

    public JSONObject getItems() {
        if (items==null){
            return new JSONObject();
        }
        return foodItems.items;
    }

    public void setItems(JSONObject items) {
        this.items = items;
    }
}
