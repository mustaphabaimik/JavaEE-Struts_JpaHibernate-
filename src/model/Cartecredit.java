package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the cartecredit database table.
 * 
 */
@Entity
@NamedQuery(name="Cartecredit.findAll", query="SELECT c FROM Cartecredit c")
public class Cartecredit implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String numCarte;

	//bi-directional many-to-one association to Proprietaire
	@ManyToOne
	@JoinColumn(name="idProprietaire")
	private Proprietaire proprietaire;

	//bi-directional many-to-one association to Transaction
	@OneToMany(mappedBy="cartecredit", fetch=FetchType.EAGER)
	private List<Transaction> transactions;

	public Cartecredit() {
	}
	
	

	public Cartecredit(String numCarte) {
		super();
		this.numCarte = numCarte;
	}



	public String getNumCarte() {
		return this.numCarte;
	}

	public void setNumCarte(String numCarte) {
		this.numCarte = numCarte;
	}

	public Proprietaire getProprietaire() {
		return this.proprietaire;
	}

	public void setProprietaire(Proprietaire proprietaire) {
		this.proprietaire = proprietaire;
	}

	public List<Transaction> getTransactions() {
		return this.transactions;
	}

	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}

	public Transaction addTransaction(Transaction transaction) {
		getTransactions().add(transaction);
		transaction.setCartecredit(this);

		return transaction;
	}

	public Transaction removeTransaction(Transaction transaction) {
		getTransactions().remove(transaction);
		transaction.setCartecredit(null);

		return transaction;
	}

}