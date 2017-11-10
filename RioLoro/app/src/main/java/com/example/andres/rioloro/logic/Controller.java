package com.example.andres.rioloro.logic;

/**
 * Created by usuario on 10/10/2017.
 */

import android.view.View;

import com.example.andres.rioloro.data.DataSourceInterface;
import com.example.andres.rioloro.data.ListItem;
import com.example.andres.rioloro.view.ViewInterface;

public class Controller {
    private ListItem temporaryListItem;
    private int temporaryListItemPosition;

    private ViewInterface view;


    private DataSourceInterface dataSource;

    public Controller(ViewInterface view, DataSourceInterface dataSource) {
        this.view = view;
        this.dataSource = dataSource;

        getListFromDataSource();
    }

    public void onListItemClick(ListItem selectedItem, View viewRoot){
        view.startDetailActivity(
                selectedItem.getDateAndTime(),
                selectedItem.getMessage(),
                selectedItem.getColorResource(),
                selectedItem.getImage(),
                viewRoot
        );
    }

    /**
     * In a real App, I would normally talk to this DataSource using RxJava 2. This is because most
     * calls to Services like a Database/Server should be executed on a seperate thread that the
     * mainThread (UI Thread). See my full projects for examples of this.
     */
    public void getListFromDataSource(){
        view.setUpAdapterAndView(
                dataSource.getListOfData()
        );
    }

    public void agregarEspecie(String especie,String nombreCientifico, String image){
        ListItem nuevaEspecie = dataSource.agregarEspecie(especie,nombreCientifico,image);
        view.addNewListItemToView(nuevaEspecie);
    }

    public void onListItemSwiped(int position, ListItem listItem) {
        //ensure that the view and data layers have consistent state
        dataSource.deleteListItem(listItem);
        view.deleteListItemAt(position);

        temporaryListItemPosition = position;
        temporaryListItem = listItem;

        view.showUndoSnackbar();

    }

    public void onUndoConfirmed() {
        if (temporaryListItem != null){
            //ensure View/Data consistency
            dataSource.insertListItem(temporaryListItem);
            view.insertListItemAt(temporaryListItemPosition, temporaryListItem);

            temporaryListItem = null;
            temporaryListItemPosition = 0;

        } else {

        }

    }

    public void onSnackbarTimeout() {
        temporaryListItem = null;
        temporaryListItemPosition = 0;
    }

    public ListItem crearEspecie(String especie,String linea, String fechaHora,String image){
        ListItem item = dataSource.recargarItem(especie,linea,fechaHora,image);
        return item;
    }
}
