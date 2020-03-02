package database.jpadao;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import database.interfaces.NoleggioDAO;
import model.Categorie;
import model.Noleggi;
import model.Utente;
import model.Veicoli;


public class JpaNoleggioDAO implements NoleggioDAO {
	
	private static JpaNoleggioDAO istance;
	
	private JpaNoleggioDAO() {
	}
	
	public static JpaNoleggioDAO getIstance() {
		if(istance==null) istance=new JpaNoleggioDAO();
		return istance;
	}
	
	
	@Override
	public ArrayList<Noleggi> getNoleggio() {
		Query query = JpaDAOFactory.getManager().createNamedQuery("Noleggi.findAll");
		return new ArrayList<Noleggi>(query.getResultList());
	}

	@Override
	public Noleggi getNoleggio(int id) {
		Query query = JpaDAOFactory.getManager().createQuery("SELECT n FROM Noleggi n WHERE n.idNoleggio = :id");
		query.setParameter("id", id);
		return (Noleggi)query.getSingleResult();
	}
	@Override
	public Noleggi getNoleggio(Date date,String targa) {
		Query query = JpaDAOFactory.getManager().createQuery("SELECT n FROM Noleggi n WHERE n.inizioNoleggio = :date AND n.veicoli.targa= :targa ");
		query.setParameter("date", date);
		query.setParameter("targa", targa);
		return (Noleggi)query.getSingleResult();
	}
	
	public ArrayList<Noleggi> getNoleggio(Utente utente) {
		Query query = JpaDAOFactory.getManager().createQuery("SELECT n FROM Noleggi n WHERE n.utente= :value ");
		query.setParameter("value", utente);
		return new ArrayList<Noleggi>(query.getResultList());
	}

	@Override
	public boolean insertTempNoleggio(Noleggi obj) {
		EntityManager manager = JpaDAOFactory.getManager();
		EntityTransaction transaction = manager.getTransaction();
		transaction.begin();
		manager.persist(obj);
		transaction.commit();
		return true;
	}
	
	@Override
	public ArrayList<Noleggi> getNoleggioGiorno(Date date) {
		Query query = JpaDAOFactory.getManager().createNativeQuery("SELECT * FROM Noleggi n WHERE n.inizioNoleggio like ?",Noleggi.class);
		LocalDateTime dateTime= date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String data = dateTime.format(formatter);
		System.out.println(data);
		query.setParameter(1, data+"%");
		return new ArrayList<Noleggi>(query.getResultList());
	}

	@Override
	public ArrayList<Veicoli> getMacchineTime(LocalDateTime inizio, LocalDateTime fine,int idCategoria) {
		Query query = JpaDAOFactory.getManager().createNativeQuery("SELECT * "
				+ "From Veicoli "
				+ "Where Veicoli.idCategoria = ?  AND Veicoli.targa Not In "
				+ "(Select Veicoli.targa From Veicoli "
				+ "Join Noleggi ON Veicoli.targa = Noleggi.targa "
				+ "Where Noleggi.inizioNoleggio <= ?  "
				+ "And Noleggi.fineNoleggio >= ?) "
				+ "Order By Veicoli.idCategoria",Veicoli.class);
		query.setParameter(1, idCategoria);
		query.setParameter(2, fine);
		query.setParameter(3, inizio);
		return new ArrayList<Veicoli>(query.getResultList());
	}
	
	@Override
	public ArrayList<Categorie> getCatgorieTime(LocalDateTime inizio, LocalDateTime fine) {
		Query query = JpaDAOFactory.getManager().createNativeQuery(""
				+ "SELECT c.idCategoria,c.tariffaGiornaliera,c.tariffaSettimanale,c.tariffaMensile,c.nomeCategoria " 
				+ "From Veicoli JOIN Categorie c ON Veicoli.idCategoria = c.idCategoria "
				+ "Where Veicoli.targa Not In "
				+ "(Select Veicoli.targa From Veicoli "
				+ "Join Noleggi ON Veicoli.targa = Noleggi.targa "
				+ "Where Noleggi.inizioNoleggio <= ?  "
				+ "And Noleggi.fineNoleggio >= ?) "
				+ "group By Veicoli.idCategoria",Categorie.class);
		query.setParameter(1, fine);
		query.setParameter(2, inizio);
		return new ArrayList<Categorie>(query.getResultList());
	}
	
	
}
