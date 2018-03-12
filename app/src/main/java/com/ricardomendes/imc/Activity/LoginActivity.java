package com.ricardomendes.imc.Activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.ricardomendes.imc.DB.ConfiguracaoFirebase;
import com.ricardomendes.imc.R;
import com.ricardomendes.imc.Usuarios;

public class LoginActivity extends AppCompatActivity {
    EditText email;
    EditText senha;
    TextView cadastrar;
    Button btEntrar;
    private FirebaseAuth autenticacao;
    private Usuarios usuarios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Referencia de elementos da activity_login
        senha = (EditText) findViewById(R.id.pass);
        email = (EditText) findViewById(R.id.emailLogin);
        btEntrar = (Button) findViewById(R.id.btAcessar);
        cadastrar = (TextView) findViewById(R.id.textCadastrar);

        //Pego o clique de cadastrar
        cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(LoginActivity.this, CadastrarActivity.class);
                startActivity(it);
            }
        });

        //Pego o clique do botão logar
        btEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!email.getText().toString().equals("") && !senha.getText().toString().equals("")){
                    usuarios = new Usuarios();
                    usuarios.setEmail(email.getText().toString());
                    usuarios.setSenha(senha.getText().toString());
                    validarLogin();

                }else{
                    Toast.makeText(LoginActivity.this, "Preencha os campos de email e senha!", Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    private void validarLogin(){
        autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();
        autenticacao.signInWithEmailAndPassword(usuarios.getEmail(), usuarios.getSenha()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    abrirTelaPrincipal();
                    Toast.makeText(LoginActivity.this, "Login efetuado com sucesso!", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(LoginActivity.this, "Usuário ou senha inválidos!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void abrirTelaPrincipal(){
        Intent abrirTelaPrincipal = new Intent(LoginActivity.this, CalculoImc.class);
        startActivity(abrirTelaPrincipal);
    }
}
