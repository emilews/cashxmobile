package com.emilews.cashxmobile;

import android.content.Context;
import android.os.Bundle;

import com.bitcoin.slpwallet.Network;

import com.emilews.cashxmobile.utils.MnemonicUtil;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.bitcoin.slpwallet.SLPWallet;


public class MainActivity extends AppCompatActivity {
    private String DATAFILENAME = "store.csi";
    private static SLPWallet wallet;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadWallet();

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupWithNavController(navView, navController);

    }




    public void loadWallet(){
        try {
            FileInputStream fis = openFileInput(DATAFILENAME);
            BufferedReader br = new BufferedReader(new InputStreamReader(new BufferedInputStream(fis)));
            String line = "";
            StringBuilder sb = new StringBuilder();
            while((line = br.readLine()) != null){
                sb.append(line);
            }
            if(sb.toString() == null){
                createWallet();
            }
            br.close();
            String[] a = sb.toString().split(",");
            List<String> wordlist = new ArrayList<>();
            for(String s : a){
                wordlist.add(s);
            }
            wallet = SLPWallet.Companion.fromMnemonic(this, Network.MAIN, wordlist, false);
        } catch (FileNotFoundException e) {
            createWallet();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public void createWallet(){
        String wordlist = MnemonicUtil.getNewMnemonic();
        wallet = SLPWallet.Companion.fromMnemonic(this, Network.MAIN, wordlist, true);
        saveWallet();
    }

    public void saveWallet(){
        try{
            FileOutputStream fos = openFileOutput(DATAFILENAME, Context.MODE_PRIVATE);
            List<String> wordlist = wallet.getMnemonic();
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < wordlist.size(); i++){
                if(i == wordlist.size()){
                    sb.append(wordlist.get(i));
                }else{
                    sb.append(wordlist.get(i)+ ",");
                }
            }
            fos.write(sb.toString().getBytes());
            fos.close();
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public static List<String> getMnemonic(){
        return wallet.getMnemonic();
    }

    public static String getBchAddress(){
        return wallet.getBchAddress();
    }
    public static Object getBalance(){
        return wallet.getBalance();
    }

}
