package sopra.promo404.formation.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import sopra.promo404.formation.model.Adresse;
import sopra.promo404.formation.model.Civilite;
import sopra.promo404.formation.model.Eleve;
import sopra.promo404.formation.model.Formateur;
import sopra.promo404.formation.model.Personne;
import sopra.promo404.formation.repository.IRepositoryPersonne;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/application-context.xml" })
public class PersonneTest {

	@Autowired
	private IRepositoryPersonne repoPersonne;

	@Test
	public void eleve() throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		int startCount = repoPersonne.findAll().size();

		Eleve eleve = new Eleve(Civilite.MLLE, "LEFEBVRE", "Ophélie", sdf.parse("11/12/1990"));
		eleve.setAdresse(new Adresse("1 rue de la Paix", "75001", "Paris", "France"));

		repoPersonne.save(eleve);

		Eleve eleveFind = (Eleve) repoPersonne.findById(eleve.getId()).get();

		Assert.assertEquals(Civilite.MLLE, eleveFind.getCivilite());
		Assert.assertEquals("LEFEBVRE", eleveFind.getNom());
		Assert.assertEquals("Ophélie", eleveFind.getPrenom());
		Assert.assertEquals(sdf.parse("11/12/1990"), eleveFind.getDtNaissance());
		Assert.assertEquals(new Adresse("1 rue de la Paix", "75001", "Paris", "France"), eleveFind.getAdresse());

		eleveFind.setCivilite(Civilite.M);
		eleveFind.setNom("DUPONT");
		eleveFind.setPrenom("Paulo");
		eleveFind.setDtNaissance(sdf.parse("11/12/1998"));
		eleveFind.setAdresse(new Adresse("10 rue de pessac", "33000", "Bordeaux", "FRANCE"));

		repoPersonne.save(eleveFind);

		eleveFind = (Eleve) repoPersonne.findById(eleve.getId()).get();

		Assert.assertEquals(Civilite.M, eleveFind.getCivilite());
		Assert.assertEquals("DUPONT", eleveFind.getNom());
		Assert.assertEquals("Paulo", eleveFind.getPrenom());
		Assert.assertEquals(sdf.parse("11/12/1998"), eleveFind.getDtNaissance());
		Assert.assertEquals(new Adresse("10 rue de pessac", "33000", "Bordeaux", "FRANCE"), eleveFind.getAdresse());

		int endCount = repoPersonne.findAll().size();

		Assert.assertEquals(1, (endCount - startCount));

		repoPersonne.delete(eleveFind);

		Optional<Personne> htmlOptional = repoPersonne.findById(eleve.getId());

		if (htmlOptional.isPresent()) {
			Assert.fail("La suppression de l'élève a échoué");
		}

	}

	@Test
	public void formateur() throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		int startCount = repoPersonne.findAll().size();

		Formateur formateur = new Formateur("SULTAN", "Eric", true, 10);
		formateur.setAdresse(new Adresse("1 rue de la Paix", "75001", "Paris", "France"));

		repoPersonne.save(formateur);

		Formateur formateurFind = (Formateur) repoPersonne.findById(formateur.getId()).get();

		Assert.assertEquals("SULTAN", formateurFind.getNom());
		Assert.assertEquals("Eric", formateurFind.getPrenom());
		Assert.assertTrue(formateurFind.isReferent());
		Assert.assertEquals(10, formateurFind.getExperience());
		Assert.assertEquals(new Adresse("1 rue de la Paix", "75001", "Paris", "France"), formateurFind.getAdresse());

		formateurFind.setNom("PERROUAULT");
		formateurFind.setPrenom("Jérémy");
		formateurFind.setReferent(false);
		formateurFind.setExperience(5);
		formateurFind.setAdresse(new Adresse("10 rue de pessac", "33000", "Bordeaux", "FRANCE"));

		repoPersonne.save(formateurFind);

		formateurFind = (Formateur) repoPersonne.findById(formateur.getId()).get();

		Assert.assertEquals("PERROUAULT", formateurFind.getNom());
		Assert.assertEquals("Jérémy", formateurFind.getPrenom());
		Assert.assertFalse(formateurFind.isReferent());
		Assert.assertEquals(5, formateurFind.getExperience());
		Assert.assertEquals(new Adresse("10 rue de pessac", "33000", "Bordeaux", "FRANCE"), formateurFind.getAdresse());

		int endCount = repoPersonne.findAll().size();

		Assert.assertEquals(1, (endCount - startCount));

		repoPersonne.delete(formateurFind);

		Optional<Personne> htmlOptional = repoPersonne.findById(formateur.getId());

		if (htmlOptional.isPresent()) {
			Assert.fail("La suppression de l'élève a échoué");
		}

	}
	
	@Test
	public void relation() throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		Formateur formateur = new Formateur("SULTAN", "Eric", true, 10);
		
		repoPersonne.save(formateur);
		
		Eleve eleve = new Eleve(Civilite.MLLE, "LEFEBVRE", "Ophélie", sdf.parse("11/12/1990"));
		eleve.setFormateur(formateur);
		
		repoPersonne.save(eleve);
		
		Eleve eleve2 = new Eleve(Civilite.M, "DUPONT", "Paulo", sdf.parse("11/12/1991"));
		eleve2.setFormateur(formateur);
		
		repoPersonne.save(eleve2);
		
		Formateur formateurFind = (Formateur) repoPersonne.findFormateurByIdWithEleves(formateur.getId());
		
		Assert.assertEquals(2, formateurFind.getEleves().size());
		
		repoPersonne.delete(eleve);
		repoPersonne.delete(eleve2);
		repoPersonne.delete(formateur);
	}

}
