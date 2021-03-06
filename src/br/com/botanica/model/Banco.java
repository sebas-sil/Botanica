package br.com.botanica.model;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.botanica.object.Planta;
import br.com.botanica.object.Usuario;

public class Banco {

	/**
	 * Insere uma planta no banco de dados
	 * 
	 * @param planta, o objeto a ser salvo no banco
	 * @return verdadeiro se inserido com sucesso
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public boolean insert(Planta planta) throws ClassNotFoundException, SQLException {
		boolean retorno = false;

		String sql = "INSERT INTO planta (nome, preco, localizacao) VALUES (?,?,?)";
		Connection conn = this.getConnection();
		PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		ps.setString(1, planta.getNome());
		ps.setFloat(2, planta.getPreco());
		ps.setString(3, planta.getLocalizacao());

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
	 * @param id, identifica��o �nica da planta
	 * @return verdadeiro se removeu com sucesso
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public boolean delete(int id) throws ClassNotFoundException, SQLException {
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
	 * Atualiza as informa��es no banco de dados
	 * 
	 * @param planta
	 * @return sucesso se atualizado
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public boolean update(Planta planta) throws ClassNotFoundException, SQLException {
		boolean retorno = false;

		String sql = "UPDATE planta SET nome=?, preco=?, localizacao=? WHERE id=?";
		Connection conn = this.getConnection();
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, planta.getNome());
		ps.setFloat(2, planta.getPreco());
		ps.setString(3, planta.getLocalizacao());
		ps.setInt(4, planta.getId());

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
	 */
	public Planta select(int id) throws ClassNotFoundException, SQLException {
		Planta planta = null;

		String sql = "SELECT nome, preco, localizacao FROM planta WHERE id = ?";
		Connection conn = this.getConnection();
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, id);

		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			String nome = rs.getString("nome");
			float preco = rs.getFloat("preco");
			String localizacao = rs.getString("localizacao");

			planta = new Planta(nome, localizacao, preco);
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
	 */
	public List<Planta> select(String nome) throws ClassNotFoundException, SQLException {
		List<Planta> plantas = new ArrayList<Planta>();

		String sql = "SELECT id, nome, preco, localizacao FROM planta WHERE nome LIKE ?";
		Connection conn = this.getConnection();
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, "%" + nome + "%");

		ResultSet rs = ps.executeQuery();
		Planta planta = null;
		int id = -1;
		float preco = -1;
		String localizacao = null;
		while (rs.next()) {
			id = rs.getInt("id");
			preco = rs.getFloat("preco");
			localizacao = rs.getString("localizacao");
			nome = rs.getString("nome");

			planta = new Planta(nome, localizacao, preco);
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
	 */
	public List<Planta> select() throws ClassNotFoundException, SQLException {
		List<Planta> plantas = new ArrayList<Planta>();

		String sql = "SELECT id,nome,preco,localizacao FROM planta";
		Connection conn = this.getConnection();
		PreparedStatement ps = conn.prepareStatement(sql);

		ResultSet rs = ps.executeQuery();
		Planta planta = null;
		while (rs.next()) {
			int id = rs.getInt("id");
			String nome = rs.getString("nome");
			float preco = rs.getFloat("preco");
			String localizacao = rs.getString("localizacao");

			planta = new Planta(nome, localizacao, preco);
			planta.setId(id);
			plantas.add(planta);
		}
		conn.close();
		return plantas;
	}
	
	public Usuario login(String login, String senha) throws ClassNotFoundException, SQLException {
		Usuario usuario = null;

		String sql = "SELECT id, nome, role FROM usuario WHERE login = ? AND senha = ?";
		Connection conn = this.getConnection();
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, login);
		ps.setString(2, senha);
		
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			int id = rs.getInt("id");
			String nome = rs.getString("nome");
			String role = rs.getString("role");

			usuario = new Usuario(id, login, nome, role);
		}
		conn.close();
		return usuario;
	}

	/**
	 * Pega uma conex�o com o banco de dados SQLITE3 Esse m�todo pode ser trocado
	 * pela configura��o deum datasource no context
	 * 
	 * @return Conexao com o banco de dados sqlite3 ou excecao
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	private Connection getConnection() throws ClassNotFoundException, SQLException {
		Class.forName(org.sqlite.JDBC.class.getName());
		File file = new File(Banco.class.getResource("/").getPath());
		// nao funciona local
		return DriverManager.getConnection("jdbc:sqlite:"+file.getParentFile()+"/botanica.db3");
	}
}
