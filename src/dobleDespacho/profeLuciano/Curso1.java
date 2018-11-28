package dobleDespacho.profeLuciano;

public class Curso1 extends Curso{

    public void sqlFunctions() {
        System.out.println("++++++++++ Aprendo a diseñar funciones");
    }

    public void sqlProcedures() {
        System.out.println("++++++++++ Aprendo a manejar cursores");
    }

    @Override
    public void aceptar(Luciano luciano) {
        luciano.visitarImpartir(this);
    }
}
