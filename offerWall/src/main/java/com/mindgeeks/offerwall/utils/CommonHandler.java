package com.mindgeeks.offerwall.utils;

import android.content.Context;

import java.util.Map;

public class CommonHandler {
    protected Map<String, Object> properties;
    protected Context context;

    public CommonHandler() {
    }

    public CommonHandler(Map<String, Object> properties, Context context) {
        this.properties = properties;
        this.context = context;
    }

}
