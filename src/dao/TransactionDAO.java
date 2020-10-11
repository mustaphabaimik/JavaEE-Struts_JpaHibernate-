package dao;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.TypedQuery;
import javax.transaction.UserTransaction;

import model.Cartecredit;
import model.Facture;
import model.Proprietaire;
import model.Transaction;

@PersistenceContext(name="projet",
type=PersistenceContextType.EXTENDED)
@TransactionManagement(TransactionManagementType.BEAN)
public class TransactionDAO {
	
	private EntityManager em;
	private UserTransaction trans;
	
	public TransactionDAO() {
		try {
			Context ctx = new InitialContext();
			em = (EntityManager) ctx.lookup("java:comp/env/persistence/myHibernateJPA");
			trans = (UserTransaction) ctx.lookup("java:comp/env/UserTransaction");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public int addOrSaveTransaction(Transaction t,String numCarte,String numFacture){
		if(em.find(Transaction.class, t.getId()) == null){
			try {
				Date d=new Date();
				DateFormat df=new SimpleDateFormat("yyyy-MM-dd"); 
				String strdate=df.format(d);
				trans.begin();
				t.setCartecredit(em.find(Cartecredit.class, numCarte));
				t.setFacture(em.find(Facture.class, numFacture));
				t.setDatePaiement(strdate);
				em.persist(t);
				trans.commit();
				return 1;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				try {
					trans.rollback();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				return -1;
			}
		}else{
			t.setDatePaiement(em.find(Transaction.class, t.getId()).getDatePaiement());
			return updateTransaction(t,numCarte,numFacture);
		}
	}
	
	
	
	public int deleteTransaction(int id){
		if(em.find(Transaction.class, id) != null){
			try {
				trans.begin();
				em.remove(em.find(Transaction.class, id));
				trans.commit();
				return 1;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				try {
					trans.rollback();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				return -1;
			}
		}else return -1;
	}
	
	public int updateTransaction(Transaction t,String numCarte,String numFacture){
		if(em.find(Transaction.class, t.getId()) != null){
			try {
				trans.begin();
				t.setCartecredit(em.find(Cartecredit.class, numCarte));
				t.setFacture(em.find(Facture.class, numFacture));
				em.merge(t);
				trans.commit();
				return 1;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				try {
					trans.rollback();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				return -1;
			}
		}else return -1;
	}
	
	public List<Cartecredit> getCartes(){
		return em.createNamedQuery("Cartecredit.findAll").getResultList();
		//return em.createQuery("select p from Proprietaire p").getResultList();
	}
	
	public List<Object[]> getListeTransactions(){			
		return em.createQuery("select t.id,t.montant,t.datePaiement,c.numCarte,p.nomPrenom,f.numFacture,f.montant,f.dateFacture from Cartecredit c,Transaction t,Facture f,Proprietaire p where t.cartecredit.numCarte=c.numCarte and t.facture.numFacture=f.numFacture and p.id=c.proprietaire.id").getResultList();	
	}
	
	
	public List<Object[]> searchbyFac(String numFacture){
		return em.createQuery("select t.id,t.montant,t.datePaiement,c.numCarte,p.nomPrenom,f.numFacture,f.montant,f.dateFacture from Cartecredit c,Transaction t,Facture f,Proprietaire p where t.cartecredit.numCarte=c.numCarte and t.facture.numFacture=f.numFacture and p.id=c.proprietaire.id and t.facture.numFacture='"+numFacture+"'").getResultList();	
	}
	
	public List<Object[]> searchbyCarte(String numCarte){
		return em.createQuery("select t.id,t.montant,t.datePaiement,c.numCarte,p.nomPrenom,f.numFacture,f.montant,f.dateFacture from Cartecredit c,Transaction t,Facture f,Proprietaire p where t.cartecredit.numCarte=c.numCarte and t.facture.numFacture=f.numFacture and p.id=c.proprietaire.id and t.cartecredit.numCarte='"+numCarte+"'").getResultList();	
	}
	
	public List<Transaction> getTransactionsByCarte(String numCarte){
		return em.createQuery("select t from Transaction t where t.cartecredit.numCarte='"+numCarte+"'").getResultList();
	}
	
	public List<Transaction> getTransactionsByFacture(String numFacture){
		return em.createQuery("select t from Transaction t where t.facture.numFacture='"+numFacture+"'").getResultList();
	}
	

	
}
