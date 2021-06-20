package com.example.grocery.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.grocery.R;
import com.example.grocery.model.Specifcproductcommentmodel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class Specificproductcommentadapter extends RecyclerView.Adapter<Specificproductcommentadapter.Viewholder> {
        private Context context;
        private List<Specifcproductcommentmodel> lists;
    String name;

    public Specificproductcommentadapter(Context context, List<Specifcproductcommentmodel> lists) {
        this.context = context;
        this.lists = lists;
    }

    @NonNull
    @NotNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.specificproductcommentsingle,parent,false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull Viewholder holder, int position) {
        Specifcproductcommentmodel temp=lists.get(position);

        holder.comment.setText(temp.getComments());

        geturl(temp.getAccountid(),holder.profile);


    }

    @Override
    public int getItemCount() {
        return lists.size();
    }

    class Viewholder extends RecyclerView.ViewHolder{

        TextView comment,profile;
        public Viewholder(@NonNull @NotNull View itemView) {
            super(itemView);
            profile=itemView.findViewById(R.id.commentusername);
            comment=itemView.findViewById(R.id.accountcomment);
        }
    }
    private void geturl(String id,TextView textView){
        DatabaseReference reference= FirebaseDatabase.getInstance().getReference("users").child(id);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                name=snapshot.child("name").getValue().toString();
                textView.setText(name);


            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {
               name="";
                Toast.makeText(context,"Cannot obtain",Toast.LENGTH_LONG).show();
            }
        });



    }
}
