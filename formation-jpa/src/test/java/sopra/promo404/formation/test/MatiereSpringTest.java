package sopra.promo404.formation.test;

import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import sopra.promo404.formation.model.Difficulte;
import sopra.promo404.formation.model.Matiere;
import sopra.promo404.formation.repository.IRepositoryMatiere;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={ "/application-context.xml" })
public class MatiereSpringTest {
	
	@Autowired
	private IRepositoryMatiere repoMatiere;
	
	@Test
	public void matiere() {
		int startCount = repoMatiere.findAll().size();

		Matiere html = new Matiere("HTML", 2, Difficulte.FACILE);

		repoMatiere.save(html);

		Matiere htmlFind = repoMatiere.findById(html.getId()).get();

		Assert.assertEquals("HTML", htmlFind.getNom());
		Assert.assertEquals(2, htmlFind.getDuree());
		Assert.assertEquals(Difficulte.FACILE, htmlFind.getDifficulte());

		htmlFind.setNom("HTML/CSS");
		htmlFind.setDuree(3);
		htmlFind.setDifficulte(Difficulte.DIFFICILE);

		repoMatiere.save(htmlFind);

		htmlFind = repoMatiere.findById(html.getId()).get();

		Assert.assertEquals("HTML/CSS", htmlFind.getNom());
		Assert.assertEquals(3, htmlFind.getDuree());
		Assert.assertEquals(Difficulte.DIFFICILE, htmlFind.getDifficulte());

		int endCount = repoMatiere.findAll().size();

		Assert.assertEquals(1, (endCount - startCount));

		repoMatiere.delete(htmlFind);

		Optional<Matiere> htmlOptional = repoMatiere.findById(html.getId());

		if (htmlOptional.isPresent()) {
			Assert.fail("La suppression de la matière a échoué");
		}

	}

}
