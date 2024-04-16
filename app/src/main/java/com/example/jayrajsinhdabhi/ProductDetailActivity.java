package com.example.jayrajsinhdabhi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;

public class ProductDetailActivity extends AppCompatActivity {
    ImageButton imageButton;
    private int quantity = 1;
    private double unitPrice;
    ImageButton cart;
    Button goToCart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);


        cart = findViewById(R.id.cart);
        imageButton = findViewById(R.id.logout);
        goToCart = findViewById(R.id.goToCart);

        // Get data from intent
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        double price = intent.getDoubleExtra("price", 0.0);
        String description = intent.getStringExtra("fulldescription");
        String imageUrl = intent.getStringExtra("imageUrl");
        unitPrice = intent.getDoubleExtra("price", 0.0);

        // Set data to views
        TextView nameTextView = findViewById(R.id.product_name);
        TextView priceTextView = findViewById(R.id.product_price);
        TextView descriptionTextView = findViewById(R.id.product_desc);
        ImageView imageView = findViewById(R.id.product_img);

        nameTextView.setText(name);
        priceTextView.setText(String.valueOf(price));
        descriptionTextView.setText(description);
        Glide.with(this).load(imageUrl).placeholder(R.drawable.infinite).error(R.drawable.infinite).into(imageView);

        updateTotalPrice();

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getApplicationContext(), Home.class);
                startActivity(intent);
                finish();
            }
        });

        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CartActivity.class);
                startActivity(intent);
            }
        });

        goToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CartActivity.class);
                startActivity(intent);
            }
        });
    }
    public void decreaseQuantity(View view) {
        if (quantity > 1) {
            quantity--;
            updateTotalPrice();
            updateQuantityTextView();
        }
    }

    // Method to increase quantity when "+" button is clicked
    public void increaseQuantity(View view) {
        quantity++;
        updateTotalPrice();
        updateQuantityTextView();
    }

    // Method to update the total price based on the quantity
    private void updateTotalPrice() {
        double totalPrice = unitPrice * quantity;
        TextView totalPriceTextView = findViewById(R.id.tv_total_price);
        totalPriceTextView.setText(String.format("Total Price: $%.2f", totalPrice));
    }

    // Method to update the quantity TextView
    private void updateQuantityTextView() {
        TextView quantityTextView = findViewById(R.id.tv_quantity);
        quantityTextView.setText(String.valueOf(quantity));
    }
}