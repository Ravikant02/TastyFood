package com.testyfood.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.gson.Gson;
import com.testyfood.R;
import com.testyfood.core.AppCore;
import com.testyfood.fragments.ItemFragment;
import com.testyfood.models.Datum;
import com.testyfood.models.ItemsResponse;
import com.testyfood.retrofit.FoodItems;
import com.testyfood.retrofit.TestyFoodApi;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private JSONObject jsonObject;
    private ProgressDialog progressBar;
    private ViewPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

       /* FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/


        // getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        progressBar = new ProgressDialog(this);
        progressBar.setCancelable(false);
        progressBar.setMessage(getString(R.string.please_wait));
        viewPager = (ViewPager) findViewById(R.id.viewpager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        adapter = new ViewPagerAdapter(getSupportFragmentManager());
        getItems();
    }

    private void getItems(){
        if (AppCore.isNetworkAvailable(this)){
            progressBar.show();
            Call<ItemsResponse> call = TestyFoodApi.getServiceInstance().getFoodItems("1");
            call.enqueue(new Callback<ItemsResponse>() {
                @Override
                public void onResponse(Call<ItemsResponse> call, Response<ItemsResponse> response) {
                    if(response!=null){
                        Log.e("FOOD===", response.body().getStatus());
                        try {
                            jsonObject = new JSONObject();
                            for (Datum datum : response.body().getData()){
                                if (jsonObject.has(datum.getCategoryName())){
                                    JSONArray jsonArray = jsonObject.getJSONArray(datum.getCategoryName());
                                    jsonArray.put(new Gson().toJson(datum));
                                    jsonObject.put(datum.getCategoryName(), jsonArray);
                                }else{
                                    JSONArray jsonArray = new JSONArray();
                                    jsonArray.put(new Gson().toJson(datum));
                                    jsonObject.put(datum.getCategoryName(), jsonArray);
                                }
                            /* if(FoodItems.getInstance().getItems().has(datum.getCategoryName())){
                                FoodItems.getInstance().getItems().getJSONArray(datum.getCategoryName()).put(datum);
                            }else{
                                JSONArray jsonArray = new JSONArray();
                                jsonArray.put(datum);
                                FoodItems.getInstance().getItems().put(datum.getCategoryName(), jsonArray);
                            }*/
                            }
                            Log.e("FOOD===", jsonObject.length()+"");
                            progressBar.cancel();
                            setupViewPager(viewPager);
                        }catch (Exception ex){
                            progressBar.cancel();
                            Log.e("FOOD===ERROR",ex.getMessage());
                        }
                    }
                    else Log.e("FOOD==","NULL");
                }

                @Override
                public void onFailure(Call<ItemsResponse> call, Throwable t) {
                    Log.e("FOOD===ERROR==RETROFIT", t.getMessage());
                    progressBar.cancel();
                }
            });
        }else{
            Toast.makeText(this, getString(R.string.please_check_internet), Toast.LENGTH_LONG).show();
        }
    }

    private void setupViewPager(ViewPager viewPager) {
        try {
             for (Iterator<String> iterator = jsonObject.keys(); iterator.hasNext(); ) {
                ItemFragment fragment = new ItemFragment();
                Bundle bundle = new Bundle();
                bundle.putString("items", jsonObject.getString(iterator.next()));
                fragment.setArguments(bundle);
                adapter.addFragment(fragment, iterator.next());
            }
        }catch (Exception ex){}
        viewPager.setAdapter(adapter);
    }

    /*private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        *//*adapter.addFragment(new ItemFragment(), "ONE");
        adapter.addFragment(new ItemFragment(), "TWO");*//*

        try {
            ItemFragment fragment = new ItemFragment();
            Bundle bundle = new Bundle();
            bundle.putString("items", jsonObject.getString("All Day Breakfast"));
            fragment.setArguments(bundle);
            adapter.addFragment(fragment, "All Day Breakfast");
        } catch (Exception ex){}
        viewPager.setAdapter(adapter);
    }*/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_veg) {
            ItemFragment itemFragment = adapter.getCurrentFragment();
            if (itemFragment!=null){
                itemFragment.sortByVeg();
            }
            return true;
        }
        if (id == R.id.action_nv) {
            ItemFragment itemFragment = adapter.getCurrentFragment();
            if (itemFragment!=null){
                itemFragment.sortByNonVeg();
            }
            return true;
        }
        if (id == R.id.action_low) {
            ItemFragment itemFragment = adapter.getCurrentFragment();
            if (itemFragment!=null){
                itemFragment.sortByLowPrice();
            }
            return true;
        }
        if (id == R.id.action_high) {
            ItemFragment itemFragment = adapter.getCurrentFragment();
            if (itemFragment!=null){
                itemFragment.sortByHighPrice();
            }
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        private ItemFragment mCurrentFragment;

        public ItemFragment getCurrentFragment() {
            return mCurrentFragment;
        }

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }

        @Override
        public void setPrimaryItem(ViewGroup container, int position, Object object) {
            if (getCurrentFragment() != object) {
                mCurrentFragment = ((ItemFragment) object);
            }
            super.setPrimaryItem(container, position, object);
        }
    }
}
