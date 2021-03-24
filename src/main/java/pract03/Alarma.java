package pract03;

import java.util.Date;

public class Alarma implements Comparable<Object> {
	
	private Date hora;
	private String id;
	
	/**
	 * Constructor de la clase
	 * @param idInicial identificador de la alarma 
	 * @param horaInicial hora programada para sonar
	 */
	public Alarma(String idInicial, Date horaInicial) {
		this.id = idInicial;
		this.hora = horaInicial;
	} 
	
	/**
	 * Establece una nueva hora
	 * @param valor
	 */
	public void setHora(Date valor) {
		this.hora = valor;
	}
	
	/**
	 * @return la hora programada para sonar
	 */
	public Date getHora() {
		return hora;
	}
	
	/**
	 * Eestablece un nuevo id para la alarma
	 * @param valor el nuevo id de la alarma
	 */
	public void setId(String valor) {
		this.id = valor;
	}
	
	/**
	 * @return el id de la alarma
	 */
	public String getId() {
		return id;
	}

	/**
	 * Si la alarma argumento va después de la indicada, devuelve 0.
	 * En caso contrario, devuelve 1.
	 */
	public int compareTo(Object a) {
		if (((Alarma) a).getHora().after(this.hora)) {
			return 0;
		}
		return 1;
	}
}
