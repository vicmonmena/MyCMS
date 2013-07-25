package es.vicmonmena.mycms.server;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import es.vicmonmena.mycms.shared.ResourceDTO;

/**
 * @author Vicente Montaño Mena
 * Clase para la persistencia de información referente a Recursos en BBDD.
 */
@Repository("resourceDAO")
public class ResourceDAO extends AbstractHibernateJpaDAO<Long, ResourceDTO> {

	@PersistenceContext(unitName = "MyPUnit")
	EntityManager entityManager;

	@Override
	protected EntityManager getEntityManager() {
		return entityManager;
	}
}
