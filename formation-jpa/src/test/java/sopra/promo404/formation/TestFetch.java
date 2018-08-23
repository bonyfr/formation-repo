package sopra.promo404.formation;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import sopra.promo404.formation.model.Difficulte;
import sopra.promo404.formation.model.Matiere;
import sopra.promo404.formation.repository.IRepositoryMatiere;

public class TestFetch {
	public static void main(String[] args) throws ParseException {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				"classpath:application-context.xml");

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		IRepositoryMatiere daoMatiere = context.getBean(IRepositoryMatiere.class);

		Matiere unix = new Matiere("UNIX", 1, Difficulte.FACILE);

		daoMatiere.save(unix);

		Matiere unixFind = daoMatiere.findById(unix.getId());

		context.close();
	}
}
