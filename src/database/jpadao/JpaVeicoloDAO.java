package database.jpadao;

import java.util.ArrayList;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.apache.catalina.tribes.group.interceptors.TwoPhaseCommitInterceptor.MapEntry;

import database.interfaces.VeicoloDAO;
import model.Veicoli;


public class JpaVeicoloDAO implements VeicoloDAO{
	@Override
	public ArrayList<Veicoli> getVeicoli() {
		return new ArrayList<Veicoli>(JpaDAOFactory.getManager().createNamedQuery("Veicoli.findAll").getResultList());
	}

	@Override
	public Veicoli getVeicoloPerTarga(String targa) {
		Query query = JpaDAOFactory.getManager().createQuery("SELECT v FROM Veicoli v WHERE v.targa= :targa");
		query.setParameter("targa", targa);
		return (Veicoli)query.getSingleResult();
	}

	@Override
	public ArrayList<Veicoli> getVeicoliPerCategoria(String cat) {
		/*Query query = JpaDAOFactory.getManager().createNativeQuery("SELECT * FROM Veicoli v "
				+ "LEFT JOIN Categorie c ON v.idCategoria = c.idCategoria"
				+ "WHERE c.nomeCategoria = cat ");*/
		Query query = JpaDAOFactory.getManager().createQuery("SELECT v FROM Veicoli v , Categorie c WHERE c.nomeCategoria = :cat");
		query.setParameter("cat", cat);
		return new ArrayList<Veicoli>(query.getResultList());
	}

	@Override
	public boolean addVeicolo(Veicoli obj) {
		EntityManager manager = JpaDAOFactory.getManager();
		EntityTransaction transaction = manager.getTransaction();
		transaction.begin();
		manager.persist(obj);
		transaction.commit();
		return true;
	}

	@Override
	public boolean removeVeicolo(Veicoli obj) {
		EntityManager manager = JpaDAOFactory.getManager();
		EntityTransaction transaction = manager.getTransaction();
		transaction.begin();
		manager.merge(obj);
		manager.remove(obj);
		transaction.commit();
		return true;
	}

	@Override
	public boolean modifyVeicolo(Veicoli obj) {
		EntityManager manager = JpaDAOFactory.getManager();
		EntityTransaction transaction = manager.getTransaction();
		transaction.begin();	   
		manager.merge(obj);
		transaction.commit();
		return true;
	}
	

	private static JpaVeicoloDAO istance;
	
	private JpaVeicoloDAO() {
		
	}
	
	public static JpaVeicoloDAO getIstance() {
		if(istance==null) istance=new JpaVeicoloDAO();
		return istance;
	}
}
