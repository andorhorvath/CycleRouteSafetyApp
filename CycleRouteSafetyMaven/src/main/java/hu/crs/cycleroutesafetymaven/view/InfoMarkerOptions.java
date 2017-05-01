/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.crs.cycleroutesafetymaven.view;

import com.lynden.gmapsfx.javascript.object.Animation;
import com.lynden.gmapsfx.javascript.object.LatLong;
import com.lynden.gmapsfx.javascript.object.MarkerOptions;
/**
 *
 * @author meow
 */
public class InfoMarkerOptions extends MarkerOptions {
        
        public InfoMarkerOptions () {
        }
        
        public InfoMarkerOptions (LatLong position) {
            this.position = position;
            this.title = "info-options";
            visible = true;
            animation = Animation.DROP;
//            icon = "path/to/info-icon-file";
        }
}