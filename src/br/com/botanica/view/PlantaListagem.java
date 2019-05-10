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
import br.com.botanica.object.Usuario;

@WebServlet(description = "Servlet para listagem das plantas já cadastradas", urlPatterns = { "/PlantaListagem" })
public class PlantaListagem extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Controle controle = new Controle();
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			Usuario usuario = (Usuario) request.getSession(true).getAttribute("usuario");
			if (usuario == null) {
				System.out.println("Usuário não autenticado, redirecionando para a autenticação");
				request.getRequestDispatcher("/login").forward(request, response);
			} else {
				if(usuario.getRole() == "ADMIN") {
					System.out.println("Usuário autenticado como " + usuario.getNome());
					List<Planta> plantas = controle.select();
					request.setAttribute("plantas", plantas);
					request.getRequestDispatcher("/listagem2.jsp").forward(request, response);
				} else {
					// nao permitido
					response.sendError(403);
				}
				
			}
		} catch (BotanicaException e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("doPost");
		doGet(req, resp);
	}

}
