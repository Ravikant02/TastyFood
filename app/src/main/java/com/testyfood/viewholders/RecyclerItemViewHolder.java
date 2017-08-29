package com.testyfood.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.testyfood.R;

/**
 * Created by viswas on 8/28/2017.
 */

public class RecyclerItemViewHolder extends RecyclerView.ViewHolder  {
    public TextView txtItemName;
    public TextView txtItemPrice;
    public ImageView imgItemImage;

    public RecyclerItemViewHolder(final View parent, TextView itemTextView, ImageView imageView,
                                  TextView itemPrice) {
        super(parent);
        txtItemName = itemTextView;
        imgItemImage = imageView;
        txtItemPrice = itemPrice;
    }

    public static RecyclerItemViewHolder newInstance(View parent) {
        TextView txtItemName = (TextView) parent.findViewById(R.id.txtItemName);
        TextView txtItemPrice = (TextView) parent.findViewById(R.id.txtItemPrice);
        ImageView imageView = (ImageView) parent.findViewById(R.id.imgItemImage);
        return new RecyclerItemViewHolder(parent, txtItemName, imageView, txtItemPrice);
    }

    public void setItemText(CharSequence text) {
        txtItemName.setText(text);
    }
}
