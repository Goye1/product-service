# product-service

-Para ejecutar este proyecto es necesario tener instalada la bdd en memoria H2: https://www.h2database.com/html/main.html

La ubicacion de la bdd una vez ejecutado el programa es esta: jdbc:h2:~/products 
-username:sa
-password:

Tener en cuenta que esta activado el "create-drop" asi que se va borrar todo y crear de nuevo cada vez que se ejecute.

-La documentacion de la api se encuentra en una coleccion de postman.
Descarga el archivo de la colección de Postman desde [aca](src/docs/product-service.postman_collection.json).

-Aclaracion: el endpoint "/products/search" puede utilizarse tanto como con el id como con el nombre.
 Buscar por nombre: http://localhost:8080/products/search?name=book
 Buscar por id: http://localhost:8080/products/search?id=1

Cuando se busca por nombre se devuelve una lista con las coincidencias encontradas, ya que se esta utilizando una consutla hql "LIKE LOWER(CONCAT('%', :name, '%'))", de esta manera se puede buscar por nombre sin tener que poner el nombre exacto. Si se busca por id devuelve solo un Objeto, no una lista. 
