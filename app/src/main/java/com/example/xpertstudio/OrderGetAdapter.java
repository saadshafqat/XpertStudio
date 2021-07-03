package com.example.xpertstudio;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class OrderGetAdapter extends RecyclerView.Adapter<OrderGetAdapter.ViewHolder> {
Context context;
ArrayList<ModelForOrders> dataholder;

    public OrderGetAdapter(Context context, ArrayList<ModelForOrders> dataholder) {
        this.context = context;
        this.dataholder = dataholder;
    }

    @NonNull
    @Override
    public OrderGetAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(context);
        View view=layoutInflater.inflate(R.layout.getitem,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);
        return  viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull OrderGetAdapter.ViewHolder holder, int position) {
            holder.txt.setText(dataholder.get(position).getFirstReq());
        holder.txt1.setText(dataholder.get(position).getSecondReq());
        holder.txt2.setText(dataholder.get(position).getThirdReq());
        holder.txt3.setText(dataholder.get(position).getPrice());





    }

    @Override
    public int getItemCount() {
        return dataholder.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txt,txt1,txt2,txt3;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txt=itemView.findViewById(R.id.firstreq);
            txt1=itemView.findViewById(R.id.secondreq);
            txt2=itemView.findViewById(R.id.thirdreq);
            txt3=itemView.findViewById(R.id.price);
        }
    }
}
