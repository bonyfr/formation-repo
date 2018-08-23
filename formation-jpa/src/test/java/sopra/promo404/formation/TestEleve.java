package sopra.promo404.formation;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import sopra.promo404.formation.model.Adresse;
import sopra.promo404.formation.model.Civilite;
import sopra.promo404.formation.model.Eleve;
import sopra.promo404.formation.repository.IRepositoryPersonne;

public class TestEleve {
	public static void main(String[] args) throws ParseException {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:application-context.xml");
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		IRepositoryPersonne daoPersonne = context.getBean(IRepositoryPersonne.class);

		Eleve baptiste = new Eleve(Civilite.M, "LALEOUSSE", "Baptiste", sdf.parse("23/11/1993"));
		baptiste.setAdresse(new Adresse("25 rue ausone", "33520", "Bruges", "France"));

		daoPersonne.save(baptiste);

		Eleve adrien = new Eleve(Civilite.M, "LAVELLE", "Adrien", sdf.parse("17/11/1993"));
		adrien.setAdresse(new Adresse("11 allée belair", "33185", "Le Haillan", "France"));

		daoPersonne.save(adrien);

		Eleve lucas = new Eleve(Civilite.M, "CRAPPEEL", "Lucas", sdf.parse("16/07/1994"));
		lucas.setAdresse(new Adresse("91 avenue de saint medard", "33300", "Bordeaux", "France"));

		daoPersonne.save(lucas);

		Eleve alix = new Eleve(Civilite.M, "JOURDAN", "Alix", sdf.parse("03/09/1993"));
		alix.setAdresse(new Adresse("102 rue Bourbon", "33300", "Bordeaux", "France"));

		daoPersonne.save(alix);

		System.out.println("Bordeaux : " + daoPersonne.findAllEleveByVille("bordeaux").size());
		
		System.out.println("17/11/1993 : "+daoPersonne.findAllEleveByDtNaissance(sdf.parse("17/11/1993")).size());
	
		context.close();
	}
}
