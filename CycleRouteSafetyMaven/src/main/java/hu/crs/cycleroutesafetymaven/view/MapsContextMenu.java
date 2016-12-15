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
public class MapsContextMenu extends ContextMenu {

    MenuItem item1;
    MenuItem item2;
    MenuItem item3;
    MenuItem item4;
    
    public MapsContextMenu() {
        this.item1 = new MenuItem("add new marker");
        this.item2 = new MenuItem("what is here?");
        this.item3 = new MenuItem("center map here");
        this.item4 = new MenuItem("+++add waypoint");
    }
}