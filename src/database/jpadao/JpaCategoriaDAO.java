package database.jpadao;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import database.interfaces.CategoriaDAO;
import model.Categorie;

public class JpaCategoriaDAO implements CategoriaDAO{
	private static JpaCategoriaDAO istance;
	
	private JpaCategoriaDAO() {
		
	}
	
	public static JpaCategoriaDAO getIstance() {
		if(istance==null) istance=new JpaCategoriaDAO();
		return istance;
	}

	@Override
	public ArrayList<Categorie> getCategorie() {
		return new ArrayList<Categorie>(JpaDAOFactory.getManager().createNamedQuery("Categorie.findAll").getResultList());
	}
	
	public Categorie getCategoria(String nome) {
		Query query = JpaDAOFactory.getManager().createQuery("SELECT c FROM  Categorie c WHERE c.nomeCategoria = :nome");
		query.setParameter("nome", nome);
		return ((Categorie)query.getSingleResult());
	}
	
	public Categorie getCategoria(int id) {
		Query query = JpaDAOFactory.getManager().createQuery("SELECT c FROM  Categorie c WHERE c.idCategoria = :id");
		query.setParameter("id", id);
		return ((Categorie)query.getSingleResult());
	}


	@Override
	public boolean addCategoria(Categorie obj) {
		EntityManager manager = JpaDAOFactory.getManager();
		EntityTransaction transaction = manager.getTransaction();
		transaction.begin();
		manager.persist(obj);
		transaction.commit();
		return true;
	}

}
