package com.mindgeeks.offerwall;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
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


public class offersList extends Fragment {

    private RecyclerView recyclerView;
    private boolean isNetworkOk;
    List<OffersListData.Offer> list;
    private FragmentActivity mActivity;
    private String securityToken;
    private static final String securityToken_Key = "securityToken";

    public offersList() {
        // Required empty public constructor
    }

    public static offersList newInstance(String mSecurityToken) {
        offersList fragment = new offersList();
        Bundle args = new Bundle();
        args.putString(securityToken_Key, mSecurityToken);
        fragment.setArguments(args);
        // Log.d("TAG", "newInstance: "+mSecurityToken);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            securityToken = getArguments().getString(securityToken_Key);
            Log.d("TAG", "newInstance: " + securityToken);
            //  mParam2 = getArguments().getString(ARG_PARAM2);

        }
        //Log.d("TAG", "newInstance:  "+getArguments().getString(securityToken_Key));

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
                   Toast.makeText(mActivity, "" + securityToken, Toast.LENGTH_SHORT).show();
               }
            }

            @Override
            public void onFailure(@NotNull Call<OffersListData> call, Throwable t) {

            }
        });
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

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
        userRequest.setUserId("1");

        userRequest.setVersionName("1.0");
        userRequest.setVersionCode("1");
        userRequest.setSecurityToken("3837ca03-41dd-402f-8ce5-ea6d74ac0127");
        return userRequest;
    }

    private void setRecycler() {
        OfferRecyclerAdapter recycler = new OfferRecyclerAdapter(mActivity, list);
        RecyclerView.LayoutManager reLayoutManager = new LinearLayoutManager(mActivity);
        recyclerView.setLayoutManager(reLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(recycler);
        recycler.notifyDataSetChanged();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_offers, container, false);
    }


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof FragmentActivity) {
            mActivity = (FragmentActivity) context;
        }
    }
}