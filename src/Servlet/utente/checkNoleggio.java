package Servlet.utente;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import database.DAOFactory;

@WebServlet("/utente/checkNoleggio")
public class checkNoleggio extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DAOFactory db;

	public checkNoleggio() {
		super();
		db = DAOFactory.getJpaDaoFactory();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String dataInizio = request.getParameter("dataInizio");
		String oraInizio = request.getParameter("oraInizio");
		String dataFine = request.getParameter("dataFine");
		String oraFine = request.getParameter("oraFine");
		
		if(dataInizio.equals("") || oraInizio.equals("") || dataFine.equals("") || oraFine.equals("")) {
			request.setAttribute("errore", "1");
			request.getRequestDispatcher(request.getServletPath()+"/utente/displayDate").forward(request, response);
		}
		
		LocalDateTime LocaldateInizioParsata = LocalDateTime.parse(dataInizio+"T"+oraInizio);
		LocalDateTime LocaldateFineParsata = LocalDateTime.parse(dataFine+"T"+oraFine);
		
		Duration p = Duration.between(LocaldateInizioParsata, LocaldateFineParsata);
		if(p.getSeconds()<=600) {
			request.setAttribute("errore", "3");
			response.sendRedirect(request.getContextPath()+"/utente/displayDate");
		}else {
			HttpSession session = request.getSession();
			session.setAttribute("dataInizio", LocaldateInizioParsata);
			session.setAttribute("dataFine", LocaldateFineParsata);
			session.setAttribute("categorie", db.getNoleggioDAO().getCatgorieTime(LocaldateInizioParsata,LocaldateFineParsata));
			response.sendRedirect(request.getContextPath()+"/utente/categorie");
		}
		
		
		
	}

}
