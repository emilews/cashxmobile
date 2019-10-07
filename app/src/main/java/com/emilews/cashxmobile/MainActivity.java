package com.emilews.cashxmobile;

import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import com.emilews.cashxmobile.bch.Wallet;
import com.emilews.cashxmobile.utils.MnemonicUtil;
import com.emilews.cashxmobile.utils.Reader;
import com.emilews.cashxmobile.utils.Writer;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;


public class MainActivity extends AppCompatActivity {
    private static Wallet wallet;
    private static Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
        loadWallet();
        UpdateBCH();

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
        wallet = Wallet.getInstance(context);
        List<String> wordlist = Reader.readWalletSeed(context);
        if(wordlist == null){
            String newMnemonic = MnemonicUtil.getNewMnemonic();
            wallet.createNewWallet(context, newMnemonic);
            saveWallet();
        }else{
            wallet.createWalletFromMnemonic(context, wordlist);
        }
    }

    public void saveWallet(){
        Writer.saveSeedToFile(context, wallet.getWalletMnemonic());
    }
    public static void bchPressedSend(){
        Toast.makeText(context, "Pressed send button!", Toast.LENGTH_LONG).show();
    }
    public static void UpdateBCH(){
        wallet.refreshBch();
    }
    public static String getBchBalance(){
        return wallet.getBchBalance();
    }
}
