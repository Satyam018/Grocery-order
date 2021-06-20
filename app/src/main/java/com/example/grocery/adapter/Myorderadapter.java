package com.example.grocery.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.grocery.R;
import com.example.grocery.model.Myordermodel;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class Myorderadapter extends RecyclerView.Adapter<Myorderadapter.Viewholder> {
    Context context;
    List<Myordermodel> myordermodelList;

    public Myorderadapter(Context context, List<Myordermodel> myordermodelList) {
        this.context = context;
        this.myordermodelList = myordermodelList;
    }

    @NonNull
    @NotNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.myordersinglerow,parent,false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull Viewholder holder, int position) {
        Myordermodel temp=myordermodelList.get(position);
        Glide.with(context).load(temp.getUrl()).into(holder.img);
        holder.tx.setText(temp.getName());

    }

    @Override
    public int getItemCount() {
        return myordermodelList.size();
    }

    class Viewholder extends RecyclerView.ViewHolder{
        ImageView img;
        TextView tx;

        public Viewholder(@NonNull @NotNull View itemView) {
            super(itemView);
            img=itemView.findViewById(R.id.myorderimage);
            tx=itemView.findViewById(R.id.myodername);
        }
    }
}
