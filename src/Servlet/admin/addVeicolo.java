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



@WebServlet("/admin/addVeicolo")
public class addVeicolo extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DAOFactory db;

    public addVeicolo() {
        super();
        db=DAOFactory.getJpaDaoFactory();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cat = request.getParameter("cat");
		request.setAttribute("cat", cat);
		request.getRequestDispatcher("/admin/AddVeicolo").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String tryTarga=request.getParameter("regTarga");
		String tryCategoria=request.getParameter("regCategoria");
		String tryMarca=request.getParameter("regMarca");
		String tryModello=request.getParameter("regModello");
		String tryNPosti=request.getParameter("regNPosti");
		String tryCapBag=request.getParameter("regCapBag");
		String tryColore=request.getParameter("regColore");
		int nposti=0;
		int capBag=0;
		if(!tryTarga.matches("[A-Z]{2}[0-9]{3}[A-Z]{2}")) {
			request.setAttribute("errore", "1");
			request.getRequestDispatcher("/admin/AddVeicolo").forward(request, response);
			return;
		}
		if(check(tryTarga)) {
			request.setAttribute("errore", "3");
			request.getRequestDispatcher("/admin/AddVeicolo").forward(request, response);
			return ;
		}
		try {
			 nposti = Integer.parseInt(tryNPosti);
			 capBag = Integer.parseInt(tryCapBag);
		} catch (Exception e) {
			request.setAttribute("errore", "2");
			request.getRequestDispatcher("/admin/AddVeicolo").forward(request, response);
			return;
		}
		Categorie tryCat = new Categorie(); 
		ArrayList<Categorie> categorie=db.getCategoriaDAO().getCategorie();
		for (Categorie categoria : categorie) {
			if(categoria.getNomeCategoria().equals(tryCategoria)) {
				tryCat = categoria;
			}
		}
		Veicoli tryVeicolo = new Veicoli();
		tryVeicolo.setTarga(tryTarga);
		tryVeicolo.setCategorie(tryCat);
		tryVeicolo.setMarca(tryMarca);
		tryVeicolo.setModello(tryModello);
		tryVeicolo.setNPosti(nposti);
		tryVeicolo.setCapacitaBagagliaio(capBag);
		tryVeicolo.setColore(tryColore);
		db.getVeicoloDAO().addVeicolo(tryVeicolo);
		request.getSession().setAttribute("utenti", db.getUtenteDAO().getUtenti());
		request.getSession().setAttribute("veicoli", db.getVeicoloDAO().getVeicoli());
		request.getSession().setAttribute("categorie", db.getCategoriaDAO().getCategorie());
		request.getRequestDispatcher("/admin/home").forward(request, response);
		
	}
	
	public boolean check(String targa) {
		ArrayList<Veicoli> veicoli = db.getVeicoloDAO().getVeicoli();
		if(veicoli!=null) {
		for(int i=0;i<veicoli.size();i++) {
			if(veicoli.get(i).getTarga().equals(targa)) {
				return true;
			}
		}
		}
		return false;
	}
}

