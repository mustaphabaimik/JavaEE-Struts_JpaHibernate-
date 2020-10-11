package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the proprietaire database table.
 * 
 */
@Entity
@NamedQuery(name="Proprietaire.findAll", query="SELECT p FROM Proprietaire p")
public class Proprietaire implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String nomPrenom;

	//bi-directional many-to-one association to Cartecredit
	@OneToMany(mappedBy="proprietaire", fetch=FetchType.EAGER)
	private List<Cartecredit> cartecredits;

	public Proprietaire() {
	}
	
	

	public Proprietaire(int id, String nomPrenom) {
		super();
		this.id = id;
		this.nomPrenom = nomPrenom;
	}



	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNomPrenom() {
		return this.nomPrenom;
	}

	public void setNomPrenom(String nomPrenom) {
		this.nomPrenom = nomPrenom;
	}

	public List<Cartecredit> getCartecredits() {
		return this.cartecredits;
	}

	public void setCartecredits(List<Cartecredit> cartecredits) {
		this.cartecredits = cartecredits;
	}

	public Cartecredit addCartecredit(Cartecredit cartecredit) {
		getCartecredits().add(cartecredit);
		cartecredit.setProprietaire(this);

		return cartecredit;
	}

	public Cartecredit removeCartecredit(Cartecredit cartecredit) {
		getCartecredits().remove(cartecredit);
		cartecredit.setProprietaire(null);

		return cartecredit;
	}

}