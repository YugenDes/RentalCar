package Servlet;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import crypto.Crypto;
import database.DAOFactory;
import model.Utente;

public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DAOFactory db;

	public Login() {
		super();
		db = DAOFactory.getJpaDaoFactory();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		/*String tempEmail ="" ;
		String tempPass ="";
		
		HttpSession session = request.getSession();
		Cookie[] cookies = request.getCookies();
		if (cookies != null && session.getAttribute("auth")!=null) {
			
			if(session.getAttribute("auth").equals("true")) {
				session.setAttribute("email", tempEmail);
				session.setAttribute("noleggiEff", db.getNoleggioDAO().getNoleggio(db.getUtenteDAO().getUtente((String)session.getAttribute("email"))));
				request.getRequestDispatcher("/utente/planNoleggio").forward(request, response);
				return;
			}
		}else {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("user_email"))
					tempEmail = Crypto.deCrypt(cookie.getValue());
				if (cookie.getName().equals("user_auth"))
					tempPass = Crypto.deCrypt(cookie.getValue());
			}
			if(tempEmail.equals("") || tempPass.equals("")) {
				request.getRequestDispatcher("/login.jsp").forward(request, response);
			}else {
				if (auth(tempEmail, tempPass)) {
					session.setAttribute("noleggiEff", db.getNoleggioDAO().getNoleggio(db.getUtenteDAO().getUtente((String)session.getAttribute("email"))));
					session.setAttribute("email", tempEmail);
					request.setAttribute("email", tempEmail);
					session.setAttribute("noleggiEff", db.getNoleggioDAO().getNoleggio(db.getUtenteDAO().getUtente((String)session.getAttribute("email"))));
					request.getRequestDispatcher("/utente/planNoleggio").forward(request, response);
				}else {
					request.getRequestDispatcher("/login.jsp").forward(request, response);
				}
			}
			
		}	*/
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String emailAdmin = this.getServletContext().getInitParameter("emailAdmin");
		String passAdmin = this.getServletContext().getInitParameter("passAdmin");

		HttpSession session = request.getSession();
		Cookie[] cookies = request.getCookies();

		String tempEmail = request.getParameter("email");
		String tempPass = request.getParameter("password");

		if (tempEmail.equals(emailAdmin) && tempPass.equals(passAdmin)) {
			request.getSession().setAttribute("type", "admin");
			request.getSession().setAttribute("auth", "true");
			request.getSession().setAttribute("email", emailAdmin);
			request.getSession().setAttribute("utenti", db.getUtenteDAO().getUtenti());
			request.getSession().setAttribute("veicoli", db.getVeicoloDAO().getVeicoli());
			request.getSession().setAttribute("categorie", db.getCategoriaDAO().getCategorie());
			response.sendRedirect(request.getContextPath() + "/admin/home");
		} else {
			if (auth(tempEmail, tempPass)) {
				Cookie user = new Cookie("user_email", Crypto.generateEncryption(tempEmail));
				Cookie pass = new Cookie("user_auth", Crypto.generateEncryption(tempPass));
				user.setMaxAge(120);
				pass.setMaxAge(120);
				response.addCookie(user);
				response.addCookie(pass);
				request.getSession().setAttribute("type", "utente");
				session.setAttribute("auth", "true");
				session.setMaxInactiveInterval(120);
				session.setAttribute("email", tempEmail);
				session.setAttribute("noleggiEff", db.getNoleggioDAO().getNoleggio(db.getUtenteDAO().getUtente((String)session.getAttribute("email"))));
				response.sendRedirect(request.getContextPath() + "/utente/displayDate");
			} else {
				response.sendRedirect("index.jsp?errore=1");
			}
		}
	}

	private boolean auth(String email, String pass) {
		ArrayList<Utente> utenti = db.getUtenteDAO().getUtenti();
		for (int i = 0; i < utenti.size(); i++) {
			if (utenti.get(i).getEmail().equals(email) && utenti.get(i).getPassword().equals(pass))
				return true;
		}
		return false;
	}

}
