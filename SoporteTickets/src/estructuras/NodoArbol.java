package estructuras;

public class NodoArbol {
    public int codigo;
    public String nombreCategoria;
    public NodoArbol izquierdo;
    public NodoArbol derecho;

    public NodoArbol(int codigo, String nombreCategoria) {
        this.codigo = codigo;
        this.nombreCategoria = nombreCategoria;
        this.izquierdo = null;
        this.derecho = null;
    }

    @Override
    public String toString() {
        return "[" + codigo + "] " + nombreCategoria;
    }
}
