package com.mindgeeks.offerwall;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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


public class offersList extends Fragment {

    private RecyclerView recyclerView;
    private boolean isNetworkOk;
    List<OffersListData.Offer> list;
    public offersList() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
     getOfferWall();
    }

    public void getOfferWall() {
        Call<OffersListData>call= ApiClient.getApiInterface().getOfferList(CreateRequest());
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

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.offer_recycler);

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

    private void setRecycler() {
        OfferRecyclerAdapter recycler = new OfferRecyclerAdapter(getContext(), list);
        RecyclerView.LayoutManager reLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(reLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(recycler);
        recycler.notifyDataSetChanged();}
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_offers, container, false);
    }
}