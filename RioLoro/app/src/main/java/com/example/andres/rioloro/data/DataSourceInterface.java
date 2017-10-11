package com.example.andres.rioloro.data;

/**
 * Created by usuario on 10/10/2017.
 */


import java.util.List;

public interface DataSourceInterface {

    List<ListItem> getListOfData();

    ListItem createNewListItem();

    void deleteListItem(ListItem listItem);

    void insertListItem(ListItem temporaryListItem);
}
