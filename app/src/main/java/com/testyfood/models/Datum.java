
package com.testyfood.models;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("food_cuisine_id")
    @Expose
    private String foodCuisineId;
    @SerializedName("food_category_id")
    @Expose
    private String foodCategoryId;
    @SerializedName("item_name")
    @Expose
    private String itemName;
    @SerializedName("item_image_url")
    @Expose
    private String itemImageUrl;
    @SerializedName("firebase_url")
    @Expose
    private String firebaseUrl;
    @SerializedName("item_type")
    @Expose
    private String itemType;
    @SerializedName("item_description")
    @Expose
    private String itemDescription;
    @SerializedName("item_search_tags")
    @Expose
    private String itemSearchTags;
    @SerializedName("location_id")
    @Expose
    private String locationId;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("item_rating")
    @Expose
    private String itemRating;
    @SerializedName("item_ingredients")
    @Expose
    private String itemIngredients;
    @SerializedName("make_my_meal")
    @Expose
    private String makeMyMeal;
    @SerializedName("display_order")
    @Expose
    private String displayOrder;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("cuisine_name")
    @Expose
    private String cuisineName;
    @SerializedName("category_name")
    @Expose
    private String categoryName;
    @SerializedName("delivery_time")
    @Expose
    private String deliveryTime;
    @SerializedName("time_slot")
    @Expose
    private Integer timeSlot;
    @SerializedName("price")
    @Expose
    private List<Price> price = null;
    @SerializedName("sideportion")
    @Expose
    private List<Object> sideportion = null;
    @SerializedName("ingredients")
    @Expose
    private Object ingredients;
    @SerializedName("itemrating")
    @Expose
    private float itemrating;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFoodCuisineId() {
        return foodCuisineId;
    }

    public void setFoodCuisineId(String foodCuisineId) {
        this.foodCuisineId = foodCuisineId;
    }

    public String getFoodCategoryId() {
        return foodCategoryId;
    }

    public void setFoodCategoryId(String foodCategoryId) {
        this.foodCategoryId = foodCategoryId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemImageUrl() {
        return itemImageUrl;
    }

    public void setItemImageUrl(String itemImageUrl) {
        this.itemImageUrl = itemImageUrl;
    }

    public String getFirebaseUrl() {
        return firebaseUrl;
    }

    public void setFirebaseUrl(String firebaseUrl) {
        this.firebaseUrl = firebaseUrl;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public String getItemSearchTags() {
        return itemSearchTags;
    }

    public void setItemSearchTags(String itemSearchTags) {
        this.itemSearchTags = itemSearchTags;
    }

    public String getLocationId() {
        return locationId;
    }

    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getItemRating() {
        return itemRating;
    }

    public void setItemRating(String itemRating) {
        this.itemRating = itemRating;
    }

    public String getItemIngredients() {
        return itemIngredients;
    }

    public void setItemIngredients(String itemIngredients) {
        this.itemIngredients = itemIngredients;
    }

    public String getMakeMyMeal() {
        return makeMyMeal;
    }

    public void setMakeMyMeal(String makeMyMeal) {
        this.makeMyMeal = makeMyMeal;
    }

    public String getDisplayOrder() {
        return displayOrder;
    }

    public void setDisplayOrder(String displayOrder) {
        this.displayOrder = displayOrder;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getCuisineName() {
        return cuisineName;
    }

    public void setCuisineName(String cuisineName) {
        this.cuisineName = cuisineName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(String deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public Integer getTimeSlot() {
        return timeSlot;
    }

    public void setTimeSlot(Integer timeSlot) {
        this.timeSlot = timeSlot;
    }

    public List<Price> getPrice() {
        return price;
    }

    public void setPrice(List<Price> price) {
        this.price = price;
    }

    public List<Object> getSideportion() {
        return sideportion;
    }

    public void setSideportion(List<Object> sideportion) {
        this.sideportion = sideportion;
    }

    public Object getIngredients() {
        return ingredients;
    }

    public void setIngredients(Object ingredients) {
        this.ingredients = ingredients;
    }

    public float getItemrating() {
        return itemrating;
    }

    public void setItemrating(float itemrating) {
        this.itemrating = itemrating;
    }

}
