package com.example.jayrajsinhdabhi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class ProductDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);



        // Get data from intent
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        double price = intent.getDoubleExtra("price", 0.0);
        String description = intent.getStringExtra("description");
        String imageUrl = intent.getStringExtra("imageUrl");

        // Set data to views
        TextView nameTextView = findViewById(R.id.product_name);
        TextView priceTextView = findViewById(R.id.product_price);
        TextView descriptionTextView = findViewById(R.id.product_desc);
        ImageView imageView = findViewById(R.id.product_img);

        nameTextView.setText(name);
        priceTextView.setText(String.valueOf(price));
        descriptionTextView.setText(description);
        Glide.with(this).load(imageUrl).placeholder(R.drawable.infinite).error(R.drawable.infinite).into(imageView);

    }
}