package com.ricardomendes.imc.Activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.auth.FirebaseUser;
import com.ricardomendes.imc.DB.ConfiguracaoFirebase;
import com.ricardomendes.imc.Helper.Base64Custom;
import com.ricardomendes.imc.Helper.Preferencias;
import com.ricardomendes.imc.R;
import com.ricardomendes.imc.Usuarios;

public class CadastrarActivity extends AppCompatActivity{
    private FirebaseAuth autenticacao;
    private EditText senha;
    private EditText email;
    private EditText aniversario;
    private EditText nome;
    private EditText confSenha;
    private Button btCad;
    private Usuarios usuarios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar);

        aniversario = (EditText) findViewById(R.id.datanascimento);
        nome = (EditText) findViewById(R.id.nome);
        btCad = (Button) findViewById(R.id.btCadastrar);
        email = (EditText) findViewById(R.id.email);
        senha = (EditText) findViewById((R.id.password));
        confSenha = (EditText) findViewById(R.id.confirmpassword);

        btCad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(senha.getText().toString().equals(confSenha.getText().toString())){
                    usuarios = new Usuarios();
                    usuarios.setAniversario(aniversario.getText().toString());
                    usuarios.setEmail(email.getText().toString());
                    usuarios.setNome(nome.getText().toString());
                    usuarios.setSenha(senha.getText().toString());
                    cadastrarUsuario();
                }else{
                    Toast.makeText(CadastrarActivity.this, "As senhas são diferentes! Por favor senhas iguais." + senha.getText().toString() + confSenha.getText().toString(), Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    private void cadastrarUsuario(){
        autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();
        autenticacao.createUserWithEmailAndPassword(
                usuarios.getEmail(),
                usuarios.getSenha()
        ).addOnCompleteListener(CadastrarActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(CadastrarActivity.this, "Usuário cadastrado com sucesso!", Toast.LENGTH_LONG).show();

                    String idUsuario = Base64Custom.codificarBase64(usuarios.getEmail());
                    FirebaseUser usuarioFirebase = task.getResult().getUser();
                    usuarios.setId(idUsuario);
                    usuarios.salvar();

                    Preferencias preferencias = new Preferencias(CadastrarActivity.this);

                    preferencias.salvarUsuario(idUsuario, usuarios.getNome());
                    abrirLoginUsuario();

                }else{
                    String erro = "";
                    try{
                        throw task.getException();
                    }catch (FirebaseAuthWeakPasswordException e){
                        erro = "Digite uma senha mais forte, contendo no minimo 8 caracteres!";
                    }catch (FirebaseAuthInvalidCredentialsException e){
                        erro = "O email digitado é invalido, digite um novo email.";
                    }catch (FirebaseAuthUserCollisionException e){
                        erro = "Esse email já está cadastrado no sistema!";
                    }catch(Exception e){
                        erro = "Erro ao efetuar o cadastro!";
                        e.printStackTrace();
                    }
                    Toast.makeText(CadastrarActivity.this, "Erro: " + erro, Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void abrirLoginUsuario(){
        Intent intent = new Intent(CadastrarActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}