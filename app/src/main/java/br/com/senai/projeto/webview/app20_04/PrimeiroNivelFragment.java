package br.com.senai.projeto.webview.app20_04;

import java.util.LinkedHashMap;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class PrimeiroNivelFragment extends Fragment //implements View.OnClickListener
{

    private static final String EXTRA_TIPO = "mTipo";
    private String mTipo;
    private LinkedHashMap<String, Class> mAcoes;

    public static PrimeiroNivelFragment novaInstancia(String tipo){
        Bundle params = new Bundle();
        params.putString(EXTRA_TIPO, tipo);
        PrimeiroNivelFragment f = new PrimeiroNivelFragment();
        f.setArguments(params);
        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        mTipo = getArguments().getString(EXTRA_TIPO);
        View layout = inflater.inflate(R.layout.layout_fragment, container, false);
        TextView textView = (TextView) layout.findViewById(R.id.textView);
        textView.setText(mTipo);
        return layout;
    }

}