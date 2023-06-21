package com.example.taskmanage.modelcalss;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.taskmanage.R;

import java.util.ArrayList;

public class recycleAdapter extends RecyclerView.Adapter<recycleAdapter.MyViewHolder> {
    private ArrayList<category> categorieList;


    public  recycleAdapter(ArrayList<category> categorieList){
        this.categorieList = categorieList;
    }
    public  class  MyViewHolder extends RecyclerView.ViewHolder{
        private TextView catId,catName;
        public MyViewHolder(final View view){
            super(view);
            catId = view.findViewById(R.id.categoryId);
            catName = view.findViewById(R.id.categoryName);

        }
    }


    @NonNull
    @Override
    public recycleAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View categoryItemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_category,parent,false);
       return new MyViewHolder(categoryItemView);
    }

    @Override
    public void onBindViewHolder(@NonNull recycleAdapter.MyViewHolder holder, int position) {
        String uCatId = categorieList.get(position).getCatId();
        String uCatName = categorieList.get(position).getCatName();

        holder.catId.setText(uCatId);
        holder.catName.setText(uCatName);

    }

    @Override
    public int getItemCount() {
        return categorieList.size();
    }
}
