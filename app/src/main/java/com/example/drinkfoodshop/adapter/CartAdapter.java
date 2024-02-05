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
import com.example.drinkfoodshop.domain.categoryDomain;
import com.example.drinkfoodshop.help.ChangeNumberItemsListener;
import com.example.drinkfoodshop.help.ManagmentCart;

import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.viewholder> {
    ArrayList<categoryDomain> list;
    private ManagmentCart managmentCart;
    ChangeNumberItemsListener changeNumberItemsListener;

    public CartAdapter(ArrayList<categoryDomain> list, Context context, ChangeNumberItemsListener changeNumberItemsListener) {
        this.list = list;
        managmentCart=new ManagmentCart(context);
        this.changeNumberItemsListener = changeNumberItemsListener;
    }

    @NonNull
    @Override
    public CartAdapter.viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate= LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_viewholder,parent,false);


        return new viewholder(inflate);
    }


    @Override
    public void onBindViewHolder(@NonNull CartAdapter.viewholder holder, int position) {
            holder.loai.setText(list.get(position).getTitle()+" ");
            holder.tienmoisp.setText(list.get(position).getPrice()+" ");
            holder.tongtien.setText(list.get(position).getNumberInCart()+"*" +(list.get(position).getNumberInCart())*list.get(position).getPrice());
            holder.soluong.setText(list.get(position).getNumberInCart()+" ");

        Glide.with(holder.itemView.getContext())
                .load(list.get(position).getPic())
                .transform(new CenterCrop(),new RoundedCorners(30))
                .into(holder.pic);

        holder.cong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                managmentCart.plusNumberItem(list, position, new ChangeNumberItemsListener() {
                    @Override
                    public void change() {
                        notifyDataSetChanged();
                        changeNumberItemsListener.change();
                    }
                });
            }
        });
        holder.tru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                managmentCart.minusNumberItem(list, position, new ChangeNumberItemsListener() {
                    @Override
                    public void change() {
                        notifyDataSetChanged();
                        changeNumberItemsListener.change();
                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size()  ;
    }

    public class viewholder extends  RecyclerView.ViewHolder{
        TextView loai,tienmoisp,cong,tru;
        ImageView pic;
        TextView tongtien,soluong;



        public viewholder(@NonNull View itemView) {
            super(itemView);
            loai=itemView.findViewById(R.id.loaitxt);
            tienmoisp=itemView.findViewById(R.id.tienmoiloai);
            pic=itemView.findViewById(R.id.anhsp);
            tongtien=itemView.findViewById(R.id.vu17);
            cong=itemView.findViewById(R.id.plusCartbtn);
            tru=itemView.findViewById(R.id.minusCartbtn);
            soluong=itemView.findViewById(R.id.numberItem);

        }

    }
}
