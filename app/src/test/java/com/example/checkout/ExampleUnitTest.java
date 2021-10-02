package com.example.checkout;

import org.junit.Test;


import static org.junit.Assert.*;

/**
 * Local unit test, which will execute on the development machine (host).
 *
 */
public class ExampleUnitTest {
    @Test
    public void subtotal_isCorrect() {
        /* Check if the subtotal is correctly calculated*/

        MainActivity.items.add(new Item("food", 20.99, 2, 0));
        MainActivity.items.add(new Item("games", 10, 1, 10));

        Cart.update_total();
        assert (Cart.sub_sum == 20.99 * 2 + 10 * 0.9);
    }
}