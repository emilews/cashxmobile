package com.emilews.cashxmobile.bch;

import android.content.Context;

import com.bitcoin.slpwallet.Network;
import com.bitcoin.slpwallet.SLPWallet;

import java.util.List;

public class WalletFactory {
    /**
        This factory creates an SLPWallet with a BCH address and an SLP address, so we can use them
        as we please.
     */
    public static SLPWallet createWallet(Context context, List<String> wordlist, boolean isNew){
        return SLPWallet.Companion.fromMnemonic(context, Network.MAIN, wordlist, isNew);
    }
    public static SLPWallet createWallet(Context context, String wordlist, boolean isNew){
        return SLPWallet.Companion.fromMnemonic(context, Network.MAIN, wordlist, isNew);
    }
}
