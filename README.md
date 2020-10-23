# delivery_manager
This project is a system of deliveries designed to unify the delivery data of different restaurants, allowing to import/export and adding information about the costumers, products, and delivery information and status. 
- fully coded on java 1.8.0 on windows 10 using Eclipse workspace.
- find the documentation here [Documentation](doc/deliveryClassDiagram.pdf)
- Since v1 some addons and changes has been made:
### Changed
- Client list is sorted after is imported using comparable interface and collections.sort [comparable method ](src\model\Client.java)  / [Collections.sort method](src\model\Manager.java)
- Client is added in a correct position (Not adding and sorting) following the lastname/name sorting order. [add client method](src\model\Manager.java) 
- Exported delivery information, is now exported sorted by The restaurant nit - client document descendant - date of delivery ascendant, using comparator and collections.sort .[export method and delivery sorting method](src\mode\manager.java)
### Added
- Option nine, allows to search efficiently a client by a given name, first sorting the clients list by name using comparator (in menu option). [added option in ui menu](src\ui\Menu.java) / [search client method in model manager](src\model\Manager.java)
- Option ten, shows a list of all the registered/imported restaurants sorted by name using comparable, and sorting with bouble sorting method. [sort restaurant method](src\model\Manager.java)
- Option eleven, Shows a list of all the registered/imported clients sorted by phone number using comparable, and sorting with selection sorting method.[sort clients by phone number](src\model\Manager.java)