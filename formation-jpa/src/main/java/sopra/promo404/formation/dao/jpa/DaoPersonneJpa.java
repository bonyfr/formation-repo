package sopra.promo404.formation.dao.jpa;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TemporalType;

import sopra.promo404.formation.Application;
import sopra.promo404.formation.dao.IDaoPersonne;
import sopra.promo404.formation.model.Civilite;
import sopra.promo404.formation.model.Eleve;
import sopra.promo404.formation.model.Formateur;
import sopra.promo404.formation.model.Personne;

public class DaoPersonneJpa implements IDaoPersonne {

	@Override
	public List<Personne> findAll() {
		List<Personne> liste = new ArrayList<>();

		EntityManager em = null;
		EntityTransaction tx = null;

		try {
			em = Application.getInstance().getEmf().createEntityManager();
			tx = em.getTransaction();
			tx.begin();

			Query query = em.createQuery("from Personne", Personne.class);
			liste = query.getResultList();

			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			if (tx != null) {
				tx.rollback();
			}
		} finally {
			if (em != null) {
				em.close();
			}
		}

		return liste;
	}

	@Override
	public Personne findById(Long id) {
		Personne entity = null;

		EntityManager em = null;
		EntityTransaction tx = null;

		try {
			em = Application.getInstance().getEmf().createEntityManager();
			tx = em.getTransaction();
			tx.begin();

			entity = em.find(Personne.class, id);

			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			if (tx != null) {
				tx.rollback();
			}
		} finally {
			if (em != null) {
				em.close();
			}
		}

		return entity;
	}

	@Override
	public Personne save(Personne entity) {
		EntityManager em = null;
		EntityTransaction tx = null;

		try {
			em = Application.getInstance().getEmf().createEntityManager();
			tx = em.getTransaction();
			tx.begin();

			if (entity.getId() == null) {
				em.persist(entity);
			} else {
				entity = em.merge(entity);
			}

			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			if (tx != null) {
				tx.rollback();
			}
		} finally {
			if (em != null) {
				em.close();
			}
		}

		return entity;
	}

	@Override
	public void delete(Personne entity) {
		EntityManager em = null;
		EntityTransaction tx = null;

		try {
			em = Application.getInstance().getEmf().createEntityManager();
			tx = em.getTransaction();
			tx.begin();

			em.remove(em.merge(entity));

			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			if (tx != null) {
				tx.rollback();
			}
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	@Override
	public void deleteById(Long id) {
		EntityManager em = null;
		EntityTransaction tx = null;

		try {
			em = Application.getInstance().getEmf().createEntityManager();
			tx = em.getTransaction();
			tx.begin();

			em.remove(em.find(Personne.class, id));

			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			if (tx != null) {
				tx.rollback();
			}
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	@Override
	public List<Eleve> findAllEleveByCivilite(Civilite civilite) {
		List<Eleve> liste = new ArrayList<>();

		EntityManager em = null;
		EntityTransaction tx = null;

		try {
			em = Application.getInstance().getEmf().createEntityManager();
			tx = em.getTransaction();
			tx.begin();

			Query query = em.createNamedQuery("Eleve.findAllByCivilite");

			query.setParameter("civilite", civilite);

			liste = query.getResultList();

			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			if (tx != null) {
				tx.rollback();
			}
		} finally {
			if (em != null) {
				em.close();
			}
		}

		return liste;
	}

	@Override
	public List<Eleve> findAllEleveByVille(String ville) {
		List<Eleve> liste = new ArrayList<>();

		EntityManager em = null;
		EntityTransaction tx = null;

		try {
			em = Application.getInstance().getEmf().createEntityManager();
			tx = em.getTransaction();
			tx.begin();

			Query query = em.createQuery("select e from Eleve e where upper(e.adresse.ville) = ?1");

			query.setParameter(1, ville.toUpperCase());

			liste = query.getResultList();

			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			if (tx != null) {
				tx.rollback();
			}
		} finally {
			if (em != null) {
				em.close();
			}
		}

		return liste;
	}

	@Override
	public List<Eleve> findAllEleveByDtNaissance(Date dt) {
		List<Eleve> liste = new ArrayList<>();

		EntityManager em = null;
		EntityTransaction tx = null;

		try {
			em = Application.getInstance().getEmf().createEntityManager();
			tx = em.getTransaction();
			tx.begin();

			Query query = em.createQuery("select e from Eleve e where e.dtNaissance = :maDate");

			query.setParameter("maDate", dt, TemporalType.DATE);

			liste = query.getResultList();

			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			if (tx != null) {
				tx.rollback();
			}
		} finally {
			if (em != null) {
				em.close();
			}
		}

		return liste;
	}

	@Override
	public Formateur findFormateurByNomAndPrenom(String nom, String prenom) {
		Formateur entity = null;

		EntityManager em = null;
		EntityTransaction tx = null;

		try {
			em = Application.getInstance().getEmf().createEntityManager();
			tx = em.getTransaction();
			tx.begin();

			Query query = em.createQuery("select f from Formateur f where f.nom = :nom and f.prenom = :prenom",
					Formateur.class);

			query.setParameter("nom", nom);
			query.setParameter("prenom", prenom);

			entity = (Formateur) query.getSingleResult();

			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			if (tx != null) {
				tx.rollback();
			}
		} finally {
			if (em != null) {
				em.close();
			}
		}

		return entity;
	}

	@Override
	public Eleve findEleveByOrdinateur(String code) {
		Eleve entity = null;

		EntityManager em = null;
		EntityTransaction tx = null;

		try {
			em = Application.getInstance().getEmf().createEntityManager();
			tx = em.getTransaction();
			tx.begin();
			
//			Query query = em.createQuery("select o.eleve from Ordinateur o where o.code = :codeOrdi", Eleve.class);

//			Query query = em.createQuery("select e from Ordinateur o join o.eleve e where o.code = :codeOrdi", Eleve.class);

//			Query query = em.createQuery("select e from Eleve e join e.ordinateur o where o.code = :codeOrdi", Eleve.class);

			Query query = em.createQuery("select e from Eleve e where e.ordinateur.code = :codeOrdi", Eleve.class);
			
			query.setParameter("codeOrdi", code);
			
			entity = (Eleve) query.getSingleResult();

			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			if (tx != null) {
				tx.rollback();
			}
		} finally {
			if (em != null) {
				em.close();
			}
		}

		return entity;
	}

	@Override
	public List<Formateur> findAllFormateurByFormation(String client, String promotion) {
		List<Formateur> liste = new ArrayList<>();

		EntityManager em = null;
		EntityTransaction tx = null;

		try {
			em = Application.getInstance().getEmf().createEntityManager();
			tx = em.getTransaction();
			tx.begin();

			Query query = em.createQuery("select distinct f from Formation form "
												+ "join form.matieres m "
												+ "join m.formateurs f "
										+ "where form.id.client = :client "
										+ "and form.id.promotion = :promotion", 
										Formateur.class);
			
			query.setParameter("client", client);
			
			query.setParameter("promotion", promotion);
			
			liste = query.getResultList();

			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			if (tx != null) {
				tx.rollback();
			}
		} finally {
			if (em != null) {
				em.close();
			}
		}

		return liste;
	}
}
