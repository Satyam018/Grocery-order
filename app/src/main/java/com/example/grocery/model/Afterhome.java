package com.example.grocery.model;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.grocery.R;
import com.example.grocery.adapter.Afterhomeadapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class Afterhome extends AppCompatActivity {
    private static final String TAG = "TAG";
    RecyclerView recyclerView;
    Afterhomeadapter afterhomeadapters;
    ProgressDialog pd;
    ProgressDialog pd1;
    List<Afterhomemodel> afterhomeadapterList;

    String offer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_afterhome);

        recyclerView=(RecyclerView)findViewById(R.id.afterhomerecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        afterhomeadapterList=new ArrayList<>();
        afterhomeadapters=new Afterhomeadapter(Afterhome.this,afterhomeadapterList);
        recyclerView.setAdapter(afterhomeadapters);

        offer=getIntent().getStringExtra("offer").toString();
        Log.e(TAG, "onCreate: "+offer );
        if (offer.equals("offers")){
            pd1=new ProgressDialog(Afterhome.this);
            pd1.setMessage("please wait");
            pd1.setMessage("Loading");
            pd1.show();

            getdata1();
        }else{
            pd=new ProgressDialog(Afterhome.this);
            pd.setMessage("please wait");
            pd.setMessage("Loading");
            pd.show();
            getdata();
        }



        Log.e(TAG, "onCreate: "+offer );
    }
    private void getdata(){
        DatabaseReference reference= FirebaseDatabase.getInstance().getReference("products");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                afterhomeadapterList.clear();
                for (DataSnapshot dataSnapshot:snapshot.getChildren()){
                    String key=dataSnapshot.getKey();
                    Log.e(TAG, "onDataChange: "+key );

                        String name=dataSnapshot.child("name").getValue().toString();
                        String imgurl=dataSnapshot.child("imgurl").getValue().toString();
                        String price=dataSnapshot.child("price").getValue().toString();
                        String offers=dataSnapshot.child("offers").getValue().toString();
                        if (offers.equals("up to 80% off")) {

                            Log.e(TAG, "onDataChange: " + name);
                            Log.e(TAG, "onDataChange: " + imgurl);
                            Log.e(TAG, "onDataChange: " + price);
                            Log.e(TAG, "onDataChange: " + offer);

                            afterhomeadapterList.add(new Afterhomemodel(name, imgurl, price, offers, key));
                        }

                }
                pd.dismiss();
                if (afterhomeadapterList.size()==0){
                    Toast.makeText(Afterhome.this,"no data available",Toast.LENGTH_LONG).show();
                }
                afterhomeadapters.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {
                pd.dismiss();
                Toast.makeText(Afterhome.this,"Cannot obatin",Toast.LENGTH_LONG).show();

            }
        });
    }
    private void getdata1(){
        DatabaseReference reference= FirebaseDatabase.getInstance().getReference("products");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                afterhomeadapterList.clear();
                for (DataSnapshot dataSnapshot:snapshot.getChildren()){
                    String key=dataSnapshot.getKey();
                    Log.e(TAG, "onDataChange: "+key );

                    String name=dataSnapshot.child("name").getValue().toString();
                    String imgurl=dataSnapshot.child("imgurl").getValue().toString();
                    String price=dataSnapshot.child("price").getValue().toString();
                    String offers=dataSnapshot.child("offers").getValue().toString();
                    if (!offers.equals("")) {

                        Log.e(TAG, "onDataChange: " + name);
                        Log.e(TAG, "onDataChange: " + imgurl);
                        Log.e(TAG, "onDataChange: " + price);
                        Log.e(TAG, "onDataChange: " + offer);

                        afterhomeadapterList.add(new Afterhomemodel(name, imgurl, price, offers, key));
                    }

                }
                pd1.dismiss();
                if (afterhomeadapterList.size()==0){
                    Toast.makeText(Afterhome.this,"no data available",Toast.LENGTH_LONG).show();
                }
                afterhomeadapters.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {
                pd1.dismiss();
                Toast.makeText(Afterhome.this,"Cannot obatin",Toast.LENGTH_LONG).show();

            }
        });

    }

}