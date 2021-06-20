package com.example.grocery;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;


public class ProfileFragment extends Fragment {
    TextView orders,logout;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view= inflater.inflate(R.layout.fragment_profile, container, false);
       logout=view.findViewById(R.id.profilelogout);
       orders=view.findViewById(R.id.profileorders);


       logout.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               ProgressDialog pd=new ProgressDialog(getContext());
               pd.setTitle("Logging out");
               pd.setMessage("Please wait");
               pd.show();
             FirebaseAuth.getInstance().signOut();
             startActivity(new Intent(getContext(),MainActivity.class));
             pd.dismiss();
             getActivity().finish();

           }
       });
       orders.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
                startActivity(new Intent(getContext(),MyordersActivity.class));
           }
       });






       return view;
    }

}