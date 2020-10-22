package br.com.koroch.agenda;

import android.app.Application;

import br.com.koroch.agenda.dao.AlunoDAO;

@SuppressWarnings("unused")
public class AgendaApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        AlunoDAO alunoDAO = new AlunoDAO();
    }
}
