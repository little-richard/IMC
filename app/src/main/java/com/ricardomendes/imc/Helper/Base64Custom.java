package com.ricardomendes.imc.Helper;

import android.util.Base64;

/**
 * Created by Ricardo Mendes on 11/03/2018.
 */

public class Base64Custom {
    public static String codificarBase64(String texto){
        return Base64.encodeToString(texto.getBytes(), Base64.DEFAULT).replaceAll("\\n|\\r", "");
    }

    public static String decodificarBase64(String textoCod){
        return new String (Base64.decode(textoCod, Base64.DEFAULT));
    }

}
