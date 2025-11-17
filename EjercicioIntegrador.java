
import javax.swing.JOptionPane;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
/**
 *
 * @author Raul
 */
public class EjercicioIntegrador {

    public static void main(String[] args) {

        Participante cocineros[] = new Participante[5];

        String menuBtn[] = {"Agregar Participantes", "Mostrar Participantes", "Editar Participantes",
            "Final de la Competencia", "Salir."};
        int opcion = -1;
        while (opcion != 5) {
            opcion = JOptionPane.showOptionDialog(null, "Seleccione una de las opciones del menu principal.", "MENU PRINCIPAL",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, menuBtn, menuBtn[0]);
            switch (opcion) {
                case 0:
                    AgregarParticipantes(cocineros);
                    break;
                case 1:
                    MostrarParticipantes(cocineros);
                    break;
                case 2:
                    EditarParticipante(cocineros);
                    break;
                case 3:
                    FinalCompetencia(cocineros);
                    break;
                case 4:
                    JOptionPane.showMessageDialog(null, "Has salido del sistema.");
                    return;
                default:
                    JOptionPane.showMessageDialog(null, "ERROR: Opcion invalida, debe marcar un boton.");
            }
        }
    }

    public static void AgregarParticipantes(Participante cocineros[]) {

        String[] tipos = {"Novato", "Amateur", "Profesional", "Experto"};

        for (int i = 0; i < cocineros.length; i++) {

            String nombre = JOptionPane.showInputDialog(
                    "Digite el nombre del participante #" + (i + 1) + ":");

            if (nombre == null || nombre.trim().equals("")) {
                nombre = "Sin nombre";
            }

            String edadTexto = JOptionPane.showInputDialog(
                    "Digite la edad de " + nombre + ":");
            int edad = 0;
            if (edadTexto != null) {
                try {
                    edad = Integer.parseInt(edadTexto);
                } catch (NumberFormatException e) {
                    edad = 0;
                }
            } else {
                edad = 0;
            }

            int opcionTipo = JOptionPane.showOptionDialog(
                    null,
                    "Seleccione el tipo de cocinero para " + nombre,
                    "Tipo de cocinero",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    tipos,
                    tipos[0]
            );

            TipoDeCocinero tipoSel = TipoDeCocinero.Novato;
            if (opcionTipo == 1) {
                tipoSel = TipoDeCocinero.Amateur;
            } else if (opcionTipo == 2) {
                tipoSel = TipoDeCocinero.Profesional;
            } else if (opcionTipo == 3) {
                tipoSel = TipoDeCocinero.Experto;
            }

            Participante p = new Participante(nombre, edad, tipoSel);
            p.generarPuntosAleatorios();

            cocineros[i] = p;
        }
    }

    public static void MostrarParticipantes(Participante[] cocineros) {
        String salida = "";

        for (int i = 0; i < cocineros.length; i++) {
            Participante p = cocineros[i];
            if (p == null) {
                continue;
            }

            salida += "****************Participante #" + (i + 1) + "****************\n";
            salida += "Nombre: " + p.getNombre() + ".\n";
            salida += "Edad: " + p.getEdad() + " años.\n";
            salida += "Tipo de cocinero: " + p.getTipC() + ".\n";
            salida += "Puntos:\n";

            for (int dia = 0; dia < 3; dia++) {
                for (int fase = 0; fase < 3; fase++) {
                    salida += "[" + p.getPunto(dia, fase) + "] ";
                }
                salida += "\n";
            }

            salida += "\n";
        }

        if (salida.equals("")) {
            JOptionPane.showMessageDialog(null, "No hay participantes registrados.");
        } else {
            JOptionPane.showMessageDialog(null, salida);
        }
    }

