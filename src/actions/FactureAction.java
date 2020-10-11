package actions;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;
import com.opensymphony.xwork2.validator.annotations.RegexFieldValidator;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;

import dao.FactureDAO;
import dao.TransactionDAO;
import model.Facture;
import model.Proprietaire;
import model.Transaction;


@Namespace("/facture")
@Results({
	@Result(name="success",location="/pages/facture.jsp"),
	@Result(name="input",location="/pages/facture.jsp"),
	@Result(name="none",location="/pages/facture.jsp")
	})
public class FactureAction extends ActionSupport implements Preparable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String numFacture;
	private String montant;
	private FactureDAO factureDAO=new FactureDAO();
	private TransactionDAO transactionDAO=new TransactionDAO();
	private List<Facture> listeFactures;
	private String searchTerm;
	
	

	public FactureAction() {
		super();
	}

	public String getNumFacture() {
		return numFacture;
	}
	
	@RequiredStringValidator(message="Ce champ ${getText(fieldName)}  est obligatoire !")
	public void setNumFacture(String numFacture) {
		this.numFacture = numFacture;
	}

	@RequiredStringValidator(message="Ce champ ${getText(fieldName)}  est obligatoire !")
	public String getMontant() {
		return montant;
	}

	public void setMontant(String montant) {
		this.montant = montant;
	}
	
	
	public String getSearchTerm() {
		return searchTerm;
	}

	public void setSearchTerm(String searchTerm) {
		this.searchTerm = searchTerm;
	}

	@Action(value="index")
	public String execute(){
		return NONE;
	}
	
	@Action(value="addOrSaveFacture")
	public String addOrSaveFacture(){
		
		factureDAO.addOrSaveFacture(new Facture(numFacture,Double.parseDouble(montant)));
		listeFactures=factureDAO.getFactures();
		numFacture="";
		montant="";
		return SUCCESS;
		
	}
	
	@Action(value="deleteFacture")
	public String deleteFacture(){
		
		if(transactionDAO.getTransactionsByFacture(numFacture).size()>0) {
			for(Transaction t:transactionDAO.getTransactionsByFacture(numFacture)) {
				transactionDAO.deleteTransaction(t.getId());
			}
		}
		factureDAO.deleteFacture(numFacture);
		listeFactures=factureDAO.getFactures();	
		numFacture="";
		montant="";
		return SUCCESS;		
	}
	
	@Action(value="search")
	public String search(){						
		listeFactures.clear();
		listeFactures=factureDAO.searchFacture(searchTerm);	
		searchTerm="";
		numFacture="";
		montant="";
		return SUCCESS;		
	}
	
	
	

	public List<Facture> getListeFactures() {
		return listeFactures;
	}

	public void setListeFactures(List<Facture> listeFactures) {
		this.listeFactures = listeFactures;
	}

	@Override
	public void prepare() throws Exception {
		// TODO Auto-generated method stub
		
		//charger liste des factures
		this.listeFactures=factureDAO.getFactures();
		
	}

}
