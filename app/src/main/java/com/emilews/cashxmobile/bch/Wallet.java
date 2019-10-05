package com.emilews.cashxmobile.bch;

import android.content.Context;

import com.bitcoin.slpwallet.SLPWallet;
import com.emilews.cashxmobile.utils.WebUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class Wallet {
    /*
        Here we abstract everything we can get from a request to the REST API.
        It is in a singleton pattern.
     */

    private double bch_balance = 0;
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
        String[] data = WebUtil.getBCHAddressData(SAVED_CONTEXT, SLP_WALLET.getBchAddress());

    }
}
