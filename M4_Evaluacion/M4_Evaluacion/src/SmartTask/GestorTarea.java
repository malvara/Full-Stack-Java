package SmartTask;

import Principal.Normas;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Clase que contiene los métodos usados por la aplicación.
 */
public class GestorTarea implements Normas{
	/**
	 * Constructor vacio de la clase.
	 */
	public GestorTarea(){
		return;
	}
	/**
	 * Crea y agrega una tarea a una lista de tareas.
	 * Incluye un validador de la prioridad (que ste en el rango 1-3). 
	 * Aqui se puede ver el uso de herencia.
	 * @param id identificador de la tarea.
	 * @return tarea nueva dentro de lista de tareas o NULL si prioridad esta fuera de rango. 
	 */
	public static Tarea agregarTarea(int id) {
		Scanner scanner=new Scanner(System.in);
		System.out.println("Ingrese el nombre de la tarea: ");
		String nombre=scanner.nextLine();
		System.out.println("1:Baja  2:Media  3:Alta");
		System.out.println("Ingrese la prioridad de la tarea: ");
		int prioridad=scanner.nextInt();
		scanner.nextLine();
		if (prioridad<1 || prioridad>3) {
			System.out.println("La prioridad esta fuera de rango.");
			return null;
		}else{
			Tarea nueva=new TareaComun(id,nombre,prioridad,false);
			return nueva;
		}
	}
	/**
	 * Elimina tarea de ID específico de la lista de tareas.
	 * Devuelve mensaje de error si ID no pertenece a la lista de tareas.
	 * @param id identificador de tarea.
	 * @param listaTareas lista de objetos Tarea.
	 */
	public static void eliminarTareaPorId(int id,ArrayList<Tarea> listaTareas){
		for(int i=0;i<listaTareas.size();i++) {
			if(listaTareas.get(i).id==id) {
				listaTareas.remove(i);
				mensaje("La tarea con ID = "+id+" fue eliminada exitosamente.");
				return;
			}
		}
		System.out.println("ERROR: El ID ingresado no existe.");
	}
	/**
	 * Construye lista de tareas activas.
	 * @param listaTareas lista de objetos Tarea.
	 * @return lista de tareas activas o NULL si no se encuentran tareas activas.
	 */
	public static ArrayList<Tarea> listarActivas(ArrayList<Tarea> listaTareas) {
		ArrayList<Tarea> listaTareasActivas=new ArrayList<>();
		for(Tarea t:listaTareas) {
			if(!t.isCompletado()) {
				listaTareasActivas.add(t);
			}
		}
		return listaTareasActivas.isEmpty() ? null : listaTareasActivas;
	}	
	/**
	 * Construye lista de tareas completadas.
	 * @param listaTareas lista de objeto Tarea.
	 * @return lista de tareas completadas o NULL si no se encuentran tareas completadas.
	 */
	public static ArrayList<Tarea> listarCompletadas(ArrayList<Tarea> listaTareas) {
		ArrayList<Tarea> listaTareasCompletadas=new ArrayList<>();
		for(Tarea t:listaTareas) {
			if(t.isCompletado()) {
				listaTareasCompletadas.add(t);
			}
		}
		return listaTareasCompletadas.isEmpty() ? null : listaTareasCompletadas;
	}
	/**
	 * Método que cambia campo completado de una tarea con ID específico.
	 * @param id identificador de tarea.
	 * @param listaTareas lista de ojetos Tarea.
	 */
	public static void marcarComoCompletada(int id,ArrayList<Tarea> listaTareas) {
		System.out.println();
		for(Tarea t:listaTareas) {
			if(t.getId()==id) {
				t.setCompletado(true);
				System.out.println("La tarea con ID = "+id+" se ha marcado como completada.");
				System.out.println();
				return;
			}
		}
		System.out.println("El ID no existe.");
		System.out.println();
	}
	/**
	 * Método que sobreescribe de método declarado en la interface Normas.
	 * Método que lee número del teclado y captura errores de ingreso.
	 * Es un ejemplo de polimorfismo.
	 */
	@Override
	public int leerNumero() {
		int opcion=99;
		Scanner scanner=new Scanner(System.in);
		try{
			opcion=scanner.nextInt();
			scanner.nextLine();
			return opcion;
		}catch(Exception e){
			System.out.println();
			scanner.nextLine();
			return opcion;
		}
	}
	/**
	 * Método que escribe mensaje en pantalla.
	 * @param info contenido del mensaje.
	 */
	public static void mensaje(String info) {
		System.out.println();
		System.out.println(info);
		System.out.println();
	}
	/**
	 * Método que sobreescribe de método declarado en la interface Normas.
	 * ndica si una lista de objetos Tarea no tiene elementos.
	 * Es un ejemplo de polimorfismo.
	 * @return {@code true}:lista vacia; {@code false}:lista no vacía.
	 */
	@Override
	public boolean listaVacia(ArrayList<Tarea> lista) {
		boolean estaVacia=false;
		if(lista.size()>0) {
			estaVacia=true;
		}
		return estaVacia;
	}
}