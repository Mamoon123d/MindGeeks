package com.mindgeeks.offerwall.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.mindgeeks.offerwall.R;
import com.mindgeeks.offerwall.model.OffersListData;
import com.squareup.picasso.Picasso;

import java.util.List;


public class OfferRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    List<OffersListData.Offer> list;

    public static class ViewHolder0 extends RecyclerView.ViewHolder {
        TextView title_tv, description_tv, rewards_tv;
        ImageView app_image;
        CardView card;


        public ViewHolder0(@NonNull View itemView) {
            super(itemView);
            title_tv = itemView.findViewById(R.id.title_tv);
            description_tv = itemView.findViewById(R.id.description_tv);
            rewards_tv = itemView.findViewById(R.id.reward_btn);
            app_image = itemView.findViewById(R.id.app_image);
            card= itemView.findViewById(R.id.offer_card);
        }
    }

    public static class ViewHolder1 extends RecyclerView.ViewHolder {
        TextView title_tv;

        public ViewHolder1(@NonNull View itemView) {
            super(itemView);

           // title_tv = itemView.findViewById(R.id.title2_tv);
        }
    }


    public OfferRecyclerAdapter(Context context, List<OffersListData.Offer> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getItemViewType(int position) {
        // Just as an example, return 0 or 2 depending on position
        // Note that unlike in ListView adapters, types don't have to be contiguous
        //  return position % 2 * 2;
        return position = 0;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        @SuppressLint("InflateParams") View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.offer_item, null);
      //  @SuppressLint("InflateParams") View view_1 = LayoutInflater.from(parent.getContext()).inflate(R.layout.offer_item2, null);

        //return new MyHolder(view);
        if (viewType == 0) {
            return new ViewHolder0(view);

        } else {
            return new ViewHolder0(view);
        }
     /*  switch (viewType){
           case 0:
               return new ViewHolder0(view) ;

           case 1:return new ViewHolder1(view_1);
           default:return new ViewHolder0(view);
       }*/
    }


    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        final OffersListData.Offer data = list.get(position);

        switch (holder.getItemViewType()) {
            case 0:
               // SharedPreferences sharedPreferences = context.getSharedPreferences(MainActivity.UserPreferences, Context.MODE_PRIVATE);
                ViewHolder0 viewHolder0 = (ViewHolder0) holder;

                    //viewHolder0.shimmerLayout.stopShimmer();
                    viewHolder0.title_tv.setText(data.getOfferName());
                    viewHolder0.title_tv.setBackground(null);
                    viewHolder0.description_tv.setText(data.getDescription());
                    viewHolder0.description_tv.setBackground(null);
                    viewHolder0.rewards_tv.setText("₹ "+" "+data.getOfferAmount());
                    //set image
                    Picasso.get().load(data.getImageUrl()).into(viewHolder0.app_image);

                    viewHolder0.card.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                           /* Intent intent = new Intent(context, OfferActivity.class);
                            Bundle bundle = new Bundle();
                            bundle.putInt(OfferActivity.OFFER_ID, data.getOfferId());
                            bundle.putString(OfferActivity.DESCRIPTION, data.getDescription());
                            bundle.putString(OfferActivity.TITLE, data.getOfferName());
                            bundle.putString(OfferActivity.REWARDS, data.getOfferAmount());
                            bundle.putString(OfferActivity.IMAGE_URL, data.getImageUrl());
                            bundle.putString(OfferActivity.PACKAGE_NAME, data.getPackageName());

                            intent.putExtras(bundle);
                            //((Activity)context).setResult(Activity.RESULT_OK);
                            ((Activity)context).startActivityForResult(intent, MainActivity.REQUEST_CODE);*/

                        }
                    });





                break;
            case 1:
                ViewHolder1 viewHolder1 = (ViewHolder1) holder;
                viewHolder1.title_tv.setText(data.getOfferName());

                break;
        }


    }

    @Override
    public int getItemCount() {
      //  return isShimmer?shimmerNo:list.size();
         return list.size();
    }

    /*public class MyHolder extends RecyclerView.ViewHolder {
        TextView title_tv, description_tv;
        Button rewards_btn;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            title_tv = itemView.findViewById(R.id.title_tv);
            description_tv = itemView.findViewById(R.id.description_tv);
            rewards_btn = itemView.findViewById(R.id.reward_btn);


        }
    }*/
}
