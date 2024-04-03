package com.example.utibuhealth;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class DiabetesOrderViewHolder extends RecyclerView.ViewHolder {
    TextView itemNameTextView;
    TextView customerEmailTextView;
    TextView quantityOrderedTextView;
    TextView priceTextView;
    TextView statusTextView;

    public DiabetesOrderViewHolder(@NonNull View itemView) {
        super(itemView);
        itemNameTextView = itemView.findViewById(R.id.textItemName);
        customerEmailTextView = itemView.findViewById(R.id.textCustomerEmail);
        quantityOrderedTextView = itemView.findViewById(R.id.textQuantityOrdered);
        priceTextView = itemView.findViewById(R.id.textPrice);
        statusTextView = itemView.findViewById(R.id.textStatus);
    }

    public void bind(Order order) {
        // Bind data to views
        itemNameTextView.setText(order.getItemName());
        customerEmailTextView.setText(order.getCustomerEmail());
        quantityOrderedTextView.setText(String.valueOf(order.getQuantityOrdered()));
        priceTextView.setText(String.valueOf(order.getPrice()));
        statusTextView.setText(order.getStatus());
    }
}
