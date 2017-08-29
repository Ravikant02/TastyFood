package com.testyfood.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;
import com.testyfood.R;
import com.testyfood.models.Datum;
import com.testyfood.retrofit.ApiUrlConfig;
import com.testyfood.viewholders.RecyclerItemViewHolder;

import java.util.List;

/**
 * Created by viswas on 8/28/2017.
 */

public class RecyclerAdapter extends  RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Datum> items;
    private Context context;
    public RecyclerAdapter(Context context){
        this.context = context;
    }
    public void setData(List<Datum> items){
        this.items = items;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item, parent, false);
        return RecyclerItemViewHolder.newInstance(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        RecyclerItemViewHolder holder = (RecyclerItemViewHolder) viewHolder;
        holder.txtItemName.setText(items.get(position).getItemName());
        String price="";
        if (items.get(position).getPrice().size()>0){
            price = items.get(position).getPrice().get(0).getAmount();
        }
        holder.txtItemPrice.setText(price);
        Picasso.with(context).load(ApiUrlConfig.BASE_URL+items.get(position).getItemImageUrl()).into(holder.imgItemImage);
    }

    @Override
    public int getItemCount() {
        return items.size();
        // return mItemList == null ? 0 : mItemList.size();
    }
}
