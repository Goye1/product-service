## Requisitos

- **Base de Datos en Memoria H2**: Para ejecutar este proyecto, es necesario tener instalada la base de datos en memoria H2. https://www.h2database.com/html/main.html.

- **Ubicación de la Base de Datos**: Una vez ejecutado el programa, la ubicación de la base de datos es la siguiente: `jdbc:h2:~/products`  `-username:sa` `-password:`. Tener en cuenta que esta activado el "create-drop" asi que se va borrar todo y crear de nuevo cada vez que se ejecute.

## Documentación de la API

La documentación de la API se encuentra en una colección de Postman. Descargar el archivo de la colección de Postman desde [aca](src/docs/product-service.postman_collection.json).

## Endpoints

### Endpoint `/products/search`

Este endpoint puede utilizarse tanto para buscar productos por ID como por nombre.

- **Buscar por Nombre**: `http://localhost:8080/products/search?name=book`
- **Buscar por ID**: `http://localhost:8080/products/search?id=1`

Cuando se busca por nombre, se devuelve una lista con las coincidencias encontradas. Esto se debe a que se está utilizando una consulta HQL `"LIKE LOWER(CONCAT('%', :name, '%'))"`, lo que permite buscar por nombre sin tener que poner el nombre exacto. Si se busca por ID, se devuelve solo un objeto Product, no una lista.
