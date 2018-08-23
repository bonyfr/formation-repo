package sopra.promo404.formation.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import sopra.promo404.formation.model.Formation;
import sopra.promo404.formation.model.FormationId;

public interface IRepositoryFormation extends JpaRepository<Formation, FormationId> {
	@Query("select f from Formation f where f.id.client = ?1 and f.id.promotion = ?2")
	Formation findByClientAndPromotion(String client, String promotion);

	@Query("select f from Formation f join f.matieres m where m.nom = :nom")
	List<Formation> findAllByMatiere(@Param("nom") String nom);
}
