package com.mindgeeks.offerwall.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class OfferDetailData {
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("offer_details")
    @Expose
    private OfferDetails offerDetails;
    // private ArrayList<OfferDetails> offerDetails=null;

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

    public OfferDetails getOfferDetails() {
        return offerDetails;
    }

    public void setOfferDetails(OfferDetails offerDetails) {
        this.offerDetails = offerDetails;
    }

    public static class OfferDetails {

        public OfferDetails(String offerName) {
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
        @SerializedName("payoutType")
        @Expose
        private String payoutType;
        @SerializedName("offerType")
        @Expose
        private String offerType;
        @SerializedName("imageUrl")
        @Expose
        private String imageUrl;
        @SerializedName("packageName")
        @Expose
        private String packageName;
        @SerializedName("description")
        @Expose
        private String description;
        @SerializedName("descriptionLoc")
        @Expose
        private String descriptionLoc;
        @SerializedName("payoutSteps")
        @Expose
        private List<Instruction> instructions = null;

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

        public String getPackageName() {
            return packageName;
        }

        public void setPackageName(String packageName) {
            this.packageName = packageName;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getDescriptionLoc() {
            return descriptionLoc;
        }

        public void setDescriptionLoc(String descriptionLoc) {
            this.descriptionLoc = descriptionLoc;
        }

        public List<Instruction> getInstructions() {
            return instructions;
        }

        public void setInstructions(List<Instruction> instructions) {
            this.instructions = instructions;
        }

    }

    public static class Instruction {
        @SerializedName("propertyName")
        @Expose
        private String propertyName;
        @SerializedName("propertyValue")
        @Expose
        private String propertyValue;

        public String getPropertyName() {
            return propertyName;
        }

        public void setPropertyName(String propertyName) {
            this.propertyName = propertyName;
        }

        public String getPropertyValue() {
            return propertyValue;
        }

        public void setPropertyValue(String propertyValue) {
            this.propertyValue = propertyValue;
        }
    }
}
