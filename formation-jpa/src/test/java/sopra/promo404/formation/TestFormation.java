package sopra.promo404.formation;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import sopra.promo404.formation.model.Adresse;
import sopra.promo404.formation.model.Civilite;
import sopra.promo404.formation.model.Difficulte;
import sopra.promo404.formation.model.Eleve;
import sopra.promo404.formation.model.Formateur;
import sopra.promo404.formation.model.Formation;
import sopra.promo404.formation.model.FormationId;
import sopra.promo404.formation.model.Matiere;
import sopra.promo404.formation.model.Ordinateur;
import sopra.promo404.formation.repository.IRepositoryFormation;
import sopra.promo404.formation.repository.IRepositoryMatiere;
import sopra.promo404.formation.repository.IRepositoryOrdinateur;
import sopra.promo404.formation.repository.IRepositoryPersonne;

public class TestFormation {
	public static void main(String[] args) throws ParseException {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:application-context.xml");
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		IRepositoryFormation daoFormation = context.getBean(IRepositoryFormation.class);

		Formation promo404 = new Formation("Sopra Steria", "404");
		promo404.setDuree(45);

		daoFormation.save(promo404);

		Formation promo404Find = daoFormation.findById(new FormationId("Sopra Steria", "404"));

		System.out.println(daoFormation.findByClientAndPromotion("Sopra Steria", "404"));
	
		context.close();
	}
}
