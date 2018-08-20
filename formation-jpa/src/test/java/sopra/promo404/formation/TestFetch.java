package sopra.promo404.formation;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import sopra.promo404.formation.dao.IDaoFormation;
import sopra.promo404.formation.dao.IDaoMatiere;
import sopra.promo404.formation.dao.IDaoOrdinateur;
import sopra.promo404.formation.dao.IDaoPersonne;
import sopra.promo404.formation.model.Adresse;
import sopra.promo404.formation.model.Civilite;
import sopra.promo404.formation.model.Difficulte;
import sopra.promo404.formation.model.Eleve;
import sopra.promo404.formation.model.Formateur;
import sopra.promo404.formation.model.Formation;
import sopra.promo404.formation.model.Matiere;
import sopra.promo404.formation.model.Ordinateur;

public class TestFetch {
	public static void main(String[] args) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		IDaoFormation daoFormation = Application.getInstance().getDaoFormation();
		IDaoMatiere daoMatiere = Application.getInstance().getDaoMatiere();
		IDaoOrdinateur daoOrdinateur = Application.getInstance().getDaoOrdinateur();
		IDaoPersonne daoPersonne = Application.getInstance().getDaoPersonne();
		
		Matiere unix = new Matiere("UNIX", 1, Difficulte.FACILE);
		
		daoMatiere.save(unix);
		
		Matiere unixFind = daoMatiere.findById(unix.getId());
		
	}
}
