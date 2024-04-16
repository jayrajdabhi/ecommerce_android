package com.example.jayrajsinhdabhi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class CheckoutActivity extends AppCompatActivity {
    Button placeAnOrder;
    TextInputEditText firstNameEditText, lastNameEditText, phoneEditText, emailEditText, addressEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        firstNameEditText = findViewById(R.id.firstNameEditText);
        lastNameEditText = findViewById(R.id.lastNameEditText);
        phoneEditText = findViewById(R.id.phoneEditText);
        emailEditText = findViewById(R.id.emailEditText);
        addressEditText = findViewById(R.id.addressEditText);
        placeAnOrder = findViewById(R.id.placeOrderButton);

        placeAnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateForm()) {
                    // If form is valid, proceed to ThankYou activity
                    Intent intent = new Intent(getApplicationContext(), ThankYou.class);
                    startActivity(intent);
                }
            }
        });
    }

    // Method to validate the form
    private boolean validateForm() {
        boolean isValid = true;

        String firstName = firstNameEditText.getText().toString().trim();
        String lastName = lastNameEditText.getText().toString().trim();
        String phone = phoneEditText.getText().toString().trim();
        String email = emailEditText.getText().toString().trim();
        String address = addressEditText.getText().toString().trim();

        if (TextUtils.isEmpty(firstName)) {
            firstNameEditText.setError("Please enter your first name");
            isValid = false;
        }

        if (TextUtils.isEmpty(lastName)) {
            lastNameEditText.setError("Please enter your last name");
            isValid = false;
        }

        if (TextUtils.isEmpty(phone)) {
            phoneEditText.setError("Please enter your phone number");
            isValid = false;
        }

        if (!Patterns.PHONE.matcher(phone).matches()) {
            phoneEditText.setError("Please enter a valid phone number");
            isValid = false;
        }

        if (TextUtils.isEmpty(email)) {
            emailEditText.setError("Please enter your email");
            isValid = false;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailEditText.setError("Please enter a valid email address");
            isValid = false;
        }

        if (TextUtils.isEmpty(address)) {
            addressEditText.setError("Please enter your address");
            isValid = false;
        }

        return isValid;
    }
}
