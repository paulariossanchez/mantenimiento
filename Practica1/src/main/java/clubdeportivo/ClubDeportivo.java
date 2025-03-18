package clubdeportivo;

import java.util.StringJoiner;

public class ClubDeportivo {

    private String nombre;
    private int ngrupos;
    private Grupo[] grupos;
    private static final int TAM = 10;

    public ClubDeportivo(String nombre) throws ClubException {
        this(nombre, TAM);
    }

    public ClubDeportivo(String nombre, int n) throws ClubException {
        if (n <= 0) {
            throw new ClubException("ERROR: el club no puede crearse con un número de grupos 0 o negativo");
        }
        this.nombre = nombre;
        this.ngrupos = 0;
        grupos = new Grupo[n];
    }

    private int buscar(Grupo g) {
        int i = 0;
        while (i < ngrupos && (grupos[i] == null || !g.equals(grupos[i]))) {
            i++;
        }
        if (i == ngrupos) {
            i = -1;
        }
        return i;
    }

    public void anyadirActividad(String[] datos) throws ClubException {
        if (datos == null || datos.length < 5) { // Verificamos si datos es null o tiene menos de 5 elementos
            throw new ClubException("ERROR: faltan datos o datos nulos");
        }
        try {
            int plazas = Integer.parseInt(datos[2]);
            int matriculados = Integer.parseInt(datos[3]);
            double tarifa = Double.parseDouble(datos[4]);
            Grupo g = new Grupo(datos[0], datos[1], plazas, matriculados, tarifa);
            anyadirActividad(g);
        } catch (NumberFormatException e) {
            throw new ClubException("ERROR: formato de número incorrecto");
        }
    }

    public void anyadirActividad(Grupo g) throws ClubException {
        if (g == null) { // ADDME: anaydido para comprobar los grupos nulos
            throw new ClubException("ERROR: el grupo es nulo");
        }
        int pos = buscar(g);
        if (pos == -1) { // El grupo es nuevo
            if (ngrupos >= grupos.length) { // Verificamos si hay espacio en el array
                //Añadimos la siguiente línea para que se lance una excepción si no hay espacio
                throw new ClubException("ERROR: no hay espacio para más grupos en el club.");
            }
            grupos[ngrupos] = g; // Guardamos en la posición correcta
            ngrupos++;
        } else { // El grupo ya existe --> modificamos las plazas
            grupos[pos].actualizarPlazas(g.getPlazas());
        }
    }

    public int plazasLibres(String actividad) {
        int p = 0;
        int i = 0;
        while (i < ngrupos) {
            if (grupos[i] != null && grupos[i].getActividad().equals(actividad)) {
                p += grupos[i].plazasLibres();
            }
            i++;
        }
        return p;
    }

    public void matricular(String actividad, int npersonas) throws ClubException {
        int plazas = plazasLibres(actividad);
        if (plazas < npersonas) {
            throw new ClubException("ERROR: no hay suficientes plazas libres para esa actividad en el club.");
        }
        int i = 0;
        while (i < ngrupos && npersonas > 0) {
            if (actividad.equals(grupos[i].getActividad())) {
                int plazasGrupo = grupos[i].plazasLibres();
                grupos[i].matricular(plazasGrupo);
            }
            i++;
        }
    }

    public double ingresos() {
        double cantidad = 0.0;
        int i = 0;
        while (i < ngrupos) {
            if (grupos[i] != null) { // Evitamos NullPointerException
                cantidad += grupos[i].getTarifa() * grupos[i].getMatriculados();
            }
            i++;
        }
        return cantidad;
    }

    public String toString() {
        StringJoiner sj = new StringJoiner(", ", "[ ", " ]");
        int i = 0;
        while (i < ngrupos) {
            sj.add(grupos[i].toString());
            i++;
        }
        return nombre + " --> " + sj.toString();
    }
}
