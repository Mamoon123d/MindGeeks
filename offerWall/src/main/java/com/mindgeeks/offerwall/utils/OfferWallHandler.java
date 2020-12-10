package com.mindgeeks.offerwall.utils;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.mindgeeks.offerwall.OfferActivity;

import java.util.HashMap;
import java.util.Map;

public class OfferWallHandler extends CommonHandler {


    private Context context;
    private View handlerCustomerLayout;
    private ViewGroup container;


    public OfferWallHandler(Map<String, Object> var1, Context var2) {
        super(var1, var2);
        this.context = var2;
    }

    public void setHandlerContainer(ViewGroup var1) {
        this.container = var1;
    }

    public OfferWallHandler(Map<String, Object> var1, Context var2, ViewGroup var3) {
        super(var1, var2);
        this.context = var2;
        this.setHandlerContainer(var3);
    }

    public static Map<String, Object> getOfferWallProperties(String var0, String var1) {
        HashMap var2 = new HashMap();
        var2.put(Constans.SDK_APP_ID, var0);
        var2.put(Constans.SECURITY_TOKEN, var1);
        return var2;
    }

    public void startOfferWall() {
        //if (this.properties != null && this.properties.containsKey("unit_id"))
        if (this.properties != null && this.properties.containsKey(Constans.SDK_APP_ID)) {


            load();
        } else g.c("", "no unit id.");
    }

    public boolean load() {
        //  this.OfferWallHandler.
        if (this.properties != null && this.properties.containsKey(Constans.SDK_APP_ID)) {

            this.loadAppWall();
        } else g.c("", "no unit id.");
        return true;
    }

    private boolean loadAppWall() {
        try {
            //  if (this.handlerCustomerLayout != null) {
            // Log.d("TAG", "getOfferWallProperties: "+this.properties.get(Constans.SDK_APP_ID));
            this.properties.put("wall_entry", this.handlerCustomerLayout);
            Intent intent = new Intent(context, OfferActivity.class);
            Bundle arg = new Bundle();
            String var0 = (String) this.properties.get(Constans.SDK_APP_ID);
            Log.d("TAG", "loadAppWall: " + var0);
            arg.putString(Constans.SECURITY_TOKEN, var0);
            if (this.properties.containsKey(Constans.PROPERTY_SCREEN_BACKGROUND_COLOR))
                arg.putString(Constans.PROPERTY_SCREEN_BACKGROUND_COLOR, (String) this.properties.get(Constans.PROPERTY_SCREEN_BACKGROUND_COLOR));

            if (this.properties.containsKey(Constans.PROPERTY_TASK_BAR_BACKGROUND_COLOR))
                arg.putString(Constans.PROPERTY_TASK_BAR_BACKGROUND_COLOR, (String) this.properties.get(Constans.PROPERTY_TASK_BAR_BACKGROUND_COLOR));

            if (this.properties.containsKey(Constans.PROPERTY_ACTION_BAR_BACKGROUND_COLOR))
                arg.putString(Constans.PROPERTY_ACTION_BAR_BACKGROUND_COLOR, (String) this.properties.get(Constans.PROPERTY_ACTION_BAR_BACKGROUND_COLOR));

            if (this.properties.containsKey(Constans.PROPERTY_ACTION_BAR_TITLE_COLOR))
                arg.putString(Constans.PROPERTY_ACTION_BAR_TITLE_COLOR, (String) this.properties.get(Constans.PROPERTY_ACTION_BAR_TITLE_COLOR));

            if (this.properties.containsKey(Constans.PROPERTY_ACTION_BAR_TITLE_TEXT))
                arg.putString(Constans.PROPERTY_ACTION_BAR_TITLE_TEXT, (String) this.properties.get(Constans.PROPERTY_ACTION_BAR_TITLE_TEXT));

            if (this.properties.containsKey(Constans.PROPERTY_ACTION_BAR_BACK_BUTTON_COLOR))
                arg.putString(Constans.PROPERTY_ACTION_BAR_BACK_BUTTON_COLOR, (String) this.properties.get(Constans.PROPERTY_ACTION_BAR_BACK_BUTTON_COLOR));


            intent.putExtras(arg);
            context.startActivity(intent);
            // }

        } catch (Exception var2) {
            var2.printStackTrace();
        }
        return true;
    }
}
