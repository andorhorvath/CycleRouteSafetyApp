CREATE TABLE Routes (
    routeID INT NOT NULL AUTO_INCREMENT,
    routeName VARCHAR(100) NOT NULL UNIQUE,
    author VARCHAR(50) NOT NULL,
    startPoint VARCHAR(100) NOT NULL,
    finishPoint VARCHAR(100) NOT NULL,
    routeLength INT NOT NULL,
    lastUpdateTime DATETIME,
    PRIMARY KEY (routeID)
);

CREATE TABLE Pois (
    poiID INT NOT NULL AUTO_INCREMENT,
    routeID INT NOT NULL,
    x INT,
    y INT,
    poiType VARCHAR(50) NOT NULL,
    PRIMARY KEY (poiID)
);