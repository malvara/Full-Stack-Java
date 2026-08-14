package SmartTask;

/**
 * Clase abstracta que crea el objeto fundamental de la aplicación, las tareas.
 */
public abstract class Tarea {
	/**
	 * Identificador de la tarea.
	 */
	protected int id;
	/**
	 * Nombre de la tarea.
	 */
	protected String nombre;
	/**
	 * Prioridad de la tarea.
	 * @return 1:Baja; 2:Media; 3:Alta.
	 */
	protected int prioridad;
	/**
	 * Indica si la tarea esta completada.
	 * @return True indica que esta completada y false lo contrario.
	 */
	protected boolean completado;
	/**
	 * Constructor de la tarea.
	 * @param id identificador de la tarea.
	 * @param nombre nombre de la tarea.
	 * @param prioridad 1:Baja; 2:Media; 3:Alta.
	 * @param completado True indica que esta completada y false lo contrario.
	 */
	public Tarea(int id,String nombre,int prioridad,boolean completado) {
		this.id=id;
		this.nombre=nombre;
		this.prioridad=prioridad;
		this.completado=completado;
	}
	/**
	 * Obtiene id de la tarea.
	 * @return id de la tarea.
	 */
	public int getId() {
		return this.id;
	}
	/**
	 * Obtiene nombre de la tarea.
	 * @return nombre de la tarea.
	 */
	public String getNombre() {
		return this.nombre;
	}
	/**
	 * Obtiene prioridad de la tarea.
	 * @return prioridad de la tarea.
	 */
	public int getPrioridad() {
		return this.prioridad;
	}
	/**
	 * Indica si la tarea fue completada.
	 * @return {@code true}: tarea completada; {@code false}:tarea no completada.
	 */
	public boolean isCompletado() {
		return this.completado;
	}
	/**
	 * Cambia estado de completación de la tarea.
	 * @param b {@code true} o {@code false}.
	 * @return campo completado con el valor b.
	 */
	public boolean setCompletado(boolean b) {
		return this.completado=b;
	}
	/**
	 * Escribe en pantalla los campos de una tarea.
	 */
	public void escribirTarea() {
		System.out.println("ID: "+getId()
		+" Nombre: "+getNombre()
		+" Prioridad: "+getPrioridad()
		+" Completada: "+isCompletado());
	}
	
}
