package com.example.juanbee;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

public class ForgotPasswordActivity extends AppCompatActivity {

    EditText pinInput, newPassword, confirmNewPassword;
    Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        pinInput = findViewById(R.id.securityPin);
        newPassword = findViewById(R.id.newPassword);
        confirmNewPassword = findViewById(R.id.confirmPassword);
        saveButton = findViewById(R.id.savePasswordButton);

        SharedPreferences sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE);
        String savedPin = sharedPreferences.getString("pin", "");

        saveButton.setOnClickListener(v -> {
            String enteredPin = pinInput.getText().toString().trim();
            String newPass = newPassword.getText().toString().trim();
            String confirmPass = confirmNewPassword.getText().toString().trim();

            if (enteredPin.isEmpty() || newPass.isEmpty() || confirmPass.isEmpty()) {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            if (!enteredPin.equals(savedPin)) {
                Toast.makeText(this, "Incorrect Security PIN!", Toast.LENGTH_SHORT).show();
                return;
            }

            if (!newPass.equals(confirmPass)) {
                Toast.makeText(this, "Passwords do not match!", Toast.LENGTH_SHORT).show();
                return;
            }

            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("password", newPass);
            editor.apply();

            Toast.makeText(this, "Password updated successfully!", Toast.LENGTH_SHORT).show();

            startActivity(new Intent(ForgotPasswordActivity.this, HomeActivity.class));
            finish();
        });
    }
}
