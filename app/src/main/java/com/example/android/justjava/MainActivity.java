package com.example.android.justjava;

import java.text.NumberFormat;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    int quantity = 2;
    boolean hasWhippedCream;
    boolean hasChocolate;
    String customer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        CheckBox whippedCream = (CheckBox) findViewById(R.id.whipped_cream_checkbox);
        hasWhippedCream = whippedCream.isChecked();
        CheckBox chocolate = (CheckBox) findViewById(R.id.chocolate_checkbox);
        hasChocolate = chocolate.isChecked();
        EditText customerName = (EditText) findViewById(R.id.name_edit_text);
        customer = customerName.getText().toString();
        displayMessage(createOrderSummary());
    }

    /**
     * Creates an order summary
     *
     *@return text summary
     */
    private String createOrderSummary() {
        String priceMessage = " Name: " + customer;
        priceMessage += "\n Add whipped cream? " + hasWhippedCream;
        priceMessage += "\n Add chocolate? " + hasChocolate;
        priceMessage += "\n Qunatity: " + quantity;
        priceMessage += "\n Total: $" + calculatePrice();
        priceMessage += "\n Thank You!";
        displayMessage(priceMessage);
        return priceMessage;
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText(""+ number);
    }

    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }

    /**
     * This method is called when the plus button is clicked.
     */
    public void increment(View view) {
        if(quantity < 100) {
            quantity = quantity + 1;
            displayQuantity(quantity);
        } else {
            Toast.makeText(MainActivity.this, "Can't buy more than 100 cups",
                    Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * This method is called when the minus button is clicked.
     */
    public void decrement(View view) {
        if (quantity > 0) {
            quantity = quantity - 1;
            displayQuantity(quantity);
        } else {
            Toast.makeText(MainActivity.this, " Can't buy less than 1 cup",
                    Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Calculates the price of the order.
     *
     *@return total price of order
     */
    private int calculatePrice() {
        if (hasWhippedCream == true && hasChocolate == true) {
            return quantity * 8;
        } else if(hasWhippedCream == true && hasChocolate == false){
            return quantity * 6;
        } else if (hasWhippedCream == false && hasChocolate == true) {
            return quantity * 7;
        } else {
            return quantity * 5;
        }
    }
}