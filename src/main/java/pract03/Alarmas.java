package pract03;

import java.util.ArrayList;
import java.util.Date;
import java.util.PriorityQueue;

public class Alarmas {
	
	private AlarmasState state;
	private final static int INTERVALO_SONANDO = 5000; // milisegundos
	private PriorityQueue<Alarma> alarmasActivas = new PriorityQueue<Alarma>();
	private ArrayList<Alarma> alarmasDesactivadas = new ArrayList<Alarma>();
	
	/**
	 * Constructor de la clase, inicializa el estado.
	 */
	public Alarmas() {
		state = AlarmasState.init(this);
	}
	
	/**
	 * No ponemos el atributo publico por seguridad, para que no se pueda modificar.
	 * @return el intervalo que debe estar sonando la alarma en ms
	 */
	public int getIntervaloSonando() {
		return INTERVALO_SONANDO;
	}
	
	/**
	 * Anhade una nueva alarma activa
	 * @param a
	 * @return
	 */
	public boolean anhadeAlarma(Alarma a) {
		if (existeAlarmaActiva(a)) {
			return false;
		}
		alarmasActivas.add(a);
		return true;
	}
	
	/**
	 * Elimina una alarma, tanto si esta activa como desactivada
	 * @param a alarma a anhadir
	 * @return true si se ha anhadido o false si ya existia
	 */
	public boolean eliminaAlarma(Alarma a) {
		if (existeAlarmaActiva(a)) {
			alarmasActivas.remove(a);
			return true;
		} else if (existeAlarmaDesactivada(a)) {
			alarmasDesactivadas.remove(a);
			return true;
		}
		return false;
	}
	
	/**
	 * Indica cual es la alarma con la hora mas proxima.
	 * @return alarma con la hora mas proxima
	 */
	public Alarma alarmaMasProxima() {
		return alarmasActivas.peek(); 
	}
	
	/**
	 * Pasa la alarma de activadas a desactivadas.
	 * @param a alarma a desactivar
	 */
	public void desactivaAlarma(Alarma a) {
		if (existeAlarmaActiva(a)) {
			alarmasActivas.remove(a);
			alarmasDesactivadas.add(a);
		}
	}
	
	/**
	 * Pasa la alarma de desactivadas a activadas.
	 * @param a alarma a activadas
	 */
	public void activaAlarma(Alarma a) {
		if (existeAlarmaDesactivada(a)) {
			alarmasDesactivadas.remove(a);
			alarmasActivas.add(a);
		}
	}
	
	/**
	 * Muestra un mensaje indicando que la alarma esta sonando.
	 */
	public void activarMelodia() {
		System.out.println("ALARMA SONANDO");
	}
	
	/**
	 * Muestra un mensaje indicando que la alarma deja de sonar.
	 */
	public void desactivarMelodia() {
		System.out.println("SONIDO APAGADO");
	}

	/**
	 * Cambia el estado
	 * @param value nuevo estado
	 */
	public void setState(AlarmasState value) {
		this.state = value;
	}
	
	/**
	 * Senhal de apagar la alarma
	 */
	public void apagar() {
		state.apagar(this);
	}
	
	/**
	 * Senhal de desactivar una alarma
	 * @param id identificador de la alarma a desactivar
	 */
	public void alarmaOff(String id) {
		state.alarmaOff(id, this);
	}
	
	/**
	 * Senhal de activar una alarma
	 * @param id identificador de la alarma a activar
	 */
	public void alarmaOn(String id) {
		state.alarmaOn(id, this);
	}
	
	/**
	 * Elimina una alarma del sistema
	 * @param id identificador de la alarma
	 */
	public void borraAlarma(String id) {
		state.borraAlarma(id, this);
	}
	
	/**
	 * Anhade una nueva alarma al sistema ya programada para sonar 
	 * @param id identificador de la alarma
	 * @param hora hora a la que debe sonar
	 */
	public void nuevaAlarma(String id, Date hora) {
		state.nuevaAlarma(id, hora, this);
	}
	
	/**
	 * Los siguientes metodos los hemos separados para el metodo "eliminaAlarma",
	 * ya que podriamos 1)cazar la excepcion de la lista/cola en la que no estuviese la alarma,
	 * 2)hacer un metodo "existeAlarma" general, pero buscando en "eliminaAlarma" en que
	 * lista/cola se encontraba o 3)hacer metodos separados. Nos hemos decantado por la 
	 * 3 opcion, ya que es la que menos gestion conlleva y mejor rendimiento creemos que
	 * proporciona.
	 */
	
	/**
	 * Comprueba si una alarma existe y esta activa
	 * @param a alarma a buscar
	 * @return true si la encuentra o false en caso contrario
	 */
	private boolean existeAlarmaActiva(Alarma a) {
		for (Alarma alar: alarmasActivas) {
			if (alar.getHora().equals(a.getHora())) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Comprueba si una alarma existe y esta desactivada
	 * @param a alarma a buscar
	 * @return true si la encuentra o false en caso contrario
	 */
	private boolean existeAlarmaDesactivada(Alarma a) {
		for (Alarma alar: alarmasDesactivadas) {
			if (alar.getHora().equals(a.getHora())) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Comprueba si una alarma existe dado su ID
	 * @param id identificador de la alarma
	 * @return la alarma buscada o false si no la encuentra
	 */
	public Alarma buscaAlarmaByID(String id) {
		for (Alarma alar: alarmasActivas) {
			if (alar.getId().equals(id)) {
				return alar;
			}
		}
		for (Alarma alar: alarmasDesactivadas) {
			if (alar.getId().equals(id)) {
				return alar;
			}
		}
		return null;
	}
	
	/**
	 * @return el numero de alarmas activas
	 */
	public int getAlarmasActivasSize() {
		return alarmasActivas.size();		
	}
	
}