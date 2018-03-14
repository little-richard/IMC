package com.ricardomendes.imc;

import com.google.firebase.database.DatabaseReference;
import com.ricardomendes.imc.DB.ConfiguracaoFirebase;

import java.util.ArrayList;

import static java.lang.Math.pow;

/**
 * Created by Ricardo Mendes on 11/03/2018.
 */

public class Imc {
    private double imc;
    private int classificacao;
    private ArrayList<String> classif;
    public Imc(String altura, String peso){
        calcularImc(altura, peso);
        setClassificacao();
    }

    public int getClassificacao() {
        return classificacao;
    }

    public ArrayList<String> getClassif() {
        return classif;
    }

    private void setClassificacao() {
        classif = new ArrayList<String>();
        classif.add("Abaixo de 17 ---- Muito abaixo do peso");
        classif.add("Entre 17 e 18,49 ---- Abaixo do peso");
        classif.add("Entre 18,5 e 24,99 ---- Peso normal");
        classif.add("Entre 25 e 29,99 ---- Acima do peso");
        classif.add("Entre 30 e 34,99 ---- Obesidade I");
        classif.add("Entre 35 e 39,99 ---- Obesidade II (severa)");
        classif.add("Acima de 40 ---- Obesidade III (mórbida)");
        if(imc<17){
            int classificacao = 0;
        }else if(imc>=17 && imc<18.5){
            int classificacao = 1;
        }else if(imc>=18.5 && imc<25){
            int classificacao = 2;
        }else if(imc>=25 && imc<30){
            int classificacao = 3;
        }else if(imc>=30 && imc<35){
            int classificacao = 4;
        }else if(imc>=35 && imc<40){
            int classificacao = 5;
        }else{
            int classificacao = 6;
        }
    }

    public void salvarIMC(String id){
        DatabaseReference referenciaFirebase = ConfiguracaoFirebase.getFirebase();
        referenciaFirebase.child("Imc").child(String.valueOf(id)).setValue(this);
    }

    private void calcularImc(String altura, String peso){
        double a = Double.parseDouble(altura);
        double p = Double.parseDouble(peso);
        imc = p / (pow(a, 2));
    }
    public double getImc(){
        return imc;
    }
}
