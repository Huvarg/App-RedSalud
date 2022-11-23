package com.example.redsalud.Adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.example.redsalud.Modelo.CentroSalud;
import com.example.redsalud.R;
import java.util.ArrayList;

public class AdaptadorCS extends BaseAdapter {
    private Context contexto;
    private ArrayList<CentroSalud> listItems;

    public AdaptadorCS(Context contexto, ArrayList<CentroSalud> listItems) {
        this.contexto = contexto;
        this.listItems = listItems;
    }

    @Override
    public int getCount() {
        return listItems.size();
    }

    @Override
    public Object getItem(int i) {
        return listItems.get(i);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        view = LayoutInflater.from(contexto).inflate(R.layout.items_listview_cs,null);
        TextView textNombreCentro = (TextView) view.findViewById(R.id.textNombreCentro);
        CentroSalud c = (CentroSalud) getItem(position);
        textNombreCentro.setText(c.getNombre());
        return view;
    }

}