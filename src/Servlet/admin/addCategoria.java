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

@WebServlet("/admin/addCategoria")
public class addCategoria extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DAOFactory db ;
       
    public addCategoria() {
        super();
        db=DAOFactory.getJpaDaoFactory();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.sendRedirect(request.getContextPath()+"/admin/addCategoria");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String tryCategoria=request.getParameter("regCategoria");
		Double tryPrezzoG;
		Double tryPrezzoS;
		Double tryPrezzoM;
		try {
			tryPrezzoG=Double.parseDouble(request.getParameter("regPrezzoG"));
			tryPrezzoS=Double.parseDouble(request.getParameter("regPrezzoS"));
			tryPrezzoM=Double.parseDouble(request.getParameter("regPrezzoM"));
		}catch (Exception e) {
			request.setAttribute("errore", "1");request.getRequestDispatcher("/admin/AddCategoria").forward(request, response);;
			return;
		}
		
		if(tryCategoria != null && tryPrezzoG!=null && tryPrezzoS != null && tryPrezzoM!=null) {
			if(!tryCategoria.equals("") || tryPrezzoG==0 || tryPrezzoS==0 || tryPrezzoM==0) {
				if(check(tryCategoria)) {
					Categorie cat = new Categorie();
					cat.setNomeCategoria(tryCategoria);
					cat.setTariffaGiornaliera(tryPrezzoG);
					cat.setTariffaSettimanale(tryPrezzoS);
					cat.setTariffaMensile(tryPrezzoM);
					db.getCategoriaDAO().addCategoria(cat);
					request.getSession().setAttribute("utenti", db.getUtenteDAO().getUtenti());
					request.getSession().setAttribute("veicoli", db.getVeicoloDAO().getVeicoli());
					request.getSession().setAttribute("categorie", db.getCategoriaDAO().getCategorie());
					response.sendRedirect(request.getContextPath()+"/admin/home");
					}else {request.setAttribute("errore", "3");request.getRequestDispatcher("/admin/AddCategoria").forward(request, response);}
				}else {request.setAttribute("errore", "2");request.getRequestDispatcher("/admin/AddCategoria").forward(request, response);}
			}else {request.setAttribute("errore", "1");request.getRequestDispatcher("/admin/AddCategoria").forward(request, response);}
	}
	
	public boolean check(String categoria) {
		
		try {
			db.getCategoriaDAO().getCategoria(categoria);
		} catch (Exception e) {
			return true;
		}
		return false;
		
		
		/*ArrayList<Categorie> categorie = db.getCategoriaDAO().getCategorie();
		if(categoria!=null) {
		for(int i=0;i<categorie.size();i++) {
			if(categorie.get(i).getNomeCategoria().equalsIgnoreCase(categoria)) return false;
		}
		return true;
	}
		return false;
	*/}


}
