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

public class Test {
	public static void main(String[] args) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		IDaoFormation daoFormation = Application.getInstance().getDaoFormation();
		IDaoMatiere daoMatiere = Application.getInstance().getDaoMatiere();
		IDaoOrdinateur daoOrdinateur = Application.getInstance().getDaoOrdinateur();
		IDaoPersonne daoPersonne = Application.getInstance().getDaoPersonne();
		
		Formation promo404 = new Formation("Sopra Steria", "404");
		promo404.setDuree(45);
		
		daoFormation.save(promo404);
		
		Matiere unix = new Matiere("UNIX", 1, Difficulte.FACILE);
		
		daoMatiere.save(unix);
		
		promo404.addMatiere(unix);
		
		Matiere algo = new Matiere("ALGO", 3, Difficulte.MOYEN);
		
		daoMatiere.save(algo);
		
		promo404.addMatiere(algo);
		
		Matiere uml = new Matiere("UML", 3, Difficulte.MOYEN);
		
		daoMatiere.save(uml);
		
		promo404.addMatiere(uml);
		
		daoFormation.save(promo404);
		
		unix.setDifficulte(Difficulte.DIFFICILE);
		
		daoMatiere.save(unix);
		
		Formateur eric = new Formateur("SULTAN", "Eric", true, 20);
		eric.setAdresse(new Adresse("1 rue de la Paix", "75001", "Paris", "France"));
		eric.addMatiere(unix);
		eric.addMatiere(algo);
		eric.addMatiere(uml);
		
		daoPersonne.save(eric);
		
		Formateur jeremy = new Formateur("PERROUAULT", "Jérémy", false, 8);
		jeremy.setAdresse(new Adresse("3 rue du chut", "33700", "Mérignac", "France"));
		jeremy.addMatiere(uml);
		
		daoPersonne.save(jeremy);
		
		Eleve baptiste = new Eleve(Civilite.M, "LALEOUSSE", "Baptiste", sdf.parse("23/11/1993"));
		baptiste.setAdresse(new Adresse("25 rue ausone", "33520", "Bruges", "France"));
		baptiste.setFormateur(eric);
		
		Ordinateur ajc126 = new Ordinateur("AJC-126", 8, true);
		daoOrdinateur.save(ajc126);
		
		baptiste.setOrdinateur(ajc126);
		
		daoPersonne.save(baptiste);
		
		Eleve adrien = new Eleve(Civilite.M, "LAVELLE", "Adrien", sdf.parse("17/11/1993"));
		adrien.setAdresse(new Adresse("11 allée belair", "33185", "Le Haillan", "France"));
		adrien.setFormateur(eric);
		
		daoPersonne.save(adrien);
		
		Eleve lucas = new Eleve(Civilite.M, "CRAPPEEL", "Lucas", sdf.parse("16/07/1994"));
		lucas.setAdresse(new Adresse("91 avenue de saint medard", "33509", "Eysines", "France"));
		lucas.setFormateur(jeremy);
		
		daoPersonne.save(lucas);
		
		Eleve alix = new Eleve(Civilite.M, "JOURDAN", "Alix", sdf.parse("03/09/1993"));
		alix.setAdresse(new Adresse("102 rue Bourbon", "33300", "Bordeaux", "France"));
		alix.setFormateur(eric);
		
		daoPersonne.save(alix);
	}
}
