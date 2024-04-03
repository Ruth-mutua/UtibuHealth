package com.example.utibuhealth;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class HIVARVOrderViewHolder extends RecyclerView.ViewHolder {
    TextView itemNameTextView;
    TextView customerEmailTextView;
    TextView quantityOrderedTextView;
    TextView priceTextView;
    TextView statusTextView;

    public HIVARVOrderViewHolder(@NonNull View itemView) {
        super(itemView);
        itemNameTextView = itemView.findViewById(R.id.textHIVItemName);
        customerEmailTextView = itemView.findViewById(R.id.textHIVCustomerEmail);
        quantityOrderedTextView = itemView.findViewById(R.id.textHIVQuantityOrdered);
        priceTextView = itemView.findViewById(R.id.textHIVPrice);
        statusTextView = itemView.findViewById(R.id.textHIVStatus);
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
