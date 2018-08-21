package sopra.promo404.formation.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name = "computer")
public class Ordinateur {
	@Id
	@Column(length = 20)
	private String code;
	@Version
	private int version;
	private int ram;
	private boolean ssd;
	@OneToOne(mappedBy = "ordinateur", fetch = FetchType.EAGER)
	private Eleve eleve;

	public Ordinateur() {
		super();
	}

	public Ordinateur(String code, int ram, boolean ssd) {
		super();
		this.code = code;
		this.ram = ram;
		this.ssd = ssd;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public int getRam() {
		return ram;
	}

	public void setRam(int ram) {
		this.ram = ram;
	}

	public boolean isSsd() {
		return ssd;
	}

	public void setSsd(boolean ssd) {
		this.ssd = ssd;
	}

	public Eleve getEleve() {
		return eleve;
	}

	public void setEleve(Eleve eleve) {
		this.eleve = eleve;
	}

	@Override
	public String toString() {
		return "Ordinateur [code=" + code + ", ram=" + ram + ", ssd=" + ssd + "]";
	}

}
