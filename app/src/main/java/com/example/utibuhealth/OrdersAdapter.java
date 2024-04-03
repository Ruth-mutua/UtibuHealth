package com.example.utibuhealth;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class OrdersAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Order> orders;
    private String loggedInUserEmail; // Add this field to store the logged-in user's email
    private ConSQL conSQL; // Add this field to interact with the database

    private static final int VIEW_TYPE_HIVARV = 1;
    private static final int VIEW_TYPE_DIABETES = 2;
    private static final int VIEW_TYPE_HYPERTENSION = 3;

    // Constructor
    public OrdersAdapter(List<Order> orders, String loggedInUserEmail, ConSQL conSQL) {
        this.orders = orders;
        this.loggedInUserEmail = loggedInUserEmail;
        this.conSQL = conSQL;
    }

    public OrdersAdapter(List<Order> orders) {

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view;

        switch (viewType) {
            case VIEW_TYPE_HIVARV:
                view = inflater.inflate(R.layout.item_hiv_arv_order, parent, false);
                return new HIVARVOrderViewHolder(view);
            case VIEW_TYPE_DIABETES:
                view = inflater.inflate(R.layout.item_diabetes_order, parent, false);
                return new DiabetesOrderViewHolder(view);
            case VIEW_TYPE_HYPERTENSION:
                view = inflater.inflate(R.layout.item_hypertension_order, parent, false);
                return new HypertensionOrderViewHolder(view);
            default:
                throw new IllegalArgumentException("Invalid view type");
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Order order = orders.get(position); // Get the order at the current position

        // Bind the order to the appropriate view holder
        if (holder instanceof HIVARVOrderViewHolder) {
            ((HIVARVOrderViewHolder) holder).bind(order);
        } else if (holder instanceof DiabetesOrderViewHolder) {
            ((DiabetesOrderViewHolder) holder).bind(order);
        } else if (holder instanceof HypertensionOrderViewHolder) {
            ((HypertensionOrderViewHolder) holder).bind(order);
        }
    }

    @Override
    public int getItemCount() {
        return orders.size();
    }

    @Override
    public int getItemViewType(int position) {
        Order order = orders.get(position);
        // Determine the medication type and return corresponding view type
        switch (order.getMedicationType()) {
            case "HIV-ARV":
                return VIEW_TYPE_HIVARV;
            case "Diabetes":
                return VIEW_TYPE_DIABETES;
            case "Hypertension":
                return VIEW_TYPE_HYPERTENSION;
            default:
                throw new IllegalArgumentException("Invalid medication type");
        }
    }
}
