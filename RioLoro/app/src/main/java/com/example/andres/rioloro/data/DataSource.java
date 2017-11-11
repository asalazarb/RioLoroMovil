package com.example.andres.rioloro.data;

/**
 * Created by usuario on 10/10/2017.
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Date;
import java.text.*;
import com.example.andres.rioloro.R;

public class DataSource implements DataSourceInterface{

    private Random random;

    //URL para el servidor
    final String serverUrl = "http://172.20.10.3:5050";

    public DataSource() {
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
    public ListItem recargarItem(String especie,String linea ,String fechaHora, String image){

        ListItem listItem = new ListItem(
                fechaHora,
                linea,
                R.drawable.white_drawable,
                image
        );
        return listItem;
    }

    /*-----------------------------------------------------------------
    * SE CREA EL ITEM DE LA ESPECIE
    * ---------------------------------------------------------------*/
    @Override
    public ListItem agregarEspecie(String especie,String linea, String image){

        ListItem listItem = new ListItem(
                getHour(),
                linea,
                R.drawable.white_drawable,
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
