package servicios;

import entidades.Ticket;
import estructuras.ColaConPrioridad;
import estructuras.Nodo;
import estructuras.Pila;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class GestorTickets {

    private ColaConPrioridad colaTickets;
    private Pila<String>     historial;
    private Pila<Ticket>     enProceso;

    public GestorTickets() {
        this.colaTickets = new ColaConPrioridad();
        this.historial   = new Pila<>();
        this.enProceso   = new Pila<>();
    }

    // ── 1. Registrar ticket ─────────────────────────────────────────────────
    public void registrarTicket(Scanner sc) {
        System.out.println("\n--- Registrar Nuevo Ticket ---");
        System.out.print("Nombre del cliente: ");
        String cliente = sc.nextLine();
        System.out.print("Descripción del problema: ");
        String descripcion = sc.nextLine();
        int prioridad = 0;
        while (prioridad < 1 || prioridad > 3) {
            System.out.println("Prioridad: 1=Alta  2=Media  3=Baja");
            System.out.print("Seleccione prioridad: ");
            prioridad = sc.nextInt(); sc.nextLine();
        }
        Ticket nuevo = new Ticket(cliente, descripcion, prioridad);
        colaTickets.enqueue(nuevo);
        historial.push("REGISTRO - " + nuevo);
        System.out.println("  Ticket registrado: " + nuevo);
    }

    // ── 2. Atender ticket ───────────────────────────────────────────────────
    public void atenderTicket() {
        System.out.println("\n--- Atender Siguiente Ticket (dequeue) ---");
        Ticket t = colaTickets.dequeue();
        if (t != null) {
            t.setEstado("ATENDIDO");
            historial.push("ATENDIDO - " + t);
            System.out.println("  Ticket atendido: " + t);
        }
    }

    // ── 3. Peek ─────────────────────────────────────────────────────────────
    public void verFrente() {
        System.out.println("\n--- Siguiente Ticket a Atender (peek) ---");
        Ticket t = colaTickets.peek();
        if (t != null) System.out.println("  Próximo: " + t);
        else System.out.println("  No hay tickets en la cola.");
    }

    // ── 4. Ver cola ─────────────────────────────────────────────────────────
    public void verCola() {
        System.out.println("\n--- Tickets en Espera (por prioridad) ---");
        colaTickets.mostrar();
        System.out.println("  Total en cola: " + colaTickets.getTamanio());
    }

    // ── 5. Buscar por ID ────────────────────────────────────────────────────
    public void buscarTicket(Scanner sc) {
        System.out.println("\n--- Buscar Ticket por ID ---");
        System.out.print("Ingrese el ID: ");
        int id = sc.nextInt(); sc.nextLine();
        Ticket t = colaTickets.buscarPorId(id);
        if (t != null) System.out.println("  Encontrado: " + t);
        else System.out.println("  No se encontró ningún ticket con ID " + id + ".");
    }

    // ── 6. Reencolar ────────────────────────────────────────────────────────
    public void reencolarTicket(Scanner sc) {
        System.out.println("\n--- Reencolar Ticket ---");
        System.out.print("Ingrese el ID del ticket a reencolar: ");
        int id = sc.nextInt(); sc.nextLine();
        boolean ok = colaTickets.reencolar(id);
        if (ok) {
            historial.push("REENCOLADO - Ticket ID:" + id);
            System.out.println("  Ticket ID:" + id + " reencolado al final de su prioridad.");
        } else {
            System.out.println("  No se encontró el ticket con ID " + id + ".");
        }
    }

    // ── 7. Ver historial ────────────────────────────────────────────────────
    public void verHistorial() {
        System.out.println("\n--- Historial de Acciones (tope → base) ---");
        historial.mostrar();
        System.out.println("  Total en historial: " + historial.getTamanio());
    }

    // ── 8. Deshacer ─────────────────────────────────────────────────────────
    public void deshacerAccion() {
        System.out.println("\n--- Deshacer Última Acción (pop) ---");
        String ultima = historial.pop();
        if (ultima != null) System.out.println("  Acción eliminada: " + ultima);
    }

    // ── 9. Marcar en proceso ────────────────────────────────────────────────
    public void marcarEnProceso() {
        System.out.println("\n--- Marcar Ticket como En Proceso ---");
        Ticket t = colaTickets.dequeue();
        if (t != null) {
            t.setEstado("EN PROCESO");
            enProceso.push(t);
            historial.push("EN PROCESO - " + t);
            System.out.println("  Ticket en proceso: " + t);
            System.out.println("  Total en proceso: " + enProceso.getTamanio());
        }
    }

    // ── 10. Ver en proceso ──────────────────────────────────────────────────
    public void verEnProceso() {
        System.out.println("\n--- Tickets En Proceso (tope → base) ---");
        enProceso.mostrar();
        System.out.println("  Total: " + enProceso.getTamanio());
    }

    // ── 11. Estadísticas ────────────────────────────────────────────────────
    public void verEstadisticas() {
        System.out.println("\n--- Estadísticas del Sistema ---");
        int[] c = colaTickets.contarPorPrioridad();
        System.out.println("  Tickets en espera     : " + colaTickets.getTamanio());
        System.out.println("    - Prioridad ALTA    : " + c[0]);
        System.out.println("    - Prioridad MEDIA   : " + c[1]);
        System.out.println("    - Prioridad BAJA    : " + c[2]);
        System.out.println("  Tickets en proceso    : " + enProceso.getTamanio());
        System.out.println("  Acciones en historial : " + historial.getTamanio());
    }

    // ── 12. Exportar historial a .txt ───────────────────────────────────────
    public void exportarHistorial() {
        System.out.println("\n--- Exportar Historial a Archivo ---");
        if (historial.estaVacia()) {
            System.out.println("  El historial está vacío.");
            return;
        }
        String archivo = "historial_tickets.txt";
        try (FileWriter fw = new FileWriter(archivo)) {
            fw.write("=== HISTORIAL DE ACCIONES - TECHSUPPORT S.A.C. ===\n\n");
            // Recorrer con pila auxiliar sin destruir la original
            Pila<String> aux = new Pila<>();
            while (!historial.estaVacia()) aux.push(historial.pop());
            int i = 1;
            while (!aux.estaVacia()) {
                String s = aux.pop();
                historial.push(s);
                fw.write(i++ + ". " + s + "\n");
            }
            fw.write("\nTotal de acciones: " + historial.getTamanio());
            System.out.println("  Exportado correctamente: " + archivo);
        } catch (IOException e) {
            System.out.println("  Error al exportar: " + e.getMessage());
        }
    }

    public String getTicketsEnFormatoJSON() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        Nodo<Ticket> actual = colaTickets.getFrente();
        boolean primero = true;
        while (actual != null) {
            TicketDTO dto = new TicketDTO(actual.dato);
            if (!primero) {
                sb.append(",");
            }
            sb.append("{");
            sb.append("\"id\":").append(dto.getId()).append(",");
            sb.append("\"cliente\":\"").append(escapeJSON(dto.getCliente())).append("\",");
            sb.append("\"descripcion\":\"").append(escapeJSON(dto.getDescripcion())).append("\",");
            sb.append("\"prioridad\":").append(dto.getPrioridad()).append(",");
            sb.append("\"prioridadTexto\":\"").append(escapeJSON(dto.getPrioridadTexto())).append("\",");
            sb.append("\"estado\":\"").append(escapeJSON(dto.getEstado())).append("\"");
            sb.append("}");
            primero = false;
            actual = actual.siguiente;
        }
        sb.append("]");
        return sb.toString();
    }

    private String escapeJSON(String str) {
        if (str == null) return "";
        return str.replace("\\", "\\\\")
                  .replace("\"", "\\\"")
                  .replace("\n", "\\n")
                  .replace("\r", "\\r")
                  .replace("\t", "\\t");
    }
}
