package aeropuerto;
public class Vuelo {

    String codigo;
    String ciudadDestino;
    String paisDestino;
    String horaSalida;
    String duracion;

    String[] puestos = new String[20];
    String[] nombrePasajero = new String[20];
    String[] documentoPasajero = new String[20];

    public Vuelo(String codigo, String ciudadDestino, String paisDestino, String horaSalida, String duracion) {
        this.codigo = codigo;
        this.ciudadDestino = ciudadDestino;
        this.paisDestino = paisDestino;
        this.horaSalida = horaSalida;
        this.duracion = duracion;

        for (int i = 0; i < 20; i++) {
            puestos[i] = "DISPONIBLE";
        }
    }
}