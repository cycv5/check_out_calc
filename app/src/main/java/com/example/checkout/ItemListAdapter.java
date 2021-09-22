package com.example.checkout;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class ItemListAdapter extends ArrayAdapter<Item> {
    private static final String TAG = "ItemListAdapter";
    private Context m_context;
    int m_resource;


    public ItemListAdapter(Context context, int resource, ArrayList<Item> objects) {
        super(context, resource, objects);
        m_context = context;
        m_resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        String name = getItem(position).getName();
        double price = getItem(position).getPrice();

        Item item = new Item(name, price);

        LayoutInflater l_inflater = LayoutInflater.from(m_context);
        convertView = l_inflater.inflate(m_resource, parent, false);

        TextView t_name = (TextView) convertView.findViewById(R.id.item_name_text);
        TextView t_price = (TextView) convertView.findViewById(R.id.item_price_text);

        t_name.setText(name);
        String price_tag = "$" + Math.round(price * 100.0) / 100.0;
        t_price.setText(price_tag);

        return convertView;
    }
}
