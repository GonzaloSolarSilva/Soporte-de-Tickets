package estructuras;

public class Pila<T> {
    private Nodo<T> tope;
    private int tamanio;

    public Pila() {
        this.tope = null;
        this.tamanio = 0;
    }

    // Inserta un elemento en el tope de la pila
    public void push(T dato) {
        Nodo<T> nuevo = new Nodo<>(dato);
        nuevo.siguiente = tope;
        tope = nuevo;
        tamanio++;
    }

    // Elimina y retorna el elemento del tope
    public T pop() {
        if (estaVacia()) {
            System.out.println("  Error: La pila está vacía.");
            return null;
        }
        T dato = tope.dato;
        tope = tope.siguiente;
        tamanio--;
        return dato;
    }

    // Retorna el tope sin eliminarlo
    public T peek() {
        if (estaVacia()) return null;
        return tope.dato;
    }

    public boolean estaVacia() { return tope == null; }
    public int getTamanio() { return tamanio; }

    // Muestra todos los elementos de la pila (tope → base)
    public void mostrar() {
        if (estaVacia()) { System.out.println("  (La pila está vacía)"); return; }
        Nodo<T> actual = tope;
        int pos = tamanio;
        while (actual != null) {
            System.out.println("  " + pos + ". " + actual.dato);
            actual = actual.siguiente;
            pos--;
        }
    }
}
