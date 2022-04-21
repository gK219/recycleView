package com.personal.physicswallahassignment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import de.hdodenhof.circleimageview.CircleImageView;


public class DetailsFragment extends Fragment {

     String mName,mSub,mQuali,mImageUri;
    private TextView mUserName,mSubject,mQualification;
    private CircleImageView mImageView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_details, container, false);
        mUserName = view.findViewById(R.id.username);
        mSubject = view.findViewById(R.id.sub);
        mQualification = view.findViewById(R.id.qualifications);
        mImageView = view.findViewById(R.id.profileimg);
        Bundle  data = getArguments();
        if(data != null){
            mName = data.getString("name");
            mImageUri = data.getString("imageUrl");
            mQuali = data.getString("qualification");
            mSub = data.getString("subject");

            //setData
            mUserName.setText(mName);
            mQualification.setText(mQuali);
            mSubject.setText(mSub);
            try {
                Glide.with(this).load(mImageUri).into(mImageView);

            }
            catch (Exception e){
                mImageView.setImageResource(R.drawable.ic_backgroud_person);
            }
        }
        return view;


    }
}