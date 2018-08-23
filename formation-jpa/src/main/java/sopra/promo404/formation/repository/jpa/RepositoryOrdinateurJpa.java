package sopra.promo404.formation.repository.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import sopra.promo404.formation.model.Ordinateur;
import sopra.promo404.formation.repository.IRepositoryOrdinateur;

@Repository
@Transactional
public class RepositoryOrdinateurJpa implements IRepositoryOrdinateur {

	@PersistenceContext
	private EntityManager em;

	@Override
	@Transactional(readOnly=true)
	public List<Ordinateur> findAll() {
		return em.createQuery("from Ordinateur", Ordinateur.class).getResultList();
	}

	@Override
	@Transactional(readOnly=true)
	public Ordinateur findById(String id) {
		return em.find(Ordinateur.class, id);
	}

	@Override
	public Ordinateur save(Ordinateur entity) {
		if (em.find(Ordinateur.class, entity.getCode()) == null) {
			em.persist(entity);
		} else {
			entity = em.merge(entity);
		}

		return entity;
	}

	@Override
	public void delete(Ordinateur entity) {
		em.remove(em.merge(entity));
	}

	@Override
	public void deleteById(String id) {
		em.remove(em.find(Ordinateur.class, id));
	}

	@Override
	@Transactional(readOnly=true)
	public List<Ordinateur> findAllBySsd(boolean ssd) {
		TypedQuery<Ordinateur> query = em.createQuery("select o from Ordinateur o where o.ssd = :paramSsd",
				Ordinateur.class);

		query.setParameter("paramSsd", ssd);

		return query.getResultList();
	}

	@Override
	@Transactional(readOnly=true)
	public List<Ordinateur> findAllStartingByCode(String code) {
		TypedQuery<Ordinateur> query = em.createQuery("select o from Ordinateur o where o.code like :recherche",
				Ordinateur.class);

		query.setParameter("recherche", code + "%");

		return query.getResultList();
	}
}
