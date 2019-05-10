package br.com.botanica.view;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.botanica.controler.Controle;
import br.com.botanica.exception.BotanicaException;
import br.com.botanica.object.Planta;

@WebServlet(description = "Servlet para listagem das plantas já cadastradas", urlPatterns = { "/PlantaListagem1" })
public class PlantaListagem1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Controle controle = new Controle();
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter print = response.getWriter();
		print.append("<html>");
		print.append("Forma 1: Escrita diretamente do servlet (não utilize esta maneira)");
		try {
			List<Planta> plantas = controle.select();
			
			print.append("<table>");
			print.append("<tr>");
			print.append(	"<th>ID</th>");
			print.append(	"<th>Nome</th>");
			print.append(	"<th>Local</th>");
			print.append(	"<th>Preço</th>");
			print.append("</tr>");
			
			for(Planta planta : plantas) {
				print.append("<tr>");
				print.append(	"<td>" + planta.getId() + "</td>");
				print.append(	"<td>" + planta.getNome() + "</td>");
				print.append(	"<td>" + planta.getLocalizacao() + "</td>");
				print.append(	"<td>" + planta.getPreco() + "</td>");
				print.append("</tr>");
			}
			
			print.append("</table>");
			
		} catch (BotanicaException e) {
			e.printStackTrace();
		}
		print.append("</html>");
	}

}
