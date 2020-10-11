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
import dao.ProprietaireDAO;
import dao.TransactionDAO;
import model.Cartecredit;
import model.Proprietaire;
import model.Transaction;


@Namespace("/credit")
@Results({
	@Result(name="success",location="/pages/credits.jsp"),
	@Result(name="input",location="/pages/credits.jsp"),
	@Result(name="none",location="/pages/credits.jsp")
	})
public class CartesCreditAction extends ActionSupport implements Preparable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String numCarte;
	private ProprietaireDAO propDAO= new ProprietaireDAO();
	private List<Proprietaire> listeProprietaires;
	private CartesCreditDAO creditDAO = new CartesCreditDAO();
	private TransactionDAO transactionDAO=new TransactionDAO();
	private List<Object[]> listeCartesProprietaires;
	private String propSelected;
	private String searchTerm;
	
	public CartesCreditAction() {
		super();
	}
	
	
	public List<Object[]> getListeCartesProprietaires() {
		return listeCartesProprietaires;
	}

   
	public String getSearchTerm() {
		return searchTerm;
	}


	public void setSearchTerm(String searchTerm) {
		this.searchTerm = searchTerm;
	}


	public void setListeCartesProprietaires(List<Object[]> listeCartesProprietaires) {
		this.listeCartesProprietaires = listeCartesProprietaires;
	}


	@RequiredStringValidator(message="Ce champ est obligatoire !")
	public String getNumCarte() {
		return numCarte;
	}
	public void setNumCarte(String numCarte) {
		this.numCarte = numCarte;
	}
	
	
	public List<Proprietaire> getListeProprietaires() {
		return listeProprietaires;
	}
	public void setListeProprietaires(List<Proprietaire> listeProprietaires) {
		this.listeProprietaires = listeProprietaires;
	}
	
	@RequiredStringValidator(message="Ce champ ${getText(fieldName)} est obligatoire !")
	public String getPropSelected() {
		return propSelected;
	}
	public void setPropSelected(String propSelected) {
		this.propSelected = propSelected;
	}
	
	
	@Action(value="index")
	public String execute(){
		return NONE;
	}
	
	@Action(value="addOrSaveCarte")
	public String addOrSaveCarte(){
		creditDAO.addOrSaveCarte(new Cartecredit(numCarte),Integer.parseInt(propSelected));
		//charger la listes cartes et prop
		listeCartesProprietaires = creditDAO.getListeCartesProprietaires();	
		numCarte="";
		propSelected="";
		return SUCCESS;
		
	}
	
	@Action(value="deleteCarte")
	public String deleteCarte(){	
		if(transactionDAO.getTransactionsByCarte(numCarte).size()>0) {
			 for(Transaction tr:transactionDAO.getTransactionsByCarte(numCarte)) {
				 transactionDAO.deleteTransaction(tr.getId());
			 }
		 }
		creditDAO.deleteCarte(numCarte);
		listeCartesProprietaires = creditDAO.getListeCartesProprietaires();	
		numCarte="";
		propSelected="";
		return SUCCESS;		
	}
	
	@Action(value="search")
	public String search(){		
		listeCartesProprietaires.clear();
		listeCartesProprietaires = creditDAO.searchcarte(searchTerm);	
		searchTerm="";
		numCarte="";
		propSelected="";
		return SUCCESS;		
	}

	@Override
	public void prepare() throws Exception {
		// TODO Auto-generated method stub
		// Charger la liste des proprietaires
				listeProprietaires = propDAO.getProprietaires();
				System.out.println(listeProprietaires);
		//charger la listes cartes et prop
				listeCartesProprietaires = creditDAO.getListeCartesProprietaires();
	}
	
	

}
