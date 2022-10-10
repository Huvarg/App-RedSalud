package com.example.redsalud.Adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.redsalud.R;
import com.example.redsalud.Modelo.Persona;

import java.util.ArrayList;

public class AdaptadorP extends BaseAdapter {
    private Context contexto;
    private ArrayList<Persona> listItems;

    public AdaptadorP(Context contexto, ArrayList<Persona> listItems) {
        this.contexto = contexto;
        this.listItems = listItems;
    }

    @Override
    public int getCount() {
        return listItems.size();
    }

    @Override
    public Object getItem(int position) {
        return listItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        view = LayoutInflater.from(contexto).inflate(R.layout.items_listview_p,null);
        ImageView imgp = (ImageView) view.findViewById(R.id.imgp);
        TextView textnombre = (TextView) view.findViewById(R.id.textnombre);
        TextView textapellido = (TextView) view.findViewById(R.id.textapellido);
        TextView textespecialidad = (TextView) view.findViewById(R.id.textespecialidad);
        Persona p = (Persona) getItem(position);
        Glide.with(contexto)
                .load(p.getRuta())
                .centerCrop()
                .fitCenter()
                .into(imgp);
        textnombre.setText(p.getNombre());
        textapellido.setText(p.getApellido());
        textespecialidad.setText(p.getEspecialidad());
        return view;
    }

}