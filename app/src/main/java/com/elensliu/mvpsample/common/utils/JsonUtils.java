package com.elensliu.mvpsample.common.utils;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Created by elensliu on 2016/11/21.
 */

public class JsonUtils {


    private final static JsonUtils UTILS = new JsonUtils ();
    private boolean isFilter = true;
    private GsonBuilder builder = new GsonBuilder ();

    {
        builder.setExclusionStrategies (new ExclusionStrategy () {
            @Override
            public boolean shouldSkipField (FieldAttributes f) {

                if (f.getName ()
                     .equals ("token")) {
                    return true;
                }
                return false;
            }

            @Override
            public boolean shouldSkipClass (Class<?> incomingClass) {
                return false;
            }
        });
    }

    private Gson gson = builder.create ();


    private JsonUtils() {

    }


    public static JsonUtils build () {

        return UTILS;
    }


    public static Gson gson () {
        return UTILS.gson;
    }


}
