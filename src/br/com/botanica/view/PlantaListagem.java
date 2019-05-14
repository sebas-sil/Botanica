package br.com.botanica.view;

import java.io.IOException;
import java.security.Principal;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

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
	
	private Logger logger = Logger.getLogger(this.getClass().getName());
	private final static String TAG = "VIEW";
	
	private Controle controle = new Controle();
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.info(String.format("%s - get", TAG));
		try {
			Principal usuario = request.getUserPrincipal(); 
			
			logger.info(String.format("%s - %s", TAG, usuario));
			if (usuario == null) {
				logger.info(String.format("%s - %s", TAG, "Usu�rio n�o autenticado, redirecionando para a autentica��o"));
				request.setAttribute("error_mgn", "�rea restrita, voc� deve estar autenticado");
				request.getRequestDispatcher("/login").forward(request, response);
			} else {
				if(request.isUserInRole("ADMIN")) {
					logger.info(String.format("%s - %s", TAG, "Usu�rio autenticado como " + usuario.getName()));
					List<Planta> plantas = controle.select();
					request.setAttribute("plantas", plantas);
					request.getRequestDispatcher("/listagem2.jsp").forward(request, response);
				} else {
					// nao permitido
					logger.info(String.format("%s - %s", TAG, "Acesso n�o autorizado para o usu�rio " + usuario.getName()));
					request.setAttribute("error_mgn", "Voc� n�o esta autorizado a utilizar esta funcionalidade");
					response.sendError(403);
				}
				
			}
		} catch (BotanicaException e) {
			logger.log(Level.SEVERE, e.getMessage(), e);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

}
