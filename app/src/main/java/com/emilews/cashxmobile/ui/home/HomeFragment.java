package com.emilews.cashxmobile.ui.home;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.emilews.cashxmobile.MainActivity;
import com.emilews.cashxmobile.R;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);


        Button bchSendButton = root.findViewById(R.id.bchSendButton);
        bchSendButton.setOnClickListener(e -> {
           MainActivity.bchPressedSend();
        });

        TextView balance = root.findViewById(R.id.bchBalanceQuantity);
        Button bchUpdateButton = root.findViewById(R.id.bchUpdateButton);


        bchUpdateButton.setOnClickListener(e -> {
            MainActivity.UpdateBCH();
            StringBuilder sb = new StringBuilder();
            System.out.println(sb.toString());
            balance.setText(sb.toString());
        });



        homeViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {

            }
        });
        return root;
    }



}