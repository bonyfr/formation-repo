package sopra.promo404.formation.repository;

import java.util.List;

import sopra.promo404.formation.model.Formation;
import sopra.promo404.formation.model.FormationId;

public interface IRepositoryFormation extends IRepository<Formation, FormationId> {
	Formation findByClientAndPromotion(String client, String promotion);
	List<Formation> findAllByMatiere(String nom);
}
