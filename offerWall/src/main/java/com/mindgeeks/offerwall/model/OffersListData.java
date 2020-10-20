package com.mindgeeks.offerwall.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class OffersListData {

    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("offers")
    @Expose
    private ArrayList<Offer> offers = null;
    @SerializedName("sliderData")
    @Expose
    private  ArrayList<SliderData> sliderData = null;


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public ArrayList<Offer> getOffers() {
        return offers;
    }

    public void setOffers(ArrayList<Offer> offers) {
        this.offers = offers;
    }

    public static class Offer {


        public Offer(String offerName) {
            this.offerName = offerName;
        }

        @SerializedName("offerId")
        @Expose
        private Integer offerId;
        @SerializedName("offerAmount")
        @Expose
        private String offerAmount;
        @SerializedName("offerName")
        @Expose
        private String offerName;
        @SerializedName("packageName")
        @Expose
        private String packageName;
        @SerializedName("payoutType")
        @Expose
        private String payoutType;
        @SerializedName("offerType")
        @Expose
        private String offerType;
        @SerializedName("imageUrl")
        @Expose
        private String imageUrl;
        @SerializedName("description")
        @Expose
        private String description;

        public Integer getOfferId() {
            return offerId;
        }

        public void setOfferId(Integer offerId) {
            this.offerId = offerId;
        }

        public String getOfferAmount() {
            return offerAmount;
        }

        public void setOfferAmount(String offerAmount) {
            this.offerAmount = offerAmount;
        }

        public String getOfferName() {
            return offerName;
        }

        public void setOfferName(String offerName) {
            this.offerName = offerName;
        }

        public String getPackageName() {
            return packageName;
        }

        public void setPackageName(String packageName) {
            this.packageName = packageName;
        }

        public String getPayoutType() {
            return payoutType;
        }

        public void setPayoutType(String payoutType) {
            this.payoutType = payoutType;
        }

        public String getOfferType() {
            return offerType;
        }

        public void setOfferType(String offerType) {
            this.offerType = offerType;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

    }

    public ArrayList<SliderData> getSliderData() {
        return sliderData;
    }

    public void setSliderData(ArrayList<SliderData> sliderData) {
        this.sliderData = sliderData;
    }
    public static class SliderData {

        @SerializedName("offerId")
        @Expose
        private Integer offerId;
        @SerializedName("imageUrl")
        @Expose
        private String imageUrl;

        public Integer getOfferId() {
            return offerId;
        }

        public void setOfferId(Integer offerId) {
            this.offerId = offerId;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

    }

}