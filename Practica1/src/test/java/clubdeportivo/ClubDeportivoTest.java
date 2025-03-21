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
    public void ClubDeportivoTest_nombre() throws ClubException {
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
    @DisplayName("El metodo debe lanzar error al añadir una actividad con grupo nulo")
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

   
    @Test
    @DisplayName("Matricular más personas de las plazas disponibles - Error esperado")
    public void ClubDeportivoAltoRendimientoTest_matricular_noPlazas() throws ClubException {
        String nombre = "Málaga";
        ClubDeportivoAltoRendimiento clubD = new ClubDeportivoAltoRendimiento(nombre, 2, 6, 2);

        String[] datos = {"AX13", "Futbol", "10", "5", "10.0"};
        clubD.anyadirActividad(datos);
        Exception exception = assertThrows(ClubException.class, () -> {
            clubD.matricular("Futbol", 6);
        });

        assertEquals("ERROR: no hay suficientes plazas libres para esa actividad en el club.", exception.getMessage());
    }

    @Test
    @DisplayName("Matricular en una actividad inexistente - Error esperado")
    public void ClubDeportivoAltoRendimientoTest_matricular_actividadInexistente() throws ClubException {
        String nombre = "Málaga";
        ClubDeportivoAltoRendimiento clubD = new ClubDeportivoAltoRendimiento(nombre, 2, 6, 2);
        Exception exception = assertThrows(ClubException.class, () -> {
            clubD.matricular("Natación", 2);
        });

        assertEquals("ERROR: no hay suficientes plazas libres para esa actividad en el club.", exception.getMessage());
    }

    //Probamos el metodo toString
    @Test
    @DisplayName("El metodo debe crear un club con un nombre valido")
    public void toStringTest() throws ClubException {

        club.anyadirActividad(new Grupo("Test1", "Futbol", 10, 5, 10));
        String expected = "Club --> [ (Test1 - Futbol - 10.0 euros - P:10 - M:5) ]";
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

    @Test
    @DisplayName("El metodo debe de comprobar que varios grupos se añaden correctamente")
    public void variosGrupos() throws ClubException {
        String[] datos = {"Test1", "Futbol", "10", "5", "10.0"};
        String[] datos2 = {"Test2", "Baloncesto", "10", "5", "10.0"};
        club.anyadirActividad(datos);
        club.anyadirActividad(datos2);
        double ingresos = club.ingresos();
        assertEquals(100.0, ingresos);
    }

    @Test
    @DisplayName("El metodo debe crear un equipo con numeros no soportados")
    public void anyadirActividad_FormatoErroneo() throws ClubException {
        String[] datos = {"Test1", "Futbol", "Error", "Error", "ERror.0"};

        Exception exception = assertThrows(ClubException.class, () -> {
            club.anyadirActividad(datos);
        });
    }

    @Test
    @DisplayName("El metodo no debería de dejar añadir más personas a un grupo que el máximo permitido")
    public void anyadirActividad_MaximoPlazas() throws ClubException {
        String[] datos = {"Test1", "Futbol", "10", "5", "10.0"};
        String[] datos1 = {"Test2", "Baloncesto", "10", "5", "10.0"};
        String[] datos2 = {"Test3", "Balonmano", "10", "5", "10.0"};
        ClubDeportivo clubPequenyo = new ClubDeportivo("Club", 1);
        Exception exception = assertThrows(ClubException.class, () -> {
            clubPequenyo.anyadirActividad(datos);
            clubPequenyo.anyadirActividad(datos1);
            clubPequenyo.anyadirActividad(datos2);
        });
    }

    @Test
    @DisplayName("El metodo debería de actualizar grupos ya registrados")
    public void anyadirActividad_MismoGrupo() throws ClubException {
        club.anyadirActividad(new Grupo("Test1", "Futbol", 10, 5, 10));
        club.anyadirActividad(new Grupo("Test1", "Futbol", 11, 5, 10));

        assertEquals(6, club.plazasLibres("Futbol"));

    }


}
