package aeropuerto;
public class ListaVuelos {

    NodoVuelo primero;
    NodoVuelo ultimo;

    public void agregar(Vuelo v) {
        NodoVuelo nuevo = new NodoVuelo(v);

        if (primero == null) {
            primero = nuevo;
            ultimo = nuevo;
        } else {
            nuevo.anterior = ultimo;
            ultimo.siguiente = nuevo;
            ultimo = nuevo;
        }
    }

    public boolean existeCodigo(String codigo) {
        NodoVuelo actual = primero;

        while (actual != null) {
            if (actual.vuelo.codigo.equalsIgnoreCase(codigo)) {
                return true;
            }
            actual = actual.siguiente;
        }

        return false;
    }

    public boolean estaVacia() {
        return primero == null;
    }
}