package com.mindgeeks.offerwall.model;

public class UserRequest {
 /*{"userId"=>"425544",
            "securityToken"=>"e9b6a29a-ea81-41d8-b664-5ea7eb17dccd",
            "versionName"=>"1.2",
            "versionCode"=>"2"}*/

    private String userId;
    private String securityToken;
    private String versionName;
    private String versionCode;
    private int offerId;
    private int storeId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getSecurityToken() {
        return securityToken;
    }

    public void setSecurityToken(String securityToken) {
        this.securityToken = securityToken;
    }

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public String getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(String versionCode) {
        this.versionCode = versionCode;
    }

    public int getOfferId() {
        return offerId;
    }

    public void setOfferId(int offerId) {
        this.offerId = offerId;
    }


    public int getStoreId() {
        return storeId;
    }

    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }
}
