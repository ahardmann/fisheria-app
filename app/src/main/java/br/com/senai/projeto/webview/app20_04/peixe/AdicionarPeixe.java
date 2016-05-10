package br.com.senai.projeto.webview.app20_04.peixe;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import br.com.senai.projeto.webview.app20_04.R;
import br.com.senai.projeto.webview.app20_04.TelaRecycler;

public class AdicionarPeixe extends AppCompatActivity {

    private EditText nome;
    private Spinner origem, porte, isca;
    private PeixeDao dao;
    private Long id;

    @Override
    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adicionar);

        nome = (EditText) findViewById(R.id.nomePeixe);

        ArrayAdapter<CharSequence> adapterOrigem = ArrayAdapter.createFromResource(
                this, R.array.origem_op, android.R.layout.simple_spinner_item);
        origem = (Spinner) findViewById(R.id.origemPeixe);
        origem.setAdapter(adapterOrigem);

        ArrayAdapter<CharSequence> adapterPorte = ArrayAdapter.createFromResource(
                this, R.array.porte_op, android.R.layout.simple_spinner_item);
        porte = (Spinner) findViewById(R.id.portePeixe);
        porte.setAdapter(adapterPorte);

        ArrayAdapter<CharSequence> adapterIsca = ArrayAdapter.createFromResource(
                this, R.array.isca_op, android.R.layout.simple_spinner_item);
        isca = (Spinner) findViewById(R.id.iscaPeixe);
        isca.setAdapter(adapterIsca);

        dao = new PeixeDao(this);
    }

    public void Salvar (View v){
        String nomePeixe = nome.getText().toString().trim();
        String origemText = origem.getSelectedItem().toString();
        String porteText = porte.getSelectedItem().toString();
        String iscaText = isca.getSelectedItem().toString();

        if(TextUtils.isEmpty(nomePeixe)){
            nome.setError(getText(R.string.campoVazio));
        }else{
            Peixe peixe = new Peixe();
            peixe.setNome(nomePeixe);
            peixe.setOrigem(origemText);
            peixe.setPorte(porteText);
            peixe.setIsca(iscaText);

            long resultado;
            resultado = dao.inserirPeixe(peixe);

            if(resultado != -1 ){
                Toast.makeText(this, getString(R.string.registro_salvo),
                        Toast.LENGTH_SHORT).show();
                clear();
                startActivity(new Intent(this, TelaRecycler.class));
                finish();
            }else{
                Toast.makeText(this, getString(R.string.erro_salvar),
                        Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void clear(){
        nome.setText("");
    }

    @Override
    protected void onDestroy() {
        dao.close();
        super.onDestroy();
    }
}
