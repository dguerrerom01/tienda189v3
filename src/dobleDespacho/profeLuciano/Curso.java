package dobleDespacho.profeLuciano;

import dobleDespacho.v2.basic.Recepcionista;

public abstract  class Curso {

    public void saludar(){
        System.out.println("?????????? Soy un curso que da los buenos días al entrar");
    }

    public abstract void aceptar(Luciano luciano);

    public void despedirse() {
        System.out.println("?????????? Soy un curso que se despide al sonar el timbre");
    }

}


