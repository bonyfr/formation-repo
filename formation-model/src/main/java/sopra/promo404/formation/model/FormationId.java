package sopra.promo404.formation.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@SuppressWarnings("serial")
@Embeddable
public class FormationId implements Serializable {
	@Column(name = "customer", length = 100)
	private String client;
	@Column(length = 100)
	private String promotion;

	public FormationId() {
		super();
	}

	public FormationId(String client, String promotion) {
		super();
		this.client = client;
		this.promotion = promotion;
	}

	public String getClient() {
		return client;
	}

	public void setClient(String client) {
		this.client = client;
	}

	public String getPromotion() {
		return promotion;
	}

	public void setPromotion(String promotion) {
		this.promotion = promotion;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((client == null) ? 0 : client.hashCode());
		result = prime * result + ((promotion == null) ? 0 : promotion.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FormationId other = (FormationId) obj;
		if (client == null) {
			if (other.client != null)
				return false;
		} else if (!client.equals(other.client))
			return false;
		if (promotion == null) {
			if (other.promotion != null)
				return false;
		} else if (!promotion.equals(other.promotion))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "FormationId [client=" + client + ", promotion=" + promotion + "]";
	}

}
