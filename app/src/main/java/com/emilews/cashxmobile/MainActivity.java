package com.emilews.cashxmobile;

import android.content.Context;
import android.os.Bundle;

import com.bitcoin.slpwallet.Network;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.bitcoin.slpwallet.SLPWallet;

public class MainActivity extends AppCompatActivity {
    private String DATAFILENAME = "store.csi";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupWithNavController(navView, navController);
    }
    public void loadData(){
        try {
            FileOutputStream fos = openFileOutput(DATAFILENAME, Context.MODE_PRIVATE);
        } catch (FileNotFoundException e) {
            createData();
        }
    }
    public void createData(){
        SLPWallet wallet = SLPWallet.Companion.fromMnemonic();
    }

}
