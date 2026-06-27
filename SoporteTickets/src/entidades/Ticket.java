package entidades;

public class Ticket {
    private static int contadorId = 1;

    private int id;
    private String cliente;
    private String descripcion;
    private int prioridad; // 1=Alta, 2=Media, 3=Baja
    private String estado; // "EN ESPERA", "EN PROCESO", "ATENDIDO"

    public Ticket(String cliente, String descripcion, int prioridad) {
        this.id = contadorId++;
        this.cliente = cliente;
        this.descripcion = descripcion;
        this.prioridad = prioridad;
        this.estado = "EN ESPERA";
    }

    public int getId() { return id; }
    public String getCliente() { return cliente; }
    public String getDescripcion() { return descripcion; }
    public int getPrioridad() { return prioridad; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public String getPrioridadTexto() {
        switch (prioridad) {
            case 1: return "ALTA";
            case 2: return "MEDIA";
            case 3: return "BAJA";
            default: return "DESCONOCIDA";
        }
    }

    @Override
    public String toString() {
        return "[ID:" + id + "] " + cliente + " - " + descripcion
               + " (Prioridad: " + getPrioridadTexto() + " | Estado: " + estado + ")";
    }
}
