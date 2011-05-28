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
    private UserTopTable tableUserTop = new UserTopTable("Widziane przez ciebie");

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
        

        user.setVisible(false);
        showUserPage(userName);
    }
    
    private void showUserPage(String userName) {
        Window window = getWindow();
        Label userNameLabel = new Label("<h1>"+userName+"</h1>",  Label.CONTENT_XHTML);
        window.showNotification("Witaj " + userName + "!");
        window.addComponent(userNameLabel);
        
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
        table.removeAllItems();
        table.addItemsFromIterator(it);
        getWindow().addComponent(table);
    }
    
    private void showUserChecklistTable() {
        Iterator it = (Iterator) userChecklist.iterator();
        tableUserTop.removeAllItems();
        tableUserTop.addItemsFromIterator(it);
        getWindow().addComponent(tableUserTop);
        
    }
    
    private class UserTopTable extends Top250Table {
        @SuppressWarnings("FieldNameHidesFieldInSuperclass")
        protected static final String DEFAULT_SORT = "Ocena";
        
        @SuppressWarnings("FieldNameHidesFieldInSuperclass")
        protected final Object[][] fields = new Object[][]{
            {"Ocena", Integer.class, null},
            {"Nazwa filmu", String.class, null}
        };
        
        public UserTopTable(String name){
            super(name);
        }
        
        @Override
        public void addItemsFromIterator(Iterator it) {
            int i = 0;
            while (it.hasNext()) {
                Map.Entry mapInstance = (Map.Entry) it.next();
                String movieName = (String)mapInstance.getKey();
                Integer rating = (Integer) mapInstance.getValue();

                table.addItem(new Object[]{rating, movieName}, new Integer(i));
                i++;
            }
            
            setSorting(false); 
        }
        
    }

    private class Top250Table extends Table {

        protected static final String DEFAULT_SORT = "Ranking";
        protected final Object[][] fields = new Object[][]{
            {"Ranking", Integer.class, null},
            {"Ocena", Float.class, null},
            {"Nazwa filmu", String.class, null},
            {"Ilość głosów", String.class, null}
        };

        public Top250Table(String name) {
            super(name);
            setFields();
        }

        private void setFields() {
            for (Object[] field : this.fields) {
                this.addContainerProperty((String) field[0], (Class) field[1], field[2]);
            }
        }
        
        protected void setSorting(boolean ascending) {
            this.setSortContainerPropertyId(DEFAULT_SORT);
            this.setSortAscending(ascending);
        }

        protected void setSorting() {
            setSorting(true);
        }

        public void addItemsFromIterator(Iterator it) {
            int i = 0;
            while (it.hasNext()) {
                Map.Entry mapInstance = (Map.Entry) it.next();
                IMDBChartRecord record = (IMDBChartRecord) mapInstance.getValue();

                table.addItem(record.toArray(), new Integer(i));
                i++;
            }
            
            setSorting(); 
        }
    }
}
