![Logotipo de Spring boot](spring-boot.jpeg)

# Introducción
**URL de la aplicación: http://35.180.128.87/** 

Este reporsitorio es una aplicación propia que he contruido para prácticar los conocemientos que había adquirido sobre Java, Spring boot, MySQL y Bootstrap.

La temática de la aplicación es videojuegos (un tópico), pero podría haber sido cualquier otra. Simplemente elegí esta por la idea de hacer una aplicación que pudiera ser útil para algunos amigos.

Todas las funcionalidades como contador de visitas, barra de búsqueda, etc son realizadas únicamente con el propósito de practicar y enfrentarme a todos los retos posibles.

## Frontend
Del mundo del desarollo, la parte fronted probablemente sea la que menos atractiva me resulta. Decidí usar **Bootstrap 5**, creo que es una gran herramienta con una curva de aprendizaje muy sencilla. Con algunos vídeos y la mágnifica documentación que tiene puedes empezar a utilizarla y crear algo visualmente bonito en pocos pasos.

La idea es crear una pantalla de inicio donde aparecen todos los videojuegos de la base de datos en formato card. Cada uno de ellos, tendrá un botón para ir otra vista donde se puede ver más información del juego y de su desarrolladora.

### Estética
En este punto tenía claro dos cosas:
1. La aplicación se vería oscura.
2. Sencillez, quería que la aplicación tuviera un diseño similar de principio a fin. Sin cambios de colores entre distintas páginas.


## Backend
Como ya dije en la introducción, he usado Java y su framework Spring boot.
Para la base de datos he usado MySQL conectado a la aplicación Workbench para facilitarme el trabajo.

En cuanto a la versión de Java he usado la 17, podría haber usado cualquier otra pero es en esta versión donde tengo la certificación OCP de Oracle y es la que habitualmente utilizamos en clase.

### Barra de búsqueda
Decidí incluir una barra de búsqueda en el **navbar**. La función sería simple, debía buscar los juegos que contuviese en su nombre el texto escrito y recargaría la página mostrando una lista de los juegos que cumpliesen el criterio.

Posteriormente incluí que también se mostrarán los juegos filtrados por desarrolladora, de esa manera si escribia parte del nombre de una desarolladora me aperecerían los juegos de la misma.

Con este enfoque tuve que cambiar la estructura original que era **List** por **Set**, el motivo era simple, no quería que se repitiera ningún juego en caso de que el texto escrito coincidiera tanto en su nombre como en su desarrolladora.

### Información sobre un videojuego
Cree una vista en la que inyectaba la información de un videojuego. En esta vista, se muestra tanto un vídeo del videojuego como una descripción más extensa. También se incluye una imagen de su desarrolladora así como información de la misma.

### Añadir Videojuegos y Desarrolladoras
Para incluir algún juego o desarrollador tenía que hacerlo de manera obligatoria por medio de la base de datos. Así que creé dos vistas, uno para añadir videojuegos y otro para añadir desarrolladores. Ambos tendrían un formulario y ejecturarían sentencias sobre la base de datos para añadir la información. De esa manera, podría incluir información nueva desde la propia aplicación.

Evidentemente esto era un problema, la página estaba en local pero mi idea era desplegarla en algún momento. Cualquiera que accediera a la página podría hacer cambios en la base de datos. Por ello, el siguiente paso era configurar algún tipo de seguridad.

En la parte final de la desarrolladora se incluye un botón con un hipervínculo que nos lleva a la web del desarrollador, el **target** es **blank** para evitar que el usuario salga de nuestra aplicación. 

### Spring Security
Lo primero que pensé fue en crear una estructura de usuarios en la base de datos, pero realmente era una comlpejidad que mi aplicación no necesitaba.

Cuando incluyes la dependencia de Spring Security, por defecto se te pone un login para cualquier página de tu aplicación. Spring genera un usuario y una contraseña que te muestra en la consola.

La configuración la podemos dividir en varias partes:
- Crear un paquete para la configuración.
- Crear y dar forma a una clase para la configuraci´no de Spring Security.
- Especificar las rutas que deben estar abiertas y cuales deben estar protegidas.
- Cambiar la configuracion de validación del login, en lugar de hacer que cada vez el servicio arranque me proporcione una contraseña generada en ese momento, decidí crear en memoria tres usuarios con sus respectivas contraseñas.
- Crear un template personaliazdo para sustituir al que proporciona por defectio Spring Security que, aún que es perfectamente funcional, se alejaba mucho de la estética de la aplicación.

### Juego aleatorio
Incluí un botón en el **navbar** que accediera directamente y de forma aleatoria a la información de alguno de los videojuegos de la base de datos.

### Contador de visitas
o primero que hice fue buscar información en Internet, pero la mayoría de las páginas ofrecían la descarga de contadores predefinidos para insertarlos directamente. Evidentemente, este no era mi objetivo.

Pensé en cómo podría crearlo con mis conocimientos. Mi primera idea fue crear una variable que se incrementara cada vez que se llamara al `endpoint` de la página de inicio. Solo tendría que asegurarme de manejar la concurrencia simultánea para evitar errores.

