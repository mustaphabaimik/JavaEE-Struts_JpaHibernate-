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
import javax.transaction.UserTransaction;

import model.Cartecredit;
import model.Facture;
import model.Proprietaire;

@PersistenceContext(name="projet",
type=PersistenceContextType.EXTENDED)
@TransactionManagement(TransactionManagementType.BEAN)
public class FactureDAO {
	
	private EntityManager em;
	private UserTransaction trans;
	
	public FactureDAO() {
		try {
			Context ctx = new InitialContext();
			em = (EntityManager) ctx.lookup("java:comp/env/persistence/myHibernateJPA");
			trans = (UserTransaction) ctx.lookup("java:comp/env/UserTransaction");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public int addOrSaveFacture(Facture f){
		if(em.find(Facture.class, f.getNumFacture()) == null){
			try {
				trans.begin();
				Date d=new Date();
				DateFormat df=new SimpleDateFormat("yyyy-MM-dd"); 
				String strdate=df.format(d);
				f.setDateFacture(strdate);
				em.persist(f);
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
			f.setDateFacture(em.find(Facture.class, f.getNumFacture()).getDateFacture());
			return updateFacture(f);
		}
	}
	
	public int deleteFacture(String numFacture){
		if(em.find(Facture.class, numFacture) != null){
			try {
				trans.begin();
				em.remove(em.find(Facture.class, numFacture));
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
	
	
	public int updateFacture(Facture f){
		if(em.find(Facture.class, f.getNumFacture()) != null){
			try {
				trans.begin();
				em.merge(f);
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
	
	public List<Facture> getFactures(){
		return em.createNamedQuery("Facture.findAll").getResultList();
	
	}
	
	public List<Facture> searchFacture(String numFacture) {
		return em.createQuery("select f from Facture f where f.numFacture like '"+numFacture+"'").getResultList();
		
	}
   

}
