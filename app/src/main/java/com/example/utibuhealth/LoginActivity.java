package com.example.utibuhealth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText email, password;
    Button login;
    TextView noAccount, forgotPass;

    ConSQL conSQL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email =(EditText) findViewById(R.id.inputEmail);
        password=(EditText) findViewById(R.id.inputPassword);
        login=(Button)findViewById(R.id.btn_login);
        noAccount=(TextView) findViewById(R.id.textView3);
        forgotPass=(TextView)findViewById(R.id.forgotPass);

        conSQL = new ConSQL();


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String mail= email.getText().toString();
                String pass= password.getText().toString();

                if (mail.equals("") || pass.equals("")) {
                    Toast.makeText(LoginActivity.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                } else {
                    boolean isAuthenticated = authenticateUser(mail, pass);
                    if (isAuthenticated) {
                        Toast.makeText(LoginActivity.this, "Sign in successful", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(LoginActivity.this, "Invalid credentials", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });


        noAccount.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v){
                startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
            }

        });

        forgotPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, ChangePswdActivity.class);
                startActivity(intent);

            }
        });
    }

    private boolean authenticateUser(String email, String password) {
        // Call method from ConSQL class to authenticate user
        return conSQL.authenticateUser(email, password);
    }
}