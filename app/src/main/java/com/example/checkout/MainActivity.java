package com.example.checkout;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private Button button_cart;
    private Button button_add;
    private EditText item_txt;
    private EditText item_price;
    private EditText item_quant;
    private EditText item_discount;

    static ArrayList<Item> items = new ArrayList<>();
    static ItemListAdapter adapter;
    Toast t;
    //static ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button_cart = (Button)findViewById(R.id.btn_cart);
        button_add = (Button)findViewById(R.id.btn_add);
        item_txt = (EditText)findViewById(R.id.item_name);
        item_price = (EditText)findViewById(R.id.price);
        item_quant = (EditText)findViewById(R.id.quantity);
        item_discount = (EditText)findViewById(R.id.discount);

        button_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                openCart();
            }
        });

        adapter = new ItemListAdapter(this, R.layout.adapter_view_layout, items);

        button_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String entered_name = item_txt.getText().toString();
                String entered_price = item_price.getText().toString();
                String entered_quant = item_quant.getText().toString();
                String entered_discount = item_discount.getText().toString();
                double discount_in_double;
                double quant_in_double;

                if (entered_discount == null || entered_discount.length() == 0) {
                    discount_in_double = 0;
                }
                else{
                    discount_in_double = Double.parseDouble(entered_discount);
                    if (discount_in_double > 100 || discount_in_double < 0){
                        makeToast("Discount cannot be larger than 100% or smaller than 0%");
                        return;
                    }
                }
                if (entered_quant == null || entered_quant.length() == 0){
                    quant_in_double = 1;
                }
                else{
                    quant_in_double = Double.parseDouble(entered_quant);
                }

                if (entered_name == null || entered_name.length() == 0 || entered_price == null || entered_price.length() == 0){
                    makeToast("Please entered a valid item name and price");
                }
                else if (quant_in_double < 0){
                    makeToast("Quantity cannot be less than 0");
                }
                else {
                    double price_in_double = Double.parseDouble(entered_price);
                    double final_price = price_in_double * quant_in_double * ((100 - discount_in_double) / 100);
                    // double final_price_rounded = Math.round(final_price * 100.0) / 100.0;
                    Item new_item = new Item(entered_name, final_price);
                    items.add(new_item);
                    adapter.notifyDataSetChanged();
                    item_txt.getText().clear();
                    item_price.getText().clear();
                    item_quant.getText().clear();
                    item_discount.getText().clear();
                    makeToast("Item added");
                }
            }
        });

    }

    public void openCart() {
        Intent intent = new Intent(this, Cart.class);
        startActivity(intent);
    }

    private void makeToast(String s){
        if (t != null) t.cancel();
        t = Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT);
        t.show();
    }
}