# create database cycleroutes;
# use cycleroutes;
# create user 'ahorvath'@'localhost' identified by 'dummy';

CREATE TABLE Routes (
    routeID INT NOT NULL AUTO_INCREMENT,
    routeName VARCHAR(100) NOT NULL UNIQUE,
    author VARCHAR(50) NOT NULL,
    startPoint VARCHAR(100) NOT NULL,
    finishPoint VARCHAR(100) NOT NULL,
    routeLength INT NOT NULL,
    lastUpdateTime DATETIME,
    plannedRoute BOOLEAN DEFAULT false;
    PRIMARY KEY (routeID)
);

CREATE TABLE Pois (
    poiID INT NOT NULL AUTO_INCREMENT,
    routeID INT NOT NULL,
    x INT,
    y INT,
    poiType VARCHAR(50) NOT NULL,
    isAlreadyPlanned BOOLEAN DEFAULT false;
    PRIMARY KEY (poiID)
);


# giving in some data for testing purposes
INSERT INTO cycleroutes.routes (`routeName`, author, `startPoint`, `finishPoint`, `routeLength`, `lastUpdateTime`) 
	VALUES ('Pálma utca - Haller Gardens', 'Andor', 'Budapest, Pálma utca 6, 1146', 'Haller Gardens - Budapest, Soroksári út 34, 1095', 7, false, NOW() );
INSERT INTO cycleroutes.routes (`routeName`, author, `startPoint`, `finishPoint`, `routeLength`, `lastUpdateTime`) 
	VALUES ('Haller Gardens - Pálma utca', 'Andor', 'Haller Gardens - Budapest, Soroksári út 34, 1095', 'Budapest, Pálma utca 6, 1146', 7, false, NOW());
INSERT INTO cycleroutes.routes (`routeName`, author, `startPoint`, `finishPoint`, `routeLength`, `lastUpdateTime`) 
	VALUES ('Otthonról Rendelőbe', 'Klára', 'Budapest, Pálma utca 6, 1146', 'Hevesi Sándor tér 1, Budapest, 1077', 3, false, CURDATE() );

INSERT INTO pois ( routeID, poiType, Lat, Lon, planned )
        VALUES ( 3, 'finish',null, null, false );
