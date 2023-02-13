package com.example.webview;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Log;
import android.webkit.JavascriptInterface;

import com.google.firebase.analytics.FirebaseAnalytics;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.Iterator;

public class AnalyticsWebInterface {

    public static final String TAG = "AnalyticsWebInterface";
    private FirebaseAnalytics mAnalytics;
    JSONObject jsonObject = new JSONObject();
    JSONArray jsonArray;
    JSONObject jsonItemObj, jObj;
    private Bundle final_bundle;

    public AnalyticsWebInterface(Context context) {
        mAnalytics = FirebaseAnalytics.getInstance(context);
    }

    @JavascriptInterface
    public void logEvent(String name, String jsonParams) throws JSONException {
        LOGD("logEvent:" + name);
        LOGD("Params:" + jsonParams);
        jsonItemObj = new JSONObject(jsonParams);
        LOGD(String.valueOf(jsonItemObj.getJSONObject("ecommerce")));
        mAnalytics.logEvent(name, bundleFromJson(jsonItemObj));
    }

    @JavascriptInterface
    public void setUserProperty(String name, String value) {
        LOGD("setUserProperty:" + name);
        mAnalytics.setUserProperty(name, value);
    }

    private void LOGD(String message) {
        // Only log on debug builds, for privacy
        if (BuildConfig.DEBUG) {
            Log.d(TAG, message);
        }
    }

