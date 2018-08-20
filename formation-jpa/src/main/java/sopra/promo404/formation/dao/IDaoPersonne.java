package sopra.promo404.formation.dao;

import java.util.Date;
import java.util.List;

import sopra.promo404.formation.model.Civilite;
import sopra.promo404.formation.model.Eleve;
import sopra.promo404.formation.model.Personne;

public interface IDaoPersonne extends IDao<Personne, Long> {
	List<Eleve> findAllEleveByCivilite(Civilite civilite);
	
	List<Eleve> findAllEleveByVille(String ville);
	
	List<Eleve> findAllEleveByDtNaissance(Date dt);
}
