package com.write.kaku.kaku.SSM_View_Fragements;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.write.kaku.kaku.MainViewActivity;
import com.write.kaku.kaku.ManualActivity;
import com.write.kaku.kaku.R;
import com.write.kaku.kaku.WriteActivity;
import com.write.kaku.kaku.WritePostActivity;

public class WriteFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setTitle("Write");
        /*if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }*/
        Log.d("WriteFragment","onCreate");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //fragment view 가져오기
        View view = inflater.inflate(R.layout.fragment_write, container, false);
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        Button logout_button = (Button)view.findViewById(R.id.logout_button);
        ImageButton write_button = (ImageButton)view.findViewById(R.id.Writing);

        write_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent((MainViewActivity)getActivity(), WritePostActivity.class);
                intent.putExtra("User", mAuth.getCurrentUser().getUid());
                Log.d("CurrentUser",mAuth.getCurrentUser().getEmail());
                //Log.d("DatabaseCheck", mDatabase.get)
                startActivity(intent);

            }
        });

        logout_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();
                Intent intent = new Intent((MainViewActivity)getActivity(), ManualActivity.class);
                startActivity(intent);

                Log.d("WriteFragment","click");
            }
        });

        return view;
    }



}
