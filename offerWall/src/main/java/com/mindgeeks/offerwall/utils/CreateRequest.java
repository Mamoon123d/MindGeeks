package com.mindgeeks.offerwall.utils;

import android.content.Context;

import java.util.Map;

public abstract class CreateRequest {
  //  void requestData(String a,String b,String c,String d);
  protected Map<String, String> properties;
    protected Context context;

    public CreateRequest() {

    }

    public CreateRequest(Map<String, String> properties, Context context) {
        this.properties = properties;
        this.context = context;
    }
    public abstract boolean load();

    public abstract void release();
}
