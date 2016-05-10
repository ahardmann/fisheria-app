package br.com.senai.projeto.webview.app20_04;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.com.senai.projeto.webview.app20_04.peixe.Peixe;
import br.com.senai.projeto.webview.app20_04.peixe.PeixeDao;
import br.com.senai.projeto.webview.app20_04.peixe.PeixeListAdapter;

public class TelaRecycler extends AppCompatActivity implements PeixeListAdapter.OnDataSelected, DialogInterface.OnClickListener{
    private List<Peixe> peixes = new ArrayList<Peixe>();
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private LinearLayoutManager linearLayoutManager;
    private GridLayoutManager gridLayoutManager;
    private PeixeDao dao;
    private AlertDialog alertDialog, confirmDialog;
    private Peixe selectedItem;
    int peixeSelecionado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_tela_recycler);

        dao = new PeixeDao(this);
        this.alertDialog = criaAlertDialog();
        this.confirmDialog = dialogConfirmacao();
        listarPeixes();

        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);

        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        gridLayoutManager = new GridLayoutManager(this,2);

        recyclerView.setLayoutManager(linearLayoutManager);

        adapter = new PeixeListAdapter(this,this, peixes);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onDataSelected(View view, int position) {
        this.selectedItem  = peixes.get(position);
        this.peixeSelecionado = position;
        Toast.makeText(this, "Item selecionado: " + selectedItem.getNome(), Toast.LENGTH_SHORT).show();
        alertDialog.show();
    }

    private List<Peixe> listarPeixes(){
        peixes = new ArrayList<Peixe>();
        List<Peixe> listaPeixe = dao.listarPeixes();

        if (listaPeixe != null){
            for(Peixe peixe : listaPeixe){
                peixe.getId();
                peixe.getNome();
                peixe.getOrigem();
                peixe.getPorte();
                peixe.getIsca();
                peixes.add(peixe);
            }
        }
        return peixes;
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        Long idPeixe = selectedItem.getId();
        switch (which){
            case 0:
                confirmDialog.show();
                break;
            case DialogInterface.BUTTON_POSITIVE:
                peixes.remove(peixeSelecionado);
                dao.removerPeixe(Long.valueOf(idPeixe));
                break;
            case DialogInterface.BUTTON_NEGATIVE:
                confirmDialog.dismiss();
                break;
        }
    }

    private AlertDialog criaAlertDialog() {
        final CharSequence[] items = {
                getString(R.string.excluir) };
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.opcoes);
        builder.setItems(items, this);
        return builder.create();
    }

    private AlertDialog dialogConfirmacao() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.confirmacao_exclusao);
        builder.setPositiveButton(getString(R.string.sim), this);
        builder.setNegativeButton(getString(R.string.nao), this);
        return builder.create();
    }

}