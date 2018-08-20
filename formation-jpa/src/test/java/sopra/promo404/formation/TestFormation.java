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
import sopra.promo404.formation.model.FormationId;
import sopra.promo404.formation.model.Matiere;
import sopra.promo404.formation.model.Ordinateur;

public class TestFormation {
	public static void main(String[] args) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		IDaoFormation daoFormation = Application.getInstance().getDaoFormation();

		Formation promo404 = new Formation("Sopra Steria", "404");
		promo404.setDuree(45);

		daoFormation.save(promo404);

		Formation promo404Find = daoFormation.findById(new FormationId("Sopra Steria", "404"));

		System.out.println(daoFormation.findByClientAndPromotion("Sopra Steria", "404"));
	}
}
