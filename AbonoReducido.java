package rafaelmartinez.abstractabono;

/**
 *
 * @author Rafael
 */
public class AbonoReducido extends Abono {

    protected String nombre;
    protected String apellidos;
    protected String dni;

    /**
     * Constructor que toma nombre apellido y DNI de usuario para crear un abono
     * reducido
     *
     * @param nombre El nombre del usuario registrado
     * @param apellidos Los apellidos del usuario registrado
     * @param dni DNI del usuario registrado
     */
    protected AbonoReducido(String nombre, String apellidos, String dni) {
        super();

        this.nombre = nombre;
        this.apellidos = apellidos;
        this.dni = dni.toUpperCase();

    }

    /**
     * Metodo para pagar un viaje con abono reducido, se necesita el dni del
     * propietario del abono para confirmar que se puede utilizar
     *
     * @param dNI El Dni del usuario
     * @return Devuelve un verdadero o falso en funcion de si el dni es correcto
     * @throws Exception En caso que no sea correcto el dni devuelve mensaje
     */
    protected boolean pagarUnViajeReducido(String dNI) throws Exception {
        //Para evitar errores con la letra del DNI al gestionar mayusculas o minusculas.
        dNI.toUpperCase();
        if (this.dni.equals(dNI)) {
            super.pagarUnViaje();
            return true;
        } else {
            throw new Exception("El DNI introducido no coincide con el usuario.");
        }
    }

}
