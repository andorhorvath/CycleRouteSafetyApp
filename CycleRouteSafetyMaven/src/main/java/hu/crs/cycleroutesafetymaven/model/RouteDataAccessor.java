/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.crs.cycleroutesafetymaven.model;

import java.sql.Connection ;
import java.sql.DriverManager ;
import java.sql.SQLException ;
import java.sql.Statement ;
import java.sql.ResultSet ;
import java.sql.Timestamp;

import java.util.List ;
import java.util.ArrayList ;
import java.util.Date;

/**
 *
 * @author ahorvath
 */
public class RouteDataAccessor {
    // TODO: use a connection pool....
    private Connection connection ;

    public RouteDataAccessor(String driverClassName, String dbURL, String user, String password) throws SQLException, ClassNotFoundException {
        Class.forName(driverClassName);
        connection = DriverManager.getConnection(dbURL, user, password);
    }

    public void shutdown() throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }

    public List<Route> getRouteList() throws SQLException {
        try (
            Statement stmnt = connection.createStatement();
            ResultSet resultSet = stmnt.executeQuery("select * from routes");
        ){
            List<Route> routeList = new ArrayList<>();
            while (resultSet.next()) {
                String name = resultSet.getString("routeName");
                String author = resultSet.getString("author");
                String start = resultSet.getString("startPoint");
                String finish = resultSet.getString("finishPoint");
                Integer length = resultSet.getInt("routeLength");
                Timestamp lastUpdateTimeStamp = resultSet.getTimestamp("lastUpdateTime");
                Date lastUpdateTime = lastUpdateTimeStamp;
                Boolean isDirectionsUsed = resultSet.getBoolean("plannedRoute");
                
                Route route = new Route(name, author, start, finish, length, lastUpdateTime, isDirectionsUsed);
                routeList.add(route);
            }
            return routeList ;
        } 
    }

    
    public void addRoute() {
        //TODO: add new Route to DB
    }
    
    public void updateRoute(Route routeToUpdate, Route newValues) {
        //TODO: update the given route w the newRoute's values
    }
}
