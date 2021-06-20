package com.example.grocery.adapter;

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
import com.example.grocery.R;
import com.example.grocery.Specificproducct;
import com.example.grocery.model.Afterhomemodel;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class Afterhomeadapter extends RecyclerView.Adapter<Afterhomeadapter.Viewholders> {

    Context contexts;
    List<Afterhomemodel> mlists;

    public Afterhomeadapter(Context contexts, List<Afterhomemodel> mlists) {
        this.contexts = contexts;
        this.mlists = mlists;
    }

    @NonNull
    @NotNull
    @Override
    public Viewholders onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
       View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.afterhomelayout,parent,false);
       return new Viewholders(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull Viewholders holder, int position) {
        Afterhomemodel temp=mlists.get(position);
        holder.offer.setText(temp.getOffer());
        holder.price.setText(temp.getPrice());

        Glide.with(contexts).load(temp.getImgurl()).into(holder.img);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(contexts, Specificproducct.class);
                i.putExtra("id",temp.getProductid());
                contexts.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mlists.size();
    }

    class Viewholders extends RecyclerView.ViewHolder{
        TextView price,offer;
        ImageView img;

        public Viewholders(@NonNull @NotNull View itemView) {
            super(itemView);
            img=itemView.findViewById(R.id.afterhomeimage);
            price=itemView.findViewById(R.id.afterhomeprice);
            offer=itemView.findViewById(R.id.afterhomeoffer);
        }
    }
}
