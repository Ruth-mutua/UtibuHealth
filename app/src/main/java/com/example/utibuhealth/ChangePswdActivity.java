package com.example.utibuhealth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class ChangePswdActivity extends AppCompatActivity {

    EditText oldPassword, newPassword, inputEmail;
    Button btnChangePassword;
    ConSQL conSQL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_pswd);


        inputEmail = findViewById(R.id.inputEmail);
        oldPassword = findViewById(R.id.oldPassword);
        newPassword = findViewById(R.id.newPassword);
        btnChangePassword = findViewById(R.id.btnChangePassword);
        conSQL = new ConSQL();


        btnChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changePassword();
            }
        });
    }

    private void changePassword() {
        String oldPasswordStr = oldPassword.getText().toString();
        String newPasswordStr = newPassword.getText().toString();
        String inputEmailStr = inputEmail.getText().toString();

        if (oldPasswordStr.isEmpty() || newPasswordStr.isEmpty() || inputEmailStr.isEmpty()) {
            Toast.makeText(this, "All fields must be filled", Toast.LENGTH_SHORT).show();
        } else {
            // Authenticate the user
            boolean isAuthenticated = conSQL.authenticateUser(inputEmailStr, oldPasswordStr);
            if (isAuthenticated) {
                // Update the password in the database
                boolean success = conSQL.updatePassword(inputEmailStr, newPasswordStr);
                if (success) {
                    Toast.makeText(this, "Password changed successfully", Toast.LENGTH_SHORT).show();
                    finish(); // Finish the activity or navigate to another screen
                } else {
                    Toast.makeText(this, "Failed to change password", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "Old password is incorrect", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