Sin embargo, había un problema: cada vez que el servidor se apagara, el contador se reiniciaría. Por lo tanto, rápidamente descarté esa opción.

Entonces, se me ocurrió que podría solucionar el problema guardando la información en un archivo al apagar el servidor, y al reiniciarlo, leería ese archivo para cargar el valor en la variable. Spring proporciona herramientas para esto, utilizando las anotaciones `@PostConstruct` y `@PreDestroy`. Sin embargo, ¿Qué ocurriría si el servidor se cerrara de manera abrupta? En ese caso, la información se perdería.

Evidentemente, esta solución tampoco era la mejor, así que decidí hacerlo de la manera más fiable que se me ocurrió: almacenando el contador en la base de datos.

Creé una tabla de estadísticas para que, cada vez que alguien accediera al `endpoint`, la información se actualizara automáticamente en la base de datos. Spring también nos facilita este proceso mediante la anotación `@Transactional`, la cual protege las actualizaciones en la base de datos con transacciones, evitando problemas de concurrencia.

Después de crear todas las clases, interfaces, tablas, etc., todo funcionaba bien. Pero, como suele ocurrir en informática, nada sale perfecto a la primera. Me encontré con un problema en el que no había pensado: si un usuario navegaba por la aplicación y volvía repetidamente al `endpoint` raíz, el contador de visitas aumentaba, lo que generaba una mala experiencia de usuario y hacía que el contador no reflejara datos reales.

Para solucionar esto, consideré dos posibles soluciones: `sesiones de navegador` o `cookies`. Ambas me ofrecían maneras distintas de controlar las visitas, pero una de ellas se ajustaba mejor a mis necesidades.

- **Cookies**: Con las cookies, podría controlar el tiempo exacto en el que un usuario sería contado como una nueva visita. Incluso si el usuario cerraba el navegador, no se contaría como una nueva visita al volver, siempre que no hubiera transcurrido el tiempo suficiente. No obstante, el usuario podría borrar las cookies para incrementar el contador de visitas (algo que no me preocupaba mucho), pero había un gran problema: el modo incógnito. Si alguien navegaba en modo incógnito, cada vez que accediera al `endpoint` raíz, se sumaría una nueva visita, lo cual era precisamente lo que quería evitar, ya que afectaría negativamente a la experiencia del usuario.
- **Sesiones de navegador**: **Spoiler**, esta fue la opción que implementé. Con las sesiones de navegador, todo funcionaba correctamente, incluso en modo incógnito. Pude controlar si el usuario ya había visitado la página en esa sesión; si no lo había hecho, incrementaba el contador y mostraba el número de visitas. Si ya había visitado la página, simplemente le mostraba el contador actual. Al cerrar por completo el navegador y volver a abrirlo, contaría como una nueva visita, lo cual no me parecía un problema. Si un usuario entraba en la aplicación y, tras dos horas, volvía a abrir el navegador para acceder de nuevo, era razonable considerarlo como una nueva visita. En cambio, si un usuario mantenía el navegador abierto indefinidamente, solo se contaría la primera visita. Sin embargo, considero que este es un caso poco común.

## Despliegue
Para desplegar la aplicación, he decido hacerlo en `Amazon AWS`. 

El motivo es que tienen una capa gratuita durante un año.
Inicialmente intenté coger una instancia básica de `EC2`, instalar Java, MySQL y correr tanto la base de datos como la aplicación web. 

El problema es que estas instancias tienen una capacidad técnica muy limitada. Era incapaz de correrlo todo en la misma instancia.
Borré la instancia y creé una nueva en la que sólo incluí la instalación de Java y el archivo jar.

Cree una instancia `RDS` para la base de datos y uní ambas instancias. De esta manera conseguí que la aplicación funcionara. También instalé y configuré un proxy inverso con `nginx`.

## ChatGPT
Evidentemente he usado la aplicación para ayudarmen en el proceso de creación. A continuación detallaré los puntos en los que la he utlizado.

- Textos, todos los textos de los videojuegos y desarolladoras han sido generados mediante ChatGPT. No veía lógico ni necesario perder tiempo en crear yo esa información.

- Bootstrap, cuando quería hacer algo con Bootstrap primero buscaba en la documentación para ver que componente tenía que utilizar, después siempre pedía a ChatGPT que me mostrará ejemplos de ese componente.

- Información, este sin duda es el punto donde más me ha ayudado. 

La IA, rara vez te da una respuesta que se ajuste a la perfección a tu problema y aún menos veces, el código que genera sirve en tu aplicación a la primera. Haciendo este proyecto, me encontré con algunas tareas que era la primera vez a la que me enfrentaba.

 Por ejemplo, como hacer la autenticación de usuario.Creo que  ChatGPT  es una mágnifica herramienta para cuando no sabes por donde empezar.

Me mostró varias tecnologías y formas de hacerlo y una vez entendí de forma superficial todas y me decanté por una, y busqué información en Internet (foros y documentación) sobre esa tecnología en concreto. 

Para mí, como primera fuente de información para realizar un acercamiento a un problema del cual no tienes ni idea es fantástica.