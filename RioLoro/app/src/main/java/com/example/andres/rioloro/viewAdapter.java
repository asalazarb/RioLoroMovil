package com.example.andres.rioloro;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

/**
 * Created by usuario on 06/10/2017.
 */

public class viewAdapter extends RecyclerView.Adapter<viewAdapter.MyViewHolder>{

    private LayoutInflater inflater;
    List<informationx> data= Collections.emptyList();

    public viewAdapter(Context context, List<informationx> data){
        inflater = LayoutInflater.from(context);
        this.data=data;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.custom_row,parent,false);
        MyViewHolder holder=new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        informationx current=data.get(position);
        holder.title.setText(current.title);
        holder.icon.setImageResource(current.iconId);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView title;
        ImageView icon;
        public MyViewHolder(View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.listText);
            icon=itemView.findViewById(R.id.listIcon);
        }
    }
}
