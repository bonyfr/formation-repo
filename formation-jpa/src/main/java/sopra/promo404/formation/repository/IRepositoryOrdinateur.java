package sopra.promo404.formation.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import sopra.promo404.formation.model.Ordinateur;

public interface IRepositoryOrdinateur extends JpaRepository<Ordinateur, String> {
	List<Ordinateur> findBySsd(boolean ssd);
	
	List<Ordinateur> findByCodeStartingWith(String code);
}
