/*
 * MyApplication.java
 *
 * Created on 28 maj 2011, 13:13
 */
package com.example.vaadin;

import com.example.server.IMDBChartList;
import com.example.util.IMDBChartRecord;
import com.example.util.UserChecklist;
import com.vaadin.Application;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.ui.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/** 
 *
 * @author ravbaker
 * @version 
 */
public class MyApplication extends Application implements Property.ValueChangeListener {

    Window mainWindow;
    private final TextField user = new TextField("Jak się nazywasz?");
    private final static String CHART_LIST_CSV_FILE_PATH = "/Users/ravbaker/NetBeansProjects/vaadin-biu/src/java/com/example/files/top250.csv";
    private IMDBChartList chartList;
    private UserChecklist userChecklist;
    private Top250Table table = new Top250Table("IMDB Top 250");

    @Override
    public void init() {
        mainWindow = new Window("IMDB top 250 chart checker");

        setMainWindow(mainWindow);

        showInputUserName();
        initMoviesList();

    }

    private void showInputUserName() {
        user.addListener(this);
        user.setImmediate(true);
        getWindow().addComponent(user);
    }

    @Override
    public void valueChange(ValueChangeEvent event) {
        String userName = (String) user.getValue();
        // Show the new value we received
        getWindow().showNotification("Witaj " + userName + "!");

        userChecklist = new UserChecklist(userName);
        showMoviesTable();
    }

    private Window getWindow() {
        return mainWindow;
    }

    private void initMoviesList() {
        chartList = new IMDBChartList(CHART_LIST_CSV_FILE_PATH);

    }

    private void showMoviesTable() {
        Iterator it = chartList.iterator();

        table.addItemsFromIterator(it);

        getWindow().addComponent(table);
    }

    private class Top250Table extends Table {

        private static final String DEFAULT_SORT = "Ranking";
        private final Object[][] fields = new Object[][]{
            {"Ranking", Integer.class, null},
            {"Ocena", Float.class, null},
            {"Nazwa filmu", String.class, null},
            {"Ilość głosów", String.class, null}
        };

        public Top250Table(String name) {
            super(name);
            setFields();
            setSorting();
        }

        private void setFields() {
            for (Object[] field : this.fields) {
                this.addContainerProperty((String) field[0], (Class) field[1], field[2]);
            }
        }

        private void setSorting() {
            this.setSortContainerPropertyId(DEFAULT_SORT);
            this.setSortAscending(true);
        }

        public void addItemsFromIterator(Iterator it) {
            int i = 0;
            while (it.hasNext()) {
                Map.Entry mapInstance = (Map.Entry) it.next();
                IMDBChartRecord record = (IMDBChartRecord) mapInstance.getValue();

                table.addItem(record.toArray(), new Integer(i));
                i++;
            }
        }
    }
}
