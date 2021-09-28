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

        MainActivity.items.add(new Item("food", 20.99));
        MainActivity.items.add(new Item("games", 12));

        Cart.update_total();
        assert (Cart.sub_sum == 20.99+12);
    }
}