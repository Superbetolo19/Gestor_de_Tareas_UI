
import excepcions.RepeatID;
import excepcions.RepeatTask;
class vertice{
    private vertice sig;
    private String nombre;
    private int duracion;
    private String id;

    //constructor
    vertice(String nombre, String id){
        this(nombre,id,(int)(Math.random()*30+1));
    }
    vertice(String nombre, String id, int duracion){
        this.nombre=nombre;
        this.id=id;
        this.duracion=duracion;
        this.sig=null;
    }
    //Metodos set -/- get
    public vertice getSig() {
        return sig;
    }
    public void setSig(vertice sig) {
        this.sig = sig;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public int getDuracion() {
        return duracion;
    }
    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public void mostrarProgreso(){
        int totalProgress = 100; // el progreso total que deseas mostrar
        int currentProgress = 0; // el progreso actual

        while (currentProgress <= totalProgress) {
            // calcular el porcentaje de progreso
            int percentage = (currentProgress * 100) / totalProgress;

            // crear la barra de progreso utilizando caracteres de texto
            StringBuilder progressBar = new StringBuilder("[");
            int i = 0;
            for (; i <= (percentage / 2); i++) {
                progressBar.append("=");
            }
            for (; i < 50; i++) {
                progressBar.append(" ");
            }
            progressBar.append("] " + percentage + "%");

            // imprimir la barra de progreso en la consola
            System.out.print("\r" + progressBar.toString());

            // incrementar el progreso
            currentProgress += 1;

            // pausa el hilo actual por un corto tiempo para que la barra de progreso no se actualice demasiado rápido
            try {
                Thread.sleep(10 * duracion);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

        // imprimir un salto de línea después de que se complete la barra de progreso
        System.out.println();

    }
}

//Segunda clase -------------------------------------------------------------------------------------
public class cola {
    private vertice inicio,fin;
    static private int nelementos = 0;
    
    //Copnstructor
    cola(){
        inicio=fin=null;
    }
    
    //Metodos de la cola 
    public boolean vacio(){
        return (nelementos==0);
    }

    public boolean nuevatarea(String nombre, String id) /*throws RepeatTask, RepeatID*/{
        /*if(compareName(nombre) == false && compareId(id) == false){*/
            vertice nuevo = new vertice(nombre,id);
            if(vacio()){
                inicio=fin= nuevo;
                nelementos++;
                return true;
                
            } else {
                fin.setSig(nuevo);
                fin=nuevo;
                nelementos++;
                return true;
            }
       // }

        
    }

    public boolean nuevatarea(String nombre, String id, int duracion) throws RepeatID, RepeatTask{
        System.out.println("Entra");
        if(compareName(nombre) == false && compareId(id) == false){
            vertice nuevo = new vertice(nombre, id, duracion);
            if(vacio()){
                inicio=fin= nuevo;
                nelementos++;
                return true;
            } else {
                fin.setSig(nuevo);
                fin=nuevo;
                nelementos++;
                return true;
            }
        }
        return false;

    }
    
public vertice getVertice(String nombre){
        vertice aux=inicio;
        if(!vacio()){
            while(nombre!=aux.getNombre()&& nombre!=aux.getId()){
                if (aux.getSig()==null) {
                    aux=null;
                    return aux;
                }
                aux=aux.getSig();
            }
        }
        else{
            aux=null;
            return aux;
        }
        return aux;  
    }

    public vertice eliminartarea(){
        if(vacio()){
            return null;
        } else {
            vertice aux=inicio;
            inicio=inicio.getSig();
            nelementos--;
            return aux;
        }
    }
    
    public String mostrartareas(){
        if(vacio()){
            return "No hay tareas en la cola";
        } else {
            vertice aux= inicio;
            String imprimir="Las tareas son: ";
            while(aux!=null){
                imprimir+=aux.getNombre()+"(ID: "+aux.getId()+", Duracion: "+String.valueOf(aux.getDuracion())+"), ";
                aux=aux.getSig();
            }
            return imprimir;
        }
    }

    //Metodos pa comparar
    public boolean compareName(String name) throws RepeatTask{
        if(!vacio()){
            vertice aux = inicio;
            while(aux != null){
                if(aux.getNombre() == name){
                    throw new RepeatTask("Ya existe una tarea con ese nombre, Ojito ahi 0.0");
                }
                aux = aux.getSig();
            }
        }
        return false;
    }

    public boolean compareId(String id) throws RepeatID{
        if(!vacio()){
            vertice aux = inicio;
            while(aux != null){
                if(aux.getId() == id){
                    throw new RepeatID("Ya existe una tarea con ese ID, PUt atention jsjs");
                }
                aux=aux.getSig();
            }
        }
        return false;
    }
}
