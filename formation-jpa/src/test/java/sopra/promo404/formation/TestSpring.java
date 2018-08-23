package sopra.promo404.formation;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import sopra.promo404.formation.repository.IRepositoryMatiere;
import sopra.promo404.formation.repository.IRepositoryPersonne;

public class TestSpring {
	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:application-context.xml");
	
		IRepositoryPersonne daoPersonne = context.getBean(IRepositoryPersonne.class);
	
		IRepositoryMatiere daoMatiere = context.getBean(IRepositoryMatiere.class);
		
		
		
	
		context.close();
	}
}
