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
import com.google.android.material.snackbar.Snackbar;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private static TextView bch_balance_text;
    private static View rt;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);


        Button bchSendButton = root.findViewById(R.id.bchSendButton);
        bchSendButton.setOnClickListener(e -> {
           MainActivity.bchPressedSend();
        });

        bch_balance_text = root.findViewById(R.id.bchBalanceQuantity);
        bch_balance_text.setText(MainActivity.getBchBalance());
        Button bchUpdateButton = root.findViewById(R.id.bchUpdateButton);


        bchUpdateButton.setOnClickListener(e -> {
            MainActivity.UpdateBCH();
        });
        rt = root;
        return root;
    }
    public static void updateBchUI(){
        if(!MainActivity.getBchBalance().equals(String.valueOf(bch_balance_text.getText()))){
            bch_balance_text.setText(MainActivity.getBchBalance());
        }
    }



}