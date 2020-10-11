package actions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;
import com.opensymphony.xwork2.validator.annotations.RegexFieldValidator;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.StringLengthFieldValidator;

import dao.CartesCreditDAO;
import dao.ProprietaireDAO;
import dao.TransactionDAO;
import model.Cartecredit;
import model.Proprietaire;
import model.Transaction;

@Namespace("/prop")
@Results({
	@Result(name="none",location="/pages/proprietaires.jsp"),
	@Result(name="input",location="/pages/proprietaires.jsp"),
	@Result(name="success",location="/pages/proprietaires.jsp")
	})
public class ProprietairesAction extends ActionSupport implements Preparable{
	
	private static final long serialVersionUID = 1L;
	private String idProp;
	private String nomPrenom;
	private ProprietaireDAO propDAO= new ProprietaireDAO();
	private CartesCreditDAO creditDAO=new CartesCreditDAO();
	private TransactionDAO transactionDAO=new TransactionDAO();
	private List<Proprietaire> listeProprietaires;
	private String searchTerm;
	
	
	public ProprietairesAction() {
		super();
	}
	
	@Action(value="index")
	public String execute(){
		return NONE;
	}
	
	
	
	@Action(value="addOrSaveProp")
	public String addOrSaveProp(){
		propDAO.addOrSaveProprietaire(new Proprietaire(Integer.parseInt(idProp),nomPrenom));
		System.out.println(propDAO.getProprietaires());
		listeProprietaires=propDAO.getProprietaires();
		this.idProp="";
		this.nomPrenom="";
		return SUCCESS;		
	}
	
	@Action(value="deleteProp")
	public String deleteProp(){
		
		if(creditDAO.getcartesbyprop(idProp).size()>0) {
			for(Cartecredit c:creditDAO.getcartesbyprop(idProp)) {
				 if(transactionDAO.getTransactionsByCarte(c.getNumCarte()).size()>0) {
					 for(Transaction tr:transactionDAO.getTransactionsByCarte(c.getNumCarte())) {
						 transactionDAO.deleteTransaction(tr.getId());
					 }
				 }
				 creditDAO.deleteCarte(c.getNumCarte());
			}
		}
		
		propDAO.deleteProprietaire(Integer.parseInt(idProp));		
		listeProprietaires=propDAO.getProprietaires();
		this.idProp="";
		this.nomPrenom="";
		return SUCCESS;
		
	}
	
	@Action(value="search")
	public String search(){	
		listeProprietaires.clear();	
		idProp="";
		nomPrenom="";
		listeProprietaires=propDAO.search(searchTerm);
		searchTerm="";
		return SUCCESS;	
	}

	
	public String getSearchTerm() {
		return searchTerm;
	}

	public void setSearchTerm(String searchTerm) {
		this.searchTerm = searchTerm;
	}

	@RequiredStringValidator(message="Ce champ ${getText(fieldName)} est obligatoire !")
	public String getIdProp() {
		return idProp;
	}

	public void setIdProp(String idProp) {
		this.idProp = idProp;
	}

	@RequiredStringValidator(message="Ce champ ${getText(fieldName)}  est obligatoire !")
	public String getNomPrenom() {
		return nomPrenom;
	}

	public void setNomPrenom(String nomPrenom) {
		this.nomPrenom = nomPrenom;
	}

	public List<Proprietaire> getListeProprietaires() {
		return listeProprietaires;
	}

	public void setListeProprietaires(List<Proprietaire> listeProprietaires) {
		this.listeProprietaires = listeProprietaires;
	}

	@Override
	public void prepare() throws Exception {
		// TODO Auto-generated method stub
		//Chargement de la liste des proprietaires !!!
		listeProprietaires=propDAO.getProprietaires();    
		
	}
	
	
	
	

}
