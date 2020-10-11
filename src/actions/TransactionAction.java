package actions;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;

import dao.CartesCreditDAO;
import dao.FactureDAO;
import dao.TransactionDAO;
import model.Cartecredit;
import model.Facture;
import model.Transaction;


@Namespace("/transaction")
@Results({
	@Result(name="success",location="/pages/transaction.jsp"),
	@Result(name="input",location="/pages/transaction.jsp"),
	@Result(name="none",location="/pages/transaction.jsp")
	})
public class TransactionAction extends ActionSupport implements Preparable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private CartesCreditDAO creditDAO=new CartesCreditDAO();
	private TransactionDAO transactionDAO=new TransactionDAO();
	private String id="-1";
	private List<Cartecredit> listeCarte;
	private FactureDAO factureDAO=new FactureDAO();
	private List<Facture> listeFacture;
	private List<Object[]> listeTransactions;
	private String montant;
	private String CarteSelected;
	private String FactureSelected;
	private String searchTerm;
	
	


	
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
 
	
	public String getSearchTerm() {
		return searchTerm;
	}

	public void setSearchTerm(String searchTerm) {
		this.searchTerm = searchTerm;
	}

	public List<Object[]> getListeTransactions() {
		return listeTransactions;
	}

	public void setListeTransactions(List<Object[]> listeTransactions) {
		this.listeTransactions = listeTransactions;
	}

	public List<Cartecredit> getListeCarte() {
		return listeCarte;
	}

	public void setListeCarte(List<Cartecredit> listeCarte) {
		this.listeCarte = listeCarte;
	}

	public List<Facture> getListeFacture() {
		return listeFacture;
	}

	public void setListeFacture(List<Facture> listeFacture) {
		this.listeFacture = listeFacture;
	}

	
	public String getMontant() {
		return montant;
	}

	public void setMontant(String montant) {
		this.montant = montant;
	}

	
	public String getCarteSelected() {
		return CarteSelected;
	}

	public void setCarteSelected(String carteSelected) {
		CarteSelected = carteSelected;
	}

	
	public String getFactureSelected() {
		return FactureSelected;
	}

	public void setFactureSelected(String factureSelected) {
		FactureSelected = factureSelected;
	}

	@Action(value="index")
	public String execute(){
		return NONE;
	}
	
	@Action(value="addOrSaveTransaction")
	public String addOrSaveTransaction(){
		Transaction t=new Transaction();
		t.setId(Integer.parseInt(id));
		t.setMontant(Double.parseDouble(montant));
		transactionDAO.addOrSaveTransaction(t, CarteSelected, FactureSelected);
		//charger la liste des transactions
		listeTransactions=transactionDAO.getListeTransactions();
		id="-1";
		CarteSelected="";
		FactureSelected="";
		montant="";
		return SUCCESS;		
	}
	
	@Action(value="deleteTransaction")
	public String deleteTransaction(){		
		transactionDAO.deleteTransaction(Integer.parseInt(id));
		//charger la liste des transactions
		listeTransactions=transactionDAO.getListeTransactions();	
		id="-1";
		CarteSelected="";
		FactureSelected="";
		montant="";
		return SUCCESS;		
	}
	
	@Action(value="searchbyFac")
	public String searchbyFac(){		
		
		//charger la liste des transactions
		listeTransactions=transactionDAO.searchbyFac(searchTerm);	
		searchTerm="";
		return SUCCESS;		
	}
	
	@Action(value="searchbyCarte")
	public String searchbyCarte(){		
		
		//charger la liste des transactions
		listeTransactions=transactionDAO.searchbyCarte(searchTerm);
		searchTerm="";
		return SUCCESS;		
	}

	@Override
	public void prepare() throws Exception {
		// TODO Auto-generated method stub
		//charger la liste des Carte
		listeCarte=transactionDAO.getCartes();
		
		//charger liste des factures
		listeFacture=factureDAO.getFactures();
		
		//charger list transaction
		listeTransactions=transactionDAO.getListeTransactions();
		
	}

}
