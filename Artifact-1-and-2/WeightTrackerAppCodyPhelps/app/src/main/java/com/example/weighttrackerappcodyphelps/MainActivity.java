package com.example.weighttrackerappcodyphelps;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    EditText username, password;
    List<String> restrictedNames = Arrays.asList(
            "admin", "root", "mod",
            "DROP", "DELETE", "SELECT", "INSERT",
            "UPDATE", "EXEC", "--", "' OR '1'='1",
            "'; DROP TABLE users; --"
    );



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button loginBtn = findViewById(R.id.btnLogin);
        Button createAcct = findViewById(R.id.btnCreateAccount);

        loginBtn.setOnClickListener(this);
        createAcct.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        DBHelper myDB = new DBHelper(MainActivity.this);
        username = findViewById(R.id.etUsername);
        password = findViewById(R.id.etPassword);
        if (v.getId() == R.id.btnLogin) {

            String inputUsername = username.getText().toString().trim();
            String inputPassword = password.getText().toString().trim();

            boolean isRestrictedUsername = restrictedUserOrPass(inputUsername, restrictedNames);
            boolean isRestrictedPassword = restrictedUserOrPass(inputPassword, restrictedNames);


            if(isRestrictedUsername || isRestrictedPassword){
                Toast.makeText(this, "Restricted Username or Password usd", Toast.LENGTH_SHORT).show();
            } else {
                // Handle login button click
                if (myDB.checkUser(inputUsername, inputPassword)) {

                    // used to keep session information such as the user that is logged in
                    SharedPreferences prefs = getSharedPreferences("LoginPrefs", MODE_PRIVATE);
                    SharedPreferences.Editor editor = prefs.edit();
                    editor.putString("loggedInUser", inputUsername);
                    editor.apply();

                    Intent loginIntent = new Intent(MainActivity.this, MainMenu.class);
                    startActivity(loginIntent);
                } else {
                    Toast.makeText(MainActivity.this, "Invalid Username or Password", Toast.LENGTH_SHORT).show();
                }
            }

        } else if (v.getId() == R.id.btnCreateAccount) {
            Intent createAccountIntent = new Intent(MainActivity.this, create_account.class);
            startActivity(createAccountIntent);
        }
    }

    public static boolean restrictedUserOrPass(String usernameOrPass, List<String> values) {
        if (usernameOrPass == null || values == null || values.isEmpty()) {
            return false;
        }

        for (String value: values) {
            if (usernameOrPass.contains(value)) {
                return true;
            }
        }
        return false;
    }
}