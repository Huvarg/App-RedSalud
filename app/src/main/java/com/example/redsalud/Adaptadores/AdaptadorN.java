package com.example.redsalud.Adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.example.redsalud.Modelo.Noticia;
import com.example.redsalud.R;
import java.util.ArrayList;

public class AdaptadorN extends BaseAdapter {
    private Context contexto;
    private ArrayList<Noticia> listItems;

    public AdaptadorN(Context contexto, ArrayList<Noticia> listItems) {
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
        view = LayoutInflater.from(contexto).inflate(R.layout.items_listview_n,null);
        ImageView imgFoto = (ImageView) view.findViewById(R.id.imgFoto);
        TextView textTitulo = (TextView) view.findViewById(R.id.textTitulo);
        Noticia n = (Noticia) getItem(position);
        Glide.with(contexto)
                .load(n.getRutaimg())
                .centerCrop()
                .fitCenter()
                .into(imgFoto);
        textTitulo.setText(n.getTitulo());
        return view;
    }

}