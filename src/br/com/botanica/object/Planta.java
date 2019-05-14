package br.com.botanica.object;

public class Planta {

	/**
	 * Nome comumente falado da planta
	 */
	private String nome;
	/**
	 * A qual familia pertence a planta (botanica)
	 */
	private String familia;

	/**
	 * A qual ordem pertence a planta (botanica)
	 */
	private String ordem;

	/**
	 * Quais os cuidados com a nutrição
	 */
	private String alimentacao;

	/**
	 * Qual sua utilidade para a humanidade
	 */
	private String utilidade;

	/**
	 * Em qual estagio de desenvolvimento esta
	 */
	private String estagio;

	/**
	 * Como deve ser seu habtati para que se desenvolva corretamente
	 */
	private String habtati;

	/**
	 * Quais os perigos em sua ingestão ou manipulação
	 */
	private String perigos;

	/**
	 * Quais os cuidados extras que deve ter ao manipular ou manutenção
	 */
	private String manipulacao;

	/**
	 * Identificação única
	 */
	private int id;

	/**
	 * preço em reais
	 */
	private float preco;

	/**
	 * localização dentro da loja
	 */
	private String localizacao;
	
	/**
	 * Imagem no formato 'data:image/webp;base64'
	 */
	private String imagem;

	/**
	 * Construtor de uma planta. Não existe planta sem nome
	 * 
	 * @param nome,        nome conhecido da planta
	 * @param localizacao, localização na loja
	 * @param preco,       preço da planta
	 * @param imagem,      imagem em base64
	 */
	public Planta(String nome, String localizacao, float preco, String imagem) {
		setNome(nome);
		setLocalizacao(localizacao);
		setPreco(preco);
		setImagem(imagem);
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		// captaliza a primeira letra
		char[] array = nome.toCharArray();
		array[0] = Character.toUpperCase(array[0]);
		this.nome = new String(array);
	}

	public String getFamilia() {
		return familia;
	}

	public void setFamilia(String familia) {
		this.familia = familia;
	}

	public String getOrdem() {
		return ordem;
	}

	public void setOrdem(String ordem) {
		this.ordem = ordem;
	}

	public String getAlimentacao() {
		return alimentacao;
	}

	public void setAlimentacao(String alimentacao) {
		this.alimentacao = alimentacao;
	}

	public String getUtilidade() {
		return utilidade;
	}

	public void setUtilidade(String utilidade) {
		this.utilidade = utilidade;
	}

	public String getEstagio() {
		return estagio;
	}

	public void setEstagio(String estagio) {
		this.estagio = estagio;
	}

	public String getHabtati() {
		return habtati;
	}

	public void setHabtati(String habtati) {
		this.habtati = habtati;
	}

	public String getPerigos() {
		return perigos;
	}

	public void setPerigos(String perigos) {
		this.perigos = perigos;
	}

	public String getManipulacao() {
		return manipulacao;
	}

	public void setManipulacao(String manipulacao) {
		this.manipulacao = manipulacao;
	}

	public int getId() {
		return id;
	}

	public float getPreco() {
		return preco;
	}

	public void setPreco(float preco) {
		this.preco = preco;
	}

	public String getLocalizacao() {
		return localizacao;
	}

	public void setLocalizacao(String localizacao) {
		this.localizacao = localizacao;
	}

	public String getImagem() {
		return imagem;
	}

	public void setImagem(String imagem) {
		this.imagem = imagem;
	}

	@Override
	public String toString() {
		return String.format("%03d %6s %20s R$ %6.2f", id, localizacao, nome, preco);
	}
	
}
