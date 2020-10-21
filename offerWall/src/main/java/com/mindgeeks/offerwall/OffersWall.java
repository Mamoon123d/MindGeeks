package com.mindgeeks.offerwall;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mindgeeks.offerwall.adapter.OfferRecyclerAdapter;
import com.mindgeeks.offerwall.model.OffersListData;
import com.mindgeeks.offerwall.model.UserRequest;
import com.mindgeeks.offerwall.network.ApiClient;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OffersWall extends RelativeLayout {
    LayoutInflater inflater;

    private RecyclerView recyclerView;
    private boolean isNetworkOk;
    List<OffersListData.Offer> list;
    public OffersWall(Context context) {
        super(context);
        inflater=LayoutInflater.from(context);
        init();
    }

    public OffersWall(Context context, AttributeSet attrs) {
        super(context, attrs);
        inflater=LayoutInflater.from(context);
        init();
    }

    public OffersWall(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inflater=LayoutInflater.from(context);
        init();
    }




    private void init() {
        View view=inflater.inflate(R.layout.fragment_offers,this,true);
        recyclerView = view.findViewById(R.id.offer_recycler);
        getOfferWall();
    }


    public UserRequest CreateRequest() {
       /* SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getContext());
        int userId = prefs.getInt(LoginActivity.USER_ID, 0);
        String securityToken = prefs.getString(LoginActivity.SECURITY_TOKEN, "");
        String versionName = prefs.getString(LoginActivity.VERSION_NAME, "");
        String versionCode = prefs.getString(LoginActivity.VERSION_CODE, "");*/
        UserRequest userRequest = new UserRequest();
        userRequest.setUserId(String.valueOf(2));

        userRequest.setVersionName("1.0");
        userRequest.setVersionCode("1");
        userRequest.setSecurityToken("462ed61f-5363-495d-b73e-f50f54af7650");
        return userRequest;
    }

    public void getOfferWall() {
        Call<OffersListData> call= ApiClient.getApiInterface().getOfferList(CreateRequest());
        call.enqueue(new Callback<OffersListData>() {
            @Override
            public void onResponse(@NotNull Call<OffersListData> call, @NotNull Response<OffersListData> response) {
                if (response.body() != null) {
                    list = new ArrayList<>();
                    list = response.body().getOffers();
                    setRecycler();
                }
            }

            @Override
            public void onFailure(Call<OffersListData> call, Throwable t) {

            }
        });
    }
    private void setRecycler() {
        OfferRecyclerAdapter recycler = new OfferRecyclerAdapter(getContext(), list);
        RecyclerView.LayoutManager reLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(reLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(recycler);
        recycler.notifyDataSetChanged();}



}
