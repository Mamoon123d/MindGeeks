package com.mindgeeks.offerwall;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mindgeeks.offerwall.adapter.OfferRecyclerAdapter;
import com.mindgeeks.offerwall.model.OffersListData;
import com.mindgeeks.offerwall.model.UserRequest;
import com.mindgeeks.offerwall.network.ApiClient;
import com.mindgeeks.offerwall.utils.Constans;
import com.mindgeeks.offerwall.utils.NetworkHelper;
import com.mindgeeks.offerwall.validate.HexColorValidator;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OfferActivity extends AppCompatActivity {

    private String data;
    List<OffersListData.Offer> list;
    private RecyclerView recyclerView;
    private String securityToken;
    private String securityToken_Key = "securityToken_key";
    private ConstraintLayout offerWallLayout;
    ActionBar actionBar;
    private ImageView back_bt;
    private TextView title_tv;

    private RelativeLayout toolBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offer);

        init();
        actionBar = getSupportActionBar();
        getData();
        setList();
        //loadFragment();
        setBackButton();
    }

    private void setBackButton() {
        back_bt.setOnClickListener(v->finish());
    }

    private void setList() {
        boolean isNetwork= NetworkHelper.isNetworkAvailable(this);
        ProgressBar loading=findViewById(R.id.loading_bar);
        if (isNetwork) {
            Call<OffersListData> call = ApiClient.getApiInterface().getOfferList(CreateRequest());
            call.enqueue(new Callback<OffersListData>() {
                @Override
                public void onResponse(@NotNull Call<OffersListData> call, @NotNull Response<OffersListData> response) {
                    if (response.body() != null) {

                        loading.setVisibility(View.GONE);
                        list = new ArrayList<>();
                        list = response.body().getOffers();
                        setRecycler();
                    }
                }

                @Override
                public void onFailure(@NotNull Call<OffersListData> call, Throwable t) {

                }
            });
        }else Toast.makeText(this, "Network Not Available", Toast.LENGTH_SHORT).show();
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
        recyclerView = findViewById(R.id.offer_list_recycler);
        offerWallLayout = findViewById(R.id.offer_wall_layout);
        //toolBar
        toolBar = findViewById(R.id.tool_bar);
        back_bt = findViewById(R.id.back_bt);
        title_tv = findViewById(R.id.title_tv);


    }


    public void getData() {
        //this.securityToken=str;
        Bundle arg = getIntent().getExtras();
        if (arg != null) {
            securityToken = arg.getString(Constans.SECURITY_TOKEN);
            if (arg.getString(Constans.PROPERTY_SCREEN_BACKGROUND_COLOR) != null)
                setBackGround(arg.getString(Constans.PROPERTY_SCREEN_BACKGROUND_COLOR));
            if (arg.getString(Constans.PROPERTY_TASK_BAR_BACKGROUND_COLOR) != null)
                setTaskBarBackground(arg.getString(Constans.PROPERTY_TASK_BAR_BACKGROUND_COLOR));
            if (arg.getString(Constans.PROPERTY_ACTION_BAR_BACKGROUND_COLOR) != null)
                setActionBarBackground(arg.getString(Constans.PROPERTY_ACTION_BAR_BACKGROUND_COLOR));
            if (arg.getString(Constans.PROPERTY_ACTION_BAR_TITLE_COLOR) != null)
                setActionBarTitleColor(arg.getString(Constans.PROPERTY_ACTION_BAR_TITLE_COLOR));
            if (arg.getString(Constans.PROPERTY_ACTION_BAR_TITLE_TEXT) != null)
                setActionBarTitleText(arg.getString(Constans.PROPERTY_ACTION_BAR_TITLE_TEXT));
            if (arg.getString(Constans.PROPERTY_ACTION_BAR_BACK_BUTTON_COLOR) != null)
                setActionBarBackButtonColor(arg.getString(Constans.PROPERTY_ACTION_BAR_BACK_BUTTON_COLOR));

            //Log.d("TAG", "sdk_property: "+getOfferWallProperties("app","id"));

        }
    }

    private void setActionBarBackButtonColor(String color) {
        boolean isColor=new HexColorValidator().validate(color);
        if (isColor)back_bt.setColorFilter(Color.parseColor(color));

    }

    private void setActionBarTitleText(String title) {
        if (title != null)
            title_tv.setText(title);
    }

    private void setActionBarTitleColor(String color) {
        //  actionBar.setTitle(Html.fromHtml("<font color="+color+">"+mTitle+"</font>"));
        boolean isColor = new HexColorValidator().validate(color);
        if (isColor) title_tv.setTextColor(Color.parseColor(color));


    }

    private void setActionBarBackground(String color) {
        boolean isColor = new HexColorValidator().validate(color);
        if (isColor) toolBar.setBackgroundColor(Color.parseColor(color));
    }

    private void setTaskBarBackground(String color) {
        boolean isColor = new HexColorValidator().validate(color);
        if (isColor) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                getWindow().setStatusBarColor(Color.parseColor(color));
            }
        }
    }

    private void setBackGround(String color) {
       /* Pattern colorPattern = Pattern.compile("#([0-9a-f]{3}|[0-9a-f]{6}|[0-9a-f]{8})");
        Matcher m = colorPattern.matcher(color);
        boolean isColor = m.matches();*/
        boolean isColor = new HexColorValidator().validate(color);
        //Log.d("TAG", "setBackGround: "+new HexColorValidator().validate(color));
        if (isColor) offerWallLayout.setBackgroundColor(Color.parseColor(color));
    }

    private boolean isColor(String color) {
        Pattern colorPattern = Pattern.compile("#([0-9a-f]{3}|[0-9a-f]{6}|[0-9a-f]{8})");
        Matcher m = colorPattern.matcher(color);
        return m.matches();
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