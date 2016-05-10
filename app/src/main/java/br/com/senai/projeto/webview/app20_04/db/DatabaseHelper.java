package br.com.senai.projeto.webview.app20_04.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String BANCO_DADOS = "OsPeixes";
    private static final int VERSAO = 1;
    public static class Peixe{
        public static final String TABELA = "PEIXE";
        public static final String _ID= "_ID";
        public static final String NOME = "NOME";
        public static final String ORIGEM = "ORIGEM";
        public static final String PORTE = "PORTE";
        public static final String ISCA = "ISCA";

        public static final String[] COLUNAS = new String[]{
                _ID,NOME,ORIGEM,PORTE,ISCA
        };
    }

    public DatabaseHelper(Context context) {
        super(context, BANCO_DADOS, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE PEIXE (_ID INTEGER PRIMARY KEY,"+
                "NOME TEXT, ORIGEM TEXT, PORTE TEXT," +
                " ISCA TEXT);");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}
}
