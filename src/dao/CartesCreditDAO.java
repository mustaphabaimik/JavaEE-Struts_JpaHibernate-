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

import model.Cartecredit;
import model.Proprietaire;

@PersistenceContext(name="projet",
type=PersistenceContextType.EXTENDED)
@TransactionManagement(TransactionManagementType.BEAN)
public class CartesCreditDAO {
	
	private EntityManager em;
	private UserTransaction trans;
	
	public CartesCreditDAO() {
		try {
			Context ctx = new InitialContext();
			em = (EntityManager) ctx.lookup("java:comp/env/persistence/myHibernateJPA");
			trans = (UserTransaction) ctx.lookup("java:comp/env/UserTransaction");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/*public int addCarteCredit(Cartecredit cr, int id){
		
		try {
			if(em.find(Cartecredit.class, cr.getNumCarte()) == null){
				trans.begin();
				cr.setProprietaire(em.find(Proprietaire.class, id));
				em.persist(cr);
				trans.commit();
			}
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
	}*/
	
	public int addOrSaveCarte(Cartecredit c,int id){
		if(em.find(Cartecredit.class, c.getNumCarte()) == null){
			try {
				trans.begin();
				c.setProprietaire(em.find(Proprietaire.class, id));
				em.persist(c);
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
			return updateCarte(c,id);
		}
	}
	
	
	public int deleteCarte(String id){
		if(em.find(Cartecredit.class, id) != null){
			try {
				trans.begin();
				em.remove(em.find(Cartecredit.class, id));
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
	
	public int updateCarte(Cartecredit c,int id){
		if(em.find(Cartecredit.class, c.getNumCarte()) != null){
			try {
				trans.begin();
				c.setProprietaire(em.find(Proprietaire.class, id));
				em.merge(c);
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
	
	
	public List<Object[]> getListeCartesProprietaires(){
		return em.createQuery("select cr.numCarte, p.nomPrenom "
				+ "from Proprietaire p join p.cartecredits cr").getResultList();
	}
	
	public List<Object[]> searchcarte(String numcarte){
		return em.createQuery("select cr.numCarte, p.nomPrenom "
				+ "from Proprietaire p join p.cartecredits cr where cr.numCarte='"+numcarte+"'").getResultList();
	}
	
	public List<Cartecredit> getcartesbyprop(String id){
		return em.createQuery("select c from Cartecredit c where c.proprietaire.id='"+Integer.parseInt(id)+"'").getResultList();
	}

}
