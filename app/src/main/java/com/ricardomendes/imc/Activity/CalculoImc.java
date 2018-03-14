package com.ricardomendes.imc.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.ricardomendes.imc.Helper.Preferencias;
import com.ricardomendes.imc.Imc;
import com.ricardomendes.imc.R;
import com.ricardomendes.imc.Usuarios;

public class CalculoImc extends AppCompatActivity {
    private EditText edtAltura;
    private EditText edtPeso;
    private Button btCalcular;
    private Imc imc;
    private FirebaseUser user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculo_imc);
        edtAltura = (EditText) findViewById(R.id.altura);
        edtPeso = (EditText) findViewById(R.id.peso);
        btCalcular = (Button) findViewById(R.id.btCalcular);
        btCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
           public void onClick(View view) {
                TextView result = (TextView) findViewById(R.id.resultImc);
                user = FirebaseAuth.getInstance().getCurrentUser();
                if(user != null){
                    imc = new Imc(edtAltura.getText().toString(), edtPeso.getText().toString());
                    double resultImc = imc.getImc();
                    result.setText("Seu Imc: " + resultImc);
                }else{
                    result.setText("Não há usuário logado!");
                }
            }
        });
    }
}
