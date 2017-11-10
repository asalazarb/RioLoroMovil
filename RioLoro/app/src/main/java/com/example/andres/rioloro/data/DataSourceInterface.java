package com.example.andres.rioloro.data;

/**
 * Created by usuario on 10/10/2017.
 */


import com.example.andres.rioloro.view.ListActivity;

import java.util.List;

public interface DataSourceInterface {

    //Si desea ingresar una nueva funcionalidad al controlador debe ser por aquí.

    List<ListItem> getListOfData();

    ListItem recargarItem(String especie,String linea,String fechaHora, String image);

    ListItem agregarEspecie(String especie, String nombreCientifico, String imageUrl);

    void deleteListItem(ListItem listItem);

    void insertListItem(ListItem temporaryListItem);
}
