package com.ricardomendes.imc;

import com.google.firebase.database.DatabaseReference;
import com.ricardomendes.imc.DB.ConfiguracaoFirebase;

import static java.lang.Math.pow;

/**
 * Created by Ricardo Mendes on 11/03/2018.
 */

public class Imc {
    private double imc;
    public Imc(double altura, double peso){
        calcularImc(altura, peso);
    }
    public void salvarIMC(String id){
        DatabaseReference referenciaFirebase = ConfiguracaoFirebase.getFirebase();
        referenciaFirebase.child("Imc").child(String.valueOf(id)).setValue(this);
    }

    public void calcularImc(double altura, double peso){
        imc = peso / (pow(altura, 2));
    }
    public double getImc(){
        return imc;
    }
}