    private Bundle bundleFromJson(JSONObject json) {

        /*if (TextUtils.isEmpty(json)) {
            return new Bundle();
        }
*/

        Log.d("AAKHO_JSON", "" + json);

        Bundle bundle = new Bundle();
        try {
//            JSONObject jsonObject = new JSONObject(json);
            /* JSONObject jobjEcom*/
            json = jsonItemObj.getJSONObject("ecommerce");
            JSONArray jArrayItems = json.getJSONArray("items");

            int arrayLength = jArrayItems.length();

            for (int i = 0; i < arrayLength; i++) {

                jObj = jArrayItems.getJSONObject(i);
                Log.d("HERE_IS_DATA", "bundleFromJson: " + jObj.keys().toString());
            }


            Iterator<String> item_keys = jObj.keys();
            Iterator<String> item_keys_map = jObj.keys();
            Iterator<String> item_keys_map_3 = jObj.keys();
            Bundle product_bundle = new Bundle();
            final_bundle = new Bundle();

            HashMap<String, String> hm = new HashMap<String, String>();

            while (item_keys.hasNext()) {
                String key = item_keys.next();
                Object item_value = jObj.get(key);

                if (key.equals("item_list_name")) {
                    final_bundle.putString(FirebaseAnalytics.Param.ITEM_LIST_NAME, item_value.toString());
                }
                if (key.equals("item_list_id")) {
                    final_bundle.putString(FirebaseAnalytics.Param.ITEM_LIST_ID, item_value.toString());
                }
                if (key.equals("affiliation")) {
                    final_bundle.putString(FirebaseAnalytics.Param.AFFILIATION, item_value.toString());
                }
                if (key.equals("coupon")) {
                    final_bundle.putString(FirebaseAnalytics.Param.COUPON, item_value.toString());
                }
                if (key.equals("currency")) {
                    final_bundle.putString(FirebaseAnalytics.Param.CURRENCY, item_value.toString());
                }
                if (key.equals("location_id")) {
                    final_bundle.putString(FirebaseAnalytics.Param.LOCATION_ID, item_value.toString());
                }
                if (key.equals("promotion_id")) {
                    final_bundle.putString(FirebaseAnalytics.Param.PROMOTION_ID, item_value.toString());
                }
                if (key.equals("promotion_name")) {
                    final_bundle.putString(FirebaseAnalytics.Param.PROMOTION_NAME, item_value.toString());
                }
            }


            for (int i = 0; i < arrayLength; i++) {

                Bundle product_bundles = new Bundle();


                while (item_keys_map_3.hasNext()) {
                    String key = item_keys_map_3.next();
                    Object item_value = jObj.get(key);

                    hm.put(key, item_value.toString());
                    product_bundles.putString(FirebaseAnalytics.Param.ITEM_ID, hm.get("item_id"));
                    product_bundles.putString(FirebaseAnalytics.Param.ITEM_NAME, hm.get("item_name"));
                    product_bundles.putString(FirebaseAnalytics.Param.ITEM_CATEGORY, hm.get("item_category"));
                    product_bundles.putString(FirebaseAnalytics.Param.ITEM_CATEGORY2, hm.get("item_category2"));
                    product_bundles.putString(FirebaseAnalytics.Param.ITEM_CATEGORY3, hm.get("item_category3"));
                    product_bundles.putString(FirebaseAnalytics.Param.ITEM_CATEGORY4, hm.get("item_category4"));
                    product_bundles.putString(FirebaseAnalytics.Param.ITEM_CATEGORY5, hm.get("item_category5"));
                    product_bundles.putString(FirebaseAnalytics.Param.ITEM_VARIANT, hm.get("item_variant"));
                    product_bundles.putString(FirebaseAnalytics.Param.ITEM_BRAND, hm.get("item_brand"));
                    product_bundles.putString(FirebaseAnalytics.Param.INDEX, hm.get("index"));
                    product_bundles.putString(FirebaseAnalytics.Param.PRICE, hm.get("price"));
                    product_bundles.putString(FirebaseAnalytics.Param.QUANTITY, hm.get("quantity"));

                }
                Log.d("ABC", "bundleFromJson: " + product_bundles);

            }

            while (item_keys_map.hasNext()) {
                String key = item_keys_map.next();
                Object item_value = jObj.get(key);

                hm.put(key, item_value.toString());
                product_bundle.putString(FirebaseAnalytics.Param.ITEM_ID, hm.get("item_id"));
                product_bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, hm.get("item_name"));
                product_bundle.putString(FirebaseAnalytics.Param.ITEM_CATEGORY, hm.get("item_category"));
                product_bundle.putString(FirebaseAnalytics.Param.ITEM_CATEGORY2, hm.get("item_category2"));
                product_bundle.putString(FirebaseAnalytics.Param.ITEM_CATEGORY3, hm.get("item_category3"));
                product_bundle.putString(FirebaseAnalytics.Param.ITEM_CATEGORY4, hm.get("item_category4"));
                product_bundle.putString(FirebaseAnalytics.Param.ITEM_CATEGORY5, hm.get("item_category5"));
                product_bundle.putString(FirebaseAnalytics.Param.ITEM_VARIANT, hm.get("item_variant"));
                product_bundle.putString(FirebaseAnalytics.Param.ITEM_BRAND, hm.get("item_brand"));
                product_bundle.putString(FirebaseAnalytics.Param.INDEX, hm.get("index"));
                product_bundle.putString(FirebaseAnalytics.Param.PRICE, hm.get("price"));
                product_bundle.putString(FirebaseAnalytics.Param.QUANTITY, hm.get("quantity"));
            }




           /* for (int i = 0; i < arrayLength; i++) {
                String key = item_keys_map.next();

                Object item_value = jObj.get(key);

                hm.put(key, item_value.toString());
                product_bundle.putString(FirebaseAnalytics.Param.ITEM_ID, hm.get("item_id"));
                product_bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, hm.get("item_name"));
                product_bundle.putString(FirebaseAnalytics.Param.ITEM_CATEGORY, hm.get("item_category"));
                product_bundle.putString(FirebaseAnalytics.Param.ITEM_CATEGORY2, hm.get("item_category2"));
                product_bundle.putString(FirebaseAnalytics.Param.ITEM_CATEGORY3, hm.get("item_category3"));
                product_bundle.putString(FirebaseAnalytics.Param.ITEM_CATEGORY4, hm.get("item_category4"));
                product_bundle.putString(FirebaseAnalytics.Param.ITEM_CATEGORY5, hm.get("item_category5"));
                product_bundle.putString(FirebaseAnalytics.Param.ITEM_VARIANT, hm.get("item_variant"));
                product_bundle.putString(FirebaseAnalytics.Param.ITEM_BRAND, hm.get("item_brand"));
                product_bundle.putString(FirebaseAnalytics.Param.INDEX, hm.get("index"));
                product_bundle.putString(FirebaseAnalytics.Param.PRICE, hm.get("price"));
                product_bundle.putString(FirebaseAnalytics.Param.QUANTITY, hm.get("quantity"));
            }*/


            final_bundle.putParcelableArray(FirebaseAnalytics.Param.ITEMS, new Parcelable[]{product_bundle});
            Log.d("PRO_BUNDLE", "bundleFromJson: " + final_bundle);

            Log.d("dfhsjbfgjd", "bundleFromJson: " + json.toString());
            Log.d("dfsdfdsf", "bundleFromJson: " + jArrayItems);
//            JSONArray jsArray = jsonItemObj.getJSONArray("ecommerce");
//            Log.d("dfhsjbfgjd", "bundleFromJson: "+jsArray.toString());
            Iterator<String> keys = jsonObject.keys();

            while (keys.hasNext()) {
                String key = keys.next();
                Object value = jsonObject.get(key);
                Object item_value = jObj.get(key);

//                if(key == "item_list_name"){
//                    bundle.putString(FirebaseAnalytics.Param.ITEM_LIST_ID, value.toString());
//                }
                if (value instanceof String) {
                    bundle.putString(key, (String) value);
                } else if (value instanceof Integer) {
                    bundle.putInt(key, (Integer) value);
                } else if (value instanceof Double) {
                    bundle.putDouble(key, (Double) value);
                } else if (value instanceof Array) {
                    bundle.putParcelableArray(FirebaseAnalytics.Param.ITEMS,
                            new Parcelable[]{});
                } else {
                    Log.w(TAG, "Value for key " + key + " not one of [String, Integer, Double]");
                }
            }
            Log.d(TAG, "bundleFromJsonttt: " + jsonObject);
        } catch (JSONException e) {
            Log.w(TAG, "Failed to parse JSON, returning empty Bundle.", e);
            return new Bundle();
        }

        return final_bundle;
        // [END_EXCLUDE]
    }

}
