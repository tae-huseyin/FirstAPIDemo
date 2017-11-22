package com.example.theappexperts.firstapidemo;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.theappexperts.firstapidemo.model.CakeModel;
import com.squareup.picasso.Picasso;

import java.util.List;


public class CakeAdapter extends RecyclerView.Adapter<CakeAdapter.MyViewHolder> {


    public List<CakeModel> cakeModelList;
    public int row_cake;
    public Context applicationContext;

    public CakeAdapter(List<CakeModel> cakeModelList, int row_cake, Context applicationContext) {
        this.cakeModelList = cakeModelList;
        this.row_cake = row_cake;
        this.applicationContext = applicationContext;
    }

    @Override
    public CakeAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(applicationContext).inflate(row_cake, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CakeAdapter.MyViewHolder holder, int position) {

        //Picasso.with(applicationContext).load(cakeModelList.get(position).getImage()).into(holder.ivPicture);
        Glide.with(applicationContext)
                .load(cakeModelList.get(position).getImage())
                .into(holder.ivPicture);

        holder.tvTitle.setText(cakeModelList.get(position).getTitle().toString());
        holder.tvDesc.setText(cakeModelList.get(position).getDesc().toString());

    }

    @Override
    public int getItemCount() {
        return cakeModelList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView tvTitle, tvDesc;
        ImageView ivPicture;

        public MyViewHolder(View itemView) {
            super(itemView);

            ivPicture = (ImageView) itemView.findViewById(R.id.ivPicture);
            tvTitle = (TextView)itemView.findViewById(R.id.tvTitle);
            tvDesc = (TextView)itemView.findViewById(R.id.tvDesc);
        }
    }
}
