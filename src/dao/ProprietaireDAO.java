package dao;

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

import model.Proprietaire;




@PersistenceContext(name="projet",
type=PersistenceContextType.EXTENDED)
@TransactionManagement(TransactionManagementType.BEAN)
public class ProprietaireDAO {
      
	private EntityManager em;
	private UserTransaction trans;
	public ProprietaireDAO() {
		try {
			Context ctx = new InitialContext();
			em = (EntityManager) ctx.lookup("java:comp/env/persistence/myHibernateJPA");
			trans = (UserTransaction) ctx.lookup("java:comp/env/UserTransaction");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public int addOrSaveProprietaire(Proprietaire p){
		if(em.find(Proprietaire.class, p.getId()) == null){
			try {
				trans.begin();
				em.persist(p);
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
			return updateProprietaire(p);
		}
	}
	
	
	public int deleteProprietaire(int id){
		if(em.find(Proprietaire.class, id) != null){
			try {
				trans.begin();
				
				em.remove(em.find(Proprietaire.class, id));
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
	
	
	public int updateProprietaire(Proprietaire p){
		if(em.find(Proprietaire.class, p.getId()) != null){
			try {
				trans.begin();
				em.merge(p);
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
	
	
	public List<Proprietaire> getProprietaires(){
		return em.createNamedQuery("Proprietaire.findAll").getResultList();
		//return em.createQuery("select p from Proprietaire p").getResultList();
	}
	
	public List<Proprietaire> search(String np) {
		return em.createQuery("select p from Proprietaire p where p.nomPrenom like '"+np+"'").getResultList();
		
	}
	
	
}
