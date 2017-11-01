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


    //Una vez que el código es leído deberá ser enviado a esta función
    public void createNewListItem() {
        /*
        To simulate telling the DataSource to create a new record and waiting for it's response,
        we'll simply have it return a new ListItem.

        In a real App, I'd use RxJava 2 (or some other
        API/Framework for Asynchronous Communication) to have the Datasource do this on the
         IO thread, and respond via an Asynchronous callback to the Main thread.
         */

        ListItem newItem = dataSource.createNewListItem();

        view.addNewListItemToView(newItem);
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

    public ListItem crearEspecie(int x){
        ListItem item = dataSource.recargarItem(x);
        return item;
    }
}
