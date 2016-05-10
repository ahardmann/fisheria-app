package br.com.senai.projeto.webview.app20_04.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
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

    @Override
    public void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        usuario = (EditText) findViewById(R.id.usuario);
        senha = (EditText) findViewById(R.id.senha);
    }

    public void enterOnClick (View v){
        String usuarioInformado = usuario.getText().toString();
        String senhaInformada = senha.getText().toString();

        if("senai".equals(usuarioInformado) && "senai".equals(senhaInformada)) {
            startActivity(new Intent(this,MainActivity.class));
        } else{
            String mensagemErro = getString(R.string.erro_autenticao);
            Toast toast = Toast.makeText(this, mensagemErro,
                    Toast.LENGTH_SHORT);
            toast.show();
        }
    }

}
