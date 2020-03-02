package Servlet.utente;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import database.DAOFactory;

import model.Noleggi;
import model.Veicoli;

@WebServlet("/utente/reviewNoleggio")
public class reviewNoleggio extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DAOFactory db;

	public reviewNoleggio() {
		super();
		db = DAOFactory.getJpaDaoFactory();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();

		String targa = request.getParameter("targa");
		String email = (String) session.getAttribute("email");

		LocalDateTime inizio = (LocalDateTime) session.getAttribute("dataInizio");
		LocalDateTime fine = (LocalDateTime) session.getAttribute("dataFine");

		Date inizioNoleggio = java.util.Date.from(inizio.atZone(ZoneId.systemDefault()).toInstant());
		Date fineNoleggio = java.util.Date.from(fine.atZone(ZoneId.systemDefault()).toInstant());

		Noleggi noleggio = new Noleggi();

		noleggio.setUtente(db.getUtenteDAO().getUtente(email));
		Veicoli veicolo = db.getVeicoloDAO().getVeicoloPerTarga(targa);
		noleggio.setVeicoli(veicolo);
		noleggio.setInizioNoleggio(inizioNoleggio);
		noleggio.setFineNoleggio(fineNoleggio);
		double importoDovuto = Noleggi.calcolaImportoDovuto(inizio.toLocalDate(), fine.toLocalDate(),
				veicolo.getCategorie());
		noleggio.setImportoDovuto(importoDovuto);
		db.getNoleggioDAO().insertTempNoleggio(noleggio);
		session.setAttribute("noleggio", noleggio);
		response.sendRedirect(request.getContextPath()+"/utente/startNoleggio");
	}

}
