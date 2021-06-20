package com.example.grocery;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.grocery.adapter.Myorderadapter;
import com.example.grocery.model.Myordermodel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class MyordersActivity extends AppCompatActivity {
    private static final String TAG ="TAG" ;
    RecyclerView recyclerView;
    Myorderadapter myorderadapters;
   List<Myordermodel>  myordermodellist;
    String productid;
    ArrayList<String> productids;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myorders);

        recyclerView=(RecyclerView)findViewById(R.id.myorderrecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        myordermodellist=new ArrayList<>();
        myorderadapters=new Myorderadapter(MyordersActivity.this,myordermodellist);
        recyclerView.setAdapter(myorderadapters);


        getdata();


    }
    private void getdata(){
        productids=new ArrayList<>();
        FirebaseUser firebaseUser= FirebaseAuth.getInstance().getCurrentUser();
        String userid=firebaseUser.getUid();
        DatabaseReference reference= FirebaseDatabase.getInstance().getReference("orders");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
              for (DataSnapshot dataSnapshot:snapshot.getChildren()){
                  for (DataSnapshot dataSnapshot1:dataSnapshot.getChildren()){
                      String id=dataSnapshot1.getKey();

                      if (id.equals(userid)){
                     for (DataSnapshot dataSnapshot2:dataSnapshot1.getChildren()){
                          productid=dataSnapshot2.getKey();
                          productids.add(productid);


                     }}
                  }

              }
              for (int i=0;i<productids.size();i++){
                  productdetail(productids.get(i));

              }



            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });
    }
    private void productdetail(String productid){
        DatabaseReference reference=FirebaseDatabase.getInstance().getReference("products").child(productid);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                String imgurl=snapshot.child("imgurl").getValue().toString();
                String productname=snapshot.child("name").getValue().toString();
                myordermodellist.add(new Myordermodel(imgurl,productid,productname));
                myorderadapters.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });


    }
}