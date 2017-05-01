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
public class HazardMarkerOptions extends MarkerOptions {
//        - position: LatLong
//	- title: Sting
//	- visible: Boolean
//	- icon: String (path to file)
//	- animation: Animation
        
        public HazardMarkerOptions () {
        }
        
        public HazardMarkerOptions (LatLong position) {
            this.position = position;
            this.title = "hazard-options";
            visible = true;
            animation = Animation.DROP;
            icon = "icons/jardaszegely-hazard-kicsi.png";
        }
}
