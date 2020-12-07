package com.mindgeeks.offerwall;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.mindgeeks.offerwall.adapter.PayOutAdapter;
import com.mindgeeks.offerwall.model.OfferDetailData;
import com.mindgeeks.offerwall.model.OfferEventData;
import com.mindgeeks.offerwall.model.UserRequest;
import com.mindgeeks.offerwall.network.ApiClient;
import com.mindgeeks.offerwall.utils.NetworkHelper;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OfferDetailsActivity extends AppCompatActivity {

    public static final String REWARDS = "rewards";
    public static final String IMAGE_URL = "imageUrl";
    public static final String PACKAGE_NAME = "packageName";
    public static final String OFFER_ID = "offerId";
    public static final String DESCRIPTION = "description";
    public static final String TITLE = "title";
    public static final int REQUEST_CODE = 121;


    //text view
    private TextView title_tv, des_tv, instructions_tv, upAmount_tv;
    private Button share_btn;
    private ListView payload_listView;
    private ImageView appImage;
    private int offerId;

    //utils
    private boolean isNetworkOk;
    private ProgressBar prograssBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offer_details);
        isNetworkOk = NetworkHelper.isNetworkAvailable(this);

        init();
        setData();
        CallApi(CreateRequest());

    }

    private void CallApi(UserRequest userRequest) {
        if (isNetworkOk) {
            Call<OfferDetailData> detailDataCall = ApiClient.getApiInterface().getOfferDetails(userRequest);
            detailDataCall.enqueue(new Callback<OfferDetailData>() {
                @SuppressLint("SetTextI18n")
                @Override
                public void onResponse(@NotNull Call<OfferDetailData> call, @NotNull Response<OfferDetailData> response) {
                    // Log.d(TAG, "onResponse: moon" + new Gson().toJson(response.body()));
                    // SharedPreferences sharedPreferences = getSharedPreferences(MainActivity.UserPreferences, Context.MODE_PRIVATE);

                    //String offerName=response.body().getOfferDetails().getOfferName();
                    assert response.body() != null;
                    OfferDetailData data = response.body();

                    title_tv.setText(response.body().getOfferDetails().getOfferName());
                    des_tv.setText(response.body().getOfferDetails().getDescription());
                    //task_tv.setText(task);
                    instructions_tv.setText(data.getOfferDetails().getDescriptionLoc());
                    List<OfferDetailData.Instruction> list;

                    list = data.getOfferDetails().getInstructions();
                    PayOutAdapter adapter = new PayOutAdapter(OfferDetailsActivity.this, list);
                    payload_listView.setAdapter(adapter);

                    upAmount_tv.setText("Upto " + getString(R.string.rupees) + " " + data.getOfferDetails().getOfferAmount());

                    Picasso.get().load(response.body().getOfferDetails().getImageUrl()).into(appImage);


                    setShareIntent(userRequest);
                }

                private void setShareIntent(UserRequest userRequest) {
                    share_btn.setOnClickListener(v -> {
                        if (isNetworkOk) {


                            prograssBar.setVisibility(View.VISIBLE);
                            Call<OfferEventData> eventDataCall = ApiClient.getApiInterface().getOfferEvent(userRequest);

                            eventDataCall.enqueue(new Callback<OfferEventData>() {
                                @Override
                                public void onResponse(@NotNull Call<OfferEventData> call, @NotNull Response<OfferEventData> response) {
                                    // String url = "http://www.google.com";
                                    prograssBar.setVisibility(View.VISIBLE);

                                    Intent i = new Intent(Intent.ACTION_VIEW);
                                    assert response.body() != null;
                                    i.setData(Uri.parse(response.body().getActionUrl()));
                                    startActivity(i);

                                }

                                @Override
                                public void onFailure(@NotNull Call<OfferEventData> call, @NotNull Throwable t) {
                                    Toast.makeText(OfferDetailsActivity.this, "something wrong", Toast.LENGTH_SHORT).show();
                                }
                            });
                        } else
                            Toast.makeText(OfferDetailsActivity.this, "Network is not available", Toast.LENGTH_SHORT).show();

                    });
                }

                @Override
                public void onFailure(@NotNull Call<OfferDetailData> call, @NotNull Throwable t) {

                }
            });

        } else
            Toast.makeText(OfferDetailsActivity.this, "Network is not available", Toast.LENGTH_SHORT).show();

    }

    public UserRequest CreateRequest() {

        UserRequest userRequest = new UserRequest();
        userRequest.setUserId(String.valueOf(2));
        userRequest.setVersionName("1.0");
        userRequest.setVersionCode("1");
        userRequest.setSecurityToken("462ed61f-5363-495d-b73e-f50f54af7650");
        userRequest.setOfferId(offerId);
        return userRequest;
    }

    private void init() {
        //task_tv = findViewById(R.id.task_tv);
        title_tv = findViewById(R.id.offer_title_tv);
        des_tv = findViewById(R.id.offer_des_tv);
        // rec_reward_tv = findViewById(R.id.receive_rewards_tv);
        instructions_tv = findViewById(R.id.instructions_tv);
        upAmount_tv = findViewById(R.id.offer_upto_amount_tv);
        share_btn = findViewById(R.id.share_btn);
        appImage = findViewById(R.id.offer_app_image);
        payload_listView = findViewById(R.id.payload_list);
        prograssBar = findViewById(R.id.progressBar);

    }

    private void setData() {
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            offerId = bundle.getInt(OFFER_ID);
            //des = bundle.getString(DESCRIPTION);
            //title = bundle.getString(TITLE);
            //String task = bundle.getString(TASK);
            // rewards = bundle.getString(REWARDS);
            //image_url = bundle.getString(IMAGE_URL);
            //packageName = bundle.getString(PACKAGE_NAME);


        }

    }
}