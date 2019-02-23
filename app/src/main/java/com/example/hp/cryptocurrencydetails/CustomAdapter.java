package com.example.hp.cryptocurrencydetails;


import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hp.cryptocurrencydetails.R;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {
    private final String TAG = "RecyclerView.Adapter";


    Context myContext;
    ArrayList<String> currencies;
    ArrayList<String> currencyValues;
    private int bigPosition;


    public CustomAdapter(Context myContext, ArrayList<String> currencies, ArrayList<String> currencyValues) {
        this.myContext = myContext;
        this.currencies = currencies;
        this.currencyValues = currencyValues;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        RelativeLayout parentLayout;
        TextView currencyName;
        ImageView Image;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            parentLayout = itemView.findViewById(R.id.parent_layout);
            Image = itemView.findViewById(R.id.currency_image);
            currencyName = itemView.findViewById(R.id.currency_name);

        }
    }



    ///   methods implemented from the RecyclerView.Adapter class
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_view, parent, false);
        MyViewHolder hold = new MyViewHolder(view);
        return hold;
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        bigPosition = position;
        Log.d(TAG, "onBindViewHolder: called.");
        holder.currencyName.setText(currencies.get(position));
        holder.Image.setImageResource(R.drawable.basic_bitcoin);

        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.d(TAG, "onClick: "+ currencies.get(position));
                Toast.makeText(myContext, currencies.get(position), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(view.getContext(), ResultActivity.class);
                String yourResult = String.valueOf(currencyValues.get(position));
                intent.putExtra("result", yourResult);
                view.getContext().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        Log.d(TAG, "lengthOfArray: " + currencyValues.size());
        return currencies.size();
    }


}




