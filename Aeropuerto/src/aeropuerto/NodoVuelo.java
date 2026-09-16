package aeropuerto;
public class NodoVuelo {

    Vuelo vuelo;
    NodoVuelo siguiente;
    NodoVuelo anterior;

    public NodoVuelo(Vuelo vuelo) {
        this.vuelo = vuelo;
        this.siguiente = null;
        this.anterior = null;
    }
}