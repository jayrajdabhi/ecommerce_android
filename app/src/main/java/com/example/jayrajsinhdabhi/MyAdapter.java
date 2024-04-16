package com.example.jayrajsinhdabhi;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    Context context;
    ArrayList<Shoe> list;

    public MyAdapter(Context context, ArrayList<Shoe> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.row_layout, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Shoe shoe = list.get(position);
        holder.name.setText(shoe.getName());
        holder.price.setText(String.valueOf(shoe.getPrice()));
        holder.description.setText(shoe.getDescription());

        Glide.with(context).load(shoe.getIurl()).placeholder(R.drawable.infinite).error(R.drawable.infinite).into(holder.iurl);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView name, price, description;
        ImageView iurl;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.product_name);
            price = itemView.findViewById(R.id.product_price);
            description = itemView.findViewById(R.id.product_desc);
            iurl = itemView.findViewById(R.id.product_img);

            // Set click listener
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            // Get item position
            int position = getAdapterPosition();
            if (position != RecyclerView.NO_POSITION) {
                // Get the shoe at the position
                Shoe shoe = list.get(position);

                // Start ProductDetailActivity with data
                Intent intent = new Intent(context, ProductDetailActivity.class);
                intent.putExtra("name", shoe.getName());
                intent.putExtra("price", shoe.getPrice());
                intent.putExtra("description", shoe.getDescription());
                intent.putExtra("imageUrl", shoe.getIurl());
                intent.putExtra("fulldescription", shoe.getFulldescription());
                context.startActivity(intent);
            }
        }
    }


}
