package io.marijmokoginta.e_commerce.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import io.marijmokoginta.e_commerce.R;
import io.marijmokoginta.e_commerce.model.Product;

public class CustomAdapter extends BaseAdapter {
    LayoutInflater inflater;
    Context context;
    ArrayList<Product> products;

    TextView productName, productDescription, productPrice;

    public CustomAdapter (Context context, ArrayList<Product> products){
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.products = products;
    }

    @Override
    public int getCount() {
        return products.size();
    }

    @Override
    public Object getItem(int i) {
        return products.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        @SuppressLint("ViewHolder")
        View v = inflater.inflate(R.layout.product_list_view, viewGroup, false);

        productName = v.findViewById(R.id.product_name);
        productDescription = v.findViewById(R.id.product_description);
        productPrice = v.findViewById(R.id.product_price);

        productName.setText(products.get(i).getName());
        productDescription.setText(products.get(i).getDescription());
        productPrice.setText(String.valueOf(products.get(i).getPrice()));

        return v;
    }
}
