package com.personal.physicswallahassignment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<UserDataModel> mUserData=new ArrayList<>();
    String url="https://my-json-server.typicode.com/easygautam/data/users";
    UserDataAdapter  adapter;

    RecyclerView mRecyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = findViewById(R.id.user_data_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setHasFixedSize(true);
        //get the data using volley  from url json
        getData();
    }

    private void getData() {
        ProgressDialog progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Loading the data...");
        progressDialog.show();

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest= new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                progressDialog.dismiss();
                for (int i=0;i<response.length();i++){
                    try {
                        JSONObject jsonObject=response.getJSONObject(i);
                       int id= jsonObject.getInt("id");
                              String name=  jsonObject.getString("name");
                            JSONArray jsonSubject=   jsonObject.getJSONArray("subjects");
                               JSONArray jsonQualificaition =jsonObject.getJSONArray("qualification");
                               String image= jsonObject.getString("profileImage");
                               ArrayList<String> sub = new ArrayList<>(jsonSubject.length());
                               ArrayList<String> qualification = new ArrayList<>(jsonQualificaition.length());
                               for(int j =0;j<jsonSubject.length();j++){
                                 sub.add(j,jsonSubject.getString(j));
                               }
                        for(int j =0;j<jsonSubject.length();j++){
                            sub.add(j,jsonSubject.getString(j));
                        }

                        for(int j =0;j<jsonQualificaition.length();j++){
                            qualification.add(j,jsonQualificaition.getString(j));

                        }
                        mUserData.add(new UserDataModel(id,name,sub,qualification,image));
                        adapter=new UserDataAdapter(getApplicationContext(),mUserData);
                        mRecyclerView.setAdapter(adapter);
                        adapter.notifyDataSetChanged();

                    } catch (JSONException e) {
                        e.printStackTrace();
                        progressDialog.dismiss();

                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(MainActivity.this, ""+error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(jsonArrayRequest);
    }
}