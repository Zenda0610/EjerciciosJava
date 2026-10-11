PRACTICA 1 - CINCO PROYECTOS JAVA Y MENU GENERAL

Cada carpeta es un proyecto independiente y tiene su propio paquete dentro de src.
Se pueden abrir por separado en NetBeans o compilar desde una terminal.

PROYECTO 1: CajeroAutomatico
Clase principal: com.taller.cajero.CajeroAutomatico

PROYECTO 2: RegistroAcademico
Clase principal: com.taller.academico.RegistroAcademico

PROYECTO 3: TiendaOnline
Clase principal: com.taller.tienda.TiendaOnline

PROYECTO 4: AgendaContactos
Clase principal: com.taller.agenda.AgendaContactos

PROYECTO 5: SistemaNotas
Clase principal: com.taller.notas.SistemaNotas

MENU GENERAL: MenuTaller
Clase principal: com.taller.menu.MenuTaller
El menú permite elegir un ejercicio del 1 al 5 y salir con 0.

EXTENSIONES DEL SEGUNDO TALLER INCLUIDAS
1. Cajero: múltiples cuentas, transferencias, últimos cinco movimientos, Banco,
   cuenta de ahorro con interés y cuenta corriente con sobregiro.
2. Académico: asignaturas múltiples, faltas, estadísticas del curso, búsqueda,
   modificación de notas y reporte de texto.
3. Tienda: categorías, descuento del 10%, productos alimenticios/electrónicos,
   cliente e inventario central.
4. Agenda: ArrayList sin límite fijo, validaciones, búsqueda parcial, grupos y
   guardado/carga automática en agenda.txt.
5. Notas: tres notas ponderadas, curva porcentual calculada desde la mejor nota,
   validación de entrada, media y desviación estándar finales.

PARA COMPILAR Y EJECUTAR
Se necesita el JDK de Java (incluye javac). Abre una terminal en la carpeta del
proyecto y ejecuta, por ejemplo:

  javac -d out src/com/taller/cajero/*.java
  java -cp out com.taller.cajero.CajeroAutomatico

En cada proyecto cambia el paquete y la clase principal según la lista anterior.
Los programas usan únicamente clases básicas de Java y Scanner.

PARA COMPILAR EL MENU GENERAL
El menú llama a los cinco ejercicios, así que compila sus fuentes juntas. Abre una
terminal en la carpeta ProyectosJava y ejecuta en PowerShell:

  $fuentes = Get-ChildItem Proyecto*/src, MenuTaller/src -Recurse -Filter *.java
  javac -encoding UTF-8 -d salida $fuentes.FullName
  java -cp salida com.taller.menu.MenuTaller

En NetBeans, para usar el menú crea un proyecto y agrega las carpetas src de los
cinco ejercicios y la carpeta MenuTaller/src, respetando los paquetes. Si el
proyecto ya estaba abierto, usa Reload Project para actualizar las clases nuevas.
