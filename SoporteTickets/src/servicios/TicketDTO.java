package servicios;

import entidades.Ticket;

public class TicketDTO {
    private int id;
    private String cliente;
    private String descripcion;
    private int prioridad;
    private String prioridadTexto;
    private String estado;

    public TicketDTO(Ticket ticket) {
        if (ticket != null) {
            this.id = ticket.getId();
            this.cliente = ticket.getCliente();
            this.descripcion = ticket.getDescripcion();
            this.prioridad = ticket.getPrioridad();
            this.prioridadTexto = ticket.getPrioridadTexto();
            this.estado = ticket.getEstado();
        }
    }

    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getCliente() { return cliente; }
    public void setCliente(String cliente) { this.cliente = cliente; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public int getPrioridad() { return prioridad; }
    public void setPrioridad(int prioridad) { this.prioridad = prioridad; }

    public String getPrioridadTexto() { return prioridadTexto; }
    public void setPrioridadTexto(String prioridadTexto) { this.prioridadTexto = prioridadTexto; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
}
