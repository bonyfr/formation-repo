package sopra.promo404.formation.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "training")
public class Formation {
	@EmbeddedId
	private FormationId id;
	@Column(name = "duration")
	private int duree;
	@ManyToMany
	@JoinTable(name = "training_subject", joinColumns = {
			@JoinColumn(name = "training_customer", referencedColumnName = "customer"),
			@JoinColumn(name = "training_promotion", referencedColumnName = "promotion") }, inverseJoinColumns = @JoinColumn(name = "subject_id"), uniqueConstraints = @UniqueConstraint(columnNames = {
					"training_customer", "training_promotion", "subject_id" }))
	private List<Matiere> matieres = new ArrayList<>();

	public Formation() {
		super();
	}

	public Formation(String client, String promotion) {
		super();
		this.id = new FormationId(client, promotion);
	}

	public FormationId getId() {
		return id;
	}

	public void setId(FormationId id) {
		this.id = id;
	}

	public int getDuree() {
		return duree;
	}

	public void setDuree(int duree) {
		this.duree = duree;
	}

	public List<Matiere> getMatieres() {
		return matieres;
	}

	public void setMatieres(List<Matiere> matieres) {
		this.matieres = matieres;
	}
	
	public void addMatiere(Matiere matiere) {
		this.matieres.add(matiere);
	}

	public void removeMatiere(Matiere matiere) {
		this.matieres.remove(matiere);
	}
}
