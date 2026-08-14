package Principal;

import java.util.ArrayList;

import SmartTask.Tarea;

/**
 * Permite ver funcionalidad de una interface.
 */
public interface Normas {
	/**
	 * Revisa si una lista de objetos esta vacía.
	 * @param lista lista de objetos.
	 * @return devuelve {@code true} si esta vacia y {@code false} en caso contrario.
	 */
	boolean listaVacia(ArrayList<Tarea> lista);
	/**
	 * Lee número del teclado.
	 * @return número ingresado por el usuario.
	 */
	int leerNumero();
}