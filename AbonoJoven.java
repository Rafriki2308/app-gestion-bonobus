package rafaelmartinez.abstractabono;
import java.util.Calendar;
/**
 *
 * @author rafae
 */
public class AbonoJoven extends AbonoReducido {
    
    private int edad;
    
    /**Constructor para crear el abono de tipo joven, comprueba que la edad
     * sea inferior a 25 y crea el abono en caso correcto
     * @param edad Parametro necesario para que se considere un abono joven
     * @param nombre Nombre del usuario
     * @param apellidos Apellidos del Usuario
     * @param dni Dni del usuario necesario para luego comprobar el abono
     * @throws Exception En caso que la edad no sea correcta no genera el abono
     */

    public AbonoJoven(int edad, String nombre, String apellidos, String dni) throws Exception {
        super(nombre, apellidos, dni);
        if (edad > 0 && edad < 25) {
            this.edad = edad;
            super.fValidez = super.setFValidez(edad);

        } else {
            throw new Exception("La edad introducida no es correcta.");
        }
    }
    
    
    
    
    
    
        
    

    

    
    
    

}
