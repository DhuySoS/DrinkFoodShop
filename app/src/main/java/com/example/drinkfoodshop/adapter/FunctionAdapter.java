package com.example.drinkfoodshop.adapter;
import com.example.drinkfoodshop.R;
import com.example.drinkfoodshop.domain.function;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
public class FunctionAdapter extends RecyclerView.Adapter<FunctionAdapter.ViewHolder> {
    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    private List<function> functions;
    private OnItemClickListener listener;

    public FunctionAdapter(List<function> functions, OnItemClickListener listener) {
        this.functions = functions;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_function, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        function function = functions.get(position);
        holder.bind(function, listener);
    }

    @Override
    public int getItemCount() {
        return functions.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView titleTextView;
        private TextView descriptionTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.titleTextView);
            descriptionTextView = itemView.findViewById(R.id.descriptionTextView);
        }

        public void bind(final function function, final OnItemClickListener listener) {
            titleTextView.setText(function.getTitle());
            descriptionTextView.setText(function.getDescription());

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(getAdapterPosition());
                }
            });
        }
    }
}
