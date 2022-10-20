package com.tcc.appsostraumas;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class DadosPessoais extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dados_pessoais);

        getSupportActionBar().hide();

    }
}
