package com.tcc.appsostraumas;

import android.view.View;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

 class FormCadastroHospital extends AppCompatActivity{

    EditText editNomeH,editFone,editEndereco,editAtendimento,editId;
    Button btnCadastrar,btnSalvar,btnExcluir;
    ListView listHospitais;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_cadastro_hospital);

        editNomeH = (EditText) findViewById(R.id.editNomeH);
        editFone = (EditText) findViewById(R.id.editFone);
        editEndereco = (EditText) findViewById(R.id.editEndereco);
        editAtendimento = (EditText) findViewById(R.id.editAtendimento);
        editId = (EditText) findViewById(R.id.editId);

        btnCadastrar = (Button) findViewById(R.id.btnCadastrar);
        btnSalvar = (Button) findViewById(R.id.btnSalvar);
        btnExcluir = (Button) findViewById(R.id.btnExcluir);

        listHospitais = (ListView) findViewById(R.id.listHospitais);

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = editId.getText().toString();
                String nome = editNomeH.getText().toString();
                String telefone = editFone.getText().toString();
                String endereco = editEndereco.getText().toString();
                String atendimento = editAtendimento.getText().toString();


            }
        });

    }
}
