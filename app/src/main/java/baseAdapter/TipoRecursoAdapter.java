package baseAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import domain.TipoRecurso;
import tela.tearapp.R;

/**
 * Created by Ruan on 29/11/2015.
 */
public class TipoRecursoAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<TipoRecurso> recursos;

    public TipoRecursoAdapter(Context context, ArrayList<TipoRecurso> recursos) {
        this.context = context;
        this.recursos = recursos;
    }



    @Override
    public int getCount() {
        return recursos.size();
    }

    @Override
    public Object getItem(int position) {
        return recursos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TipoRecurso tr = recursos.get(position);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.item_tiporecurso, null);

        TextView cod = (TextView) layout.findViewById(R.id.cod);
        cod.setText(tr.getIdTipoRecurso());

        TextView descricao = (TextView) layout.findViewById(R.id.descricao);
        descricao.setText(tr.getDescricao());

        return layout;
    }
}
