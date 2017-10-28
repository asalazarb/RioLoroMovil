package com.example.andres.rioloro.data;

/**
 * Created by usuario on 10/10/2017.
 */

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Date;
import java.text.*;
import com.example.andres.rioloro.R;
import com.example.andres.rioloro.persistence.DatabaseHelper;

public class FakeDataSource implements DataSourceInterface{
    private static final int sizeOfCollection = 12;

    private Random random;

    private final String[] datesAndTimes = {
            "6:30AM 10/13/2017",
            "9:26PM 10/10/2017",
            "2:43AM 10/10/2017",
            "2:13PM 9/10/2017",
            "2:11PM 9/10/2017",
            "2:01PM 9/10/2017",
    };

    private final String[] messages = {
            "Mariposa Morpho\nNombre Cientfíco: Morpho didius\nReino: Animalia\nFilo: Arthropoda\nClase: Insecta\nOrden: Lepidoptera\nFamilia:  	Nymphalidae\nGénero: Morpho",

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
            R.drawable.yellow_drawable
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
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm a dd/MM/yyyy");
        return simpleDateFormat.format(date);
    };

    /*-----------------------------------------------------------------
    * SE CREA EL ITEM DE LA ESPECIE
    * ---------------------------------------------------------------*/
    public ListItem addSpecie(String scientificName){
        //Conexion a la base
        //Llamar al sp con el scientificName
        //Crear el listItem
        ListItem listItem = new ListItem(
                getHour(),
                messages[0],
                drawables[0]
        );

        return listItem;

    }

    /*Cada vez que el dispositivo cambia de orientación esta función recrea el contenido*/
    /*==================================================================================*/
    //Cuando se desea extraer los datos de la base SQLite se debe hacer en esta función
    @Override
    public List<ListItem> getListOfData() {
        ArrayList<ListItem> listOfData = new ArrayList<>();

        return listOfData;
    }
    /*==================================================================================*/

    public ListItem recargarItem(int x){
        ListItem listItem = new ListItem(
                getHour(),
                messages[x%4],
                drawables[x%4]
        );
        return listItem;
    }

    public ListItem crearItem(int x){
        ListItem listItem = new ListItem(
                getHour(),
                messages[x],
                drawables[x%4]
        );
        String nombreCientifico = String.valueOf(x);

        return listItem;
    }



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

    @Override
    public void deleteListItem(ListItem listItem) {

    }

    @Override
    public void insertListItem(ListItem temporaryListItem) {

    }
}
