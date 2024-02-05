package com.example.drinkfoodshop.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.example.drinkfoodshop.R;
import com.example.drinkfoodshop.domain.food;

import java.util.ArrayList;

public class bestFoodAdapter extends RecyclerView.Adapter<bestFoodAdapter.viewholder> {
    ArrayList<food> items;
    Context context;

    public bestFoodAdapter(ArrayList<food> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public bestFoodAdapter.viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_best_food,parent,false);
        return new viewholder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull bestFoodAdapter.viewholder holder, int position) {
        holder.titleTxt.setText(items.get(position).getTitle());
        holder.priceTxt.setText(items.get(position).getPrice() + " VND");
        Glide.with(context)
                .load(items.get(position).getImagePath())
                .transform(new CenterCrop(),new RoundedCorners(30))
                .into(holder.pic);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class viewholder extends RecyclerView.ViewHolder{
        TextView titleTxt, priceTxt;
        ImageView pic;
        public viewholder(@NonNull View itemView) {
            super(itemView);
            titleTxt = itemView.findViewById(R.id.txtTitle);
            priceTxt = itemView.findViewById(R.id.txtPrice);
            pic = itemView.findViewById(R.id.pic);
        }
    }
}
