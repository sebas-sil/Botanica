package br.com.botanica.controler;

import java.sql.SQLException;
import java.util.List;

import br.com.botanica.exception.BotanicaException;
import br.com.botanica.model.Banco;
import br.com.botanica.object.Planta;

public class Controle {

	Banco banco = new Banco();

	/**
	 * Lógica para inserção de uma planta na loja
	 * 
	 * @param planta
	 * @return sucesso se adicionado
	 * @throws BotanicaException, quando ocorre algum erro de banco. Essa excecão
	 *                            não deve ser vista no código
	 */
	public boolean insert(Planta planta) throws BotanicaException {
		boolean resultado = false;
		try {
			resultado = banco.insert(planta);
		} catch (ClassNotFoundException | SQLException e) {
			throw new BotanicaException(e);
		}

		return resultado;
	}

	/**
	 * Remove, permanentemente, uma planta da loja. Este método deve ser chamado
	 * quando uma planta morre
	 * 
	 * @param id
	 * @return sucesso ou fracasso
	 * @throws BotanicaException, quando ocorre algum erro de banco. Essa excecão
	 *                            não deve ser vista no código
	 */
	public boolean delete(int id) throws BotanicaException {
		boolean resultado = false;
		try {
			resultado = banco.delete(id);
		} catch (ClassNotFoundException | SQLException e) {
			throw new BotanicaException(e);
		}

		return resultado;
	}

	/**
	 * Realiza a atualização da planta na loja
	 * 
	 * @param planta
	 * @return sucesso ou fracasso
	 * @throws BotanicaException, quando ocorre algum erro de banco. Essa excecão
	 *                            não deve ser vista no código
	 */
	public boolean update(Planta planta) throws BotanicaException {
		boolean resultado = false;
		try {
			resultado = banco.update(planta);
		} catch (ClassNotFoundException | SQLException e) {
			throw new BotanicaException(e);
		}

		return resultado;
	}

	/**
	 * Entrega uma planta pela identificação única
	 * 
	 * @param id
	 * @return uma planta ou exceção
	 * @throws BotanicaException, lançada quando há um erro de banco ou quando não
	 *                            são achados registros
	 */
	public Planta select(int id) throws BotanicaException {
		Planta resultado = null;
		try {
			resultado = banco.select(id);
		} catch (ClassNotFoundException | SQLException e) {
			throw new BotanicaException(e);
		}

		if (resultado == null) {
			throw new BotanicaException("Nenhum planta não encontrada com o id fornecido");
		}

		return resultado;
	}

	/**
	 * Entrega uma planta pelo nome. A busca é feita se a planta contem o nome
	 * fornecido
	 * 
	 * @param nome
	 * @return lista de plantas ou exceção
	 * @throws BotanicaException, lançada quando há um erro de banco ou quando não
	 *                            são achados registros
	 */
	public List<Planta> select(String nome) throws BotanicaException {
		List<Planta> resultado = null;
		try {
			resultado = banco.select(nome);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

		if (resultado.isEmpty()) {
			throw new BotanicaException("Nenhum planta não encontrada com o nome fornecido");
		}

		return resultado;
	}

	/**
	 * Entrega todas as plantas que estão na loja
	 * 
	 * @return
	 * @throws BotanicaException, lançada quando há um erro de banco ou quando não
	 *                            são achados registros
	 */
	public List<Planta> select() throws BotanicaException {
		List<Planta> resultado = null;
		try {
			resultado = banco.select();
		} catch (ClassNotFoundException | SQLException e) {
			throw new BotanicaException(e);
		}

		if (resultado.isEmpty()) {
			throw new BotanicaException("Nenhum planta cadastrada");
		}

		return resultado;
	}
}
