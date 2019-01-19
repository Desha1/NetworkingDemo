package com.example.nh.networkingdemo;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView allUsersList;
    private ProgressBar loader;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*User user = new User(1,"omnia","omnia","omnia.rezeka@yahoo.com");
        JSONObject userjson = new JSONObject();
        try {
            userjson.put("id",user.getId());
            userjson.put("name",user.getName());
            userjson.put("username",user.getUsername());
            userjson.put("email",user.getEmail());

            Log.e("userjson",userjson.toString());

        } catch (JSONException e) {
            e.printStackTrace();
        }

        JSONArray jsonArray = new JSONArray();
        jsonArray.put(userjson);
        jsonArray.put(userjson);
        Log.e("jsonArray",jsonArray.toString());*/

        allUsersList = findViewById(R.id.all_users_list);
        loader = findViewById(R.id.loader);


        final JsonParser parser = new JsonParser();

        new AsyncTask<String, Integer, String>() {

            @Override
            protected void onPreExecute() {
                loader.setVisibility(View.VISIBLE);
            }

            @Override
            protected String doInBackground(String... strings) {
                publishProgress(1,2,3,4);
                return parser.getAllUsers(strings[0]);
            }

            @Override
            protected void onPostExecute(String s) {
                loader.setVisibility(View.GONE);
                Log.e("data",s);
                ArrayList<User> allUsers = parser.convertDataToList(s);
                ArrayAdapter<User> adapter = new ArrayAdapter<User>(MainActivity.this,android.R.layout.simple_list_item_1,allUsers);
                allUsersList.setAdapter(adapter);
            }

            @Override
            protected void onProgressUpdate(Integer... values) {
                for (int i=0;i<values.length;i++){
                    Log.e("data",values[i]+"");
                }
            }
        }.execute("https://jsonplaceholder.typicode.com/users");

        //parser.getAllUsers();

    }
}
