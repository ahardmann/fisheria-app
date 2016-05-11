package br.com.senai.projeto.webview.app20_04.login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import br.com.senai.projeto.webview.app20_04.MainActivity;
import br.com.senai.projeto.webview.app20_04.R;

/**
 * Created by arthur on 08/05/2016.
 */
public class Login extends AppCompatActivity {
    private EditText usuario;
    private EditText senha;
    private CheckBox check;

    @Override
    public void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        usuario = (EditText) findViewById(R.id.usuario);
        senha = (EditText) findViewById(R.id.senha);
        check = (CheckBox) findViewById(R.id.checkLembrar);
    }

    public void enterOnClick (View v){

        if (check.isChecked()){
            CarregaUsuario();
        }
        String usuarioInformado = usuario.getText().toString();
        String senhaInformada = senha.getText().toString();
        EfetuaLogin(usuarioInformado,senhaInformada);
    }

    public void CarregaUsuario(){
        SharedPreferences prefs = getSharedPreferences("Configuracoes", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("usuario", "senai");
        editor.putString("senha", "senai");
        editor.commit();
        String usuarioS = prefs.getString("usuario", null);
        String senhaS = prefs.getString("senha", null);
        usuario.setText(usuarioS);
        senha.setText(senhaS);
        EfetuaLogin(usuarioS,senhaS);
    }

    public void EfetuaLogin(String usuarionInf, String senhaInf){

        if("senai".equals(usuarionInf) && "senai".equals(senhaInf)) {
            startActivity(new Intent(this, MainActivity.class));
        } else{
            String mensagemErro = getString(R.string.erro_autenticao);
            Toast toast = Toast.makeText(this, mensagemErro,
                    Toast.LENGTH_SHORT);
            toast.show();
        }
    }
}
