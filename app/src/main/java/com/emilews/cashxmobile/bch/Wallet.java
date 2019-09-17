package com.emilews.cashxmobile.bch;

import android.content.Context;

import com.bitcoin.slpwallet.SLPWallet;
import com.emilews.cashxmobile.utils.WebUtil;

import org.json.JSONObject;

import java.util.List;

public class Wallet {
    /*
        Here we abstract everything we can from a request to the REST API.
        It is in a singleton pattern.
     */

    private String bch_balance = "";
    private String slp_balance = "";

    private SLPWallet wallet;
    private static Wallet instance;
    private Wallet(){
        //Nothing
    }
    public static Wallet getInstance(){
        if ( instance == null){
            instance = new Wallet();
        }
        return instance;
    }
    public void createNewWallet(Context context, String wordlist){
        wallet = WalletFactory.createWallet(context, wordlist, true);
    }
    public void createWalletFromMnemonic(Context context, List<String> wordlist){
        wallet = WalletFactory.createWallet(context, wordlist, false);
    }
    public List<String> getWalletMnemonic(){
        return wallet.getMnemonic();
    }
    public String getBchAddress(){
        return wallet.getBchAddress();
    }
    public String getSlpAddress(){
        return wallet.getSlpAddress();
    }
    public void refreshBch(Context context){
        JSONObject data = WebUtil.getBCHAddressData(context, wallet.getBchAddress());

    }
}
