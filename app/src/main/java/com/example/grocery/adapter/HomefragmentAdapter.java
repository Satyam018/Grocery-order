package com.example.grocery.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import com.bumptech.glide.Glide;
import com.example.grocery.CategoryFragment;
import com.example.grocery.R;
import com.example.grocery.model.Afterhome;
import com.example.grocery.model.Cardviewhomefragment1;
import com.example.grocery.model.Cardviewhomefragment2;
import com.example.grocery.model.Cardviewhomefragment3;
import com.example.grocery.model.Categorymodel;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class HomefragmentAdapter extends RecyclerView.Adapter {
    Context context;
    List mlist;

    public HomefragmentAdapter(Context context, List mlist) {
        this.context = context;
        this.mlist = mlist;
    }

    @Override
    public int getItemViewType(int position) {
        if (mlist.get(position)instanceof Cardviewhomefragment1){
            return 1;
        }else if (mlist.get(position)instanceof Cardviewhomefragment2){
            return 2;
        }else if (mlist.get(position)instanceof Cardviewhomefragment3){
            return 3;
        }else {
            return 4;
        }
    }

    @NonNull
    @NotNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
       if (viewType==1){
           View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.cardviewhomefragment1,parent,false);
           return new Viewholder1(view);
       }else if (viewType==2){
           View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.cardviewhomefragment2,parent,false);
           return new Viewholder2(view);
       }else if (viewType==3){
           View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.cardviewhomefragment3,parent,false);
           return new Viewholder3(view);
       }else {
           return null;
       }
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position)==1){
            Cardviewhomefragment1 temp=(Cardviewhomefragment1) mlist.get(position);
          Viewholder1 holders=(Viewholder1)holder;

          holders.tx1.setText(temp.getText());
            Glide.with(context).load(temp.getImg()).into(holders.img1);

            holders.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i=new Intent(context, Afterhome.class);
                    i.putExtra("offer",temp.getText());
                    context.startActivity(i);

                }
            });


        }else if (getItemViewType(position)==2){
            Cardviewhomefragment2 temp=(Cardviewhomefragment2)mlist.get(position);
            Viewholder2 holders=(Viewholder2)holder;

            holders.tx2.setText(temp.getTextfragment2());
            holders.recyclerView2.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false));
            ArrayList <Categorymodel> categorymodels= temp.getHomefraemnt4modelArrayList();
            CategoryAdapter categoryAdapters=new CategoryAdapter(categorymodels,context);
            holders.recyclerView2.setAdapter(categoryAdapters);

            holders.tx2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((FragmentActivity)context).getSupportFragmentManager().beginTransaction().replace(R.id.frame1layout1,new CategoryFragment()).commit();
                }
            });


        }else if (getItemViewType(position)==3){
            Cardviewhomefragment3 temp=(Cardviewhomefragment3)mlist.get(position);
            Viewholder3 holders=(Viewholder3)holder;
            Glide.with(context).load(temp.getImage()).into(holders.img3);

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i=new Intent(context, Afterhome.class);
                    i.putExtra("offer","offers");
                    context.startActivity(i);
                }
            });
        }


    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    class Viewholder1 extends RecyclerView.ViewHolder{

        ImageView img1;
        TextView tx1;

        public Viewholder1(@NonNull @NotNull View itemView) {
            super(itemView);
            img1=itemView.findViewById(R.id.homecard1img);
            tx1=itemView.findViewById(R.id.homecard1text);

        }
    }

    class Viewholder2 extends RecyclerView.ViewHolder{
        TextView tx2;
        RecyclerView recyclerView2;

        public Viewholder2(@NonNull @NotNull View itemView) {
            super(itemView);
            tx2=itemView.findViewById(R.id.homefragmentcard2);
            recyclerView2=itemView.findViewById(R.id.homefragmentrecycler2);
        }
    }

    class Viewholder3 extends RecyclerView.ViewHolder{
        ImageView img3;
        public Viewholder3(@NonNull @NotNull View itemView) {
            super(itemView);
            img3=itemView.findViewById(R.id.homefragmentimg3);
        }
    }
}
