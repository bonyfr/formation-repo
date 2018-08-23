package sopra.promo404.formation.repository;

import java.util.Date;
import java.util.List;

import sopra.promo404.formation.model.Civilite;
import sopra.promo404.formation.model.Eleve;
import sopra.promo404.formation.model.Formateur;
import sopra.promo404.formation.model.Personne;

public interface IRepositoryPersonne extends IRepository<Personne, Long> {
	List<Eleve> findAllEleveByCivilite(Civilite civilite);

	List<Eleve> findAllEleveByVille(String ville);

	List<Eleve> findAllEleveByDtNaissance(Date dt);

	Formateur findFormateurByNomAndPrenom(String nom, String prenom);
	
	Eleve findEleveByOrdinateur(String code);
	
	List<Formateur> findAllFormateurByFormation(String client, String promotion);
}
