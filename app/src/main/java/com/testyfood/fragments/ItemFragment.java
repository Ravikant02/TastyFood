package com.testyfood.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.testyfood.R;
import com.testyfood.adapter.RecyclerAdapter;
import com.testyfood.models.Datum;
import com.testyfood.models.ItemsResponse;

import org.json.JSONArray;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ItemFragment extends Fragment {
    private JSONArray jsonArray;
    private RecyclerView rvItems;
    private Context context;
    private String jsonString;
    private  List<Datum> items;

    public ItemFragment(){

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        items = new ArrayList<>();
        if (jsonString != null) {
            try{
                jsonArray = new JSONArray(jsonString);
                Log.e("FOOD===FRAG", jsonArray.toString());
            }catch (Exception ex){}
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_item, container, false);
        rvItems = (RecyclerView) view.findViewById(R.id.rvItems);

        if(jsonArray!=null) {
            initItems();
            initRecyclerView(items);
        }
        return view;
    }

    private void initItems(){
        try {
            for (int counter=0; counter<jsonArray.length(); counter++){
                Datum datum = new Gson().fromJson(jsonArray.get(counter).toString(), Datum.class);
                items.add(datum);
            }
        }catch (Exception ex){}
    }

    private void initRecyclerView(List<Datum> items){
        // Type listType = new TypeToken<List<Datum>>(){}.getType();
         // new Gson().fromJson(jsonArray.toString(), listType);

        RecyclerAdapter recyclerAdapter = new RecyclerAdapter(context);
        rvItems.setLayoutManager(new LinearLayoutManager(context));
        recyclerAdapter.setData(items);
        rvItems.setAdapter(recyclerAdapter);
        recyclerAdapter.notifyDataSetChanged();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;

        Bundle bundle = this.getArguments();
        if (bundle!=null){
            jsonString = bundle.getString("items");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    public List<Datum> getItems(){
      return items;
    }

    public void sortByVeg(){
        List<Datum> finalItems = new ArrayList<>();
        for (Datum datum : items){
            if (datum.getItemType().equals("2")){
                finalItems.add(datum);
            }
        }
        initRecyclerView(finalItems);
    }


    public void sortByNonVeg(){
        List<Datum> finalItems = new ArrayList<>();
        for (Datum datum : items){
            if (datum.getItemType().equals("1")){
                finalItems.add(datum);
            }
        }
        initRecyclerView(finalItems);
    }

    public void sortByLowPrice(){
        Collections.sort(items, new Comparator<Datum>() {
            @Override
            public int compare(Datum o1, Datum o2) {
                if (o1.getPrice().size()>0 && o2.getPrice().size()>0) {
                    double comp = Double.valueOf(o1.getPrice().get(0).getAmount()) - Double.valueOf(o2.getPrice().get(0).getAmount());
                    return (int) comp;
                }
                return  0;
            }
        });
        initRecyclerView(items);
    }

    public void sortByHighPrice(){
        Collections.sort(items, new Comparator<Datum>() {
            @Override
            public int compare(Datum o1, Datum o2) {
                if (o1.getPrice().size()>0 && o2.getPrice().size()>0) {
                    double comp = Double.valueOf(o1.getPrice().get(0).getAmount()) - Double.valueOf(o2.getPrice().get(0).getAmount());
                    return (int) comp;
                }
                return  0;
            }
        });
        List<Datum> finalItems = items;
        Collections.reverse(finalItems);
        initRecyclerView(finalItems);
    }
}
