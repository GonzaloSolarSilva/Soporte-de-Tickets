package servicios;

import estructuras.ArbolBinarioBusqueda;
import estructuras.NodoArbol;
import java.util.Scanner;

public class GestorCategorias {

    private ArbolBinarioBusqueda arbol;

    public GestorCategorias() {
        this.arbol = new ArbolBinarioBusqueda();
        // Categorías precargadas
        arbol.insertar(101, "Hardware - Equipos");
        arbol.insertar(102, "Hardware - Periféricos");
        arbol.insertar(201, "Software - Sistema Operativo");
        arbol.insertar(202, "Software - Aplicaciones");
        arbol.insertar(301, "Red - Conectividad");
        arbol.insertar(302, "Red - VPN y Accesos Remotos");
        arbol.insertar(401, "Seguridad - Antivirus");
        arbol.insertar(501, "Otros");
    }

    public void menuCategorias(Scanner sc) {
        int op;
        do {
            System.out.println("\n  --- Módulo de Categorías (ABB) ---");
            System.out.println("  1. Listar categorías (inorder)");
            System.out.println("  2. Buscar categoría por código");
            System.out.println("  3. Insertar nueva categoría");
            System.out.println("  4. Eliminar categoría");
            System.out.println("  5. Ver árbol preorder");
            System.out.println("  0. Volver al menú principal");
            System.out.print("  Opción: ");
            op = sc.nextInt(); sc.nextLine();
            switch (op) {
                case 1:
                    System.out.println("\n  Categorías (ordenadas por código):");
                    arbol.inorder();
                    break;
                case 2:
                    System.out.print("  Ingrese el código a buscar: ");
                    int cod = sc.nextInt(); sc.nextLine();
                    NodoArbol encontrado = arbol.buscar(cod);
                    if (encontrado != null) System.out.println("  Encontrado: " + encontrado);
                    else System.out.println("  No se encontró el código " + cod + ".");
                    break;
                case 3:
                    System.out.print("  Código de la nueva categoría: ");
                    int nc = sc.nextInt(); sc.nextLine();
                    System.out.print("  Nombre de la categoría: ");
                    String nombre = sc.nextLine();
                    arbol.insertar(nc, nombre);
                    System.out.println("  Categoría insertada correctamente.");
                    break;
                case 4:
                    System.out.print("  Código de la categoría a eliminar: ");
                    int ec = sc.nextInt(); sc.nextLine();
                    arbol.eliminar(ec);
                    System.out.println("  Operación de eliminación completada.");
                    break;
                case 5:
                    System.out.println("\n  Categorías (preorder):");
                    arbol.preorder();
                    break;
                case 0:
                    System.out.println("  Volviendo al menú principal...");
                    break;
                default:
                    System.out.println("  Opción inválida.");
            }
        } while (op != 0);
    }
}
