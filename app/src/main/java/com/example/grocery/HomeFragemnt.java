package com.example.grocery;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.grocery.adapter.HomefragmentAdapter;
import com.example.grocery.model.Cardviewhomefragment1;
import com.example.grocery.model.Cardviewhomefragment2;
import com.example.grocery.model.Cardviewhomefragment3;
import com.example.grocery.model.Categorymodel;

import java.util.ArrayList;
import java.util.List;


public class HomeFragemnt extends Fragment {
    RecyclerView recyclerView;
    List mlist;

    HomefragmentAdapter homefragmentAdapterl;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_home_fragemnt, container, false);
        recyclerView=view.findViewById(R.id.homerecyclerview);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        mlist=new ArrayList();
        homefragmentAdapterl=new HomefragmentAdapter(getContext(),mlist);
        recyclerView.setAdapter(homefragmentAdapterl);

        setdata();

        return view;
    }
    private void setdata(){
        mlist.add(new Cardviewhomefragment3(R.drawable.offer2));
        mlist.add(new Cardviewhomefragment1("Best offers",R.drawable.offer1));


        ArrayList<Categorymodel> categorymodels=new ArrayList<>();
        categorymodels.add(new Categorymodel("Fruits",R.drawable.fruitbasket));
        categorymodels.add(new Categorymodel("Vegetables",R.drawable.vegetable));
        categorymodels.add(new Categorymodel("Meat",R.drawable.meat));
        categorymodels.add(new Categorymodel("Aquatic Foods",R.drawable.aquatic));
        categorymodels.add(new Categorymodel("Dairy Products",R.drawable.milkproducts));
        categorymodels.add(new Categorymodel("Cold Drinks",R.drawable.coldrink));
        categorymodels.add(new Categorymodel("Juice",R.drawable.fruitjuice));
        categorymodels.add(new Categorymodel("Bakery",R.drawable.bakery));
        categorymodels.add(new Categorymodel("Pickles",R.drawable.pickles));
        mlist.add(new Cardviewhomefragment2("see all",categorymodels));

        mlist.add(new Cardviewhomefragment1("up to 80% off",R.drawable.offer80));
        mlist.add(new Cardviewhomefragment3(R.drawable.offerl));




        homefragmentAdapterl.notifyDataSetChanged();






    }

}