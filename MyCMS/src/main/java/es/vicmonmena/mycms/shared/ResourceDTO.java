package es.vicmonmena.mycms.shared;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Vicente Montaño Mena
 * Clase que permite la transferencia de información referente a RECURSOS, entre
 * las capas cliente y servidor.
 */
@Entity
@Table(name = "TABLE_RESOURCE")
public class ResourceDTO implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5998110167821698366L;

	// Attributes
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private Long id;

	@Column(name = "NAME", nullable = false)
	private String name;
	
	@Column(name = "EMAIL", nullable = false)
	private String email;
	
	@Column(name = "Department")
	private String department;
	
	@Column(name = "Knowledges")
	private String knowledges;

	// Constructors
	
	public ResourceDTO(Long id, String name, String email, String department,
			String knowledges) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.department = department;
		this.knowledges = knowledges;
	}

	// Getter and Setter
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getKnowledges() {
		return knowledges;
	}

	public void setKnowledges(String knowledges) {
		this.knowledges = knowledges;
	}
	
	// Methods
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((department == null) ? 0 : department.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((knowledges == null) ? 0 : knowledges.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ResourceDTO other = (ResourceDTO) obj;
		if (department == null) {
			if (other.department != null)
				return false;
		} else if (!department.equals(other.department))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (knowledges == null) {
			if (other.knowledges != null)
				return false;
		} else if (!knowledges.equals(other.knowledges))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ResourceDTO [id=" + id + ", name=" + name + ", email=" + email
				+ ", department=" + department + ", knowledges=" + knowledges
				+ "]";
	}
	
}
