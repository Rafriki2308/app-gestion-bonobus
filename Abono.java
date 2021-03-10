package rafaelmartinez.abstractabono;

import java.util.Calendar;

/**
 *
 * @author rafae
 */
public class Abono {

    private static final float PRECIO_BILLETE = 1.40f;
    private static int counterId = 0;
    protected final int id;
    protected float saldo = 0;
    protected final Calendar fCreacion;
    protected Calendar fValidez;

    /**
     * Constructor que genera un abono de transporte en general tiene un
     * contador interno para contar la cantidad de abonos generados y para
     * generar un numero de serie para el abono
     */
    public Abono() {
        counterId++;
        this.id = counterId;
        fCreacion = Calendar.getInstance();
        fValidez = Calendar.getInstance();
        fValidez.add(fCreacion.YEAR, 2);
    }

    public float getSaldo() {
        return saldo;
    }

    public Calendar getfValidez() {
        return fValidez;
    }

    /**
     * Este metodo gestiona la fecha de validez del abono en funcion del tipo de
     * abono que estemos creando y la edad del usuario
     *
     * @param edad Edad del usuario que desea crear el abono
     * @return La fecha de expiracion del abono en funcion de la edad
     * @throws Exception Gestiona el rango de edad para cada tipo de abono
     */
    protected Calendar setFValidez(int edad) throws Exception {
        if (edad > 0 && edad < 25) {
            fValidez.add(fCreacion.YEAR, 23 - edad);
            return fValidez;
        } else if (edad > 65) {
            fValidez.add(fCreacion.YEAR, 100);
            return fValidez;
        } else {
            throw new Exception("La edad introducida no entra en el rango.");
        }
    }

    /**
     * Este metodo permite descontar un solo viaje del saldo del abono comprueba
     * que haya saldo suficiente y que el abono no este caducado
     *
     * @return Devuelve si se ha validado el viaje o no
     */
    public boolean pagarUnViaje() {
        //Comprueba que la fecha de caducidad sea mayor que la fecha actual
        int validez = fValidez.compareTo(Calendar.getInstance());
        //Condicional que filtra que haya saldo y que el abono no este caducado
        if (saldo > PRECIO_BILLETE && validez >= 0) {

            if (this.getClass().getName().equals(AbonoJoven.class.getName())) {
                saldo -= (PRECIO_BILLETE / 2);
            } else if (this.getClass().getName().equals(AbonoJubilado.class.getName())) {
                saldo -= (PRECIO_BILLETE / 3);
            } else {
                saldo -= PRECIO_BILLETE;
            }
            return true;
        }
        return false;

    }

    /**
     * Este metodo sive para pagar una cantidad de viajes que decida el usuario,
     * restandolo del saldo que tiene guardado en su tarjeta, comprobando si hay
     * saldo suficiente y si el abono esta en fecha.
     *
     * @param nViajes introducido por el usuario en funcion de los billetes que
     * quiere descontar del bono
     * @return El saldo restante que le queda al abono.
     */
    public boolean pagarNviajes(int nViajes) {
        float importeBilletes = (nViajes * PRECIO_BILLETE);
        //Compruebo si la fecha de validez es superior a la fecha actual
        int validez = fValidez.compareTo(Calendar.getInstance());

        //Este boolean restringe el uso del metodo a bono bus normal
        boolean correcto = this.getClass().getName().equals(Abono.class.getName());

        //Este boolean comprueba que haya saldo y la fecha de validez del bono
        boolean esValido = importeBilletes <= saldo && validez >= 0;

        if (correcto && esValido) {
            saldo -= importeBilletes;
            return true;
        }
        return false;
    }

    /**
     * Este metodo recarga el saldo del abono, en funcion de la carga que
     * introduce el usuario. Distingue si la carga se hace sobre un abono normal
     * o un abono Joven o Jubilado, para ampliar la fecha de caducidad hasta dos
     * años.
     *
     * @param carga Importe con el que el usuario quiere recargar el abono
     * @return Devuelve el saldo total del abono
     * @throws Exception Gestiona el error del usuario en caso que introduzca
     * una numero negativo.
     */
    public float recargarSaldo(int carga) throws Exception {

        if (carga > 0) {
            saldo += carga;
            //Este condicional amplia la caducidad del bono normal hasta dos 
            //años cuando se recarga 
            if (this.getClass().getName().equals(Abono.class.getName())) {
                //Calculo y guardo la diferencia entre la fecha de validez y
                //la fecha actual para calcular la extension que hay que aplicar
                int extension = (fValidez.YEAR - Calendar.getInstance().YEAR);
                //Cuando se recarga la tarjeta se amplia hasta dos años la fecha validez
                fValidez.add(fValidez.YEAR, (extension));
            }
            return saldo;
        } else {
            throw new Exception("Importe introducido incorrecto.");
        }
    }

}
