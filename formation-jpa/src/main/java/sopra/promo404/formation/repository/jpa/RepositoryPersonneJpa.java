package sopra.promo404.formation.repository.jpa;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import sopra.promo404.formation.model.Civilite;
import sopra.promo404.formation.model.Eleve;
import sopra.promo404.formation.model.Formateur;
import sopra.promo404.formation.model.Personne;
import sopra.promo404.formation.repository.IRepositoryOrdinateur;
import sopra.promo404.formation.repository.IRepositoryPersonne;

@Repository
@Transactional
public class RepositoryPersonneJpa implements IRepositoryPersonne {

	@PersistenceContext
	private EntityManager em;

	@Autowired
	private IRepositoryOrdinateur daoOrdinateur;

	@Override
	@Transactional(readOnly=true)
	public List<Personne> findAll() {
		return em.createQuery("from Personne", Personne.class).getResultList();
	}

	@Override
	@Transactional(readOnly=true)
	public Personne findById(Long id) {
		return em.find(Personne.class, id);
	}

	@Override
	public Personne save(Personne entity) {
		if (entity.getId() == null) {
			em.persist(entity);
		} else {
			entity = em.merge(entity);
		}

		return entity;
	}

	@Override
	public void delete(Personne entity) {
		if (entity instanceof Eleve) {
			Eleve eleve = (Eleve) entity;
			if (eleve.getOrdinateur() != null) {
				daoOrdinateur.delete(eleve.getOrdinateur());
			}
		}

		em.remove(em.merge(entity));
	}

	@Override
	public void deleteById(Long id) {
		em.remove(em.find(Personne.class, id));
	}

	@Override
	@Transactional(readOnly=true)
	public List<Eleve> findAllEleveByCivilite(Civilite civilite) {
		return em.createNamedQuery("Eleve.findAllByCivilite", Eleve.class).getResultList();
	}

	@Override
	@Transactional(readOnly=true)
	public List<Eleve> findAllEleveByVille(String ville) {
		TypedQuery<Eleve> query = em.createQuery("select e from Eleve e where upper(e.adresse.ville) = ?1",
				Eleve.class);

		query.setParameter(1, ville.toUpperCase());

		return query.getResultList();
	}

	@Override
	@Transactional(readOnly=true)
	public List<Eleve> findAllEleveByDtNaissance(Date dt) {
		TypedQuery<Eleve> query = em.createQuery("select e from Eleve e where e.dtNaissance = :maDate", Eleve.class);

		query.setParameter("maDate", dt, TemporalType.DATE);

		return query.getResultList();
	}

	@Override
	@Transactional(readOnly=true)
	public Formateur findFormateurByNomAndPrenom(String nom, String prenom) {
		TypedQuery<Formateur> query = em
				.createQuery("select f from Formateur f where f.nom = :nom and f.prenom = :prenom", Formateur.class);

		query.setParameter("nom", nom);
		query.setParameter("prenom", prenom);

		return query.getSingleResult();
	}

	@Override
	@Transactional(readOnly=true)
	public Eleve findEleveByOrdinateur(String code) {
		// Query query = em.createQuery("select o.eleve from Ordinateur o where o.code =
		// :codeOrdi", Eleve.class);

		// Query query = em.createQuery("select e from Ordinateur o join o.eleve e where
		// o.code = :codeOrdi", Eleve.class);

		// Query query = em.createQuery("select e from Eleve e join e.ordinateur o where
		// o.code = :codeOrdi", Eleve.class);

		Query query = em.createQuery("select e from Eleve e where e.ordinateur.code = :codeOrdi", Eleve.class);

		query.setParameter("codeOrdi", code);

		return (Eleve) query.getSingleResult();

	}

	@Override
	@Transactional(readOnly=true)
	public List<Formateur> findAllFormateurByFormation(String client, String promotion) {
		TypedQuery<Formateur> query = em
				.createQuery(
						"select distinct f from Formation form " + "join form.matieres m " + "join m.formateurs f "
								+ "where form.id.client = :client " + "and form.id.promotion = :promotion",
						Formateur.class);

		query.setParameter("client", client);

		query.setParameter("promotion", promotion);

		return query.getResultList();
	}
}
