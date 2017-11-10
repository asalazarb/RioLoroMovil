package com.example.andres.rioloro.data;

/**
 * Created by usuario on 10/10/2017.
 */

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Date;
import java.text.*;
import com.example.andres.rioloro.R;
import com.example.andres.rioloro.persistence.DatabaseHelper;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class FakeDataSource implements DataSourceInterface{

    private Random random;

    //URL para el servidor
    final String serverUrl = "http://172.20.10.3:5050";

    private final String[] especies = {
            "Morpho didius",
            "geris",
            "Lumbricidae",
            "Cathartes aura",
            "Ficus pertusa",
            "Heliconia tortuosa"
    };

    private final String[] messages = {
            "ultimo gerald",

            "Mariposa Morpho\nNombre Cientfíco: Morpho didius\nReino: Animalia\nFilo: Arthropoda\nClase: Insecta\nOrden: Lepidoptera\nFamilia: Nymphalidae\nGénero: Morpho",

            "Lombriz de tierra\nNombre Cientfíco: Lumbricidae\nReino: Animalia\nFilo: Annelida\nClase: Clitellata\nOrden: Haplotaxida\nFamilia: Lumbricidae\nGénero: Lumbricus",

            "Zopilote\nNombre Cientfíco: Cathartes aura\nReino: Animalia\nFilo: Chrdata\nClase: Aves\nOrden: Falconiformes\nFamilia: Cathartidae\nGénero: Cathartes",

            "Amatillo\nNombre Cientfíco: Ficus pertusa\nReino: Plantae\nFilo: Magnoliophyta\nClase: Magnoliopsida\nOrden: Urticales\nFamilia: Moraceae\nGénero: Ficus",

            "Heliconia Tortuosa\nNombre Cientfíco: Heliconia tortuosa\nReino: Plantae\nFilo: Monocots\nClase: Commelinids\nOrden: Zingiberales\nFamilia: Heliconiaceae\nGénero: Heliconia",

            "Lantana\nNombre Cientfíco: Lantana camara\nReino: Plantae\nFilo: Magnoliophyta\nClase: Magnoliopsida\nOrden: Lamiales\nFamilia: Verbenaceae\nGénero: Lantana"
    };

    private final int[] drawables = {
            R.drawable.morpho,
            R.drawable.green_drawable,
            R.drawable.red_drawable,
            R.drawable.blue_drawable,
            R.drawable.yellow_drawable,
            R.drawable.blue_drawable,
    };


    public FakeDataSource() {
        random = new Random();
    }

    public Integer posicionImagen(String nombre){
        int posicion = 0;

        for (int i=0; i<especies.length; i++){
            if (especies[i].equals(nombre)){
                posicion=i;
            }
        }
        return posicion;
    }

    /**
     * Creates a list of ListItems.
     *
     * @return A list of 12 semi-random ListItems for testing purposes
     */

    /*Función que da la hora*/
    public String getHour(){
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm a");
        return simpleDateFormat.format(date);
    }

    /*Cada vez que el dispositivo cambia de orientación esta función recrea el contenido*/
    /*==================================================================================*/
    @Override
    public List<ListItem> getListOfData() {
        ArrayList<ListItem> listOfData = new ArrayList<>();

        return listOfData;
    }
    /*==================================================================================*/

    @Override
    public ListItem recargarItem(String especie,String linea ,String fechaHora, String image){

        Integer pos = posicionImagen(especie);

        ListItem listItem = new ListItem(
                fechaHora,
                linea,
                drawables[pos],
                image
        );
        return listItem;
    }

    /*-----------------------------------------------------------------
    * SE CREA EL ITEM DE LA ESPECIE
    * ---------------------------------------------------------------*/
    @Override
    public ListItem agregarEspecie(String especie,String linea, String image){

        Integer pos = posicionImagen(especie);

        ListItem listItem = new ListItem(
                getHour(),
                linea,
                drawables[pos],
                image
        );
        return listItem;

    }

    @Override
    public void deleteListItem(ListItem listItem) {

    }

    @Override
    public void insertListItem(ListItem temporaryListItem) {

    }
}
