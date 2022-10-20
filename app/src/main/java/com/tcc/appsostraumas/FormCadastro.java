package com.tcc.appsostraumas;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

public class FormCadastro extends AppCompatActivity {

    private EditText editNome,editLogin,editSenha,editSenhaConf,editPerfil;
    private Button btnCadastrar;

    private String HOST = "http://192.168.18.8/testeLogin";
    Intent intent = getIntent();
    //String mensagem = intent.getStringExtra("MINHA_MENSAGEM");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_cadastro);
        getSupportActionBar().hide();


        editNome = (EditText) findViewById(R.id.editNome);
        editLogin = (EditText) findViewById(R.id.editLogin);
        editSenha = (EditText) findViewById(R.id.editSenha);
        editSenhaConf = (EditText) findViewById(R.id.editSenhaConf);
        editPerfil = (EditText) findViewById(R.id.editPerfil);

        btnCadastrar = (Button) findViewById(R.id.btnCadastrar);

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String nome = editNome.getText().toString();
                String login = editLogin.getText().toString();
                String senha = editSenha.getText().toString();
                String confsenha = editSenhaConf.getText().toString();
                String perfil = editPerfil.getText().toString();

                String URL = HOST + "/cadastroLogin.php";

                if(confsenha.equals(senha)){
                    if (nome.isEmpty() || login.isEmpty() || senha.isEmpty() || confsenha.isEmpty() || perfil.isEmpty()){
                        Toast.makeText(FormCadastro.this, "Todos os campos são obrigatórios", Toast.LENGTH_LONG).show();
                    }else{
                        //Solicitação web
                        Ion.with(FormCadastro.this)
                                .load(URL)
                                .setBodyParameter("nome",nome)
                                .setBodyParameter("login",login)
                                .setBodyParameter("senha",senha)
                                .setBodyParameter("perfil",perfil)
                                .asJsonObject()
                                .setCallback(new FutureCallback<JsonObject>() {
                                    @Override
                                    public void onCompleted(Exception e, JsonObject result) {
                                        try {
                                            //Toast.makeText(FormCadastro.this, "Nome: " + result.get("NOME").getAsString(), Toast.LENGTH_LONG).show();
                                            String RETORNO = result.get("CADASTRO").getAsString();

                                            if (RETORNO.equals("LOGIN_ERRO")){
                                                Toast.makeText(FormCadastro.this, "Este email já está cadastrado", Toast.LENGTH_LONG).show();
                                            }else if (RETORNO.equals("SUCESSO")){
                                                //Toast.makeText(FormCadastro.this, "cadastrado com Sucesso", Toast.LENGTH_LONG).show();
                                                Intent principal = new Intent(FormCadastro.this, DadosPessoais.class);
                                                startActivity(principal);

                                            } else {
                                                Toast.makeText(FormCadastro.this, "Ocorreu um erro", Toast.LENGTH_LONG).show();
                                            }
                                        }catch (Exception erro){
                                            Toast.makeText(FormCadastro.this, "Ocorreu um erro" + erro, Toast.LENGTH_LONG).show();
                                        }
                                    }
                                });
                    }
                }else{
                    Toast.makeText(FormCadastro.this, "As senhas não conferem", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}
