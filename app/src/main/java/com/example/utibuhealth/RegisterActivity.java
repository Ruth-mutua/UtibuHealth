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

public class RegisterActivity extends AppCompatActivity {

    EditText username, creditCardNo, email, password, repassword;
    Button register;
    TextView signin;

    ConSQL conSQL;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        conSQL = new ConSQL();

        username =(EditText) findViewById(R.id.inputUsername);
        email =(EditText) findViewById(R.id.inputEmail);
        password =(EditText) findViewById(R.id.inputPassword);
        repassword = (EditText) findViewById(R.id.repassword);
        register =(Button) findViewById(R.id.btnRegister);
        signin= (TextView) findViewById(R.id.textViewSignup);
        email =(EditText) findViewById(R.id.inputEmail);


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username.getText().toString();
                String mail = email.getText().toString();
                String pass = password.getText().toString();
                String repass= repassword.getText().toString();

                if (user.equals("") || mail.equals("")||  pass.equals("")|| repass.equals(""))
                    Toast.makeText(RegisterActivity.this,"Please enter all the fields", Toast.LENGTH_SHORT).show();
                else{
                    if (pass.equals(repass)){
                        // Assuming you have a method in ConSQL to insert user data
                        boolean insertSuccessful = conSQL.insertUserData(user, mail, pass);
                        if(insertSuccessful){
                            Toast.makeText(RegisterActivity.this, "Registered successfully", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(RegisterActivity.this, "Registration failed", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(RegisterActivity.this, "Password not matching", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        TextView btn = findViewById(R.id.textViewSignup);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            }
        });
    }
}