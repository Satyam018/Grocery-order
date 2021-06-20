package com.example.grocery.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.grocery.R;
import com.example.grocery.Specificcategory;
import com.example.grocery.Specificproducct;
import com.example.grocery.model.Specificcategorymodel;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class Specificcategoryadapter extends RecyclerView.Adapter<Specificcategoryadapter.Viewhodlers> {
    private static final String TAG ="TAG" ;
    private Context context;
        private List<Specificcategorymodel>  mlist;

    public Specificcategoryadapter(Context context, List<Specificcategorymodel> mlist) {
        this.context = context;
        this.mlist = mlist;

    }

    @NonNull
    @NotNull
    @Override
    public Viewhodlers onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.specifccategorysinglerow,parent,false);
        return new Viewhodlers(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull Viewhodlers holder, int position) {
        Specificcategorymodel temp=mlist.get(position);
        holder.textView.setText(temp.getName());
        String price="Rs."+temp.getPrice();
        holder.textView1.setText(price);

        Glide.with(context).load(temp.getImageurl()).into(holder.img);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i=new Intent(context, Specificproducct.class);
                i.putExtra("id",temp.getId());
                context.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    class Viewhodlers extends RecyclerView.ViewHolder{
        ImageView img;
        TextView textView,textView1;

        public Viewhodlers(@NonNull @NotNull View itemView) {
            super(itemView);
            img=itemView.findViewById(R.id.specificcategoryimage);
            textView=itemView.findViewById(R.id.specificcatergoyname);
            textView1=itemView.findViewById(R.id.specificcategoryprice);
        }
    }
}
