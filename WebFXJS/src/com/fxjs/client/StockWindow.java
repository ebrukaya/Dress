package com.fxjs.client;

import com.extjs.gxt.ui.client.Style;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.Label;
import com.extjs.gxt.ui.client.widget.Window;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.tips.QuickTip;
import com.google.gwt.user.client.Element;

import java.util.ArrayList;
import java.util.List;


public class StockWindow extends Window {




    @Override
    protected void onRender(Element parent, int pos) {
        super.onRender(parent, pos);



//        Label label = new Label("MERHABA");
//        add(label);

        List<ColumnConfig> configs = new ArrayList<ColumnConfig>();
        ListStore store = new ListStore();
        ColumnModel cm = new ColumnModel(configs);


        ColumnConfig column = new ColumnConfig();
        column.setId("kod");
        column.setHeader("Kod");
        column.setWidth(80);
        column.setRowHeader(true);
        configs.add(column);

        column = new ColumnConfig();
        column.setId("seri");
        column.setHeader("Seri");
        column.setWidth(80);
        column.setRowHeader(true);
        configs.add(column);


        Grid grid = new Grid(store, cm);
        grid.setStyleAttribute("borderTop", "none");
//        grid.setAutoExpandColumn("kod");
        grid.setBorders(true);
        grid.setStripeRows(true);
        grid.setColumnLines(true);
        grid.setColumnReordering(true);
        grid.setWidth(400);
        grid.setHeight(400);



        ContentPanel cp = new ContentPanel();
    cp.setBodyBorder(true);
    cp.setHeading("Stock Grid");
    cp.setButtonAlign(Style.HorizontalAlignment.CENTER);
    cp.setLayout(new FitLayout());
//    cp.getHeader().setIconAltText("Grid Icon");
    cp.setSize(600, 300);

        cp.add(grid);
        add(cp);
        new QuickTip(grid);



    }
}
