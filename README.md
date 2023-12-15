# CaC-23545_TP_Final
Trabajo Final del curso Full Stack Java de Codo a Codo

## Video Demo Integracion con el Frontend

https://www.youtube.com/watch?v=h-hgWX4VrgE

## Frontend

https://github.com/manfredcamacho/JAVA-FRONT-END-2023-2

# Requisitos
Para ejecutar el proyecto necesitas crear el archivo `config.properties` dentro la carpeta `src/main/resources/`.

Los datos que debe contener el archivo de configuracion deben ser los siguientes:

```config
db.host=
db.username=
db.password=
db.port=
db.name=
```

## Base URL
`http://localhost:8080/api`

## Endpoints Disponibles

### Obtener Todos los Oradores

**Endpoint:** `/speakers`

**Método:** GET

**Descripción:** Obtiene todos los oradores registrados para el evento.

**Respuesta Exitosa:**
```json5
{
  "message": "ok"
  "statusCode": 200
  "data": [
    {
      "id": 1,
      "name": "Nombre del Orador",
      "lastName": "Apellido del Orador",
      "email": "correo@orador.com",
      "topic": "Tema de la Presentación",
      "creationDate": "2023-12-12"
    },
    // ... otros oradores
  ]
}
```

**Respuesta de Error:**
```json
{
  "message": "Mensaje de error",
  "statusCode": 404,
  "data": null
}
```

### Obtener Orador por ID

**Endpoint:** `/speaker?id={id}`

**Método:** GET

**Descripción:** Obtiene un orador específico por su ID.

**Parámetros de Consulta:**
- `id` (Número): ID del orador.

**Ejemplo de Solicitud:**

`GET /speaker?id=1`

**Respuesta Exitosa:**
```json
{
  "message": "Mensaje de exito",
  "statusCode": 200,
  "data": {
    "id": 1,
    "name": "Nombre del Orador",
    "lastName": "Apellido del Orador",
    "email": "correo@orador.com",
    "topic": "Tema de la Presentación",
    "creationDate": "2023-12-12"
  }
}
```

### Eliminar Orador por ID

**Endpoint:** `/speaker?id={id}`

**Método:** DELETE

**Descripción:** Elimina un orador específico por su ID.

**Parámetros de Consulta:**
- `id` (Número): ID del orador.

**Ejemplo de Solicitud:**
`DELETE /speaker?id=1`

**Respuesta Exitosa:**
```json
{
  "message": "Orador eliminado exitosamente",
  "statusCode": 200,
  "data": null
}
```

### Actualizar Orador por ID

**Endpoint:** `/speaker?id={id}`

**Método:** PUT

**Descripción:** Actualiza la información de un orador específico por su ID.

**Parámetros de Consulta:**
- `id` (Número): ID del orador.

**Cuerpo de la Solicitud:**
```json
{
  "name": "Nuevo Nombre",
  "lastName": "Nuevo Apellido",
  "topic": "Nuevo Tema"
}
```

**Ejemplo de Solicitud:**
`PUT /speker?id=1`

**Respuesta Exitossa.**
```json
{
  "message": "Solicitud exitosa",
  "statusCode": 200,
  "data": {
    "id": 1,
    "name": "Nuevo Nombre",
    "lastName": "nuevo Apellido",
    "email": "correo@orador.com",
    "topic": "Nuevo Tema",
    "creationDate": "2023-12-18"
  }
}
```
