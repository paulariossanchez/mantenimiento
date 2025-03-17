package clubdeportivo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class GrupoTest {
    private Grupo grupo;

    @BeforeEach
    public void setUp() throws ClubException {
        grupo = new Grupo("897A", "Natación", 20, 10, 25.0);
    }
    
    //Se debe crear un grupo con un código válido
    @Test
    @DisplayName("El método debe crear un grupo con un código válido")
    public void GrupoTest_codigo() throws ClubException{
        String codigo = "897A";
        Grupo grupo = new Grupo(codigo, "Natación", 20, 10, 25.0);
        assertEquals(codigo, grupo.getCodigo());
    }

    //Se debe crear un grupo con un nombre valido
    @Test
    @DisplayName("El método debe crear un grupo con un nombre válido")
    public void GrupoTest_nombre() throws ClubException{
        String nombre = "Natación";
        Grupo grupo = new Grupo("897A", nombre, 20, 10, 25.0);
        assertEquals(nombre, grupo.getActividad());
    }

    //El número de plazas es igual que 0
    @Test
    @DisplayName("El método no debe crear un grupo con un número de plazas mayor que 0")
    public void GrupoTest_plazas() {
        Exception exception = assertThrows(ClubException.class, () -> {
            new Grupo("897A", "Natación", 0, 10, 25.0);
        });
        assertEquals("ERROR: los datos numéricos no pueden ser menores o iguales que 0.", exception.getMessage());
    }

    //El número de plazas es menor que 0
    @Test
    @DisplayName("El método no debe crear un grupo con un número de plazas menor que 0")
    public void GrupoTest_plazasMenor() {
        Exception exception = assertThrows(ClubException.class, () -> {
            new Grupo("897A", "Natación", -1, 10, 25.0);
        });
        assertEquals("ERROR: los datos numéricos no pueden ser menores o iguales que 0.", exception.getMessage());
    }

    //El grupo se crea con un número de plazas correcto
    @Test
    @DisplayName("El método debe crear un grupo con un número de plazas correcto")
    public void GrupoTest_plazasCorrecto() throws ClubException{
        int plazas = 20;
        Grupo grupo = new Grupo("897A", "Natación", plazas, 10, 25.0);
        assertEquals(plazas, grupo.getPlazas());
    }

    //El grupo se crea con un número de matriculados correcto
    @Test
    @DisplayName("El método debe crear un grupo con un número de matriculados correcto")
    public void GrupoTest_matriculadosCorrecto() throws ClubException{
        int matriculados = 10;
        Grupo grupo = new Grupo("897A", "Natación", 20, matriculados, 25.0);
        assertEquals(matriculados, grupo.getMatriculados());
    }

    //El número de matriculados es menor que 0
    @Test
    @DisplayName("El método no debe crear un grupo con un número de matriculados menor que 0")
    public void GrupoTest_matriculados() {
        Exception exception = assertThrows(ClubException.class, () -> {
            new Grupo("897A", "Natación", 20, -1, 25.0);
        });
        assertEquals("ERROR: los datos numéricos no pueden ser menores o iguales que 0.", exception.getMessage());
    }

    //El grupo se crea con un precio de tarifa correcto
    @Test
    @DisplayName("El método debe crear un grupo con un precio de tarifa correcto")
    public void GrupoTest_tarifa() throws ClubException{
        double tarifa = 25.0;
        Grupo grupo = new Grupo("897A", "Natación", 20, 10, tarifa);
        assertEquals(tarifa, grupo.getTarifa());
    }

    //El precio de tarifa es menor que 0
    @Test
    @DisplayName("El método no debe crear un grupo con un precio de tarifa menor que 0")
    public void GrupoTest_tarifaMenor() {
        Exception exception = assertThrows(ClubException.class, () -> {
            new Grupo("897A", "Natación", 20, 10, -1);
        });
        assertEquals("ERROR: los datos numéricos no pueden ser menores o iguales que 0.", exception.getMessage());
    }

    //El precio de la tarifa es igual que 0
    @Test
    @DisplayName("El método no debe crear un grupo con un precio de tarifa igual que 0")
    public void GrupoTest_tarifaIgual() {
        Exception exception = assertThrows(ClubException.class, () -> {
            new Grupo("897A", "Natación", 20, 10, 0);
        });
        assertEquals("ERROR: los datos numéricos no pueden ser menores o iguales que 0.", exception.getMessage());
    }

    //El número de matriculados es mayor que el número de plazas
    @Test
    @DisplayName("El método no debe crear un grupo con un número de matriculados mayor que el número de plazas")
    public void GrupoTest_matriculadosMayor() {
        Exception exception = assertThrows(ClubException.class, () -> {
            new Grupo("897A", "Natación", 20, 21, 25.0);
        });
        assertEquals("ERROR: El número de plazas es menor que el de matriculados.", exception.getMessage());
    }

    //El número de plazas se actualiza correctamente
    @Test
    @DisplayName("El método debe actualizar el número de plazas correctamente")
    public void GrupoTest_actualizarPlazas() throws ClubException{
        int plazas = 30;
        grupo.actualizarPlazas(plazas);
        assertEquals(plazas, grupo.getPlazas());
    }

    //El número de plazas se actualiza a un número menor que 0
    @Test
    @DisplayName("El método no debe actualizar el número de plazas a un número menor que 0")
    public void GrupoTest_actualizarPlazasMenor() {
        Exception exception = assertThrows(ClubException.class, () -> {
            grupo.actualizarPlazas(-1);
        });
        assertEquals("ERROR: número de plazas negativo.", exception.getMessage());
    }

    //El número de plazas se actualiza a un número igual que 0
    @Test
    @DisplayName("El método no debe actualizar el número de plazas a un número igual que 0")
    public void GrupoTest_actualizarPlazasIgual() {
        Exception exception = assertThrows(ClubException.class, () -> {
            grupo.actualizarPlazas(0);
        });
        assertEquals("ERROR: número de plazas negativo.", exception.getMessage());
    }

    //El número de plazas se actualiza a un número correcto
    @Test
    @DisplayName("El método debe actualizar el número de plazas a un número correcto")
    public void GrupoTest_actualizarPlazasCorrecto() throws ClubException{
        int plazas = 30;
        grupo.actualizarPlazas(plazas);
        assertEquals(plazas, grupo.getPlazas());
    }

    //El número de matriculados se actualiza correctamente
    @Test
    @DisplayName("El método debe actualizar el número de matriculados correctamente")
    public void GrupoTest_matricular() throws ClubException{
        int matriculados = 5;
        grupo.matricular(matriculados);
        assertEquals(15, grupo.getMatriculados());
    }

    //El número de matriculados se actualiza a un número menor que 0
    @Test
    @DisplayName("El método no debe actualizar el número de matriculados a un número menor que 0")
    public void GrupoTest_matricularMenor() {
        int matriculaciones = -1;
        Exception exception = assertThrows(ClubException.class, () -> {
            grupo.matricular(matriculaciones);
        });
        assertEquals(("ERROR: no hay plazas libres suficientes, plazas libre: "+ grupo.plazasLibres()+ " y matriculas: "+matriculaciones), exception.getMessage());
    }

    //El número de matriculados se actualiza a un número igual que 0
    @Test
    @DisplayName("El método no debe actualizar el número de matriculados a un número igual que 0")
    public void GrupoTest_matricularIgual() {
        int matriculaciones = 0;
        Exception exception = assertThrows(ClubException.class, () -> {
            grupo.matricular(matriculaciones);
        });
        assertEquals(("ERROR: no hay plazas libres suficientes, plazas libre: "+ grupo.plazasLibres()+ " y matriculas: "+matriculaciones), exception.getMessage());
    }

    //El método toString se ejecuta correctamente
    @Test
    @DisplayName("El método toString se debe ejecutar correctamente")
    public void GrupoTest_toString() {
        String toString = "("+ grupo.getCodigo() + " - "+grupo.getActividad()+" - " + grupo.getTarifa() + " euros "+ "- P:" + grupo.getPlazas() +" - M:" +grupo.getMatriculados()+")";
        assertEquals(toString, grupo.toString());
    }

    //El método equals se ejecuta correctamente
    @Test
    @DisplayName("El método equals se debe ejecutar correctamente")
    public void GrupoTest_equals() throws ClubException{
        Grupo grupo2 = new Grupo("897A", "Natación", 20, 10, 25.0);
        assertEquals(true, grupo.equals(grupo2));
    }

    //El método equals se ejecuta correctamente
    @Test
    @DisplayName("El método equals se debe ejecutar correctamente")
    public void GrupoTest_equals2() throws ClubException{
        Grupo grupo2 = new Grupo("897B", "Natación", 20, 10, 25.0);
        assertEquals(false, grupo.equals(grupo2));
    }

    //El método hashCode se ejecuta correctamente
    @Test
    @DisplayName("El método hashCode se debe ejecutar correctamente")
    public void GrupoTest_hashCode() throws ClubException{
        Grupo grupo2 = new Grupo("897B", "Natación", 20, 10, 25.0);
        assertEquals(false, grupo.hashCode() == grupo2.hashCode());
    }

    //El método hashCode se ejecuta correctamente
    @Test
    @DisplayName("El método hashCode se debe ejecutar correctamente")
    public void GrupoTest_hashCode2() throws ClubException{
        Grupo grupo2 = new Grupo("897A", "Natación", 20, 10, 25.0);
        assertEquals(true, grupo.hashCode() == grupo2.hashCode());
    }
    
}
