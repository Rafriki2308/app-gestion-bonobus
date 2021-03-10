package rafaelmartinez.abstractabono;

import java.util.Calendar;

/**
 *
 * @author Rafael
 */
public class AppAbono {

    public static void main(String[] args) throws Exception {

        try {
            Abono b1 = new Abono();
            b1.recargarSaldo(10);
            System.out.println(b1.toString());
            
            AbonoJoven b2 = new AbonoJoven (20, "Rafael", "Martinez","74660189G");
            b2.recargarSaldo(10);
            boolean viajo = b2.pagarNviajes(10);
            System.out.println(viajo);
             
            
            System.out.println(b2.toString());
        } catch (Exception e) {
            System.out.println(e);

        }

    }

}
