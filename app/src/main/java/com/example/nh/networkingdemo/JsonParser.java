package com.example.nh.networkingdemo;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

public class JsonParser {

    public String getAllUsers(){
        StringBuilder dataStringBuilder = new StringBuilder();
        try {
            URL url = new URL("https://jsonplaceholder.typicode.com/users");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            if (connection.getResponseCode() == 200) {
                Scanner sc = new Scanner(connection.getInputStream());
                while (sc.hasNextLine()) {
                    dataStringBuilder.append(sc.nextLine());
                }
            }
        } catch (MalformedURLException e) {
            //e.printStackTrace();
            Log.e("invalid url","invalid url");
        } catch (IOException e) {
            Log.e("connection error","can not open connection to this url");
        }
        return dataStringBuilder.toString();
    }

    public ArrayList<User> convertDataToList(String data){
        ArrayList<User> allUsers = new ArrayList<>();
        try {
            JSONArray dataArray = new JSONArray(data);
            JSONObject item;
            User user;
            for(int i = 0; i<dataArray.length();i++){
                item = (JSONObject) dataArray.get(i);
                int id = item.getInt("id");
                String name = item.getString("name");
                String username = item.getString("username");
                String email = item.getString("email");
                user = new User(id,name,username,email);
                allUsers.add(user);
            }
        } catch (JSONException e) {
            Log.e("json Array","Not json array format");
        }
        return allUsers;
    }
}
