package com.example.redsalud;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import com.example.redsalud.Adaptadores.AdaptadorN;
import com.example.redsalud.Modelo.Noticia;
import com.example.redsalud.Modelo.Persona;

import java.util.ArrayList;

public class ForoNoticia extends Fragment {
    private ArrayList<Noticia> listado;
    Noticia n;

    public static ForoNoticia newInstance() {
        ForoNoticia fragment = new ForoNoticia();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_foro_noticia, container, false);

        TextView titleToolbar = getActivity().findViewById(R.id.toolbarName);
        titleToolbar.setText("Noticias");

        ListView l = (ListView) view.findViewById(R.id.lvNoticia);
        AdaptadorN adaptadorN = new AdaptadorN(getContext(),cargar_datos());
        l.setAdapter(adaptadorN);

        l.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Noticia n = listado.get(position);
                Intent intent = new Intent(getActivity(),DetalleNoticia.class);
                intent.putExtra("noticia",n);
                startActivity(intent);
            }
        });
        return view;
    }

    public ArrayList<Noticia> cargar_datos() {
        listado = new ArrayList<>();
        listado.add(new Noticia("Alimento","https://firebasestorage.googleapis.com/v0/b/redsalud-f48e3.appspot.com/o/Foro-Noticias%2Falimento-500x500.jpg?alt=media&token=14fa16fb-e59a-4df9-99bd-0bffb9edb7e3","Alimentos para reducir el colesterol.","El experto señala que además, los alimentos deben ser ricos en ácidos grasos poliinsaturados, ya que estos disminuyen los niveles de colesterol, deben tener estándoles y esteroles, que son unos compuestos vegetales que impiden la absorción del colesterol a nivel del intestino. “Finalmente, deben contener antocianinas que favorecen una menor producción de colesterol en el cuerpo”, añade.\n" +
                "Aunque no hay un único alimento que tenga todas estas características en cantidades tan importantes para que por sí solo pueda ser la clave en la reducción del colesterol, existen una amplia variedad que poseen algunas de las características citadas y por tanto deberían consumirse habitualmente, tal y como indica ahora:\n" +
                "1) El aguacate\n" +
                "2) Los cereales integrales\n" +
                "3) Los frutos secos\n" +
                "4) Legumbres\n" +
                "5) Verdura\n" +
                "6) Los alimentos ricos en antocianinas\n"));
        listado.add(new Noticia("Consejo","https://firebasestorage.googleapis.com/v0/b/redsalud-f48e3.appspot.com/o/Foro-Noticias%2Falergias-500x500.jpg?alt=media&token=f7ca3f85-8d3f-420f-b7f0-67a68a7e6fdb","Alergias: Consejos para ventilar tu hogar.","Tanto para los pequeños como para los más grandes, la primavera se torna insoportable si es que se sufren de alergias. Las esporas, el polen, el polvo, el sol, algunas flores, hojas, etc., afectan las narices y ojos tuyos y de tus niños. Es por eso que, queremos entregarte estos consejos para que sepas como airear de forma correcta tu casa y parcialmente librarla de aquellos agentes que producen incesantes estornudos.\n" +
                "Si el problema es el polvo, te recomendamos que al momento de limpiar tus muebles, adornos, utensilios, etc., lo hagas con un paño húmedo y no utilizando un plumero. Si lo haces de esta forma, el polvo quedará en el paño y no se levantarán esporas que dañen la salud de tu familia.\n" +
                "Si se trata de polen, lo recomendable es que durante la noche abras ventanas y puertas de tu casa, debido a que es cuando los niveles de liberación disminuyen y evitas que ingresen nuevas esporas a tu casa. Al mismo tiempo, limpiarás tu casa de ácaros u otros microorganismos volátiles que se transportan con las brisas de viento.\n"));
        listado.add(new Noticia("Alimento","https://firebasestorage.googleapis.com/v0/b/redsalud-f48e3.appspot.com/o/Foro-Noticias%2FChia-Fresca-Feature-500x500.jpg?alt=media&token=da0731f4-c0c2-4e0b-94ed-f4e7c3487572","Limón y agua: Una mezcla perfecta.","El agua con limón es una combinación perfecta para hidratar el cuerpo, pero con el valor añadido de las propiedades aportadas por el limón, ya que este cítrico ayuda a recomponer el organismo con las sales y los minerales que ha perdido debido a la deshidratación. El agua con limón es, por lo tanto, una forma natural de recuperar el agua del organismo. Además, esta combinación natural ofrece otras ventajas también muy importantes, ya que está muy recomendada para situaciones de temperatura, humedad y calor excesivos combinados, o en caso de diarrea, todas ellas situaciones que se reproducen con mucha frecuencia, sobre todo en los meses de verano. Para conseguir la proporción más saludable entre ambos elementos, hay que mezclar el zumo de dos limones en un litro de agua y, si se desea, incluso se le puede añadir un poco (aunque solo un poco) de bicarbonato o de sal, con el fin de incorporarle un extra de sales y de minerales."));
        return listado;
    }

}