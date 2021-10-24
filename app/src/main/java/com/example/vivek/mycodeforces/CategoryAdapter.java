package com.example.vivek.mycodeforces;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by vivek on 15/7/18.
 */

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {

    private String[] data;
    private Context ctx;
    public CategoryAdapter(String[] data, Context ctx)
    {
        this.data = data;
        this.ctx = ctx;
    }

    @Override
    public CategoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.category_list_item_layout,parent,false);
        return new CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CategoryViewHolder holder, int position) {
        String category = data[position];
        holder.category.setText(category);
    }

    @Override
    public int getItemCount() {
        return data.length;
    }

    public class CategoryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView category;

        public CategoryViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            category = itemView.findViewById(R.id.category);
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(ctx,CategoryActivity.class);
            int position = getAdapterPosition();
            intent.putExtra("tag",data[position]);
            ctx.startActivity(intent);

        }
    }

}
