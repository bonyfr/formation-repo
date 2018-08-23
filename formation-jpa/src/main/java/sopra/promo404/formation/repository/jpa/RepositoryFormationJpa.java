package sopra.promo404.formation.repository.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import sopra.promo404.formation.model.Formation;
import sopra.promo404.formation.model.FormationId;
import sopra.promo404.formation.repository.IRepositoryFormation;

@Repository
@Transactional
public class RepositoryFormationJpa implements IRepositoryFormation {

	@PersistenceContext
	private EntityManager em;

	@Override
	@Transactional(readOnly=true)
	public List<Formation> findAll() {
		return em.createQuery("from Formation", Formation.class).getResultList();
	}

	@Override
	@Transactional(readOnly=true)
	public Formation findById(FormationId id) {
		return em.find(Formation.class, id);
	}

	@Override
	public Formation save(Formation entity) {
		if (em.find(Formation.class, entity.getId()) == null) {
			em.persist(entity);
		} else {
			entity = em.merge(entity);
		}

		return entity;
	}

	@Override
	public void delete(Formation entity) {
		em.remove(em.merge(entity));
	}

	@Override
	public void deleteById(FormationId id) {
		em.remove(em.find(Formation.class, id));
	}

	@Override
	@Transactional(readOnly=true)
	public Formation findByClientAndPromotion(String client, String promotion) {
		TypedQuery<Formation> query = em.createQuery(
				"select f from Formation f where f.id.client = ?1 and f.id.promotion = ?2", Formation.class);

		query.setParameter(1, client);
		query.setParameter(2, promotion);

		return query.getSingleResult();
	}

	@Override
	@Transactional(readOnly=true)
	public List<Formation> findAllByMatiere(String nom) {
		TypedQuery<Formation> query = em.createQuery("select f from Formation f join f.matieres m where m.nom = :nom",
				Formation.class);

		query.setParameter("nom", nom);

		return query.getResultList();
	}
}
