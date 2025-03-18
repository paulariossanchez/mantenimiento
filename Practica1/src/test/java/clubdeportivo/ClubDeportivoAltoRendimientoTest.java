package clubdeportivo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ClubDeportivoAltoRendimientoTest {

    private ClubDeportivoAltoRendimiento club;

    //Se debe crear un club con un nombre valido
    @Test
    @DisplayName("La clase se inicializa adecuadamente - 1")
    public void ClubDeportivoAltoRendimientoTest_constructor_nombre() throws ClubException {
        String nombre = "Málaga";
        int maximo = 2;
        double incremento = 2;

        ClubDeportivoAltoRendimiento clubD = new ClubDeportivoAltoRendimiento(nombre, maximo, incremento);

    }

    @Test
    @DisplayName("La clase se inicializa adecuadamente - Parámetro 1 negativos")
    public void ClubDeportivoAltoRendimientoTest_constructor_pneg1() throws ClubException {
        String nombre = "Málaga";
        assertThrows(ClubException.class, () -> {
            ClubDeportivoAltoRendimiento clubD = new ClubDeportivoAltoRendimiento(nombre, -2, 2);
        });

    }

    @Test
    @DisplayName("La clase se inicializa adecuadamente - Parámetro 2 negativos")
    public void ClubDeportivoAltoRendimientoTest_constructor_pneg2() throws ClubException {
        String nombre = "Málaga";
        assertThrows(ClubException.class, () -> {
            ClubDeportivoAltoRendimiento clubD = new ClubDeportivoAltoRendimiento(nombre, 2, -2);
        });

    }

    //Se debe crear un club con un nombre valido
    @Test
    @DisplayName("La clase se inicializa adecuadamente - 2")
    public void ClubDeportivoAltoRendimientoTest_constructor_tam_expected() throws ClubException {
        String nombre = "Málaga";
        int maximo = 2;
        double incremento = 2;
        ClubDeportivoAltoRendimiento clubD = new ClubDeportivoAltoRendimiento(nombre, 2, maximo, incremento);

    }

    @Test
    @DisplayName("La clase se inicializa adecuadamente - Parámetro 1 negativos")
    public void ClubDeportivoAltoRendimientoTest_constructor_tam_pneg1() throws ClubException {
        String nombre = "Málaga";
        assertThrows(ClubException.class, () -> {
            ClubDeportivoAltoRendimiento clubD = new ClubDeportivoAltoRendimiento(nombre, -2, 2, 2);
        });

    }

    @Test
    @DisplayName("La clase se inicializa adecuadamente - Parámetro 2 negativos")
    public void ClubDeportivoAltoRendimientoTest_constructor_tam_pneg2() throws ClubException {
        String nombre = "Málaga";
        assertThrows(ClubException.class, () -> {
            ClubDeportivoAltoRendimiento clubD = new ClubDeportivoAltoRendimiento(nombre, 2, -2, 2);
        });

    }

    @Test
    @DisplayName("La clase se inicializa adecuadamente - Parámetro 3 negativos")
    public void ClubDeportivoAltoRendimientoTest_constructor_tam_pneg3() throws ClubException {
        String nombre = "Málaga";
        assertThrows(ClubException.class, () -> {
            ClubDeportivoAltoRendimiento clubD = new ClubDeportivoAltoRendimiento(nombre, 2, 2, -2);
        });

    }

    @Test
    @DisplayName("La clase se inicializa adecuadamente - Tamaño mayor que el máximo")
    public void ClubDeportivoAltoRendimientoTest_constructor_tam_exceeded() throws ClubException {
        String nombre = "Málaga";
        assertThrows(ClubException.class, () -> {
            ClubDeportivoAltoRendimiento clubD = new ClubDeportivoAltoRendimiento(nombre, 5, 3, -2);
        });

    }

    @Test
    @DisplayName("El método de prueba que se añade una actividad correctamente")
    public void ClubDeportivoAltoRendimientoTest_anyadir_expected() throws ClubException {
        String nombre = "Málaga";

        ClubDeportivoAltoRendimiento clubD = new ClubDeportivoAltoRendimiento(nombre, 2, 6, 2);

        //clubD.anyadirActividad(new String[]{"AX21", "Balonsesto", "10", "5", "10.0"});
        //assertEquals(5, clubD.plazasLibres("Balonsesto"));

        String[] datos = {"AX13", "Futbol", "10", "5", "10.0"};
        clubD.anyadirActividad(datos);

        Exception exception = assertThrows(ClubException.class, () -> {
            clubD.matricular("Futbol", 6); 
        });
        assertEquals("ERROR: no hay suficientes plazas libres para esa actividad en el club.", exception.getMessage());

    }

    //Test de ingresos
    @Test
    @DisplayName("El método de prueba que se añade una actividad correctamente")
    public void ClubDeportivoAltoRendimientoTest_ingresos_expected() throws ClubException {
        String nombre = "Málaga";

        ClubDeportivoAltoRendimiento clubD = new ClubDeportivoAltoRendimiento(nombre, 2, 6, 2);

        //clubD.anyadirActividad(new String[]{"AX21", "Balonsesto", "10", "5", "10.0"});
        //assertEquals(5, clubD.plazasLibres("Balonsesto"));

        String[] datos = {"AX13", "Futbol", "10", "5", "10.0"};
        clubD.anyadirActividad(datos);

        assertEquals(50.0 * 1.02, clubD.ingresos(), 0.01);

    }


}
