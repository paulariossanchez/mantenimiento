package clubdeportivo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


public class ClubExceptionTest {

    //Test para comprobar que se crea una excepción sin mensaje
    @Test
    @DisplayName("El método debe crear una excepción sin mensaje")
    public void ClubExceptionTest_sinMensaje() {
        ClubException clubException = new ClubException();
        assertEquals(null, clubException.getMessage());
    }

    //Test para comprobar que se crea una excepción con mensaje
    @Test
    @DisplayName("El método debe crear una excepción con mensaje")
    public void ClubExceptionTest_conMensaje() {
        ClubException clubException = new ClubException("Mensaje");
        assertEquals("Mensaje", clubException.getMessage());
    }

    
}
