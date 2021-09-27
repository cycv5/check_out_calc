package com.example.checkout;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void subtotal_isCorrect() {
        /* Check if the subtotal is correctly calculated*/
        MainActivity.items.add(new Item("food", 10));
        MainActivity.items.add(new Item("game", 20.99));
        double subtotal_sum = 0;
        for(int i = 0; i < MainActivity.items.size(); i++){
            subtotal_sum += MainActivity.items.get(i).getPrice();
        }
        subtotal_sum = Math.round(subtotal_sum * 100.0) / 100.0;
        assert(subtotal_sum == Cart.sub_sum);
    }
}