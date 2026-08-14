package SmartTask;

/**
 * Clase hija de Tarea, se creo para apoyar el método AgregarTarea(),
 * de manera de mantener la clase padre abstracta.
 */
public class TareaComun extends Tarea{
	/**
	 * Constructor de la clase.
	 * @param id identificador de la tarea.
	 * @param nombre nombre de la tarea.
	 * @param prioridad 1:Baja; 2;Media; 3:Alta;
	 * @param completado {@code True}:tarea completada; {@code false}:Tarea no completada.
	 */
	public TareaComun(int id,String nombre,int prioridad,boolean completado) {
		super(id,nombre,prioridad,completado);
	}
}