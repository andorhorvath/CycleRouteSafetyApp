# CycleRouteSafetyApp

A javaFX application (or a wannabe rich internet application) that let the user see cycle routes, and displays them in a googlemaps map.
The user can add, edit, delete waypoints (POIs) on the map, can use the map as it would be the original maps in a browser.

The routes are stored in a standalone MySQL db.

The application is able to rank the routes according to their safety. The least crossings, the least problematic situations that can arise because of the actual route, the better.
These POIs could be anything that makes cycling there difficult. Branches that must be avoided, crossings that couldn't be seen from speed, typical traffic situations, a park where dogs usually without control, etc.

There is also user management, so the users can see other user's uploads, but can't edit them.
There will be an admin user that can edit other user's routes.
