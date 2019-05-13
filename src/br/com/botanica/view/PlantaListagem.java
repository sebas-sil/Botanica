package br.com.botanica.view;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.botanica.controler.Controle;
import br.com.botanica.exception.BotanicaException;
import br.com.botanica.object.Planta;

@WebServlet(description = "Servlet para listagem das plantas j� cadastradas", urlPatterns = { "/PlantaListagem" })
public class PlantaListagem extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Controle controle = new Controle();
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			Principal usuario = request.getUserPrincipal(); 
			
			System.out.println(usuario);
			if (usuario == null) {
				System.out.println("Usu�rio n�o autenticado, redirecionando para a autentica��o");
				request.setAttribute("error_mgn", "�rea restrita, voc� deve estar autenticado");
				request.getRequestDispatcher("/login").forward(request, response);
			} else {
				System.out.println(usuario);
				if(request.isUserInRole("ADMIN")) {
					System.out.println("Usu�rio autenticado como " + usuario.getName());
					List<Planta> plantas = controle.select();
					request.setAttribute("plantas", plantas);
					request.getRequestDispatcher("/listagem2.jsp").forward(request, response);
				} else {
					// nao permitido
					request.setAttribute("error_mgn", "Voc� n�o esta autorizado a utilizar esta funcionalidade");
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
