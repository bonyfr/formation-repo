package sopra.promo404.formation.dao;

import java.util.List;

import sopra.promo404.formation.model.Formation;
import sopra.promo404.formation.model.FormationId;

public interface IDaoFormation extends IDao<Formation, FormationId> {
	Formation findByClientAndPromotion(String client, String promotion);
	List<Formation> findAllByMatiere(String nom);
}
