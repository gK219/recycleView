package com.personal.physicswallahassignment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

public class DetailsActivity extends AppCompatActivity {
    private String name,subs,qualifications,imageURI;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            //getData
            name = bundle.getString("name");
            imageURI = bundle.getString("imageUrl");
            qualifications = bundle.getString("qualification");
            subs = bundle.getString("subject");



        }
        openFragment();
    }

    private void openFragment() {
        DetailsFragment fragment=new DetailsFragment();
        FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
        Bundle data = new Bundle();
        data.putString("name",name);
        data.putString("imageUrl",imageURI);
        data.putString("qualification",qualifications);
        data.putString("subject",subs);
        fragment.setArguments(data);
        fragmentTransaction.replace(R.id.details_container,fragment).commit();
    }
}