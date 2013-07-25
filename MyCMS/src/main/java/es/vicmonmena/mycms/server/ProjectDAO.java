package es.vicmonmena.mycms.server;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import es.vicmonmena.mycms.shared.ProjectDTO;

/**
 * @author Vicente Montaño Mena
 * Clase para la persistencia de información referente a Recursos en BBDD.
 */
@Repository("projectDAO")
public class ProjectDAO extends AbstractHibernateJpaDAO<Long, ProjectDTO>{

	@PersistenceContext(unitName = "MyPUnit")
	EntityManager entityManager;

	@Override
	protected EntityManager getEntityManager() {
		return entityManager;
	}
}
