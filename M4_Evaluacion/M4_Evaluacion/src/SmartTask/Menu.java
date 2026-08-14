package SmartTask;

/**
 * Permite implementar un menú.
 */
public class Menu {
	/**
	 * Constructor vacío.
	 */
	public Menu() {
		return;
	}
	/**
	 * Método que muestra opciones del menú, para que sea 
	 * seleccionado por el usuario.
	 */
	public static void menuOpciones() {
		System.out.println("==============================");
		System.out.println("====== SMART TASK: MENÚ ======");
		System.out.println("==============================");
		System.out.println("1: Agregar Tarea");
		System.out.println("2: Listar todo");
		System.out.println("3: Listar Tareas Activas");
		System.out.println("4: Listar Tareas Completadas");
		System.out.println("5: Marcar Tarea Como Completada");
		System.out.println("6: Eliminar Tarea por ID");
		System.out.println("0: Salir");
		System.out.println("===============================");
		System.out.println();
		System.out.println("Ingrese su opción aquí: ");
	}
}