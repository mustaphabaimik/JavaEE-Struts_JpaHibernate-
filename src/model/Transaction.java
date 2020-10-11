package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the transaction database table.
 * 
 */
@Entity
@NamedQuery(name="Transaction.findAll", query="SELECT t FROM Transaction t")
public class Transaction implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	
	private String datePaiement;

	private double montant;

	//bi-directional many-to-one association to Cartecredit
	@ManyToOne
	@JoinColumn(name="numCarte")
	private Cartecredit cartecredit;

	//bi-directional many-to-one association to Facture
	@ManyToOne
	@JoinColumn(name="numFacture")
	private Facture facture;

	public Transaction() {
	}
	
	

	



	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDatePaiement() {
		return this.datePaiement;
	}

	public void setDatePaiement(String datePaiement) {
		this.datePaiement = datePaiement;
	}

	public double getMontant() {
		return this.montant;
	}

	public void setMontant(double montant) {
		this.montant = montant;
	}

	public Cartecredit getCartecredit() {
		return this.cartecredit;
	}

	public void setCartecredit(Cartecredit cartecredit) {
		this.cartecredit = cartecredit;
	}

	public Facture getFacture() {
		return this.facture;
	}

	public void setFacture(Facture facture) {
		this.facture = facture;
	}

}