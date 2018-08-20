package sopra.promo404.formation.dao;

import java.util.List;

import sopra.promo404.formation.model.Ordinateur;

public interface IDaoOrdinateur extends IDao<Ordinateur, String> {
	List<Ordinateur> findAllBySsd(boolean ssd);
	
	List<Ordinateur> findAllStartingByCode(String code);
}
