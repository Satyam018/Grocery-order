package com.example.grocery;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.grocery.adapter.CategoryAdapter;
import com.example.grocery.model.Categorymodel;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class CategoryFragment extends Fragment {
    RecyclerView recyclerView;
    List<Categorymodel> categorymodels;
    CategoryAdapter categoryAdapters;





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view= inflater.inflate(R.layout.fragment_category, container, false);
       recyclerView=view.findViewById(R.id.categoryrecycler);
        GridLayoutManager gridLayoutManager=new GridLayoutManager(getContext(),3);
        recyclerView.setLayoutManager(gridLayoutManager);
        categorymodels=new ArrayList<>();
        categoryAdapters=new CategoryAdapter(categorymodels,getContext());
        recyclerView.setAdapter(categoryAdapters);

        showdata();

       return view;
    }
    private void showdata(){
        categorymodels.add(new Categorymodel("Fruits",R.drawable.fruitbasket));
        categorymodels.add(new Categorymodel("Vegetables",R.drawable.vegetable));
        categorymodels.add(new Categorymodel("Meat",R.drawable.meat));
        categorymodels.add(new Categorymodel("Aquatic Foods",R.drawable.aquatic));
        categorymodels.add(new Categorymodel("Dairy Products",R.drawable.milkproducts));
        categorymodels.add(new Categorymodel("Cold Drinks",R.drawable.coldrink));
        categorymodels.add(new Categorymodel("Juice",R.drawable.fruitjuice));
        categorymodels.add(new Categorymodel("Bakery",R.drawable.bakery));
        categorymodels.add(new Categorymodel("Pickles",R.drawable.pickles));
        categoryAdapters.notifyDataSetChanged();

    }
}