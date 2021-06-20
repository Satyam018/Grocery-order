package com.example.grocery;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.grocery.adapter.Specificproductcommentadapter;
import com.example.grocery.model.Specifcproductcommentmodel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Specificproducct extends AppCompatActivity {
    private static final String TAG = "TAG";
    String id;
    TextView name,price;
    ImageView img,postcomment;
    Button buynow;
    EditText addcomment;
    RecyclerView showcomment;
    Specificproductcommentadapter specificproductcommentadapters;
    List<Specifcproductcommentmodel> specifcproductcommentmodelslist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specificproducct);
        img=(ImageView)findViewById(R.id.imageofproductselected);
        buynow=(Button)findViewById(R.id.buynow);
        name=(TextView)findViewById(R.id.showproductofselected) ;
        price=(TextView)findViewById(R.id.priceofselecteditem);
        addcomment=(EditText)findViewById(R.id.addcomments);
        postcomment=(ImageView)findViewById(R.id.postcomment);
        showcomment=(RecyclerView)findViewById(R.id.showcommentofproduct);

        showcomment.setLayoutManager(new LinearLayoutManager(Specificproducct.this));
        specifcproductcommentmodelslist =new ArrayList<>();
        specificproductcommentadapters=new Specificproductcommentadapter(Specificproducct.this,specifcproductcommentmodelslist);
        showcomment.setAdapter(specificproductcommentadapters);



        id=getIntent().getStringExtra("id");
        Log.e(TAG, "onCreate: "+id );

        getdata(id);
         getcomments(id);

        postcomment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkcomment();
            }
        });

        buynow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Bottomdialogbuy bottomdialogbuys=new Bottomdialogbuy(id,Specificproducct.this);
                bottomdialogbuys.show(getSupportFragmentManager(),"TAG");
            }
        });

    }
    private void getdata(String ids){
        DatabaseReference reference= FirebaseDatabase.getInstance().getReference("products").child(ids);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {

                    String category=snapshot.child("category").getValue().toString();
                    String imgurl=snapshot.child("imgurl").getValue().toString();
                    String offers=snapshot.child("offers").getValue().toString();
                    String prices=snapshot.child("price").getValue().toString();
                    String names=snapshot.child("name").getValue().toString();



                    Glide.with(Specificproducct.this).load(imgurl)
                            .into(img);
                    name.setText(names);
                    price.setText("Rs. "+prices);
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });

    }
    private void checkcomment(){
        String comments=addcomment.getText().toString();
        if (TextUtils.isEmpty(comments)){
            Toast.makeText(Specificproducct.this,"Please insert a comment",Toast.LENGTH_LONG).show();

        }else {
            entercomment(comments);
        }
    }
    private void entercomment(String comments){
        FirebaseUser firebaseUser= FirebaseAuth.getInstance().getCurrentUser();
        String userid=firebaseUser.getUid();

        DatabaseReference reference=FirebaseDatabase.getInstance().getReference("comments").child(id).child(userid);
        HashMap<String,Object> hashMap=new HashMap<>();
        hashMap.put("productcomment",comments);
        reference.setValue(hashMap);
        addcomment.setText("");
    }
    private void getcomments(String id){

        DatabaseReference reference=FirebaseDatabase.getInstance().getReference("comments").child(id);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                specifcproductcommentmodelslist.clear();
                for (DataSnapshot dataSnapshot:snapshot.getChildren()){
                    String uids=dataSnapshot.getKey().toString();
                    String comment=dataSnapshot.child("productcomment").getValue().toString();
                    Log.e(TAG, "onDataChange: "+uids );
                    Log.e(TAG, "onDataChange: "+comment );
                    specifcproductcommentmodelslist.add(new Specifcproductcommentmodel(uids,comment));
                    Log.e(TAG, "onDataChange: "+specifcproductcommentmodelslist.size() );
                }

                 specificproductcommentadapters.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });


    }

}