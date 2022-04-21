package com.personal.physicswallahassignment;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class UserDataAdapter extends RecyclerView.Adapter<UserDataAdapter.ViewHolder> {

    // userDataList input for this adapter
    private Context context;
    List<UserDataModel> mUserData;
    public UserDataAdapter(Context context, List<UserDataModel> userData) {
        this.context=context;
    this.mUserData=userData;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_user_data_list_view,parent,false);
        return new ViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String name=mUserData.get(position).getName();
        holder.mPersonName.setText(name);
        String subject= mUserData.get(position).getSubject().get(0);

        holder.mSubject.setText(subject);

        String temp = mUserData.get(position).getQualification().toString();
        StringBuilder sb= new StringBuilder(temp);
        sb.deleteCharAt(temp.length() - 1);
        sb.deleteCharAt(0);
      String qualification = sb.toString();
        holder.mQualifications.setText(qualification);
        String imageUrl=mUserData.get(position).getImageURI();
        try {
            Glide.with(context).load(imageUrl).into(holder.imageView);

        }
        catch (Exception e){
            holder.imageView.setImageResource(R.drawable.ic_backgroud_person);
        }
        holder.mViewMoreBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newDetailIntent = new Intent(context, DetailsActivity.class);

                Bundle bundle = new Bundle();
//Add your data from getFactualResults method to bundle
                newDetailIntent.putExtra("name", name);
                newDetailIntent.putExtra("subject", subject);
                newDetailIntent.putExtra("qualification", qualification);
                newDetailIntent.putExtra("imageUrl", imageUrl);
                newDetailIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(newDetailIntent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return mUserData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        int  id;
        CircleImageView imageView;
        TextView mPersonName;
        TextView mSubject;
        TextView mQualifications;
        Button mViewMoreBtn;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView=itemView.findViewById(R.id.profile_img);
            mPersonName=itemView.findViewById(R.id.user_name);
            mSubject=itemView.findViewById(R.id.subjects);
            mQualifications=itemView.findViewById(R.id.qualifications);
            mViewMoreBtn=itemView.findViewById(R.id.view_profile);
        }
    }
}
