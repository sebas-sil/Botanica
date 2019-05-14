package br.com.botanica.view;

import java.io.IOException;
import java.security.Principal;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.botanica.controler.Controle;
import br.com.botanica.object.Planta;

@WebServlet(description = "Adiciona uma nova planta a loja", urlPatterns = { "/PlantaNovo" })
public class PlantaNovo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private Logger logger = Logger.getLogger(this.getClass().getName());
	private final static String TAG = "VIEW";
	
	private Controle controle = new Controle();
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.info(String.format("%s - post", TAG));
		
		try {
			Principal usuario = request.getUserPrincipal(); 
			
			logger.info(String.format("%s - %s", TAG, usuario));
			if (usuario == null) {
				logger.info(String.format("%s - %s", TAG, "Usuário não autenticado, redirecionando para a autenticação"));
				request.setAttribute("error_mgn", "Área restrita, você deve estar autenticado");
				request.getRequestDispatcher("/login").forward(request, response);
			} else {
				if(request.isUserInRole("ADMIN")) {
					
					String nome = request.getParameter("nome");
					String precoStr = request.getParameter("preco");
					String local = request.getParameter("local");
					String imagem = request.getParameter("imagem");
					
					float preco = -1;
					try {
						preco = Float.parseFloat(precoStr);
						
						logger.info(String.format("%s - %s", TAG, "Usuário autenticado como " + usuario.getName()));
						boolean isOK = controle.insert(new Planta(nome, local, preco, imagem));
						if(isOK) {
							request.setAttribute("success_mgn", "Salvo com sucesso");
							request.getRequestDispatcher("PlantaListagem").forward(request, response);
						} else {
							request.setAttribute("error_mgn", "Erro ao salvar registro");
							request.getRequestDispatcher("/novo.jsp").forward(request, response);
						}
					}catch(NumberFormatException e) {
						logger.warning("Valor informado não é um número");
						request.setAttribute("error_mgn", "Valor informado não é um número");
						request.getRequestDispatcher("/novo.jsp").forward(request, response);
					}
					
				} else {
					// nao permitido
					logger.info(String.format("%s - %s", TAG, "Acesso não autorizado para o usuário " + usuario.getName()));
					request.setAttribute("error_mgn", "Você não esta autorizado a utilizar esta funcionalidade");
					response.sendError(403);
				}
				
			}
		} catch (Exception e) {
			logger.log(Level.SEVERE, e.getMessage(), e);
			request.setAttribute("error_mgn", "Um erro ocorreu ao gravar o registro");
			response.sendError(500);
		}
	}

}
