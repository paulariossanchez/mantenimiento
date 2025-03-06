package clubdeportivo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


public class ClubDeportivoTest {
    private String nombre;
	private int ngrupos;
	private Grupo[] grupos;
	private static final int TAM = 10;

    @BeforeEach
    public void setUp() {
        nombre = "Club";
        ngrupos = 1;
        grupos = new Grupo[TAM];
    }

    //Se debe crear un club con un nombre valido
    @Test
    @DisplayName("El metodo debe crear un club con un nombre valido")
    public void ClubDeportivoTest_nombre() throws ClubException{
        String nombre = "Málaga";
        ClubDeportivo club = new ClubDeportivo(nombre);
    }


    //El tamanio del club supera el maximo permitido
    @Test
    @DisplayName("El metodo debe crear un equipo con numeros superiores al maximo permitido")
    public void ClubDeportivoTest_superior() {
        try {
            ClubDeportivo club = new ClubDeportivo("Club", 11);
        } catch (ClubException e) {
            assertEquals("ERROR: el club no puede crearse con un número de grupos 0 o negativo", e.getMessage());
        }
    }

    //Se crea club con numeros negativos
    @Test
    @DisplayName("El metodo debe crear un equipo con numeros negativos")
    public void ClubDeportivoTest_negativo() {
        try {
            ClubDeportivo club = new ClubDeportivo("Club", -1);
        } catch (ClubException e) {
            assertEquals("ERROR: el club no puede crearse con un número de grupos 0 o negativo", e.getMessage());
        }
    }




  

   




}
