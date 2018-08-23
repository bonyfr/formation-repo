package sopra.promo404.formation;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import sopra.promo404.formation.model.Ordinateur;
import sopra.promo404.formation.repository.IRepositoryOrdinateur;

public class TestOrdi {
	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				"classpath:application-context.xml");

		IRepositoryOrdinateur daoOrdinateur = context.getBean(IRepositoryOrdinateur.class);

		Ordinateur ajc126 = new Ordinateur("AJC-126", 8, true);
		daoOrdinateur.save(ajc126);

		Ordinateur ajc133 = new Ordinateur("AJC-133", 8, false);
		daoOrdinateur.save(ajc133);

		Ordinateur ajc134 = new Ordinateur("AJC-134", 8, false);
		daoOrdinateur.save(ajc134);

		System.out.println("avec ssd : " + daoOrdinateur.findAllBySsd(true).size());

		System.out.println("avec ssd : " + daoOrdinateur.findAllStartingByCode("AJC-13").size());

		context.close();
	}
}
