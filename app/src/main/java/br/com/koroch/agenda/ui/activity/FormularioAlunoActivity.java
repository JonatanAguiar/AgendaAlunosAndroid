package br.com.koroch.agenda.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import br.com.koroch.agenda.R;
import br.com.koroch.agenda.dao.AlunoDAO;
import br.com.koroch.agenda.model.Aluno;

public class FormularioAlunoActivity extends AppCompatActivity {

    public static final String TITULOAPPBAR = "Novo aluno";
    private EditText campoNome;
    private EditText campoTelefone;
    private EditText campoEmail;
    private final AlunoDAO dao = new AlunoDAO();
    private Aluno aluno;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_aluno);
        setTitle(TITULOAPPBAR);
        inicializacaoDosCampos();
        configuraBotaoSalvar();

        Intent dados = getIntent();
        aluno = (Aluno) dados.getSerializableExtra("aluno");
        campoNome.setText(aluno.getNome());
        campoTelefone.setText(aluno.getTelefone());
        campoEmail.setText(aluno.getEmail());

//        ActiveFaceLiveness mActiveFaceLiveness = new ActiveFaceLiveness.Builder("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiI1ZjIzNDBhM2UxMjY5YjAwMDdlOTdmOGEifQ.pWN09KRGTPZdxHMHjjpgp-HvAvkyFmqkz6RRa7FX0T8")
//                // veja a tabela abaixo
//                .build();
//        Intent mIntent = new Intent(this, ActiveFaceLivenessActivity.class);
//        mIntent.putExtra(ActiveFaceLiveness.PARAMETER_NAME, mActiveFaceLiveness);
//        startActivityForResult(mIntent, 1);
    }

    private void configuraBotaoSalvar() {
        Button botaoSalvar = findViewById(R.id.activity_formulario_aluno_botao_salvar);
        botaoSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Aluno alunoNovo = preencheAluno();
//                salvaAluno(alunoNovo);
                preencheAluno();
                dao.edita(aluno);
            }
        });
    }

    private void inicializacaoDosCampos() {
        campoNome = findViewById(R.id.activity_formulario_aluno_nome);
        campoTelefone = findViewById(R.id.activity_formulario_aluno_telefone);
        campoEmail = findViewById(R.id.activity_formulario_aluno_email);
    }

    private void salvaAluno(Aluno alunoNovo) {
        dao.salva(alunoNovo);
        finish();
    }

    private void preencheAluno() {
        String nome = campoNome.getText().toString();
        String telefone = campoTelefone.getText().toString();
        String email = campoEmail.getText().toString();

        aluno.setNome(nome);
        aluno.setTelefone(telefone);
        aluno.setEmail(email);
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