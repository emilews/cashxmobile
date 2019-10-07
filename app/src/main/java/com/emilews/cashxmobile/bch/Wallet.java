package com.emilews.cashxmobile.bch;

import android.content.Context;

import com.bitcoin.slpwallet.SLPWallet;
import com.emilews.cashxmobile.observers.BalanceObserver;
import com.emilews.cashxmobile.observers.CustomObserver;
import com.emilews.cashxmobile.utils.WebUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class Wallet{
    /**
     * This class tries to abstract some things from a BCH/SLP wallet and adapts them.
     */


    //Observers array
    private static List<CustomObserver> OBSERVERS_ARRAY = new ArrayList<>();


    private static double bch_balance = 0;
    private double slp_balance_in_usd = 0;
    private String[] tx;
    private String legacy_address;
    private double unconfirmed_balance = 0;


    private SLPWallet SLP_WALLET;
    private static Wallet WALLET_ADAPTER_INSTANCE;
    private static Context SAVED_CONTEXT;
    private Wallet(){
        //Nothing
    }
    public static Wallet getInstance(Context context){
        if(SAVED_CONTEXT == null){
            SAVED_CONTEXT = context;
        }
        if ( WALLET_ADAPTER_INSTANCE == null){
            WALLET_ADAPTER_INSTANCE = new Wallet();
            BalanceObserver bo = BalanceObserver.getInstance();
            OBSERVERS_ARRAY.add(bo);
        }
        return WALLET_ADAPTER_INSTANCE;
    }
    public void createNewWallet(Context context, String wordlist){
        SLP_WALLET = WalletFactory.createWallet(context, wordlist, true);
    }
    public void createWalletFromMnemonic(Context context, List<String> wordlist){
        SLP_WALLET = WalletFactory.createWallet(context, wordlist, false);
        refreshBch();
    }
    public List<String> getWalletMnemonic(){
        return SLP_WALLET.getMnemonic();
    }
    public String getBchAddress(){
        return SLP_WALLET.getBchAddress();
    }
    public String getSlpAddress(){
        return SLP_WALLET.getSlpAddress();
    }
    public void refreshBch(){
        WebUtil.getBCHAddressData(getBchAddress());
    }

    public static void setBch_balance(String nb){
        Double balance = null;
        try {
            balance = Double.valueOf(nb);
        }catch(Exception e){
            e.printStackTrace();
        }
        if(balance == null){

        }else{
            bch_balance = balance;
        }
        for(CustomObserver o : OBSERVERS_ARRAY){
            o.notifyBchBalanceChange();
        }
    }
    public String getBchBalance(){
        return String.valueOf(bch_balance);
    }

}
