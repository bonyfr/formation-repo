package sopra.promo404.formation.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.UniqueConstraint;

@Entity
@DiscriminatorValue("formateur")
public class Formateur extends Personne {
	private boolean referent;
	private int experience;
	@OneToMany(mappedBy = "formateur")
	private List<Eleve> eleves = new ArrayList<>();
	@ManyToMany
	@JoinTable(name = "person_subject", joinColumns = @JoinColumn(name = "person_id"), inverseJoinColumns = @JoinColumn(name = "subject_id"), uniqueConstraints = @UniqueConstraint(columnNames = {
			"person_id", "subject_id" }))
	private Set<Matiere> matieres = new HashSet<>();

	public Formateur() {
		super();
	}

	public Formateur(String nom, String prenom, boolean referent, int experience) {
		super(nom, prenom);
		this.referent = referent;
		this.experience = experience;
	}

	public boolean isReferent() {
		return referent;
	}

	public void setReferent(boolean referent) {
		this.referent = referent;
	}

	public int getExperience() {
		return experience;
	}

	public void setExperience(int experience) {
		this.experience = experience;
	}

	public List<Eleve> getEleves() {
		return eleves;
	}

	public void setEleves(List<Eleve> eleves) {
		this.eleves = eleves;
	}

	public Set<Matiere> getMatieres() {
		return matieres;
	}

	public void setMatieres(Set<Matiere> matieres) {
		this.matieres = matieres;
	}

	public void addMatiere(Matiere matiere) {
		this.matieres.add(matiere);
	}

	public void removeMatiere(Matiere matiere) {
		this.matieres.remove(matiere);
	}

}
