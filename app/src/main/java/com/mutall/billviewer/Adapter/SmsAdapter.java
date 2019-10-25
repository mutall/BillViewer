package com.mutall.billviewer.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mutall.billviewer.Model.Sms;
import com.mutall.billviewer.R;

import java.util.List;

public class SmsAdapter extends RecyclerView.Adapter<SmsAdapter.SmsViewHolder>{
    List<Sms> smsList;
    public SmsAdapter(List<Sms> smsList) {
        this.smsList = smsList;
    }

    @NonNull
    @Override
    public SmsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_item, parent, false);
        return new SmsViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull SmsViewHolder holder, int position) {
        Sms sms = smsList.get(position);
        holder.num.setText(sms.getNumber());
        holder.msg.setText(sms.getMessage());
    }

    @Override
    public int getItemCount() {
        return smsList.size();
    }

    public class SmsViewHolder extends RecyclerView.ViewHolder{
        TextView num, msg;
        public SmsViewHolder(@NonNull View itemView) {
            super(itemView);
            num = itemView.findViewById(R.id.number);
            msg = itemView.findViewById(R.id.message);
        }
    }
}
