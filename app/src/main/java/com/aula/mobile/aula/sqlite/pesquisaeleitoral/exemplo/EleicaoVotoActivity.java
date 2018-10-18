package com.aula.mobile.aula.sqlite.pesquisaeleitoral.exemplo;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.aula.mobile.aula.R;
import com.aula.mobile.aula.sqlite.pesquisaeleitoral.helper.CandidatoHelper;
import com.aula.mobile.aula.sqlite.pesquisaeleitoral.helper.CategoriaHelper;
import com.aula.mobile.aula.sqlite.pesquisaeleitoral.helper.DbHelper;
import com.aula.mobile.aula.sqlite.pesquisaeleitoral.entity.Candidato;
import com.aula.mobile.aula.sqlite.pesquisaeleitoral.entity.Categoria;
import com.aula.mobile.aula.sqlite.pesquisaeleitoral.helper.VotoHelper;

import java.util.List;

public class EleicaoVotoActivity extends AppCompatActivity {

    private ListView listView;
    private TextView tvText;
    EleicaoVotoActivity activity = this;

    private DbHelper dbHelper;
    public static final String TABLE_CATEGORIA = "categoria";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eleicao_voto);

        listView = findViewById(R.id.list);
        tvText = findViewById(R.id.tvText);

        int idCategoria = this.getIntent().getIntExtra("id", 0);

        CategoriaHelper categoriaHelper = new CategoriaHelper(this);

        List<Categoria> categorias = categoriaHelper.getList();
        tvText.setText("Pesquisa para: " + categorias.get(idCategoria-1).getNome() + " - " + categorias.get(idCategoria-1).getEstado());


        CandidatoHelper candidatoHelper = new CandidatoHelper(this);
        List<Candidato> candidatos = candidatoHelper.getList(idCategoria);

        ArrayAdapter<Candidato> adapter = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, candidatos);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final Candidato item = (Candidato) parent.getItemAtPosition(position);
                new AlertDialog.Builder(activity)
                        .setIcon(R.drawable.ic_vote)
                        .setTitle("Pesquisa eleitoral")
                        .setMessage("Confirma seu voto em: " + item.getNome())
                        .setPositiveButton("SIM", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                VotoHelper votoHelper = new VotoHelper(EleicaoVotoActivity.this);
                                votoHelper.votar(item.getNome().toString());

                            }

                        })
                        .setNegativeButton("NAO", null)
                        .show();
            }
        });
    }

}
