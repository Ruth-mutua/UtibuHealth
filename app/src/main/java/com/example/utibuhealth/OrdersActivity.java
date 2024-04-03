package com.example.utibuhealth;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class OrdersActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private OrdersAdapter adapter;
    private List<Order> orders;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Fetch orders for the logged-in user from the database
        // Pass the logged-in user's email address to the method
        ConSQL conSql = new ConSQL();
        String loggedInUserEmail = conSql.getLoggedInUserEmail(); // Assuming this method retrieves the logged-in user's email from the database
        orders = fetchOrdersFromDatabase(loggedInUserEmail);

        // Create and set adapter
        adapter = new OrdersAdapter(orders);
        recyclerView.setAdapter(adapter);
    }


    private List<Order> fetchOrdersFromDatabase(String emailAddress) {
        // Initialize list to store orders
        List<Order> orders = new ArrayList<>();

        // Instantiate ConSQL
        ConSQL conSql = new ConSQL();

        // Fetch orders for the logged-in user from the database
        // Here, you should implement the logic to query the database
        // to retrieve orders for the given email address
        // For demonstration, let's assume the method name in ConSQL is getOrdersByEmail
        orders = conSql.getOrdersByEmail(emailAddress);

        // Return the fetched orders
        return orders;
    }

}
