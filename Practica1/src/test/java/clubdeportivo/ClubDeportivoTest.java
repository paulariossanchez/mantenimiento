package clubdeportivo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


public class ClubDeportivoTest {
   private ClubDeportivo club;

   @BeforeEach
    public void setUp() throws ClubException {
         club = new ClubDeportivo("Club");
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
        Exception exception = assertThrows(ClubException.class, () -> {
            new ClubDeportivo("Club", -1);
        });
        assertEquals("ERROR: el club no puede crearse con un número de grupos 0 o negativo", exception.getMessage());
    
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

    
    //Probar el metodo ingresos
    @Test
    @DisplayName("El metodo debe crear un equipo con numeros negativos")
    public void ingresosTest() throws ClubException {
        String[] datos = {"Futbol", "Deporte", "10", "5", "10.0"};
        club.anyadirActividad(datos);
        double ingresos = club.ingresos();
        assertEquals(50.0, ingresos);
    }

    //Al añadir una actividad el grupo es nulo
    @Test
    @DisplayName("El metodo debe crear un equipo con numeros negativos")
    public void anyadirActividadTest_grupoNulo() {
        Exception exception = assertThrows(ClubException.class, () -> {
            club.anyadirActividad((Grupo) null);
        });
        assertEquals("ERROR: el grupo es nulo", exception.getMessage());
    }

   
    @Test
    @DisplayName("Matricular más personas de las plazas disponibles")
    public void matricularTest_sinPlazas() throws ClubException {
        String[] datos = {"Futbol", "Deporte", "10", "5", "10.0"};
        club.anyadirActividad(datos);

        Exception exception = assertThrows(ClubException.class, () -> {
            club.matricular("Futbol", 6); 
        });
        assertEquals("ERROR: no hay suficientes plazas libres para esa actividad en el club.", exception.getMessage());
    }

    //Probar el metodo matricular
    /* 
    @Test
    @DisplayName("Debe matricular correctamente cuando hay suficientes plazas disponibles")
    public void matricularTest_correcto() throws ClubException {
        String[] datos = {"Futbol", "Deporte", "10", "5", "15.0"};
        club.anyadirActividad(datos);

        assertEquals(5, club.plazasLibres("Futbol"), "Debe haber 5 plazas libres antes de la matrícula.");

        club.matricular("Futbol", 3);

        assertEquals(2, club.plazasLibres("Futbol"), "Deben quedar 2 plazas libres después de matricular.");
    }
    */



    //Probar metodos toString
    @Test
    @DisplayName("El metodo debe crear un club con un nombre valido")
    public void toStringTest() throws ClubException {
        ClubDeportivo club = new ClubDeportivo("Club");
        String expected = "Club --> [  ]";
        assertEquals(expected, club.toString());
    }

    @Test
    @DisplayName("No debe permitir añadir una actividad con datos inválidos")
    public void anyadirActividadTest_datosInvalidos() {
        String[] datosIncorrectos = {"Futbol", "Deporte", "10"}; // Faltan datos
        Exception exception = assertThrows(ClubException.class, () -> {
            club.anyadirActividad(datosIncorrectos);
        });
        assertEquals("ERROR: faltan datos o datos nulos", exception.getMessage());
}



   
    




}
