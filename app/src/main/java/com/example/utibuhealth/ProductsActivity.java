package com.example.utibuhealth;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ProductsActivity extends AppCompatActivity {

    CardView cardHIV;
    CardView cardDiabetes;
    CardView cardHypertension;
    CardView cardLogout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);

        cardHIV= findViewById(R.id.cardHIV);
        cardDiabetes= findViewById(R.id.cardDiabetes);
        cardHypertension= findViewById(R.id.cardHypertension);
        cardLogout=findViewById(R.id.cardLogout);


        cardHIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ProductsActivity.this, HIVCheckout.class));
            }
        });
        cardDiabetes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ProductsActivity.this, DiabetesCheckout.class));
            }
        });
        cardHypertension.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ProductsActivity.this, HyperCheckout.class));
            }
        });
        cardLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ProductsActivity.this, LoginActivity.class));
            }
        });
    }
}