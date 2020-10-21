package com.mindgeeks.offerwall.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


import com.mindgeeks.offerwall.R;
import com.mindgeeks.offerwall.model.OfferDetailData;

import java.util.List;

public class PayOutAdapter extends ArrayAdapter<OfferDetailData.Instruction> {
    Context context;
    List<OfferDetailData.Instruction> list;

    public PayOutAdapter(@NonNull Context context, List<OfferDetailData.Instruction> list) {
        super(context, R.layout.payload_item_layout,list);
        this.context=context;
        this.list=list;
    }

    @SuppressLint("SetTextI18n")
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;
        OfferDetailData.Instruction data=getItem(position);
        final View result;
        if (convertView==null){
            viewHolder=new ViewHolder();
            LayoutInflater inflater=LayoutInflater.from(context);
            convertView=inflater.inflate(R.layout.payload_item_layout,parent,false);

            viewHolder.name_tv=convertView.findViewById(R.id.property_name_tv);
            viewHolder.value_tv=convertView.findViewById(R.id.property_value_tv);
            result=convertView;
            convertView.setTag(viewHolder);
        }else {
            viewHolder= (ViewHolder) convertView.getTag();
            result=convertView;
        }
        assert data != null;
       // SharedPreferences sharedPreferences = context.getSharedPreferences(MainActivity.UserPreferences, Context.MODE_PRIVATE);
        viewHolder.name_tv.setText(data.getPropertyName());
        viewHolder.value_tv.setText(context.getString(R.string.rupees)+" "+data.getPropertyValue());

        return convertView;
    }

    private static class ViewHolder{
        TextView name_tv,value_tv;
    }

}
