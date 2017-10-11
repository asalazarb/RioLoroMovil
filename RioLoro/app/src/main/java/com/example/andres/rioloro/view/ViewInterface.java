package com.example.andres.rioloro.view;

/**
 * Created by usuario on 10/10/2017.
 */

import android.view.View;

import java.util.List;

import com.example.andres.rioloro.data.ListItem;

public interface ViewInterface {
    void startDetailActivity(String dateAndTime, String message, int colorResource, View viewRoot);

    void setUpAdapterAndView(List<ListItem> listOfData);

    void addNewListItemToView(ListItem newItem);

    void deleteListItemAt(int position);

    void showUndoSnackbar();

    void insertListItemAt(int temporaryListItemPosition, ListItem temporaryListItem);
}
