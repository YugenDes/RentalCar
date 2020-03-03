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
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String where = request.getParameter("where");
		if(where !=null && where.equals("fromadmin")) {
			//TODO
		}
		
	}

	public String getCatString(int id) {
		ArrayList<Categorie> categorie = db.getCategoriaDAO().getCategorie();
		for (Categorie categoria : categorie) {
			if (categoria.getIdCategoria() == id) {
				return categoria.getNomeCategoria();
			}
		}
		return null;
	}

	public boolean check(String targa) {
		ArrayList<Veicoli> veicoli = db.getVeicoloDAO().getVeicoli();
		for (int i = 0; i < veicoli.size(); i++) {
			System.out.println(veicoli.get(i).getTarga());
			System.out.println(targa);
			if (veicoli.get(i).getTarga().equals(targa)) {
				return true;
			}
		}
		return false;
	}


}
