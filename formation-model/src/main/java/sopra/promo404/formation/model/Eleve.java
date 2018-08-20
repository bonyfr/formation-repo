package sopra.promo404.formation.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@DiscriminatorValue("eleve")
@NamedQueries({
		@NamedQuery(name = "Eleve.findAllByCivilite", query = "select e from Eleve e where e.civilite = :civilite"),
		@NamedQuery(name = "Eleve.findAllByFormateur", query = "select e from Eleve e where e.formateur = :formateur") })
public class Eleve extends Personne {
	@Enumerated
	@Column(name = "civility", length = 10)
	private Civilite civilite;
	@Temporal(TemporalType.DATE)
	@Column(name = "birthdate")
	private Date dtNaissance;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "trainer_id")
	private Formateur formateur;
	@OneToOne
	@JoinColumn(name = "computer_code")
	private Ordinateur ordinateur;

	public Eleve() {
		super();
	}

	public Eleve(Civilite civilite, String nom, String prenom, Date dtNaissance) {
		super(nom, prenom);
		this.civilite = civilite;
		this.dtNaissance = dtNaissance;
	}

	public Civilite getCivilite() {
		return civilite;
	}

	public void setCivilite(Civilite civilite) {
		this.civilite = civilite;
	}

	public Date getDtNaissance() {
		return dtNaissance;
	}

	public void setDtNaissance(Date dtNaissance) {
		this.dtNaissance = dtNaissance;
	}

	public Formateur getFormateur() {
		return formateur;
	}

	public void setFormateur(Formateur formateur) {
		this.formateur = formateur;
	}

	public Ordinateur getOrdinateur() {
		return ordinateur;
	}

	public void setOrdinateur(Ordinateur ordinateur) {
		this.ordinateur = ordinateur;
	}

}
