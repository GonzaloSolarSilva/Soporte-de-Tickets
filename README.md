# 🎫 Sistema de Soporte de Tickets — TechSupport S.A.C.

Un sistema integral, ligero y de alto rendimiento para la gestión, priorización y atención de incidencias técnicas. Desarrollado en **Java** y complementado con un **Dashboard Web interactivo**, este proyecto implementa estructuras de datos personalizadas para simular un entorno de atención al cliente real.

---

## 🚀 Características Principales

*   **Doble Interfaz:**
    *   **Consola Interactiva (Java):** Panel administrativo de control total para registrar, priorizar, atender y deshacer acciones en tiempo real.
    *   **Dashboard Web (HTML/JS):** Panel visual moderno que consume el estado de los tickets en tiempo real desde un servidor HTTP local en Java.
*   **Servidor HTTP Nativo:** Integración de un microservicio web ligero con `com.sun.net.httpserver` en el puerto `8080` que expone los datos en formato JSON.
*   **Historial con Función Deshacer (Undo):** Permite revertir las últimas acciones de registro o atención.
*   **Exportación de Reportes:** Generación automática de estadísticas y persistencia del historial de auditoría en archivos de texto plano `.txt`.

---

## 🛠️ Estructuras de Datos Utilizadas

Para garantizar un rendimiento óptimo de almacenamiento y recuperación, el núcleo del proyecto utiliza implementaciones propias de estructuras de datos lineales y no lineales en lugar de las librerías por defecto de Java:

### 1. 📊 Cola con Prioridad (`ColaConPrioridad`)
*   **Propósito:** Almacena los tickets pendientes de atención.
*   **Funcionamiento:** Los tickets se organizan según su nivel de urgencia (`1 = Alta`, `2 = Media`, `3 = Baja`). Al atender (`dequeue`), el sistema despacha primero los tickets con prioridad 1, luego los de prioridad 2 y finalmente los de prioridad 3, manteniendo el orden FIFO (First In, First Out) entre elementos del mismo nivel.

### 2. 🥞 Pilas (`Pila<T>`)
Se implementan dos flujos basados en el principio LIFO (Last In, First Out):
*   **Pila de Historial:** Registra cronológicamente cada acción (registro, atención, etc.). Sirve como base para la funcionalidad de **Deshacer (Undo)** la última operación realizada.
*   **Pila en Proceso:** Administra el conjunto de tickets asignados que actualmente están siendo revisados o trabajados por el equipo técnico.

### 3. 🌲 Árbol Binario de Búsqueda (`ArbolBinarioBusqueda`)
*   **Propósito:** Gestión y clasificación jerárquica de categorías de incidencias (Hardware, Software, Redes, Accesos, etc.).
*   **Funcionamiento:** Permite búsquedas, inserciones y recorridos ordenados (in-order) en tiempo logarítmico $O(\log n)$, estructurando el catálogo de soporte eficientemente.

---

## 💻 Tecnologías y Herramientas

*   **Backend:** Java SE 8+ (Capa de lógica, estructuras de datos puras y microservidor de red).
*   **Frontend:** HTML5, CSS3 (Diseño moderno responsivo) y JavaScript ES6 (Consumo de APIs asíncronas vía `fetch`).
*   **Gestión de Construcción:** Ant (`build.xml`) para automatización de compilación.

---

## ⚙️ Instrucciones de Ejecución

### 1. Ejecutar el Servidor Backend (Java)
Abre la consola en el directorio del proyecto y compila/ejecuta la clase principal [SoporteTickets.java](file:///c:/Users/USUARIO/Downloads/Avance/SoporteTickets/SoporteTickets/src/principal/SoporteTickets.java):

```bash
# Compilar e iniciar usando Ant
ant run

# O compilar directamente de forma manual desde el directorio SoporteTickets/src:
javac principal/SoporteTickets.java
java principal.SoporteTickets
```
Al iniciar, verás el mensaje:
`[SERVIDOR WEB] Iniciado en http://localhost:8080/api/tickets`

### 2. Abrir el Dashboard Web Frontend
Una vez que el servidor Java esté en ejecución:
1. Dirígete a la carpeta `SoporteTickets/`
2. Abre el archivo [index.html](file:///c:/Users/USUARIO/Downloads/Avance/SoporteTickets/SoporteTickets/index.html) en tu navegador preferido.
3. El panel se actualizará automáticamente y mostrará el estado gráfico de los tickets ingresados en la consola.

---

## 📖 Notas de Desarrollo

*   **Modularidad:** Toda la lógica del negocio está encapsulada en la capa de `servicios` ([GestorTickets](file:///c:/Users/USUARIO/Downloads/Avance/SoporteTickets/SoporteTickets/src/servicios/GestorTickets.java)), la interfaz de consola en `principal` y las estructuras puras en el paquete `estructuras`.
*   **Sin dependencias externas:** El servidor REST está construido enteramente sobre la API nativa de Java, lo que garantiza una ejecución ultrarápida y portable sin necesidad de frameworks pesados.