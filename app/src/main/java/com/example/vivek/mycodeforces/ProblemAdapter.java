package com.example.vivek.mycodeforces;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by vivek on 15/7/18.
 */

public class ProblemAdapter extends RecyclerView.Adapter<ProblemAdapter.ProblemViewHolder> {

    private ArrayList<Problem> data;
    private Context ctx;

    public ProblemAdapter(ArrayList<Problem> data, Context ctx)
    {
        this.data = data;
        this.ctx = ctx;
    }

    @Override
    public ProblemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.problem_list_item_layout,parent,false);
        return new ProblemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProblemViewHolder holder, int position) {
        Problem problem = data.get(position);
        holder.problem.setText(problem.index + "." + problem.name);
        holder.solvedCount.setText("Solved by: " + problem.solvedCount);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ProblemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView problem;
        TextView solvedCount;

        public ProblemViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            problem = itemView.findViewById(R.id.problem);
            solvedCount = itemView.findViewById(R.id.solve_count);
        }

        @Override
        public void onClick(View v) {
            Log.i("stat","clicked");
            int position = getAdapterPosition();
            Intent intent = new Intent(ctx,ProblemActivity.class);
            intent.putExtra("contestId",data.get(position).contestId);
            intent.putExtra("index",data.get(position).index);
            intent.putExtra("name",data.get(position).name);
            ctx.startActivity(intent);
        }
    }

}
