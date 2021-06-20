package com.example.grocery;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public class Bottomdialogbuy extends BottomSheetDialogFragment {
        EditText personname,houseno,housename,address,city,phoneno,quantity;
        Button confrim,viewprice;
        TextView price,back;
        String id;
        Context context;
        Bottomdialogbuy(String id,Context context){
            this.id=id;
            this.context=context;
        }



    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.bottomdialoguebuy,container,false);
        personname=view.findViewById(R.id.orderpersoninfo);
        houseno=view.findViewById(R.id.orderhouseno);
        housename=view.findViewById(R.id.orderhousename);
        address=view.findViewById(R.id.orderlocation);
        city=view.findViewById(R.id.ordercity);
        phoneno=view.findViewById(R.id.orderphoneno);
        confrim=view.findViewById(R.id.confirmorder);
        back=view.findViewById(R.id.back);
        quantity=view.findViewById(R.id.orderquantity);
        price=view.findViewById(R.id.orderprice);
        viewprice=view.findViewById(R.id.orderpricedetail);






        viewprice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                checkdata();

            }
        });
        confrim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkinfo();

            }
        });


        return view;
    }
   private void checkinfo(){
            String personnames=personname.getText().toString();
            String housenos=houseno.getText().toString();
            String housenames=housename.getText().toString();
            String addresses=address.getText().toString();
            String cities=city.getText().toString();
            String phonenos=phoneno.getText().toString();
            String quantities=quantity.getText().toString();
            if (TextUtils.isEmpty(personnames)||TextUtils.isEmpty(housenames)||TextUtils.isEmpty(housenos)||
                    TextUtils.isEmpty(addresses)||TextUtils.isEmpty(phonenos)||TextUtils.isEmpty(cities)||TextUtils.isEmpty(quantities)){
                Toast.makeText(context,"ALl fields are required",Toast.LENGTH_LONG).show();
            }else{
                insertdata(personnames,housenos,housenames,addresses,cities,phonenos,quantities);
            }
   }
   private void insertdata(String personname,String houseno,String housename,String address,String city,String phoneno,String quantity){
       FirebaseUser firebaseUser=FirebaseAuth.getInstance().getCurrentUser();
       String userid=firebaseUser.getUid();

       DatabaseReference reference= FirebaseDatabase.getInstance().getReference("orders");
       HashMap<String,Object>hashMap=new HashMap<>();
       hashMap.put("personname",personname);
       hashMap.put("houseno",houseno);
       hashMap.put("housename",housename);
       hashMap.put("address",address);
       hashMap.put("city",city);
       hashMap.put("phoneno",phoneno);
       hashMap.put("quantity",quantity);
       reference.push().child(userid).child(id).setValue(hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
           @Override
           public void onSuccess(Void unused) {
               Toast.makeText(context,"Order placed",Toast.LENGTH_LONG).show();
               dismiss();
           }
       }).addOnFailureListener(new OnFailureListener() {
           @Override
           public void onFailure(@NonNull @NotNull Exception e) {
               Toast.makeText(context,"unable to order",Toast.LENGTH_LONG).show();
           }
       });



   }
   private void showprice(String qunatity){
            DatabaseReference reference=FirebaseDatabase.getInstance().getReference("products").child(id);
            reference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                   String priceses=snapshot.child("price").getValue().toString();
                   int prices=Integer.parseInt(priceses);
                   int quantities=Integer.parseInt(qunatity);
                   int tottalpirice= prices*quantities;
                   price.setVisibility(View.VISIBLE);
                   back.setVisibility(View.VISIBLE);
                   price.setText("Rs."+String.valueOf(tottalpirice));
                    confrim.setVisibility(View.VISIBLE);
                    viewprice.setVisibility(View.INVISIBLE);


                }

                @Override
                public void onCancelled(@NonNull @NotNull DatabaseError error) {
                    Toast.makeText(context,"unable to obtain",Toast.LENGTH_LONG).show();
                }
            });
   }
   private void checkdata(){
       String personnames=personname.getText().toString();
       String housenos=houseno.getText().toString();
       String housenames=housename.getText().toString();
       String addresses=address.getText().toString();
       String cities=city.getText().toString();
       String phonenos=phoneno.getText().toString();
       String quantities=quantity.getText().toString();
       if (TextUtils.isEmpty(personnames)||TextUtils.isEmpty(housenames)||TextUtils.isEmpty(housenos)||
               TextUtils.isEmpty(addresses)||TextUtils.isEmpty(phonenos)||TextUtils.isEmpty(cities)||TextUtils.isEmpty(quantities)){
           Toast.makeText(context,"ALl fields are required",Toast.LENGTH_LONG).show();
       }else{
           showprice(quantities);
       }
   }
}
