package br.com.koroch.agenda.dao;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import br.com.koroch.agenda.model.Aluno;

public class AlunoDAO {
    private final static List<Aluno> alunos = new ArrayList<>();
    private static int contadorDeIds = 1;
    public void salva(Aluno aluno) {
        aluno.setId(contadorDeIds);
        alunos.add(aluno);
        atualizaIds();
    }

    private void atualizaIds() {
        contadorDeIds++;
    }

    public void edita(Aluno aluno){
        Aluno alunoEncontrado = buscaAlunoById(aluno);
        if (alunoEncontrado!=null){
            int posicaoAluno = alunos.indexOf(alunoEncontrado);
            alunos.set(posicaoAluno, aluno);
        }
    }

    @Nullable
    private Aluno buscaAlunoById(Aluno aluno) {
        for (Aluno a: alunos) {
            if(a.getId()==aluno.getId()){
                return a;
            }
        }
        return null;
    }

    public List<Aluno> todos() {
        return new ArrayList<>(alunos);
    }

    public void remove(Aluno aluno) {
        Aluno alunoDevolvido = buscaAlunoById(aluno);
        if(alunoDevolvido != null)
            alunos.remove(alunoDevolvido);
    }
}
