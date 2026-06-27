package estructuras;

public class ArbolBinarioBusqueda {
    private NodoArbol raiz;

    public ArbolBinarioBusqueda() {
        this.raiz = null;
    }

    // Insertar una nueva categoría
    public void insertar(int codigo, String nombre) {
        raiz = insertarRec(raiz, codigo, nombre);
    }

    private NodoArbol insertarRec(NodoArbol nodo, int codigo, String nombre) {
        if (nodo == null) return new NodoArbol(codigo, nombre);
        if (codigo < nodo.codigo)
            nodo.izquierdo = insertarRec(nodo.izquierdo, codigo, nombre);
        else if (codigo > nodo.codigo)
            nodo.derecho = insertarRec(nodo.derecho, codigo, nombre);
        else
            System.out.println("  Aviso: El código " + codigo + " ya existe en el árbol.");
        return nodo;
    }

    // Buscar una categoría por código
    public NodoArbol buscar(int codigo) {
        return buscarRec(raiz, codigo);
    }

    private NodoArbol buscarRec(NodoArbol nodo, int codigo) {
        if (nodo == null) return null;
        if (codigo == nodo.codigo) return nodo;
        if (codigo < nodo.codigo) return buscarRec(nodo.izquierdo, codigo);
        return buscarRec(nodo.derecho, codigo);
    }

    // Eliminar una categoría por código
    public void eliminar(int codigo) {
        raiz = eliminarRec(raiz, codigo);
    }

    private NodoArbol eliminarRec(NodoArbol nodo, int codigo) {
        if (nodo == null) { System.out.println("  Error: Código no encontrado."); return null; }
        if (codigo < nodo.codigo) {
            nodo.izquierdo = eliminarRec(nodo.izquierdo, codigo);
        } else if (codigo > nodo.codigo) {
            nodo.derecho = eliminarRec(nodo.derecho, codigo);
        } else {
            // Nodo encontrado
            if (nodo.izquierdo == null) return nodo.derecho;
            if (nodo.derecho == null) return nodo.izquierdo;
            // Tiene dos hijos: reemplazar con el mínimo del subárbol derecho
            NodoArbol minDerecho = minimoNodo(nodo.derecho);
            nodo.codigo = minDerecho.codigo;
            nodo.nombreCategoria = minDerecho.nombreCategoria;
            nodo.derecho = eliminarRec(nodo.derecho, minDerecho.codigo);
        }
        return nodo;
    }

    private NodoArbol minimoNodo(NodoArbol nodo) {
        while (nodo.izquierdo != null) nodo = nodo.izquierdo;
        return nodo;
    }

    // Recorrido inorder (muestra categorías en orden por código)
    public void inorder() {
        if (raiz == null) { System.out.println("  (El árbol está vacío)"); return; }
        inorderRec(raiz);
    }

    private void inorderRec(NodoArbol nodo) {
        if (nodo == null) return;
        inorderRec(nodo.izquierdo);
        System.out.println("  " + nodo);
        inorderRec(nodo.derecho);
    }

    // Recorrido preorder
    public void preorder() {
        if (raiz == null) { System.out.println("  (El árbol está vacío)"); return; }
        preorderRec(raiz);
    }

    private void preorderRec(NodoArbol nodo) {
        if (nodo == null) return;
        System.out.println("  " + nodo);
        preorderRec(nodo.izquierdo);
        preorderRec(nodo.derecho);
    }

    public boolean estaVacio() { return raiz == null; }
}
