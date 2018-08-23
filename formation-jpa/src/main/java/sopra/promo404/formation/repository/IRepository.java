package sopra.promo404.formation.repository;

import java.util.List;

public interface IRepository<T,PK> {
	List<T> findAll();
	T findById(PK id);
	T save(T entity);
	void delete(T entity);
	void deleteById(PK id);
}
