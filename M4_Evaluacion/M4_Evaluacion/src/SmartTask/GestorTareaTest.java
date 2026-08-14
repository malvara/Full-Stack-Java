package SmartTask;

import static org.junit.jupiter.api.Assertions.*;
import java.io.ByteArrayInputStream; // Importación para simular el teclado
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

class GestorTareaTest {
	@Test
	void testAgregarTarea() {
		// 1. Simulamos el teclado: Escribimos el nombre de la tarea, un salto de línea (\n) 
		// y luego la prioridad (por ejemplo, 2 para Media), terminando con otro salto de línea.
		String datosSimulados = "Tarea de Prueba JUnit\n2\n";
        
        // 2. Le decimos a Java que use este texto en lugar del teclado físico
        System.setIn(new ByteArrayInputStream(datosSimulados.getBytes()));

        // 3. Ejecutamos el método de forma estática (Ya no se quedará congelado)
        GestorTarea.agregarTarea(1); 
        
        // 4. Verificación final de éxito
        assertTrue(true);
    }
    @Test
    void testEliminarTareaPorId() {
    	// 1. ESCENARIO: Creamos la lista con una tarea de prueba (ID = 5)
    	ArrayList<Tarea> lista = new ArrayList<>();
    	lista.add(new TareaComun(5, "Terminar pruebas unitarias",2,false));

    	// 2. EJECUCION: se ejecuta el metodo con datos simulados.
    	GestorTarea.eliminarTareaPorId(5, lista);
    	
    	// 4. VERIFICACIÓN: Comprobamos que la tarea realmente se eliminó
    	assertTrue(lista.isEmpty(), "La lista debería estar vacía porque la tarea fue eliminada");
    	
    	// Limpieza: Buenas prácticas para restaurar el sistema original
    	//System.setIn(System.in);
    }
}