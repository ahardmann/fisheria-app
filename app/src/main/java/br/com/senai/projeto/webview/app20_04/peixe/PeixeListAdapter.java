package br.com.senai.projeto.webview.app20_04.peixe;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import br.com.senai.projeto.webview.app20_04.R;

public class PeixeListAdapter extends RecyclerView.Adapter<PeixeListAdapter.ViewHolder> {

    private List<Peixe> peixes;
    private Context context;
    private OnDataSelected  onDataSelected;

    public PeixeListAdapter(Context context, OnDataSelected onDataSelected, List<Peixe> peixes) {
        this.context = context;
        this.onDataSelected = onDataSelected;
        this.peixes = peixes;
    }

    @Override
    public PeixeListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_peixe, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Peixe be = peixes.get(position);
        holder.textViewNome.setText(be.getNome());
        holder.textViewOrigem.setText(be.getOrigem());
        holder.textViewPorte.setText(be.getPorte());
        holder.textViewIsca.setText(be.getIsca());
    }

    @Override
    public int getItemCount() {
        return peixes.size();
    }

    public static interface OnDataSelected {

        public void onDataSelected(View view, int position);

    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView textViewNome;
        public TextView textViewOrigem;
        public TextView textViewPorte;
        public TextView textViewIsca;


        public ViewHolder(View view) {
            super(view);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    treatOnDataSelectedIfNecessary(v, getAdapterPosition());
                }
            });

            textViewNome = (TextView)view.findViewById(R.id.nomePeixe);
            textViewOrigem = (TextView)view.findViewById(R.id.origemPeixe);
            textViewPorte = (TextView)view.findViewById(R.id.portePeixe);
            textViewIsca = (TextView)view.findViewById(R.id.iscaPeixe);
        }
    }

    private void treatOnDataSelectedIfNecessary(View view, int position) {
        if(onDataSelected != null) {
            onDataSelected.onDataSelected(view, position);
        }
    }
}
