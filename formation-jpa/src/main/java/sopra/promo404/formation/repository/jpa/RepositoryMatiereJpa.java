package sopra.promo404.formation.repository.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import sopra.promo404.formation.model.Matiere;
import sopra.promo404.formation.repository.IRepositoryMatiere;

@Repository
@Transactional
public class RepositoryMatiereJpa implements IRepositoryMatiere {

	@PersistenceContext
	private EntityManager em;

	@Override
	@Transactional(readOnly=true)
	public List<Matiere> findAll() {
		return em.createQuery("from Matiere", Matiere.class).getResultList();
	}

	@Override
	@Transactional(readOnly=true)
	public Matiere findById(Long id) {
		return em.find(Matiere.class, id);
	}

	@Override
	public Matiere save(Matiere entity) {
		if (entity.getId() == null) {
			em.persist(entity);
		} else {
			entity = em.merge(entity);
		}

		return entity;
	}

	@Override
	public void delete(Matiere entity) {
		em.remove(em.merge(entity));
	}

	@Override
	public void deleteById(Long id) {
		em.remove(em.find(Matiere.class, id));
	}

	@Override
	@Transactional(readOnly=true)
	public Matiere findByIdWithFormateurs(Long id) {
		TypedQuery<Matiere> query = em.createQuery("select m from Matiere m join fetch m.formateurs where id = :id",
				Matiere.class);
		query.setParameter("id", id);

		return query.getSingleResult();

	}
}
