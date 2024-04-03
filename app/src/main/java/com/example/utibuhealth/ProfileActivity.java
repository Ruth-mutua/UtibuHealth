package com.example.utibuhealth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ProfileActivity extends AppCompatActivity {

    TextView name, email;
    ImageView btnLogout, profilePic;
    Button changePass;
    ConSQL conSQL;


    @Override
    protected void onCreate(Bundle savedInstaceState) {
        super.onCreate(savedInstaceState);
        setContentView(R.layout.activity_profile);
        btnLogout = (ImageView) findViewById(R.id.btnLogout);
        profilePic = (ImageView) findViewById(R.id.profilePic);
        changePass = (Button) findViewById(R.id.btn_changePass);
        name = (TextView) findViewById(R.id.name);
        email = (TextView) findViewById(R.id.email);

        conSQL = new ConSQL();


        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProfileActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        // Fetch user information from the database and set it to the respective TextViews
        // Replace the following lines with actual database calls
        String userEmail = "user@example.com"; // Replace with the actual email of the logged-in user
        UserInfo userInfo = conSQL.getUserInfoByEmail(userEmail);

        // Check if userInfo is not null (i.e., user exists)
        if (userInfo != null) {
            name.setText(userInfo.getName()); // Replace with the actual method to get the user's name
            email.setText(userInfo.getEmail()); // Replace with the actual method to get the user's email
        } else {
            // Handle the case where the user does not exist
            name.setText("User not found");
            email.setText("Email not found");
        }

        // Set an onClickListener for the change password button
        changePass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Add code to handle password change
                Intent changePassIntent = new Intent(ProfileActivity.this, ChangePswdActivity.class);
                startActivity(changePassIntent);
            }
        });
    }
}