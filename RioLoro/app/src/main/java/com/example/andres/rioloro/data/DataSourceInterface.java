package com.example.andres.rioloro.data;

/**
 * Created by usuario on 10/10/2017.
 */


import java.util.List;

public interface DataSourceInterface {

    //Si desea ingresar una nueva funcionalidad al controlador debe ser por aquí.

    List<ListItem> getListOfData();

    ListItem createNewListItem();

    ListItem recargarItem(int x);

    ListItem crearItem(int x);

    void deleteListItem(ListItem listItem);

    void insertListItem(ListItem temporaryListItem);
}
