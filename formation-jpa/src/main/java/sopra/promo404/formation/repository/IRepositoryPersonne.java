package sopra.promo404.formation.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import sopra.promo404.formation.model.Civilite;
import sopra.promo404.formation.model.Eleve;
import sopra.promo404.formation.model.Formateur;
import sopra.promo404.formation.model.Personne;

public interface IRepositoryPersonne extends JpaRepository<Personne, Long> {
	List<Eleve> findAllByCivilite(@Param("civilite") Civilite civilite);

	List<Eleve> findAllByFormateur(@Param("formateur") Formateur formateur);

	@Query("select e from Eleve e where upper(e.adresse.ville) = ?1")
	List<Eleve> findAllEleveByVille(String ville);

	@Query("select e from Eleve e where e.dtNaissance = :maDate")
	List<Eleve> findAllEleveByDtNaissance(@Param("maDate") Date dt);

	@Query("select f from Formateur f where f.nom = :nom and f.prenom = :prenom")
	Formateur findFormateurByNomAndPrenom(@Param("nom") String nom, @Param("prenom") String prenom);

	@Query("select e from Eleve e where e.ordinateur.code = :codeOrdi")
	Eleve findEleveByOrdinateur(@Param("codeOrdi") String code);

	@Query("select distinct f from Formation form join form.matieres m join m.formateurs f where form.id.client = :client and form.id.promotion = :promotion")
	List<Formateur> findAllFormateurByFormation(@Param("client") String client, @Param("promotion") String promotion);
	
	@Query("select distinct f from Formateur f join fetch f.eleves e where f.id = :id")
	Formateur findFormateurByIdWithEleves(@Param("id") Long id);
}
