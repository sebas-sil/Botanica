package br.com.botanica.controler;

import java.sql.SQLException;
import java.util.List;

import br.com.botanica.exception.BotanicaException;
import br.com.botanica.model.Banco;
import br.com.botanica.object.Planta;

public class Controle {

	Banco banco = new Banco();

	/**
	 * L�gica para inser��o de uma planta na loja
	 * 
	 * @param planta
	 * @return sucesso se adicionado
	 * @throws BotanicaException, quando ocorre algum erro de banco. Essa excec�o
	 *                            n�o deve ser vista no c�digo
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
	 * Remove, permanentemente, uma planta da loja. Este m�todo deve ser chamado
	 * quando uma planta morre
	 * 
	 * @param id
	 * @return sucesso ou fracasso
	 * @throws BotanicaException, quando ocorre algum erro de banco. Essa excec�o
	 *                            n�o deve ser vista no c�digo
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
	 * Realiza a atualiza��o da planta na loja
	 * 
	 * @param planta
	 * @return sucesso ou fracasso
	 * @throws BotanicaException, quando ocorre algum erro de banco. Essa excec�o
	 *                            n�o deve ser vista no c�digo
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
	 * Entrega uma planta pela identifica��o �nica
	 * 
	 * @param id
	 * @return uma planta ou exce��o
	 * @throws BotanicaException, lan�ada quando h� um erro de banco ou quando n�o
	 *                            s�o achados registros
	 */
	public Planta select(int id) throws BotanicaException {
		Planta resultado = null;
		try {
			resultado = banco.select(id);
		} catch (ClassNotFoundException | SQLException e) {
			throw new BotanicaException(e);
		}

		if (resultado == null) {
			throw new BotanicaException("Nenhum planta n�o encontrada com o id fornecido");
		}

		return resultado;
	}

	/**
	 * Entrega uma planta pelo nome. A busca � feita se a planta contem o nome
	 * fornecido
	 * 
	 * @param nome
	 * @return lista de plantas ou exce��o
	 * @throws BotanicaException, lan�ada quando h� um erro de banco ou quando n�o
	 *                            s�o achados registros
	 */
	public List<Planta> select(String nome) throws BotanicaException {
		List<Planta> resultado = null;
		try {
			resultado = banco.select(nome);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

		if (resultado.isEmpty()) {
			throw new BotanicaException("Nenhum planta n�o encontrada com o nome fornecido");
		}

		return resultado;
	}

	/**
	 * Entrega todas as plantas que est�o na loja
	 * 
	 * @return
	 * @throws BotanicaException, lan�ada quando h� um erro de banco ou quando n�o
	 *                            s�o achados registros
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
