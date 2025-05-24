package javafxmlapplication;

import java.time.LocalDate;

public class Resultado {
   public String fecha;
    public String resultado;

    public Resultado(String fecha, String resultado) {
        this.fecha = fecha;
        this.resultado = resultado;
    }
    
    public String getFecha() {
        return fecha;
    }

    public String getResultado() {
        return resultado;
    }
}