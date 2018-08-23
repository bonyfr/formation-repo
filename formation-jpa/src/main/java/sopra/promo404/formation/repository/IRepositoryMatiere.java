package sopra.promo404.formation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import sopra.promo404.formation.model.Matiere;

public interface IRepositoryMatiere extends JpaRepository<Matiere, Long> {
	@Query("select m from Matiere m join fetch m.formateurs where id = :id")
	Matiere findByIdWithFormateurs(@Param("id") Long id);
}
