package com.ricardomendes.imc;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Exclude;
import com.ricardomendes.imc.DB.ConfiguracaoFirebase;

import java.util.HashMap;
import java.util.Map;

import static java.lang.Math.pow;

/**
 * Created by Ricardo Mendes on 11/03/2018.
 */

public class Usuarios {
    private String id;
    private String nome;
    private String senha;
    private String email;
    private String aniversario;
    private String telefone;
    public Usuarios(){


    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAniversario() {
        return aniversario;
    }

    public void setAniversario(String sexo) {
        this.aniversario = aniversario;
    }

    public void salvar(){
        DatabaseReference referenciaFirebase = ConfiguracaoFirebase.getFirebase();
        referenciaFirebase.child("usuario").child(String.valueOf(getId())).setValue(this);
    }
    @Exclude
    public Map<String, Object> toMap(){
        HashMap<String, Object>hashUsuario = new HashMap<>();

        hashUsuario.put("id", getId());
        hashUsuario.put("email", getEmail());
        hashUsuario.put("nome", getNome());
        hashUsuario.put("senha", getSenha());

        return hashUsuario;
    }
}
