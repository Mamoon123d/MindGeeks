package com.mindgeeks.offerwall.network;

import com.mindgeeks.offerwall.model.OffersListData;
import com.mindgeeks.offerwall.model.UserRequest;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiInterface {

    //------------------------offer-----------------------------------
    @POST("scofferList")
    Call<OffersListData> getOfferList(@Body UserRequest userRequest);

   /* @POST("scofferDetails")
    Call<OfferDetailData>getOfferDetails(@Body UserRequest userRequest);

    @POST("scofferClicked")
    Call<OfferEventData>getOfferEvent(@Body UserRequest userRequest);*/


}
