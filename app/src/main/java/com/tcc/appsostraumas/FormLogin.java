package com.tcc.appsostraumas;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

public class FormLogin extends AppCompatActivity {

    private EditText editSenhaLogar,editLogar;
    private Button btEntrar;
    private TextView text_tela_cadastro;

    private String HOST = "http://192.168.18.8/testeLogin";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_login);

        getSupportActionBar().hide();//esconder barra de ação

        IniciarComponentes();

        editLogar = (EditText) findViewById(R.id.editLogar);
        editSenhaLogar = (EditText) findViewById(R.id.editSenhaLogar);
        btEntrar = (Button) findViewById(R.id.btEntrar);

        text_tela_cadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FormLogin.this,FormCadastro.class);
                String mensagem = "Ola mundo";
                intent.putExtra("Minha_MENSAGEM",mensagem);
                startActivity(intent);
            }
        });
        btEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String login = editLogar.getText().toString();
                String senha = editSenhaLogar.getText().toString();

                String URL = HOST + "/Logar.php";

                if ( login.isEmpty() || senha.isEmpty() ){
                    Toast.makeText(FormLogin.this, "Todos os campos são obrigatórios", Toast.LENGTH_LONG).show();
                }else{
                    //Solicitação web
                    Ion.with(FormLogin.this)
                            .load(URL)

                            .setBodyParameter("login",login)
                            .setBodyParameter("senha",senha)

                            .asJsonObject()
                            .setCallback(new FutureCallback<JsonObject>() {
                                @Override
                                public void onCompleted(Exception e, JsonObject result) {
                                    try {
                                        //Toast.makeText(FormCadastro.this, "Nome: " + result.get("NOME").getAsString(), Toast.LENGTH_LONG).show();
                                        String RETORNO = result.get("LOGIN").getAsString();

                                        if (RETORNO.equals("ERRO")){
                                            Toast.makeText(FormLogin.this, "Login ou senha incorreta", Toast.LENGTH_LONG).show();
                                        }else if (RETORNO.equals("SUCESSO")){
                                            Intent principal = new Intent(FormLogin.this, DadosPessoais.class);
                                            startActivity(principal);

                                        } else {
                                            Toast.makeText(FormLogin.this, "Ocorreu um erro", Toast.LENGTH_LONG).show();
                                        }
                                    }catch (Exception erro){
                                        Toast.makeText(FormLogin.this, "Ocorreu um erro" + erro, Toast.LENGTH_LONG).show();
                                    }
                                }
                            });
                }
            }
        });
    }

    private void IniciarComponentes(){
        text_tela_cadastro = findViewById(R.id.text_tela_cadastro);//recuperando id
    }
}
