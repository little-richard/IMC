package com.ricardomendes.imc.Helper;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Ricardo Mendes on 11/03/2018.
 */

public class Preferencias {
    private Context context;
    private SharedPreferences preferences;
    private String NOME_ARQUIVO = "projetoFirebase.preferencias";
    private int MODE = 0;
    private SharedPreferences.Editor editor;

    private final String CHAVE_ID = "identificarUsuarioLogado!";
    private final String CHAVE_NOME = "nomeUsuarioLogado";

    public Preferencias(Context context){
        this.context = context;
        preferences = context.getSharedPreferences(NOME_ARQUIVO, MODE);

        editor = preferences.edit();
    }

    public void salvarUsuario(String idUsuario, String nomeUsuario){
        editor.putString(CHAVE_ID, idUsuario);
        editor.putString(CHAVE_NOME, nomeUsuario);
        editor.commit();
    }

    public String getIdentificador(){
        return preferences.getString(CHAVE_ID, null);
    }
    public String getNome(){
        return preferences.getString(CHAVE_NOME, null);
    }
}
