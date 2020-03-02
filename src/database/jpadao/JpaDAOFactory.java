package database.jpadao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import database.DAOFactory;
import database.interfaces.CategoriaDAO;
import database.interfaces.NoleggioDAO;
import database.interfaces.UtenteDAO;
import database.interfaces.VeicoloDAO;
//import database.jdbcdao.JdbcDAOFactory;

public class JpaDAOFactory extends DAOFactory {

	@Override
	public UtenteDAO getUtenteDAO() {
		return JpaUtenteDAO.getIstance();
	}

	@Override
	public VeicoloDAO getVeicoloDAO() {
		return JpaVeicoloDAO.getIstance();
	}

	@Override
	public CategoriaDAO getCategoriaDAO() {
		return JpaCategoriaDAO.getIstance();
	}

	@Override
	public NoleggioDAO getNoleggioDAO() {
		return JpaNoleggioDAO.getIstance();
	}

	@Override
	public DAOFactory getFactory() {
		return new JpaDAOFactory();
	}

	public static EntityManager getManager() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		EntityManagerFactory factory = 
				Persistence.createEntityManagerFactory("Autonoleggio");
		EntityManager manager = factory.createEntityManager();
		return manager;
	}
}
