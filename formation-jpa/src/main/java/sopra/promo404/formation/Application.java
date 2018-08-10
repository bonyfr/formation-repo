package sopra.promo404.formation;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import sopra.promo404.formation.dao.IDaoFormation;
import sopra.promo404.formation.dao.IDaoMatiere;
import sopra.promo404.formation.dao.IDaoOrdinateur;
import sopra.promo404.formation.dao.IDaoPersonne;
import sopra.promo404.formation.dao.jpa.DaoFormationJpa;
import sopra.promo404.formation.dao.jpa.DaoMatiereJpa;
import sopra.promo404.formation.dao.jpa.DaoOrdinateurJpa;
import sopra.promo404.formation.dao.jpa.DaoPersonneJpa;

public class Application {
	private static Application instance = null;

	private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("formation");
	
	private final IDaoFormation daoFormation = new DaoFormationJpa();
	private final IDaoMatiere daoMatiere = new DaoMatiereJpa();
	private final IDaoOrdinateur daoOrdinateur = new DaoOrdinateurJpa();
	private final IDaoPersonne daoPersonne = new DaoPersonneJpa();

	private Application() {
	}

	public static Application getInstance() {
		if (instance == null) {
			instance = new Application();
		}

		return instance;
	}

	public EntityManagerFactory getEmf() {
		return emf;
	}

	public IDaoFormation getDaoFormation() {
		return daoFormation;
	}

	public IDaoMatiere getDaoMatiere() {
		return daoMatiere;
	}

	public IDaoOrdinateur getDaoOrdinateur() {
		return daoOrdinateur;
	}

	public IDaoPersonne getDaoPersonne() {
		return daoPersonne;
	}

}
