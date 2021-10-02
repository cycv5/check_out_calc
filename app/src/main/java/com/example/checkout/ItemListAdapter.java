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

public class ItemListAdapter extends ArrayAdapter<Item> {
    private final Context m_context;
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
        double quant = getItem(position).getQuant();
        double discount = getItem(position).getDiscount();


        Item item = new Item(name, price, quant, discount);

        LayoutInflater l_inflater = LayoutInflater.from(m_context);
        convertView = l_inflater.inflate(m_resource, parent, false);

        TextView t_name = convertView.findViewById(R.id.item_name_text);
        TextView t_price = convertView.findViewById(R.id.item_price_text);

        String name_field;
        int rounded_quant;
        if (quant % 1 == 0){
            rounded_quant = (int)Math.round(quant);
            if (discount == 0){
                name_field = name + " (x" + rounded_quant + ")";
            }
            else{
                name_field = name + " (x" + rounded_quant + ", " + discount + "% off)";
            }
        }
        else {
            if (discount == 0){
                name_field = name + " (x" + quant + ")";
            }
            else{
                name_field = name + " (x" + quant + ", " + discount + "% off)";
            }
        }


        t_name.setText(name_field);
        String price_tag = "$" + Math.round(item.getFinalPrice() * 100.0) / 100.0;
        t_price.setText(price_tag);

        return convertView;
    }
}
