package Servlet.admin;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.DAOFactory;
import model.Categorie;
import model.Veicoli;


@WebServlet("/admin/modifyVeicolo")
public class modifyVeicolo extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DAOFactory db;

	public modifyVeicolo() {
		super();
		db = DAOFactory.getJpaDaoFactory();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getSession().setAttribute("veicoloDaModificare", db.getVeicoloDAO().getVeicoloPerTarga(request.getParameter("targa")));
		request.getRequestDispatcher("/admin/ModifyVeicolo").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String tryTarga=request.getParameter("regTarga");
		String tryCategoria=request.getParameter("regCategoria");
		String tryMarca=request.getParameter("regMarca");
		String tryModello=request.getParameter("regModello");
		String tryNPosti=request.getParameter("regNPosti");
		String tryCapBag=request.getParameter("regCapBag");
		String tryColore=request.getParameter("regColore");
		System.out.println(tryNPosti);
		System.out.println(tryCapBag);
		int nposti=0;
		double capBag=0;
	
		try {
			 nposti = Integer.parseInt(tryNPosti);
			 capBag = Double.parseDouble(tryCapBag);
		} catch (Exception e) {
			request.setAttribute("errore", "2");
			request.getRequestDispatcher("/admin/ModifyVeicolo").forward(request, response);
			return;
		}
		try {
			db.getCategoriaDAO().getCategoria(tryCategoria);
		} catch (Exception e) {
			request.setAttribute("errore", "1");
			request.getRequestDispatcher("/admin/ModifyVeicolo").forward(request, response);
			return;
		}
		
		Veicoli tryVeicolo = new Veicoli();
		tryVeicolo.setTarga(tryTarga);
		tryVeicolo.setCategorie(db.getCategoriaDAO().getCategoria(tryCategoria));
		tryVeicolo.setMarca(tryMarca);
		tryVeicolo.setModello(tryModello);
		tryVeicolo.setNPosti(nposti);
		tryVeicolo.setCapacitaBagagliaio(capBag);
		tryVeicolo.setColore(tryColore);
		db.getVeicoloDAO().modifyVeicolo(tryVeicolo);
		request.getSession().setAttribute("utenti", db.getUtenteDAO().getUtenti());
		request.getSession().setAttribute("veicoli", db.getVeicoloDAO().getVeicoli());
		request.getSession().setAttribute("categorie", db.getCategoriaDAO().getCategorie());
		request.getRequestDispatcher("/admin/home").forward(request, response);
		request.getSession().removeAttribute("veicoloDaModificare");
		return;
	}

}
