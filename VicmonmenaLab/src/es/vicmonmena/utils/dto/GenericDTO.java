package es.vicmonmena.utils.dto;

import java.util.Calendar;

/**
 * Clase genérica de presentación de registros de BD en la capa vista
 * <br/>
 * <br/>
 * <b>Creado el: 08/02/2012
 * @author Vicente Montaño Mena
 * <br/>
 * Twitter: @vicmonmena
 * <br/>
 * GTalk: vicmonmena@gmail.com
 * */
public class GenericDTO {

	private int id;
	private String name;
	private String description;
	private Calendar date;
	private boolean active;
	
	/**
	 * Constructor simple para mostrar listados de elementos
	 * 
	 * @param id - identificador del elemento
	 * @param name - nombre descriptivo del elemento
	 *  
	 * */
	public GenericDTO(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	
	/**
	 * Construye n objeto de presentación
	 * 
	 * @param id - identificador del elemento para visualizarlo en listas
	 * @param name - nombre descriptivo del elemento
	 * @param description - descripción del elemento
	 * @param date - fecha representativa del objeto (de creción, de actualización, etc.)
	 * @param active - a true indica que el elemento está activo
	 * 
	 * @return GenericDTO
	 * */
	public GenericDTO(int id, String name, String description, Calendar date,
			boolean active) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.date = date;
		this.active = active;
	}
	
	/**
	 * 
	 * */
	public String getDescripcionLarga() {
		return "(" + id + ") " + name + ": " + description;
	}
	
	/**
	 * 
	 * */
	public String getDescripcionCorta() {
		return "(" + id + ") " + name;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return name;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the date
	 */
	public Calendar getDate() {
		return date;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(Calendar date) {
		this.date = date;
	}

	/**
	 * @return the active
	 */
	public boolean isActive() {
		return active;
	}

	/**
	 * @param active the active to set
	 */
	public void setActive(boolean active) {
		this.active = active;
	}
	
	
}
