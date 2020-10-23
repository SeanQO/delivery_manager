# delivery_manager
This project is a system of deliveries designed to unify the delivery data of different restaurants, allowing to import/export and adding information about the costumers, products, and delivery information and status. 
- fully coded on java 1.8.0 on windows 10 using Eclipse workspace.
- find the documentation here [Documentation](doc/deliveryClassDiagram.pdf)
- Since v1 some addons and changes has been made:
### Changed
- Client list is sorted after is imported using comparable interface and collections.sort [comparable method ](src\model\Client.java)  / [Collections.sort method](src\model\Manager.java)
- Client is added in a correct position (Not adding and sorting) following the lastname/name sorting order. [add client method](src\model\Manager.java) 
### Added
- Option nine, allows to search efficiently a client by a given name. [added option in ui menu](src\ui\Menu.java) / [search client method in model manager](src\model\Manager.java)
