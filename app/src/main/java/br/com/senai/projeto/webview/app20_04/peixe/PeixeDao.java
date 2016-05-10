package br.com.senai.projeto.webview.app20_04.peixe;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import br.com.senai.projeto.webview.app20_04.db.DatabaseHelper;

public class PeixeDao {
    private DatabaseHelper helper;
    private SQLiteDatabase db;

    public PeixeDao(Context context){
        helper = new DatabaseHelper(context);
    }

    private SQLiteDatabase getDb(){
        if(db == null){
            db = helper.getWritableDatabase();
        }
        return db;
    }

    public void close(){
        helper.close();
        db = null;
    }

    public List<Peixe> listarPeixes(){

        Cursor cursor = getDb().query(DatabaseHelper.Peixe.TABELA
                , DatabaseHelper.Peixe.COLUNAS, null, null, null, null, null);
        cursor.moveToFirst();

        List<Peixe> peixes = new ArrayList<Peixe>();

        if(cursor.moveToFirst()&& cursor.getCount() >= 1){
            do{
                Peixe peixeU = criarPeixe(cursor);
                peixes.add(peixeU);
            }while(cursor.moveToNext());
        }

        cursor.close();
        return peixes;
    }

//    public Peixe buscaPorId(Long id){
//        Cursor cursor = getDb().query(DatabaseHelper.Peixe.TABELA, DatabaseHelper.Peixe.COLUNAS
//                , DatabaseHelper.Peixe._ID +" = ?", new String[]{id.toString()}, null, null, null);
//
//        if(cursor.moveToNext()){
//            Peixe peixeU = criarPeixe(cursor);
//            cursor.close();
//            return peixeU;
//        }
//        return null;
//    }

    public long inserirPeixe(Peixe peixe){
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.Peixe.NOME, peixe.getNome());
        values.put(DatabaseHelper.Peixe.ORIGEM, peixe.getOrigem());
        values.put(DatabaseHelper.Peixe.PORTE, peixe.getPorte());
        values.put(DatabaseHelper.Peixe.ISCA, peixe.getIsca());

        return getDb().insert(DatabaseHelper.Peixe.TABELA, null, values);
    }

    public boolean removerPeixe(Long id){
        String whereClause = DatabaseHelper.Peixe._ID + " = ?";
        String[] whereArgs = new String[]{id.toString()};
        int remover = getDb().delete(DatabaseHelper.Peixe.TABELA, whereClause, whereArgs);

        return remover > 0;
    }

    private Peixe criarPeixe(Cursor cursor){
        Peixe peixe = new Peixe(
                cursor.getLong(cursor.getColumnIndex(DatabaseHelper.Peixe._ID)),
                cursor.getString(cursor.getColumnIndex(DatabaseHelper.Peixe.NOME)),
                cursor.getString(cursor.getColumnIndex(DatabaseHelper.Peixe.ORIGEM)),
                cursor.getString(cursor.getColumnIndex(DatabaseHelper.Peixe.PORTE)),
                cursor.getString(cursor.getColumnIndex(DatabaseHelper.Peixe.ISCA))
        );
        return peixe;
    }
}
