package Servlet;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.DAOFactory;

import model.Utente;

@WebServlet("/Registrazione")
public class Registrazione extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DAOFactory db;
    public Registrazione() {
        super();
        db=DAOFactory.getJpaDaoFactory();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String tryEmail=request.getParameter("regEmail");
		String tryPassword=request.getParameter("regPassword");
		String tryNome=request.getParameter("regNome");
		String tryCognome=request.getParameter("regCognome");
		String tryDateS=request.getParameter("regDate");
		LocalDate tryDate;
		 if(tryDateS== null || tryDateS=="") {
			request.setAttribute("errore", "2");request.getRequestDispatcher("Registrazione.jsp").forward(request, response);
			return;
		}else {
			tryDate=LocalDate.parse(tryDateS);
			Period intervalPeriod = Period.between(tryDate, LocalDate.now());
			if(intervalPeriod.getYears()<18 || intervalPeriod.getYears()>65) {
				request.setAttribute("errore", "4");request.getRequestDispatcher("Registrazione.jsp").forward(request, response);
				return;
				}		
		}
		if(tryEmail != null && tryPassword!=null && tryNome != null && tryCognome!=null && tryDate!=null) {
			if(!tryEmail.equals("") || !tryPassword.equals("") || !tryNome.equals("") || !tryCognome.equals("")) {
				if(check(tryEmail)) {
					db.getUtenteDAO().addUtente(new Utente(tryEmail, tryPassword,tryNome,tryCognome,tryDateS));
					request.getSession().setAttribute("email", tryEmail);
					//TODO Non funziona il login dopo la registrazione
					response.sendRedirect(request.getContextPath() + "/");
					}else {request.setAttribute("errore", "3");request.getRequestDispatcher("Registrazione.jsp").forward(request, response);}
				}else {request.setAttribute("errore", "2");request.getRequestDispatcher("Registrazione.jsp").forward(request, response);}
			}else {request.setAttribute("errore", "1");request.getRequestDispatcher("Registrazione.jsp").forward(request, response);}
		}
	
	public boolean check(String email) {
		ArrayList<Utente> utenti = db.getUtenteDAO().getUtenti();
		for(int i=0;i<utenti.size();i++) {
			if(utenti.get(i).getEmail().equalsIgnoreCase(email)) return false;
		}
		return true;
	}

}
