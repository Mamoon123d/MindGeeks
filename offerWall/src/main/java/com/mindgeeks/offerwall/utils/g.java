package com.mindgeeks.offerwall.utils;

import android.util.Log;

public final class g {
    public static boolean c = true;
    public static boolean d=true;

    public static void c(String var0, String var1) {
      if (d){
          Log.d(var0, var1);
      }
    }
    static{
       if (!Constans.DEBUG){
          c=false;
          d=false;
       }
    }
}
