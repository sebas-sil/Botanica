package br.com.botanica.view;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.botanica.controler.Controle;
import br.com.botanica.exception.BotanicaException;
import br.com.botanica.object.Planta;

@WebServlet(description = "Servlet para listagem das plantas já cadastradas", urlPatterns = { "/PlantaListagem3" })
public class PlantaListagem3 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Controle controle = new Controle();
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			List<Planta> plantas = controle.select();
			
			request.setAttribute("plantas", plantas);
			
			request.getRequestDispatcher("/listagem2.jsp").forward(request, response);
		} catch (BotanicaException e) {
			e.printStackTrace();
		}
	}

}
