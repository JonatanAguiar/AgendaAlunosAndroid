package br.com.koroch.agenda.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.combateafraude.activefaceliveness.ActiveFaceLivenessActivity;
import com.combateafraude.activefaceliveness.input.ActiveFaceLiveness;
import com.combateafraude.activefaceliveness.output.ActiveFaceLivenessResult;

import br.com.koroch.agenda.R;

public class FormularioAlunoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_aluno);

        ActiveFaceLiveness mActiveFaceLiveness = new ActiveFaceLiveness.Builder("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6Ijc3ZWQ1Y2UxLTIzMTYtNDBkZi1iNzM3LTkzNmE2N2I3YjUwZCIsImlhdCI6MTU2NTk2MjMxM30.-oWGhwn54Mrt-aHuJVAFFBP4jY2AzXBPDQDePQZEQTw")
                // veja a tabela abaixo
                .build();
        Intent mIntent = new Intent(this, ActiveFaceLivenessActivity.class);
        mIntent.putExtra(ActiveFaceLiveness.PARAMETER_NAME, mActiveFaceLiveness);
        startActivityForResult(mIntent, 1);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1){
            if (resultCode == RESULT_OK && data != null) {
                ActiveFaceLivenessResult mActiveFaceLivenessResult = (ActiveFaceLivenessResult) data.getSerializableExtra(ActiveFaceLivenessResult .PARAMETER_NAME);
                if (mActiveFaceLivenessResult.wasSuccessful()){
                    mActiveFaceLivenessResult.getImage();
                }else{

                }
            } else {
                // o usuário fechou a activity
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}