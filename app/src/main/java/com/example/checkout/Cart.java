package com.example.checkout;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


public class Cart extends AppCompatActivity {

    public ListView listView;
    private TextView subtotal_p;
    private TextView total_p;
    private EditText tax_p;
    public static double tax;
    public static double default_tax = 13;
    public static double sub_sum = 0;

    Toast t;
    ItemListAdapter adapter = MainActivity.getInstanceActivity().adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        Button button_back = findViewById(R.id.btn_back);
        Button button_save = findViewById(R.id.btn_save);
        subtotal_p = findViewById(R.id.subtotal_price);
        total_p = findViewById(R.id.total_price);
        tax_p = findViewById(R.id.tax_amt);

        tax_p.setHint(String.valueOf(default_tax));
        tax = default_tax;

        tax_p.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String tax_str = tax_p.getText().toString();
                if (tax_str.length() == 0){
                    tax = default_tax;
                }
                else{
                    tax = Double.parseDouble(tax_str);
                }
                String[] totals = update_total();
                setTotalTxt(totals[0], totals[1]);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        listView = findViewById(R.id.listview);

        listView.setAdapter(adapter);

        listView.setOnItemLongClickListener((parent, view, i, l) -> {
            removeItem(i);
            String[] totals = update_total();
            setTotalTxt(totals[0], totals[1]);
            makeToast("Item removed");
            return false;
        });

        button_back.setOnClickListener(v -> openMain());

        button_save.setOnClickListener(v -> {
            if (tax_p.getText().toString().length() == 0){
                makeToast("Nothing to be saved");
            }
            else{
                default_tax = Double.parseDouble(tax_p.getText().toString());
                tax_p.setHint(String.valueOf(default_tax));
                makeToast("New default tax rate saved");
            }

        });

        String[] totals = update_total();
        setTotalTxt(totals[0], totals[1]);
    }

    private void makeToast(String s){
        if (t != null) t.cancel();
        t = Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT);
        t.show();
    }

    public void removeItem(int index){
        MainActivity.items.remove(index);
        adapter.notifyDataSetChanged();
    }

    public void openMain(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public static String[] update_total(){
        sub_sum = 0;
        for (int i = 0; i < MainActivity.items.size(); i++){
            sub_sum += MainActivity.items.get(i).getPrice();
        }
        double sum = Math.round(sub_sum * (1 + tax/100) * 100.0) / 100.0;

        String subtotal_txt = "$" + Math.round(sub_sum * 100.0) / 100.0;
        String total_txt = "$" + sum;

        String[] totals = new String[2];
        totals[0] = subtotal_txt;
        totals[1] = total_txt;

        return totals;
    }

    public void setTotalTxt(String sub, String ttl){
        subtotal_p.setText(sub);
        total_p.setText(ttl);
    }

}