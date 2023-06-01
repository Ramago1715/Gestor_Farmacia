# Gestor_Farmacia
## Proyecto FullStack para gestionar medicamentos, pacientes y chips con cuentas para los médicos.
### En el frontend vamos a usar HTML, CSS y JavaScript, en el backend usaremos JAVA y en la base de datos MariaDB.

![imagen](https://github.com/Ramago1715/Gestor_Farmacia/assets/74196893/74bbf43c-a35c-40bd-957c-5d3dfdbf0471)
#### En esta imagen se puede ver la pantalla de inicio donde los doctores previamente registrados desde la base de datos
#### por el administrador ponen sus credenciales que serán comprobadas en la base de datos a través del backend. A continuación
#### se pasará a la página de gestión y se generará un session que se guardará en el sessionstorage junto al email del doctor.
#### Además de actualizarlo en la base de datos ademas del last_log para que cuando pase un día te haga volver a iniciar sesión.

![imagen](https://github.com/Ramago1715/Gestor_Farmacia/assets/74196893/7e529054-c91e-4bc4-a696-6a4f017153c2)
#### Al acceder a gestión saldrán todos los xips asignados al doctor que ha iniciado sesión, esto recogido de la base de datos.


![imagen](https://github.com/Ramago1715/Gestor_Farmacia/assets/74196893/0c79ebf4-b20e-41f1-8ddf-05b511a1010f)
#### Esto es el menú de alta en el que el doctor de la sesión puede crear un nuevo xip asociandole su paciente, medicamento, id y su fecha de caducidad
#### toda esta información al enviarla se quedará registrada en la base de datos para después poder consultarla en la página de gestión.







