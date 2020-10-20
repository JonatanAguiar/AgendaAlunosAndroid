package br.com.koroch.agenda.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.Serializable;
import java.util.List;

import br.com.koroch.agenda.R;
import br.com.koroch.agenda.dao.AlunoDAO;
import br.com.koroch.agenda.model.Aluno;

public class ListaAlunosActivity extends AppCompatActivity implements IConstantesActivities {

    public static final String TITULO_APPBAR = "Lista de Alunos";
    private final AlunoDAO dao = new AlunoDAO();
    private ArrayAdapter<Aluno> adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_alunos);
        AlunoDAO dao = new AlunoDAO();
        setTitle(TITULO_APPBAR);
        configuraFabNovoAluno();
        configuraLista();


//        ActiveFaceLiveness mActiveFaceLiveness = new ActiveFaceLiveness.Builder("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiI1ZjIzNDBhM2UxMjY5YjAwMDdlOTdmOGEifQ.pWN09KRGTPZdxHMHjjpgp-HvAvkyFmqkz6RRa7FX0T8")
//                // veja a tabela abaixo
//                .build();
//        Intent mIntent = new Intent(this, ActiveFaceLivenessActivity.class);
//        mIntent.putExtra(ActiveFaceLiveness.PARAMETER_NAME, mActiveFaceLiveness);
//        startActivityForResult(mIntent, 1);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.activity_lista_alunos_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if(itemId == R.id.activity_lista_alunos_menu_remover){
            AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
            Aluno alunoEscolhido = adapter.getItem(menuInfo.position);
            remove(alunoEscolhido);
        }
        return super.onContextItemSelected(item);
    }

    private void remove(Aluno alunoEscolhido) {
        dao.remove(alunoEscolhido);
        adapter.remove(alunoEscolhido);
    }

    private void configuraFabNovoAluno() {
        FloatingActionButton botaoNovoAluno = findViewById(R.id.activity_lista_alunos_fab_novo_aluno);
        botaoNovoAluno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                abreFormularioModoInsereAluno();
            }
        });
    }

    private void abreFormularioModoInsereAluno() {
        startActivity(new Intent(this, FormularioAlunoActivity.class));
    }

    @Override
    protected void onResume() {
        super.onResume();
        adapter.clear();
        adapter.addAll(dao.todos());
    }

    private void configuraLista() {
        ListView listaDeAlunos = findViewById(R.id.activity_lista_de_alunos_listview);
        configuraAdapter(listaDeAlunos);
        configuraListenerDeCliquePorItem(listaDeAlunos);
        registerForContextMenu(listaDeAlunos);
    }

    private void configuraListenerDeCliquePorItem(ListView listaDeAlunos) {
        listaDeAlunos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int posicao, long id) {
                Aluno alunoEscolhido = (Aluno) adapterView.getItemAtPosition(posicao);
                abreFormularioModoEditaAluno(alunoEscolhido);
            }
        });
    }

    private void abreFormularioModoEditaAluno(Aluno aluno) {
        Intent vaiParaFormularioActivity = new Intent(ListaAlunosActivity.this, FormularioAlunoActivity.class);
        vaiParaFormularioActivity.putExtra(CHAVE_ALUNO, aluno);
        startActivity(vaiParaFormularioActivity);
    }

    private void configuraAdapter(ListView listaDeAlunos) {
        adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1);
        listaDeAlunos.setAdapter(adapter);
    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        if (requestCode == 1){
//            if (resultCode == RESULT_OK && data != null) {
//                ActiveFaceLivenessResult mActiveFaceLivenessResult = (ActiveFaceLivenessResult) data.getSerializableExtra(ActiveFaceLivenessResult .PARAMETER_NAME);
//                if (mActiveFaceLivenessResult.wasSuccessful()){
//                    mActiveFaceLivenessResult.getImage();
//                }else{
//
//                }
//            } else {
//                // o usuário fechou a activity
//            }
//        }
//        super.onActivityResult(requestCode, resultCode, data);
//    }
}
