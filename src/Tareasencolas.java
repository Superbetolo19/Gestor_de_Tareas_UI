import excepcions.RepeatID;
import excepcions.RepeatTask;
import java.util.Scanner;

public class Tareasencolas {

    public static void main(String[] args) throws RepeatTask, RepeatID{
        Scanner scan= new Scanner(System.in);
        boolean salir = false;
        String opcion,nombre, ID;
        cola cola = new cola();
        vertice aux;
        cola.nuevatarea("B", "03");
         System.out.println( cola.mostrartareas());
            cola.nuevatarea("A", "01");


        do{
            System.out.println("------ GESTOR DE TAREAS ------");
            System.out.println("1.- Agregar Tarea");
            System.out.println("2.- Ejecutar Tareas");
            System.out.println("3.- Cancelar Tarea");
            System.out.println("4.- Salir");
            opcion=scan.nextLine();
            switch (opcion) {
                case "1":
                    System.out.println("Ingrese el nombre de la tarea");
                    nombre= scan.nextLine();
                    System.out.println("Ingrese ID de la tarea");
                    ID= scan.nextLine();
                    cola.nuevatarea(nombre, ID);
                    break;
                case "2":
                    while (!cola.vacio()) {
                        aux=cola.eliminartarea();    
                        System.out.println("Ejecutando tarea: "+aux.getNombre());
                        aux.mostrarProgreso();
                    }
                    System.out.println("Tareas ejecutadas");
                    
                    break;
                case "3":
                    cola.eliminartarea();
                    break;
                case "4":
                    salir=true;
                default:
                    break;
            }
            
        }while(salir != true);

        scan.close();
    }
    
}
