# ### POIs TABLE ### #
DESCRIBE Pois;
SELECT * FROM Pois;

# ### ROUTES TABLE ### #
DESCRIBE Routes;
SELECT * FROM Routes;

# giving in some data for testing purposes
INSERT INTO cycleroutes.routes (`routeName`, author, `startPoint`, `finishPoint`, `routeLength`, `lastUpdateTime`) 
	VALUES ('Pálma utca - Haller Gardens', 'Andor', 'Budapest, Pálma utca 6, 1146', 'Haller Gardens - Budapest, Soroksári út 34, 1095', 7, NOW() );
INSERT INTO cycleroutes.routes (`routeName`, author, `startPoint`, `finishPoint`, `routeLength`, `lastUpdateTime`) 
	VALUES ('Haller Gardens - Pálma utca', 'Andor', 'Haller Gardens - Budapest, Soroksári út 34, 1095', 'Budapest, Pálma utca 6, 1146', 7, NOW());
INSERT INTO cycleroutes.routes (`routeName`, author, `startPoint`, `finishPoint`, `routeLength`, `lastUpdateTime`) 
	VALUES ('Otthonról Rendelőbe', 'Klára', 'Budapest, Pálma utca 6, 1146', 'Hevesi Sándor tér 1, Budapest, 1077', 3, CURDATE() );


# ALTER TABLE Routes DROP lastUpdateTime;
ALTER TABLE Routes ADD plannedRoute BOOLEAN;
# ALTER TABLE cycleroutes.routes ADD UNIQUE (routeName);