    public static void EditarParticipante(Participante[] cocineros) {
        String nombreBuscado = JOptionPane.showInputDialog("Digite el nombre del participante a editar:");

        if (nombreBuscado == null || nombreBuscado.trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Nombre inválido.");
            return;
        }

        int indice = -1;
        for (int i = 0; i < cocineros.length; i++) {
            if (cocineros[i] != null && cocineros[i].getNombre().equalsIgnoreCase(nombreBuscado)) {
                indice = i;
                break;
            }
        }

        if (indice == -1) {
            JOptionPane.showMessageDialog(null, "No se encontró el participante.");
            return;
        }

        Participante p = cocineros[indice];

        String[] opciones = {"Nombre", "Edad", "Tipo de cocinero"};
        int opcion = JOptionPane.showOptionDialog(
                null,
                "¿Qué desea editar?",
                "Editar participante",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                opciones,
                opciones[0]
        );

        if (opcion == 0) {
            String nuevoNombre = JOptionPane.showInputDialog("Digite el nuevo nombre:", p.getNombre());
            if (nuevoNombre != null && !nuevoNombre.trim().equals("")) {
                p.setNombre(nuevoNombre);
            }
        } else if (opcion == 1) {
            String edadTexto = JOptionPane.showInputDialog("Digite la nueva edad:", p.getEdad());
            if (edadTexto != null) {
                try {
                    int nuevaEdad = Integer.parseInt(edadTexto);
                    p.setEdad(nuevaEdad);
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Edad inválida, no se actualizó.");
                }
            }
        } else if (opcion == 2) {
            String[] tipos = {"Novato", "Amateur", "Profesional", "Experto"};
            int opcionTipo = JOptionPane.showOptionDialog(
                    null,
                    "Seleccione el nuevo tipo de cocinero:",
                    "Tipo de cocinero",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    tipos,
                    tipos[0]
            );

            if (opcionTipo == 0) {
                p.setTipC(TipoDeCocinero.Novato);
            } else if (opcionTipo == 1) {
                p.setTipC(TipoDeCocinero.Amateur);
            } else if (opcionTipo == 2) {
                p.setTipC(TipoDeCocinero.Profesional);
            } else if (opcionTipo == 3) {
                p.setTipC(TipoDeCocinero.Experto);
            }
        } else {
            return;
        }

        p.generarPuntosAleatorios();
        JOptionPane.showMessageDialog(null, "Participante editado y puntos recalculados.");
    }

    public static void FinalCompetencia(Participante[] cocineros) {
        int n = cocineros.length;
        int[] totales = new int[n];
        double[] finales = new double[n];
        boolean hayParticipantes = false;

        for (int i = 0; i < n; i++) {
            if (cocineros[i] != null) {
                hayParticipantes = true;
                totales[i] = cocineros[i].getTotalPuntos();
                finales[i] = cocineros[i].getPuntosFinales();
            } else {
                totales[i] = -1;
                finales[i] = -1;
            }
        }

        if (!hayParticipantes) {
            JOptionPane.showMessageDialog(null, "No hay participantes registrados.");
            return;
        }

        double maxFinal = -1;
        double minFinal = Double.MAX_VALUE;

        for (int i = 0; i < n; i++) {
            if (finales[i] >= 0) {
                if (finales[i] > maxFinal) {
                    maxFinal = finales[i];
                }
                if (finales[i] < minFinal) {
                    minFinal = finales[i];
                }
            }
        }

        String salida = "";

        for (int i = 0; i < n; i++) {
            Participante p = cocineros[i];
            if (p == null) {
                continue;
            }

            int total = totales[i];
            double bono = p.getBono();
            double puntosFinales = finales[i];

            String calificacion;
            if (puntosFinales == maxFinal) {
                calificacion = "Primer Lugar.";
            } else if (puntosFinales == minFinal) {
                calificacion = "Último Lugar.";
            } else {
                calificacion = "Lugar intermedio.";
            }

            salida += "****************Participante #" + (i + 1) + "****************\n";
            salida += "Nombre: " + p.getNombre() + ".\n";
            salida += "Puntos totales: " + total + "/90.\n";
            salida += "Bono por tipo: " + p.getTipC() + " es de: " + bono + "\n";
            salida += "Puntos finales: " + puntosFinales + " pts.\n";
            salida += "Calificación: " + calificacion + "\n\n";
        }

        JOptionPane.showMessageDialog(null, salida);
    }

}
