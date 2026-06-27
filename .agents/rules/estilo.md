---
trigger: always_on
---

# Reglas de Desarrollo y Arquitectura

1. **Preservación de Estructura:** Nunca modifiques las clases de datos en 'entidades' ni las implementaciones de estructuras de datos (Pila, Cola, Arbol) sin permiso explícito, ya que son el núcleo algorítmico del proyecto.
2. **Formato de Código:** Mantén líneas cortas (< 100 caracteres). Si debes crear un método largo, prioriza la legibilidad. Usa el estilo de indentación estándar de Java.
3. **Migración a Web (Dinámica):**
   - Cuando te pida crear una interfaz web, no intentes reemplazar la lógica de Java.
   - Propón crear un controlador (usando Spring Boot si es posible) para exponer la lógica de 'servicios' como endpoints REST (JSON).
   - Para el frontend, usa HTML/JS moderno. Si el código requiere cambios, preséntalos como una mejora al 'GestorTickets.java' existente.
4. **Seguridad y Estabilidad:** Cada vez que generes código nuevo para el frontend, asegúrate de que el código Java backend sea capaz de responder a las peticiones (hacer las conexiones necesarias entre capas).
5. **Calidad:** Antes de modificar un archivo, analiza el impacto en las clases del paquete 'principal'. Prioriza la modularidad.