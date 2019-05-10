package br.com.botanica.view;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.botanica.controler.Controle;
import br.com.botanica.exception.BotanicaException;
import br.com.botanica.object.Usuario;

@WebServlet(description = "Servlet para autneticação de usuários", urlPatterns = { "/login" })
public class PlantaLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Controle controle = new Controle();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getSession(true).getAttribute("usuario") == null) {
			// usuario nao autenticado
			String login = request.getParameter("login");
			String senha = request.getParameter("senha");
			
			System.out.println(login);
			System.out.println(senha);
			
			if(login == null || senha == null) {
				response.sendRedirect("login.html");
			} else {
				Usuario usuario;
				try {
					usuario = controle.autentica(login, senha);
					request.getSession(true).setAttribute("usuario", usuario);
					request.getRequestDispatcher("/").forward(request, response);
				} catch (BotanicaException e) {
					System.out.println(e.getMessage());
					response.sendError(401);
				}
			}
		}
	}

}
