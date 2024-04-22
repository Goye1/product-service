# Mi Proyecto de Productos

Este proyecto es una aplicación de gestión de productos desarrollada con Java y Spring Boot, utilizando una base de datos en memoria H2.

## Requisitos

- **Base de Datos en Memoria H2**: Para ejecutar este proyecto, es necesario tener instalada la base de datos en memoria H2. Puedes descargarla desde [aquí](https://www.h2database.com/html/main.html).

- **Ubicación de la Base de Datos**: Una vez ejecutado el programa, la ubicación de la base de datos es la siguiente: `jdbc:h2:~/products -username:sa -password:`. Ten en cuenta que esta activado el "create-drop", lo que significa que se borrará todo y se creará de nuevo cada vez que se ejecute la aplicación.

## Documentación de la API

La documentación de la API se encuentra en una colección de Postman. Puedes descargar el archivo de la colección de Postman desde [aquí](#).

## Endpoints

### Endpoint `/products/search`

Este endpoint puede utilizarse tanto para buscar productos por ID como por nombre.

- **Buscar por Nombre**: `http://localhost:8080/products/search?name=book`
- **Buscar por ID**: `http://localhost:8080/products/search?id=1`

Cuando se busca por nombre, se devuelve una lista con las coincidencias encontradas. Esto se debe a que se está utilizando una consulta HQL `"LIKE LOWER(CONCAT('%', :name, '%'))"`, lo que permite buscar por nombre sin tener que poner el nombre exacto. Si se busca por ID, se devuelve solo un objeto, no una lista.

## Conclusión

Este proyecto es un ejemplo de cómo desarrollar una aplicación de gestión de productos utilizando Java, Spring Boot, y una base de datos en memoria H2. La documentación de la API proporcionada en Postman facilita la interacción con los endpoints disponibles.

Si tienes alguna pregunta o necesitas más información, no dudes en contactarme.
