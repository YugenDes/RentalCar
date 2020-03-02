package database.jpadao;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import database.interfaces.UtenteDAO;
import model.Utente;

public class JpaUtenteDAO implements UtenteDAO {
private static JpaUtenteDAO istance;
	
	private JpaUtenteDAO() {
		
	}
	
	public static JpaUtenteDAO getIstance() {
		if(istance==null) istance=new JpaUtenteDAO();
		return istance;
	}
	
	@Override
	public Integer getIdUtente(String email) {
		Query query = JpaDAOFactory.getManager().createQuery("SELECT u FROM  Utente u WHERE u.email = :email");
		query.setParameter("email", email);
		return ((Utente)query.getSingleResult()).getIdUtente();
	}
	
	@Override
	public Utente getUtente(Integer id) {
		Query query = JpaDAOFactory.getManager().createQuery("SELECT u FROM  Utente u WHERE u.idUtente = :id");
		query.setParameter("id", id);
		return ((Utente)query.getSingleResult());
	}

	@Override
	public ArrayList<Utente> getUtenti() {
		return new ArrayList<Utente>(JpaDAOFactory.getManager().createNamedQuery("Utente.findAll").getResultList());
	}
	
	@Override
	public boolean addUtente(Utente obj) {
		EntityManager manager = JpaDAOFactory.getManager();
		EntityTransaction transaction = manager.getTransaction();
		transaction.begin();
		manager.persist(obj);
		transaction.commit();
		return false;
	}	
	
	
	@Override
	public Utente getUtente(String email) {
		Query query = JpaDAOFactory.getManager().createQuery("SELECT u FROM  Utente u WHERE u.email = :email");
		query.setParameter("email", email);
		return ((Utente)query.getSingleResult());
	}
	
	
	
}
