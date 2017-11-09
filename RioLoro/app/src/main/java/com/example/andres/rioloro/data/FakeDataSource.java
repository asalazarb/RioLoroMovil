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
            "geris",
            "Morpho didius",
            "Lumbricidae",
            "Cathartes aura",
            "Ficus pertusa",
            "Heliconia tortuosa",
            "Lantana camara",
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
            R.drawable.green_drawable,
            R.drawable.red_drawable,
            R.drawable.blue_drawable,
            R.drawable.yellow_drawable,
            R.drawable.blue_drawable,
    };


    public FakeDataSource() {
        random = new Random();
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
    public ListItem recargarItem(String especie,String fechaHora){
        ListItem listItem = new ListItem(
                fechaHora,
                especie,
                R.drawable.red_drawable
        );
        return listItem;
    }


    //BORRAR
    @Override
    public ListItem createNewListItem() {

        //these will be 0, 1, 2, or 3
        int randOne = random.nextInt(4);
        int randTwo = random.nextInt(4);
        int randThree = random.nextInt(4);

        //creates a semi-random ListItem
        ListItem listItem = new ListItem(
                getHour(),
                messages[randTwo],
                drawables[randThree]
        );
        return listItem;
    }

    /*-----------------------------------------------------------------
    * SE CREA EL ITEM DE LA ESPECIE
    * ---------------------------------------------------------------*/
    @Override
    public ListItem agregarEspecie(String scientificName, String imagen){

        ListItem listItem = new ListItem(
                getHour(),
                scientificName,
                R.drawable.green_drawable
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
