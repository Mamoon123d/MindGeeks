package com.mindgeeks.offerwall;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mindgeeks.offerwall.adapter.OfferRecyclerAdapter;
import com.mindgeeks.offerwall.model.OffersListData;
import com.mindgeeks.offerwall.model.UserRequest;
import com.mindgeeks.offerwall.network.ApiClient;
import com.mindgeeks.offerwall.utils.CreateRequest;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OffersWall extends RelativeLayout{
    LayoutInflater inflater;

    private RecyclerView recyclerView;
    private boolean isNetworkOk;

    String userId, versionName, versionCode, securityCode;

    Context context;

    public OffersWall(Context context) {
        super(context);
        this.context = context;
        inflater = LayoutInflater.from(context);
        init();
    }


    public OffersWall(Context context, AttributeSet attrs) {
        super(context, attrs);
        inflater = LayoutInflater.from(context);
        init();
    }

    public OffersWall(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inflater = LayoutInflater.from(context);
        init();
    }


    private void init() {
        View view = inflater.inflate(R.layout.fragment_offers, this, true);
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

       /* userRequest.setUserId(userId);

        userRequest.setVersionName(versionName);
        userRequest.setVersionCode(versionCode);
        userRequest.setSecurityToken(securityCode);*/
        return userRequest;
    }

    public void getOfferWall() {
       /* UserRequest userRequest = new UserRequest();
        userRequest.setUserId(userId);
        userRequest.setVersionName(versionName);
        userRequest.setVersionCode(versionCode);
        userRequest.setSecurityToken(securityCode);*/
        Call<OffersListData> call = ApiClient.getApiInterface().getOfferList(CreateRequest());
        call.enqueue(new Callback<OffersListData>() {
            @Override
            public void onResponse(@NotNull Call<OffersListData> call, @NotNull Response<OffersListData> response) {
                if (response.body() != null) {
                    List<OffersListData.Offer>  list = response.body().getOffers();
                    setRecycler(list);
                }
            }

            @Override
            public void onFailure(@NotNull Call<OffersListData> call, @NotNull Throwable t) {

            }
        });
    }

    private void setRecycler(List<OffersListData.Offer> list) {
        OfferRecyclerAdapter recycler = new OfferRecyclerAdapter(context, list);
        RecyclerView.LayoutManager reLayoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(reLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(recycler);
        recycler.notifyDataSetChanged();

    }


   /* public static Map<String, String> getWallProperties(String var0, String var1) {
        HashMap var2 = new HashMap();
        var2.put("unit_id", var1);
        var2.put("plugin_name", new String[]{"MVWallPlugin"});
        var2.put("layout_type", 3);
        return var2;
    }*/


}
