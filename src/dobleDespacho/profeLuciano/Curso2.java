package dobleDespacho.profeLuciano;



public class Curso2 extends Curso {


    public void recibirTitulo(Luciano luciano) {
        System.out.println("---------- Soy de segundo y ya programo como Grady Booch y Martin Fowler ");
        luciano.regalos(3);

    }

    @Override
    public void aceptar(Luciano luciano) {
        luciano.visitarImpartir(this);
    }
}

