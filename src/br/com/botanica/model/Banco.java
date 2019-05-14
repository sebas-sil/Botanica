package br.com.botanica.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import br.com.botanica.object.Planta;

public class Banco {

	/**
	 * Insere uma planta no banco de dados
	 * 
	 * @param planta, o objeto a ser salvo no banco
	 * @return verdadeiro se inserido com sucesso
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws NamingException 
	 */
	public boolean insert(Planta planta) throws ClassNotFoundException, SQLException, NamingException {
		boolean retorno = false;

		String sql = "INSERT INTO planta (nome, preco, localizacao) VALUES (?,?,?,?)";
		Connection conn = this.getConnection();
		PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		ps.setString(1, planta.getNome());
		ps.setFloat(2, planta.getPreco());
		ps.setString(3, planta.getLocalizacao());
		ps.setString(4, planta.getImagem());

		ps.executeUpdate();
		
		ResultSet rs = ps.getGeneratedKeys();
		if (rs.next()) {
			planta.setId(rs.getInt(1));
		}
		
		retorno = (planta.getId() > 0);
		conn.close();
		return retorno;
	}

	/**
	 * Remove uma planta do banco de dados
	 * 
	 * @param id, identificação única da planta
	 * @return verdadeiro se removeu com sucesso
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws NamingException 
	 */
	public boolean delete(int id) throws ClassNotFoundException, SQLException, NamingException {
		boolean retorno = false;

		String sql = "DELETE FROM planta WHERE id = ?";
		Connection conn = this.getConnection();
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, id);

		int qtd = ps.executeUpdate();
		retorno = (qtd == 1);
		conn.close();
		return retorno;
	}

	/**
	 * Atualiza as informações no banco de dados
	 * 
	 * @param planta
	 * @return sucesso se atualizado
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws NamingException 
	 */
	public boolean update(Planta planta) throws ClassNotFoundException, SQLException, NamingException {
		boolean retorno = false;

		String sql = "UPDATE planta SET nome=?, preco=?, localizacao=?, imagem=? WHERE id=?";
		Connection conn = this.getConnection();
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, planta.getNome());
		ps.setFloat(2, planta.getPreco());
		ps.setString(3, planta.getLocalizacao());
		ps.setString(4, planta.getImagem());
		ps.setInt(5, planta.getId());

		int qtd = ps.executeUpdate();
		retorno = (qtd == 1);
		conn.close();
		return retorno;
	}

	/**
	 * Retorna uma planta de acordo com o ID informado
	 * 
	 * @param id
	 * @return planta se encontrada ou null
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws NamingException 
	 */
	public Planta select(int id) throws ClassNotFoundException, SQLException, NamingException {
		Planta planta = null;

		String sql = "SELECT nome, preco, localizacao, imagem FROM planta WHERE id = ?";
		Connection conn = this.getConnection();
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, id);

		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			String nome = rs.getString("nome");
			float preco = rs.getFloat("preco");
			String localizacao = rs.getString("localizacao");
			String imagem = rs.getString("imagem");

			planta = new Planta(nome, localizacao, preco, imagem);
			planta.setId(id);
		}
		conn.close();
		return planta;
	}

	/**
	 * Retorna as planta pelo nome
	 * 
	 * @param nome
	 * @return plantas encontradas ou lista vazia
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws NamingException 
	 */
	public List<Planta> select(String nome) throws ClassNotFoundException, SQLException, NamingException {
		List<Planta> plantas = new ArrayList<Planta>();

		String sql = "SELECT id, nome, preco, localizacao, imagem FROM planta WHERE nome LIKE ?";
		Connection conn = this.getConnection();
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, "%" + nome + "%");

		ResultSet rs = ps.executeQuery();
		Planta planta = null;
		int id = -1;
		float preco = -1;
		String localizacao = null;
		String imagem = null;
		while (rs.next()) {
			id = rs.getInt("id");
			preco = rs.getFloat("preco");
			localizacao = rs.getString("localizacao");
			nome = rs.getString("nome");
			imagem = rs.getString("imagem");

			planta = new Planta(nome, localizacao, preco, imagem);
			planta.setId(id);
			plantas.add(planta);
		}
		conn.close();
		return plantas;
	}

	/**
	 * Retorna todas as plantas cadastradas no banco de dados
	 * 
	 * @return uma lista de plantas ou lista vazia
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws NamingException 
	 */
	public List<Planta> select() throws ClassNotFoundException, SQLException, NamingException {
		List<Planta> plantas = new ArrayList<Planta>();

		String sql = "SELECT id,nome,preco,localizacao,imagem FROM planta";
		Connection conn = this.getConnection();
		PreparedStatement ps = conn.prepareStatement(sql);

		ResultSet rs = ps.executeQuery();
		Planta planta = null;
		int id = -1;
		float preco = -1;
		String localizacao = null;
		String nome = null;
		String imagem = null;
		while (rs.next()) {
			id = rs.getInt("id");
			preco = rs.getFloat("preco");
			localizacao = rs.getString("localizacao");
			nome = rs.getString("nome");
			imagem = rs.getString("imagem");

			planta = new Planta(nome, localizacao, preco, imagem);
			planta.setId(id);
			plantas.add(planta);
		}
		conn.close();
		return plantas;
	}

	/**
	 * Pega uma conexão com o banco de dados SQLITE3
	 * 
	 * @return Conexao com o banco de dados sqlite3 ou excecao
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws NamingException 
	 */
	private Connection getConnection() throws NamingException, SQLException {
		InitialContext ctx = new InitialContext();
		Context initCtx = (Context) ctx.lookup("java:/comp/env");
		DataSource ds = (DataSource) initCtx.lookup("jdbc/local_botanica");
		Connection conn = ds.getConnection();
		return conn;
	}
}
