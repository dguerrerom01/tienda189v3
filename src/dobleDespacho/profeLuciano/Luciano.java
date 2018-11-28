package dobleDespacho.profeLuciano;

public class Luciano {

    public void recibir(Curso curso) {
        System.out.println("!!!!!!!!!! Soy un profesor que saluda al curso igualmente ");
        // this.visitarImpartir((Curso1) curso); no se puede
       // if curso instanceof Curso1  no se debe
        curso.aceptar(this);
    }

    public void visitarImpartir(Curso1 curso1) {
        System.out.println("!!!!!!!!!! Soy el profesor de BBDD, que intentara enseñaros SQL en esta primera clase");
        curso1.sqlFunctions();
        System.out.println("!!!!!!!!!! Soy un profesor que se admira de vuestra capacidad de abstraccion");
        curso1.sqlProcedures();
    }

    public void visitarImpartir(Curso2 curso2) {

        curso2.recibirTitulo(this);
    }

    public void regalos(int jamones) {
        System.out.println("!!!!!!!!!! Soy el profesor Luciano al que me regalaron " + jamones + " jamones");
    }

    public void agradecerVisita() {
        System.out.println("!!!!!!!!!! Soy el profesor Luciano que agradece vuestra asistencia");
    }


}
