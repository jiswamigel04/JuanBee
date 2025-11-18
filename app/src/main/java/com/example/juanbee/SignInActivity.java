package com.example.juanbee;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

public class SignInActivity extends AppCompatActivity {

    EditText fullName, email, password, confirmPassword, securityPin;
    Button signUpButton;
    TextView backToLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        fullName = findViewById(R.id.fullName);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        confirmPassword = findViewById(R.id.confirmPassword);
        securityPin = findViewById(R.id.securityPin);
        signUpButton = findViewById(R.id.signUpButton);
        backToLogin = findViewById(R.id.backToLogin);

        SharedPreferences sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE);

        // Go back to login page
        backToLogin.setOnClickListener(v -> {
            Intent intent = new Intent(SignInActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        });

        // Sign up button logic
        signUpButton.setOnClickListener(v -> {
            String name = fullName.getText().toString().trim();
            String userEmail = email.getText().toString().trim();
            String userPass = password.getText().toString().trim();
            String confirmPass = confirmPassword.getText().toString().trim();
            String pin = securityPin.getText().toString().trim();

            // Validate fields
            if (name.isEmpty() || userEmail.isEmpty() || userPass.isEmpty() || confirmPass.isEmpty() || pin.isEmpty()) {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            if (!userPass.equals(confirmPass)) {
                Toast.makeText(this, "Passwords do not match!", Toast.LENGTH_SHORT).show();
                return;
            }

            // Save data in SharedPreferences
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("name", name);
            editor.putString("email", userEmail);
            editor.putString("password", userPass);
            editor.putString("pin", pin);
            editor.apply();

            Toast.makeText(this, "Hive Created Successfully! 🐝", Toast.LENGTH_SHORT).show();

            // Redirect to Home
            Intent intent = new Intent(SignInActivity.this, HomeActivity.class);
            startActivity(intent);
            finish();
        });
    }
}
