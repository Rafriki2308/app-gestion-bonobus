package rafaelmartinez.abstractabono;

/**
 *
 * @author Rafael
 */
public class AbonoJubilado extends AbonoReducido {

    private int edad;

    public AbonoJubilado(int edad, String nombre, String apellidos, String dni) throws Exception {
        super(nombre, apellidos, dni);
        if (edad > 65) {
            this.edad = edad;
            super.fValidez = super.setFValidez(edad);

        } else {
            throw new Exception("La edad introducida no es correcta.");
        }
    }

}
