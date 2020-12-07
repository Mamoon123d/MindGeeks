package com.mindgeeks.offerwall;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.mindgeeks.offerwall.adapter.OfferRecyclerAdapter;
import com.mindgeeks.offerwall.fragment.OffersFragment;
import com.mindgeeks.offerwall.model.OffersListData;
import com.mindgeeks.offerwall.model.UserRequest;
import com.mindgeeks.offerwall.network.ApiClient;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OfferActivity extends AppCompatActivity {

    private String data;
    List<OffersListData.Offer> list;
    private RecyclerView recyclerView;
    private String securityToken;
    private String securityToken_Key="securityToken_key";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offer);
        init();
        getData();
        setList();
        //loadFragment();
    }

    private void setList() {
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
            public void onFailure(@NotNull Call<OffersListData> call, Throwable t) {

            }
        });
    }

    private void setRecycler() {
        OfferRecyclerAdapter recycler = new OfferRecyclerAdapter(this, list);
        RecyclerView.LayoutManager reLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(reLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(recycler);
        recycler.notifyDataSetChanged();
    }

    private void init() {
        recyclerView=findViewById(R.id.offer_list_recycler);
    }

    private void getData() {
        Bundle arg=getIntent().getExtras();
        if (arg!=null) {
           securityToken= arg.getString(securityToken_Key);
            Toast.makeText(this, ""+data, Toast.LENGTH_SHORT).show();
        }
    }
    private void loadFragment() {
       /* Fragment fragment;
        fragment = new OffersFragment();
        OffersFragment.newInstance("3837ca03-41dd-402f-8ce5-ea6d74ac0127","45445");
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//frame_container is your layout name in xml file
        transaction.replace(R.id.frame_container, fragment);
        // transaction.addToBackStack(null);
        transaction.commit();*/
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
     //   userRequest.setSecurityToken("3837ca03-41dd-402f-8ce5-ea6d74ac0127");
        userRequest.setSecurityToken(securityToken);

        return userRequest;
    }
}