# Lemmings

Implementación en Java de una versión de consola, por turnos, inspirada en el clásico **Lemmings (1991)**. El proyecto está planteado como una práctica de Tecnología de la Programación y reúne en una sola base de código el modelo del juego, el sistema de comandos, la vista por consola, la carga y guardado de configuraciones y la batería de pruebas automatizadas.

## Índice

- [Descripción general](#descripción-general)
- [Cómo ejecutar el proyecto](#cómo-ejecutar-el-proyecto)
- [Cómo se juega](#cómo-se-juega)
- [Roles de los lemmings](#roles-de-los-lemmings)
- [Elementos del tablero](#elementos-del-tablero)
- [Arquitectura del código](#arquitectura-del-código)
- [Guardado y carga](#guardado-y-carga)
- [Tests](#tests)
- [Estructura del repositorio](#estructura-del-repositorio)
- [Autores](#autores)

## Descripción general

El juego se desarrolla sobre un tablero fijo de `10 x 10` y avanza mediante ciclos:

- Los lemmings se mueven de forma automática en cada turno.
- El jugador no controla el desplazamiento directamente, sino que modifica el comportamiento de los lemmings mediante comandos de texto.
- La partida se gana cuando suficientes lemmings alcanzan la salida y ya no queda ninguno pendiente de resolver en el tablero.
- La partida se pierde si la configuración del nivel hace imposible alcanzar el objetivo.

El proyecto incluye tres configuraciones iniciales integradas en el código:

- `0`: configuración base.
- `1`: nivel por defecto al arrancar sin argumentos.
- `2`: nivel alternativo con contenido adicional.

## Cómo ejecutar el proyecto

### Requisitos

- Java JDK 8 o superior.
- Un terminal o un IDE compatible con Java.

### Desde terminal

Clona el repositorio y entra en la carpeta del proyecto:

```bash
git clone https://github.com/marioUCM/lemmings
cd lemmings
```

Compila el proyecto desde la raíz del repositorio:

```bash
mkdir -p bin
javac -encoding UTF-8 -cp lib/junit-platform-console-standalone-1.10.2.jar -d bin $(find src -name "*.java")
```

Ejecuta el juego con:

```bash
java -cp bin tp1.Main
```

Argumentos disponibles:

```bash
java -cp bin tp1.Main 0
java -cp bin tp1.Main 2 COLORS
```

Si no indicas nivel, el juego arranca en el nivel `1`. El segundo argumento `COLORS` activa la vista con colores ANSI.

### Desde IDE

Importa el proyecto como proyecto Java normal, marca `src/` como carpeta de código fuente y ejecuta la clase `tp1.Main`.

## Cómo se juega

La entrada del juego se introduce por consola mediante comandos de texto. El controlador lee la orden, la convierte en un comando concreto y delega en el modelo la ejecución.

| Comando | Atajo | Uso | Descripción |
| --- | --- | --- | --- |
| `none` | `n` | `n` o línea vacía | Avanza un ciclo sin realizar ninguna acción manual. |
| `help` | `h` | `h` | Muestra la ayuda con la lista de comandos. |
| `reset` | `r` | `r` o `r <nivel>` | Reinicia la partida actual o carga uno de los niveles iniciales. |
| `load` | `l` | `l <fichero>` | Carga una configuración desde `conf/<fichero>`. |
| `save` | `s` | `s <fichero>` | Guarda la partida actual en `conf/<fichero>`. |
| `setrole` | `sr` | `sr <rol> <fila> <columna>` | Aplica un rol al primer lemming válido de esa posición. |
| `exit` | `e` | `e` | Finaliza la ejecución del juego. |

Ejemplos:

```text
Command > h
Command > sr Parachuter A 8
Command > load partida.txt
Command > save copia.txt
Command > r 2
Command > e
```

Las filas del tablero se introducen con letras y las columnas con números. Por ejemplo, `A 8` corresponde a la primera fila y la octava columna.

## Roles de los lemmings

Los roles están definidos en `src/tp1/logic/lemmingRoles/` y siguen una estructura polimórfica. El juego actual incluye estos roles:

- `Walker`: rol base. El lemming camina de forma normal.
- `Parachuter`: evita muertes por caída mientras el lemming está en el aire.
- `DownCaver`: permite cavar hacia abajo cuando el terreno lo permite.

El comando `setrole` usa la factoría de roles para convertir el texto introducido en el rol correspondiente. Cada rol admite nombre largo y abreviatura.

## Elementos del tablero

Los objetos del tablero están en `src/tp1/logic/gameobjects/`. Los más importantes son:

- `Lemming`: criatura protagonista del juego.
- `Wall`: pared normal.
- `MetalWall`: pared resistente.
- `ExitDoor`: puerta de salida.

Todos los objetos comparten una interfaz común para que el modelo pueda tratarlos de forma uniforme y sin depender de tipos concretos.

## Arquitectura del código

El proyecto está organizado siguiendo una separación clara entre presentación, control y lógica.

### `tp1.Main`

Punto de entrada de la aplicación. Configura la localización del proceso, interpreta los argumentos de arranque y conecta el modelo con la vista y el controlador.

### `tp1.control`

Contiene el bucle principal del juego.

- `Controller` lee comandos desde la vista.
- `CommandGenerator` resuelve qué comando debe ejecutarse.
- `Command`, `NoParamsCommand` y sus subclases encapsulan cada acción del usuario.

### `tp1.logic`

Contiene el modelo del juego.

- `Game` coordina el estado general de la partida.
- `GameModel`, `GameStatus` y `GameWorld` exponen vistas parciales del modelo según el contexto.
- `GameObjectContainer` almacena los objetos del tablero.
- `Position` y `Direction` modelan coordenadas y desplazamientos.
- `FileGameConfiguration` y `GameConfiguration` gestionan la carga de configuraciones desde fichero.

### `tp1.logic.gameobjects`

Implementa los objetos del tablero y la factoría que permite reconstruirlos a partir de texto.

### `tp1.logic.lemmingRoles`

Implementa los roles de los lemmings y la factoría que convierte cadenas en roles concretos.

### `tp1.view`

Implementa la vista de consola.

- `ConsoleView` muestra el tablero sin colores.
- `ConsoleColorsView` añade salida coloreada.
- `Messages` centraliza los textos visibles al usuario.

### `tp1.exceptions`

Agrupa las excepciones específicas del dominio para separar errores de parseo, ejecución y carga de ficheros.

## Guardado y carga

El juego puede cargar y guardar configuraciones dentro del directorio `conf/`.

### Carga

El comando `load` lee un fichero de texto con este formato:

1. Primera línea con el estado global del juego.
2. Una línea por objeto del tablero.
3. Cada objeto se serializa con su posición y su tipo.

Ejemplo:

```text
0 1 0 0 2
(3,2) Lemming RIGHT 1 Walker
(4,2) Wall
(4,3) MetalWall
(5,4) ExitDoor
```

La lectura y validación del fichero se realiza en `FileGameConfiguration`, y cualquier error se propaga al controlador mediante excepciones específicas.

### Guardado

El comando `save` escribe el estado actual de la partida en `conf/<fichero>`. El archivo resultante conserva el formato necesario para volver a cargar la partida después.

## Tests

La clase `tp1.Tests` contiene la batería de pruebas automatizadas del proyecto. La estrategia de test consiste en comparar la salida real del programa con ficheros esperados dentro de `tests/`.

Ejecutar tests desde consola:

```bash
java -jar lib/junit-platform-console-standalone-1.10.2.jar --class-path bin --scan-class-path
```

Si usas un IDE, ejecuta `tp1.Tests` como una prueba JUnit normal.

## Estructura del repositorio

- `src/tp1/`: código fuente principal.
- `src/tp1/control/`: controlador y comandos.
- `src/tp1/logic/`: modelo y configuración.
- `src/tp1/logic/gameobjects/`: objetos del tablero.
- `src/tp1/logic/lemmingRoles/`: roles de los lemmings.
- `src/tp1/view/`: vista de consola y textos.
- `src/tp1/exceptions/`: excepciones del dominio.
- `conf/`: configuraciones iniciales y ficheros de guardado/carga.
- `tests/`: entradas, salidas esperadas y resultados generados por la suite de pruebas.
- `lib/`: dependencias externas, incluida la librería de JUnit.

## Autores

- Universidad Complutense de Madrid (UCM)
- Mario Granados Guerrero

## Notas

- La aplicación fija la localización a `es_ES` para evitar diferencias de formato en los tests.
- El proyecto no usa Maven ni Gradle; la compilación se hace directamente con `javac` o desde el IDE.
- Los ficheros de carga y guardado se trabajan siempre dentro de `conf/`.
