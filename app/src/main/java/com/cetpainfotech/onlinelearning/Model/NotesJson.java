package com.cetpainfotech.onlinelearning.Model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Dell on 22-Jan-18.
 */

public class NotesJson {
    public static String[] name;
    public static String[] url;
    private static String json;


    public NotesJson(String json) {
        this.json = json;
    }

    public static void notejson(){
        JSONObject noteObject=null;
        try {
            noteObject=new JSONObject(json);
            JSONArray jsonArray=noteObject.getJSONArray("pdf");
            name=new String[jsonArray.length()];
            url=new String[jsonArray.length()];
            for (int i = 0; i <jsonArray.length() ; i++) {
                JSONObject jsonObject1=jsonArray.getJSONObject(i);
                name[i]=jsonObject1.getString("name");
                url[i]=jsonObject1.getString("url");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
