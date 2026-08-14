package Principal;

import java.util.Scanner;
import java.util.ArrayList;

import SmartTask.GestorTarea;
import SmartTask.Menu;
import SmartTask.Tarea;

/**
 * Clase principal, con la cual interactúa el usuario.
 */
public class Main {
	/**
	 * Constructor vacío de la clase.
	 */
	private Main() {
		return;
	}
	/**
	 * Código principal de la aplicación.
	 * Define y guarda la lista de tareas e incluye un bloque condicional, 
	 * en base a un menú con opciones, identificadas por un número.
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GestorTarea gestor=new GestorTarea();
		Scanner scanner=new Scanner(System.in);
		ArrayList<Tarea> listaTareas;
		listaTareas=new ArrayList<>();
		int siguienteId=1;
		boolean exit=true;
		int opcion=0;
		do {
			try {
				Menu.menuOpciones();
				opcion=gestor.leerNumero();
				switch (opcion) {
				case 1:
					Tarea tarea=GestorTarea.agregarTarea(siguienteId);
					if (tarea != null) {
						GestorTarea.mensaje("La tarea se creo exitosamente.");
						siguienteId++;
						listaTareas.add(tarea);
					}else {
						GestorTarea.mensaje("La tarea no fue creada.");
					}
					break;
				case 2:
					if(gestor.listaVacia(listaTareas)) {
						for(Tarea t:listaTareas) {
							t.escribirTarea();
						}
						System.out.println();
					}else {
						GestorTarea.mensaje("No hay tareas registradas.");
					}
					break;
				case 3:
					if(gestor.listaVacia(listaTareas)) {
						ArrayList<Tarea> a=GestorTarea.listarActivas(listaTareas);
						if(a!=null) {
							for(Tarea t:a) {
								t.escribirTarea();
							}
							System.out.println();
							}else {
								GestorTarea.mensaje("No se registran tareas activas.");
							}
					}else {
						GestorTarea.mensaje("No hay tareas registradas.");
					}
					break;
				case 4:
					if(gestor.listaVacia(listaTareas)) {
						ArrayList<Tarea> b=GestorTarea.listarCompletadas(listaTareas);
						if(b!=null) {
							for(Tarea t:b) {
								t.escribirTarea();
								}
							System.out.println();
						}else {
							GestorTarea.mensaje("No se registran tareas completadas.");
						}
					}else {
						GestorTarea.mensaje("No hay tareas registradas.");
					}
					break;
				case 5:
					try {
						if(gestor.listaVacia(listaTareas)) {
							GestorTarea.mensaje("Ingrese el ID de la tarea que desea marcar como completada.");
							int id=scanner.nextInt();
							scanner.nextLine();
							GestorTarea.marcarComoCompletada(id, listaTareas);
						}else {
							GestorTarea.mensaje("No hay tareas registradas.");
						}
					}
					catch(Exception e) {
						GestorTarea.mensaje(("El ID ingresado no es válido."));
						break;
					}
					break;
				case 6:
					try {
						if(gestor.listaVacia(listaTareas)) {
							GestorTarea.mensaje("Ingrese el ID de la tarea que desea eliminar.");
							int id1=scanner.nextInt();
							scanner.nextLine();
							GestorTarea.eliminarTareaPorId(id1, listaTareas);
						}else {
							GestorTarea.mensaje("No hay tareas registradas.");
						}
					}
					catch(Exception e) {
						GestorTarea.mensaje(("El ID ingresado no es válido."));
						break;
					}
					break;
				case 0:
					System.out.println("===========================================");
					GestorTarea.mensaje("Gracias por usar SMART TASK. Hasta pronto!!");
					exit=false;
					break;
				default:
					GestorTarea.mensaje("Opción inválida.");
				break;
				}
			}
			catch (Exception e) {
				GestorTarea.mensaje("Error desconocido.");
			}
		}while (exit);
	}
}