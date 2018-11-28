package dobleDespacho.profeLuciano;

import java.util.Random;

public class ImpartirClase {
    private Luciano luciano;

    private ImpartirClase(){
        luciano = new Luciano();
    }

    public static void main(String[] args) {
        new ImpartirClase().simularEscenario();
    }

    private void simularEscenario() {
        Random random = new Random(System.currentTimeMillis());
        for (int i = 0; i < 3; i++) {
            Curso curso = null;

            if (random.nextInt(2) == 0) {
                curso = new Curso1();
            } else {
                curso = new Curso2();
            }
            this.simularEscenario(curso);
        }
    }

    private void simularEscenario(Curso curso) {
        curso.saludar();
        // Ahora luciano tiene que conocer al curso para impartir clase
        luciano.recibir(curso);
        curso.despedirse();
        luciano.agradecerVisita();
        System.out.println("... ");
        System.out.println("... pasa el tiempo!");
        System.out.println("... ");
    }

}


