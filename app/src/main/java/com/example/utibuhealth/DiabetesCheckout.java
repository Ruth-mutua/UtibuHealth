package com.example.utibuhealth;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class DiabetesCheckout extends AppCompatActivity {

    EditText etEmail, etQuantity, etPrice;
    Button btnMakeOrder;
    TextView tvItemName; // Add TextView reference for item name
    ConSQL conSql; // Instance variable for ConSQL

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diabetes_checkout);

        // Initialize ConSQL instance
        conSql = new ConSQL();

        etEmail = findViewById(R.id.etEmail);
        etQuantity = findViewById(R.id.etQuantity);
        etPrice = findViewById(R.id.price);
        btnMakeOrder = findViewById(R.id.btnMakeOrder);
        tvItemName = findViewById(R.id.textView3); // Initialize TextView reference

        btnMakeOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get input values
                String itemName = tvItemName.getText().toString(); // Retrieve item name from TextView
                String emailAddress = etEmail.getText().toString().trim();
                int quantity = Integer.parseInt(etQuantity.getText().toString());
                double price = Double.parseDouble(etPrice.getText().toString());

                // Perform quantity comparison
                boolean isApproved = performQuantityComparison(itemName, quantity);

                if (isApproved) {
                    // Quantity is within limits, approve the order
                    String status = "Approved";
                    makeOrder(itemName, emailAddress, quantity, price, status);
                    Toast.makeText(DiabetesCheckout.this, "Order placed successfully", Toast.LENGTH_SHORT).show();
                } else {
                    // Quantity exceeds limits, decline the order
                    String status = "Declined";
                    makeOrder(itemName, emailAddress, quantity, price, status);
                    Toast.makeText(DiabetesCheckout.this, "Quantity exceeds limits, order declined", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private boolean performQuantityComparison(String itemName, int quantity) {
        // Implement logic to compare quantity with the stock table in the database
        // Query the database to get the available quantity for the given item name
        // Compare the available quantity with the requested quantity
        // Return true if requested quantity is within limits, false otherwise
        int availableQuantity = conSql.getAvailableQuantityFromDatabase(itemName);
        return quantity <= availableQuantity;
    }

    private void makeOrder(String itemName, String emailAddress, int quantity, double price, String status) {
        // Implement logic to insert the order into the orders table in the database
        // For demonstration, you can print the order details
        Order order = new Order(itemName, emailAddress, quantity, price, status);
        System.out.println("Order Details: " + order.toString());
        // Update the orders table in the database with the order details
        // You need to implement the actual database update operation using ConSQL class
        conSql.insertOrderData(itemName, emailAddress, quantity, price, status);
    }
}