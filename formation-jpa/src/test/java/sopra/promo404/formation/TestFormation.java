package sopra.promo404.formation;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import sopra.promo404.formation.model.Formation;
import sopra.promo404.formation.model.FormationId;
import sopra.promo404.formation.repository.IRepositoryFormation;

public class TestFormation {
	public static void main(String[] args) throws ParseException {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				"classpath:application-context.xml");

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		IRepositoryFormation daoFormation = context.getBean(IRepositoryFormation.class);

		Formation promo404 = new Formation("Sopra Steria", "404");
		promo404.setDuree(45);

		daoFormation.save(promo404);

		Formation promo404Find = daoFormation.findById(new FormationId("Sopra Steria", "404")).get();

		System.out.println(daoFormation.findByClientAndPromotion("Sopra Steria", "404"));

		context.close();
	}
}
