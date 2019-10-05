package com.emilews.cashxmobile.common;

public class Constants {
    //Section for data file names only.
    private static final String SEED_FILE_NAME = "seed.csi";
    private static final String USER_DATA_FILE_NAME = "user.csi";
    //Section for URLs only.
    private static final String SINGLE_ADRESS_URL = "https://rest.bitcoin.com/";
    private static final String TX_DETAIL_URL = "https://bch.btc.com/";



    public static String getSingleAdressUrl(){
        return SINGLE_ADRESS_URL;
    }

    public static String getSeedFileName() {
        return SEED_FILE_NAME;
    }

    public static String getUserDataFileName() {
        return USER_DATA_FILE_NAME;
    }

    public static String getTxDetailUrl() { return TX_DETAIL_URL; }
}
