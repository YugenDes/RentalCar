package database;

import database.interfaces.CategoriaDAO;
import database.interfaces.NoleggioDAO;
import database.interfaces.UtenteDAO;
import database.interfaces.VeicoloDAO;
import database.jpadao.JpaDAOFactory;

public  abstract class DAOFactory {
	public abstract UtenteDAO getUtenteDAO();
	public abstract VeicoloDAO getVeicoloDAO();
	public abstract CategoriaDAO getCategoriaDAO();
	public abstract NoleggioDAO getNoleggioDAO();
	public abstract DAOFactory getFactory();
	
	public static DAOFactory getJpaDaoFactory() {
		return new JpaDAOFactory();
	}
}
