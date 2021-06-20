package com.example.grocery;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.grocery.adapter.Specificcategoryadapter;
import com.example.grocery.model.Specificcategorymodel;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class Specificcategory extends AppCompatActivity {
    RecyclerView recyclerView;
    Specificcategoryadapter specificcategoryadapters;
    List<Specificcategorymodel> specificcategorymodellist;
    String text;
    TextView categorynames;
    ProgressDialog pd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specificcategory);
        recyclerView=(RecyclerView)findViewById(R.id.sepecifccategory);
        recyclerView.setLayoutManager(new LinearLayoutManager(Specificcategory.this));
        categorynames=(TextView)findViewById(R.id.specificcategoryname);

       text=getIntent().getStringExtra("category").toString();
       categorynames.setText(text);


        specificcategorymodellist=new ArrayList<>();
        specificcategoryadapters=new Specificcategoryadapter(Specificcategory.this,specificcategorymodellist);
        recyclerView.setAdapter(specificcategoryadapters);

        getdata();

    }
    private void getdata(){
        pd=new ProgressDialog(Specificcategory.this);
        pd.setMessage("Please wait");
        pd.setTitle("Loading");
        pd.show();

        DatabaseReference reference= FirebaseDatabase.getInstance().getReference("products");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                specificcategorymodellist.clear();
                for (DataSnapshot dataSnapshot:snapshot.getChildren()){
                    String categorys=dataSnapshot.child("category").getValue().toString();

                    if (categorys.equals(text)) {
                        String imgurl = dataSnapshot.child("imgurl").getValue().toString();
                        String name = dataSnapshot.child("name").getValue().toString();
                        String offer = dataSnapshot.child("offers").getValue().toString();
                        String price = dataSnapshot.child("price").getValue().toString();
                        String id = dataSnapshot.getKey().toString();
                        specificcategorymodellist.add(new Specificcategorymodel(categorys, imgurl, name, offer, price, id));

                    }
//                    }else {
//                        Toast.makeText(Specificcategory.this,"No product available",Toast.LENGTH_LONG).show();
//                    }
                }
                pd.dismiss();
                if (specificcategorymodellist.size()==0){
                    Toast.makeText(Specificcategory.this,"No product available",Toast.LENGTH_LONG).show();
                }


                specificcategoryadapters.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {
                Toast.makeText(Specificcategory.this,"cancelled",Toast.LENGTH_LONG).show();
            }
        });
    }

}