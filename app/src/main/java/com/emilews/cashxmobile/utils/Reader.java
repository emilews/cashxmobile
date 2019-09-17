package com.emilews.cashxmobile.utils;

import android.content.Context;

import com.bitcoin.slpwallet.Network;
import com.bitcoin.slpwallet.SLPWallet;
import com.emilews.cashxmobile.common.Constants;

import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Reader {
    /*
       This is just a class to read saved data, it does everything in the private app space, that's
       why it needs an android Context, as it uses it to access the relevant methods and spaces.
       To use this, just call the methods, since it doesn't need an instance.
     */
    public static JSONObject readUserData(){
        return null;
    }

    public static List<String> readWalletSeed(Context context){
        List<String> wordlist = new ArrayList<>();
        try {
            FileInputStream fis = context.openFileInput(Constants.getSeedFileName());
            BufferedReader br = new BufferedReader(new InputStreamReader(new BufferedInputStream(fis)));
            String line = "";
            StringBuilder sb = new StringBuilder();
            while((line = br.readLine()) != null){
                sb.append(line);
            }
            if(sb.toString() == null){
                return null;
            }
            br.close();
            String[] a = sb.toString().split(",");
            for(String s : a){
                wordlist.add(s);
            }
            return wordlist;
        } catch (FileNotFoundException e) {
            return null;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }





}
