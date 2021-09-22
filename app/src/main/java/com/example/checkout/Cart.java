package com.example.checkout;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Cart extends AppCompatActivity {

    public ListView listView;
    private Button button_back;
    private Button button_save;
    private TextView subtotal_p;
    private TextView total_p;
    private EditText tax_p;
    public static double tax;
    public static double default_tax = 13;
    Toast t;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        button_back = (Button) findViewById(R.id.btn_back);
        button_save = (Button) findViewById(R.id.btn_save);
        subtotal_p = (TextView) findViewById(R.id.subtotal_price);
        total_p = (TextView) findViewById(R.id.total_price);
        tax_p = (EditText) findViewById(R.id.tax_amt);

        tax_p.setHint(String.valueOf(default_tax));
        tax = default_tax;

        tax_p.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String tax_str = tax_p.getText().toString();
                if (tax_str == null || tax_str.length() == 0){
                    tax = default_tax;
                }
                else{
                    tax = Double.parseDouble(tax_str);
                }
                update_total();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        listView = (ListView) findViewById(R.id.listview);

        listView.setAdapter(MainActivity.adapter);

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int i, long l) {
                removeItem(i);
                update_total();
                makeToast("Item removed");
                return false;
            }
        });

        button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                openMain();
            }
        });

        button_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tax_p.getText().toString() == null || tax_p.getText().toString().length() == 0){
                    makeToast("Nothing to be saved");
                }
                else{
                    default_tax = Double.parseDouble(tax_p.getText().toString());
                    tax_p.setHint(String.valueOf(default_tax));
                    makeToast("New default tax rate saved");
                }

            }
        });

        update_total();
    }

    private void makeToast(String s){
        if (t != null) t.cancel();
        t = Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT);
        t.show();
    }

    public void removeItem(int index){
        MainActivity.items.remove(index);
        MainActivity.adapter.notifyDataSetChanged();
    }

    public void openMain(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void update_total(){
        double sub_sum = 0;
        for (int i = 0; i < MainActivity.items.size(); i++){
            sub_sum += MainActivity.items.get(i).getPrice();
        }
        double sum = Math.round(sub_sum * (1 + tax/100) * 100.0) / 100.0;

        subtotal_p.setText("$" + String.valueOf(Math.round(sub_sum * 100.0) / 100.0));
        total_p.setText("$" + String.valueOf(sum));
    }
}