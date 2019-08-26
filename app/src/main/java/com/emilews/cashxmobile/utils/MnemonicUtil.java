package com.emilews.cashxmobile.utils;

import java.security.SecureRandom;

import io.github.novacrypto.bip39.MnemonicGenerator;
import io.github.novacrypto.bip39.Words;
import io.github.novacrypto.bip39.wordlists.English;

public class MnemonicUtil {
    public static String getNewMnemonic(){
        StringBuilder sb = new StringBuilder();
        byte[] entropy = new byte[Words.TWELVE.byteLength()];
        new SecureRandom().nextBytes(entropy);
        new MnemonicGenerator(English.INSTANCE)
                .createMnemonic(entropy, sb::append);
        System.out.println(sb.toString());
        return sb.toString();
    }
}
