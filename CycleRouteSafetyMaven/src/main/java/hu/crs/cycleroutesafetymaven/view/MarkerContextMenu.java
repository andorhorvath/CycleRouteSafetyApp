/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.crs.cycleroutesafetymaven.view;

import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;

/**
 * This class is a specialization of javafx's contextMenu, with three predefined
 * items.
 * @author ahorvath
 */
public class MarkerContextMenu extends ContextMenu {

    MenuItem item1;
    MenuItem item2;
    MenuItem item3;
    
    public MarkerContextMenu() {
        this.item1 = new MenuItem("delete marker");
        this.item2 = new MenuItem("edit marker");
        this.item3 = new MenuItem("move marker");
    }
}


