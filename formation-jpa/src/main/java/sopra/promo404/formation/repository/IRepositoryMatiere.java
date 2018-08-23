package sopra.promo404.formation.repository;

import sopra.promo404.formation.model.Matiere;

public interface IRepositoryMatiere extends IRepository<Matiere, Long> {
	Matiere findByIdWithFormateurs(Long id);
}
