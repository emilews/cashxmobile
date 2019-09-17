package com.emilews.cashxmobile.utils;

import android.content.Context;

import com.emilews.cashxmobile.common.Constants;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class Writer {
    /*
       This is just a class to write data, it does everything in the private app space, that's
       why it needs an android Context, as it uses it to access the relevant methods and spaces.
       To use this, just call the methods, since it doesn't need an instance.
    */
    public static void saveSeedToFile(Context context, List<String> wordlist){
        try{
            FileOutputStream fos = context.openFileOutput(Constants.getSeedFileName(), Context.MODE_PRIVATE);
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
}
