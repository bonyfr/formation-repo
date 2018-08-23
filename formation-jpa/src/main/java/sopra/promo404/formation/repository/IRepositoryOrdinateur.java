package sopra.promo404.formation.repository;

import java.util.List;

import sopra.promo404.formation.model.Ordinateur;

public interface IRepositoryOrdinateur extends IRepository<Ordinateur, String> {
	List<Ordinateur> findAllBySsd(boolean ssd);
	
	List<Ordinateur> findAllStartingByCode(String code);
}
