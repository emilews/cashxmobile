package com.emilews.cashxmobile.observers;

import android.os.Handler;

import com.emilews.cashxmobile.MainActivity;
import com.emilews.cashxmobile.controllers.AddressDataController;
import com.emilews.cashxmobile.ui.home.HomeFragment;

public class BalanceObserver implements CustomObserver{
    private static BalanceObserver instance;
    private BalanceObserver(){
        //Nothing
    }
    public static BalanceObserver getInstance() {
        if(instance == null){
            instance = new BalanceObserver();
        }
        return instance;
    }

    /**
     * Used when the BCH amount changes.
     */
    @Override
    public void notifyBchBalanceChange() {
        android.os.Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                HomeFragment.updateBchUI();
            }
        };
        handler.post(runnable);

    }

    @Override
    public void notifySlpTokenChange() {
        //Nothing
    }

    @Override
    public void notifySlpTokenAdded() {
        //Nothing
    }
}
