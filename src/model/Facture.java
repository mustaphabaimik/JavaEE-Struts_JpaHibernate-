package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the facture database table.
 * 
 */
@Entity
@NamedQuery(name="Facture.findAll", query="SELECT f FROM Facture f")
public class Facture implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String numFacture;

	private String dateFacture;

	private double montant;

	//bi-directional many-to-one association to Transaction
	@OneToMany(mappedBy="facture", fetch=FetchType.EAGER)
	private List<Transaction> transactions;

	public Facture() {
	}
	
	

	public Facture(String numFacture, double montant) {
		super();
		this.numFacture = numFacture;
		this.montant = montant;
	}



	public String getNumFacture() {
		return this.numFacture;
	}

	public void setNumFacture(String numFacture) {
		this.numFacture = numFacture;
	}

	public String getDateFacture() {
		return this.dateFacture;
	}

	public void setDateFacture(String dateFacture) {
		this.dateFacture = dateFacture;
	}

	public double getMontant() {
		return this.montant;
	}

	public void setMontant(double montant) {
		this.montant = montant;
	}

	public List<Transaction> getTransactions() {
		return this.transactions;
	}

	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}

	public Transaction addTransaction(Transaction transaction) {
		getTransactions().add(transaction);
		transaction.setFacture(this);

		return transaction;
	}

	public Transaction removeTransaction(Transaction transaction) {
		getTransactions().remove(transaction);
		transaction.setFacture(null);

		return transaction;
	}

}