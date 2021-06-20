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

import com.example.grocery.R;
import com.example.grocery.Specificcategory;
import com.example.grocery.model.Categorymodel;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.Viewholders> {
    List<Categorymodel> categorymodels;
    Context context;

    public CategoryAdapter(List<Categorymodel> categorymodels, Context context) {
        this.categorymodels = categorymodels;
        this.context = context;
    }

    @NonNull
    @NotNull
    @Override
    public Viewholders onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.categorycard,parent,false);
        return new Viewholders(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull Viewholders holder, int position) {
        Categorymodel temp=categorymodels.get(position);
        holder.tx1.setText(temp.getCategory());
        holder.img.setImageResource(temp.getImage());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(context, Specificcategory.class);
                i.putExtra("category",temp.getCategory());
                context.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return categorymodels.size();
    }


    class Viewholders extends RecyclerView.ViewHolder{
        ImageView img;
        TextView tx1;
        public Viewholders(@NonNull @NotNull View itemView) {
            super(itemView);
            img=itemView.findViewById(R.id.categoryimg);
            tx1=itemView.findViewById(R.id.categorytext);

        }
    }
}
