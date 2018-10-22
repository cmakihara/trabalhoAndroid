package com.aula.mobile.aula.sqlite.pesquisaeleitoral.exemplo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.aula.mobile.aula.R;
import com.aula.mobile.aula.sqlite.pesquisaeleitoral.entity.Voto;
import com.aula.mobile.aula.sqlite.pesquisaeleitoral.helper.VotoHelper;

import java.util.List;

public class ResultadoActivity extends AppCompatActivity {

    private ListView listaResultado;
    private TextView tvText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado);

        tvText = findViewById(R.id.tvTexto);

        int idCategoria = this.getIntent().getIntExtra("id", 0);
        listaResultado = findViewById(R.id.listRes);
        VotoHelper votoHelper = new VotoHelper(this);
        List<Voto> votos = votoHelper.getList(idCategoria);
       tvText.setText("Apuração ");


        ArrayAdapter<Voto> adapter = new ArrayAdapter<Voto>(this, android.R.layout.simple_list_item_1, votos);
        listaResultado.setAdapter(adapter);
    }
}
