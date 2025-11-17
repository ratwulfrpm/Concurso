/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Raul
 */
public class Participante {

    private String nombre;
    private int edad;
    private TipoDeCocinero tipC;
    private int[][] puntos;

    public Participante() {
        this.nombre = "";
        this.edad = 0;
        this.tipC = TipoDeCocinero.Novato;
        this.puntos = new int[3][3];
    }

    public Participante(String nombre, int edad, TipoDeCocinero tipC) {
        this.nombre = nombre;
        this.edad = edad;
        this.tipC = tipC;
        this.puntos = new int[3][3];
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public TipoDeCocinero getTipC() {
        return tipC;
    }

    public void setTipC(TipoDeCocinero tipC) {
        this.tipC = tipC;
    }

    public int[][] getPuntos() {
        return puntos;
    }

    public void setPunto(int dia, int fase, int nota) {
        this.puntos[dia][fase] = nota;
    }

    public int getPunto(int dia, int fase) {
        return this.puntos[dia][fase];
    }

    public void generarPuntosAleatorios() {
        for (int dia = 0; dia < 3; dia++) {
            for (int fase = 0; fase < 3; fase++) {
                this.puntos[dia][fase] = (int) (Math.random() * 10) + 1;
            }
        }
    }

    public int getTotalPuntos() {
        int total = 0;
        for (int dia = 0; dia < 3; dia++) {
            for (int fase = 0; fase < 3; fase++) {
                total += this.puntos[dia][fase];
            }
        }
        return total;
    }

    public double getBono() {
        switch (this.tipC) {
            case Novato:
                return 1.0;
            case Amateur:
                return 1.2;
            case Profesional:
                return 1.3;
            case Experto:
                return 1.5;
            default:
                return 1.0;
        }
    }

    public double getPuntosFinales() {
        return getTotalPuntos() * getBono();
    }
}
