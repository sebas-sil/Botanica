package br.com.botanica.view;

import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import br.com.botanica.controler.Controle;
import br.com.botanica.exception.BotanicaException;
import br.com.botanica.object.Planta;

public class Tela {
	
	private static Logger logger = Logger.getLogger(Tela.class.getName());
	private final static String TAG = "VIEW";
	
	public static void main(String[] args) {
		logger.info(String.format("%s - start console", TAG));
		Controle controle = new Controle();
		Scanner sc = new Scanner(System.in);
		int opt = -1;
		Planta planta = null;
		List<Planta> plantas = null;
		String nome = null;
		String localizacao = null;
		float preco = -1;
		int id = -1;

		while (opt != 7) {
			planta = null;
			nome = null;
			localizacao = null;
			preco = -1;
			id = -1;
			plantas = null;
			opt = -1;

			System.out.println("Escolha uma opção: ");

			System.out.println("[ 1 ] inserir planta");
			System.out.println("[ 2 ] editar  planta");
			System.out.println("[ 3 ] buscar  por id");
			System.out.println("[ 4 ] buscar  por nome");
			System.out.println("[ 5 ] buscar  tudo");
			System.out.println("[ 6 ] remover planta");
			System.out.println("[ 7 ] SAIR");

			while (opt == -1) {
				try {
					opt = Integer.parseInt(sc.nextLine());
				} catch (NumberFormatException e) {
					System.err.println("Valor digitado não é um número, ente novamente.");
				}
			}

			switch (opt) {
			case 1:
				System.out.print("Digite o nome da planta: ");
				nome = sc.nextLine();
				System.out.print("Digite a localização da planta: ");
				localizacao = sc.nextLine();

				while (preco == -1) {
					try {
						System.out.print("Digite o preço da planta: ");
						preco = Float.parseFloat(sc.nextLine());
					} catch (NumberFormatException e) {
						System.err.println("Valor digitado não é um número, tente novamnete.");
					}
				}

				planta = new Planta(nome, localizacao, preco, null);
				try {
					controle.insert(planta);
					System.out.println("Planta inserida com sucesso!");
				} catch (BotanicaException e) {
					System.out.println(e.getMessage());
				}
				break;
			case 2:

				while (id == -1) {
					try {
						System.out.print("Digite o ID da planta a ser modificada: ");
						id = Integer.parseInt(sc.nextLine());
					} catch (NumberFormatException e) {
						System.err.println("Valor digitado não é um número, tente novamnete.");
					}
				}

				try {
					planta = controle.select(id);

					System.out.print("Digite o nome da planta [" + planta.getNome() + "]: ");
					nome = sc.nextLine();
					if (!nome.isEmpty()) {
						planta.setNome(nome);
					}
					System.out.print("Digite a localização da planta [" + planta.getLocalizacao() + "]: ");
					localizacao = sc.nextLine();
					if (!localizacao.isEmpty()) {
						planta.setLocalizacao(localizacao);
					}
					while (preco == -1) {
						try {
							System.out.print("Digite o preço da planta [" + planta.getPreco() + "]: ");
							String p = sc.nextLine();
							if (p != null) {
								preco = Float.parseFloat(p);
								planta.setPreco(preco);
							}
						} catch (NumberFormatException e) {
							System.err.println("Valor digitado não é um número, tente novamnete.");
						}
					}

					controle.update(planta);
					System.out.println("Planta atualizada com sucesso!");

				} catch (BotanicaException e) {
					logger.log(Level.SEVERE, e.getMessage(), e);
				}

				break;
			case 3:

				while (id == -1) {
					try {
						System.out.print("Digite o ID da planta a ser procurada: ");
						id = Integer.parseInt(sc.nextLine());
					} catch (NumberFormatException e) {
						System.err.println("Valor digitado não é um número, tente novamnete.");
					}
				}

				try {
					planta = controle.select(id);
					System.out.println();
					System.out.println(String.format("%3s  %s  %19s %8s", "ID", "Local", "Nome", "Preço"));
					System.out.println(planta);
					System.out.println();
				} catch (BotanicaException e) {
					logger.log(Level.SEVERE, e.getMessage(), e);
				}
				break;
			case 4:
				System.out.print("Digite parte do nome da planta a ser procurada: ");
				nome = sc.nextLine();
				try {
					plantas = controle.select(nome);
					System.out.println();
					System.out.println(String.format("%3s  %s  %19s %8s", "ID", "Local", "Nome", "Preço"));
					for (Planta p : plantas) {
						System.out.println(p);
					}
					System.out.println("Encontrados " + plantas.size());
					System.out.println();
				} catch (BotanicaException e) {
					logger.log(Level.SEVERE, e.getMessage(), e);
				}
				break;
			case 5:
				try {
					plantas = controle.select();
					System.out.println();
					System.out.println(String.format("%3s  %s  %19s %8s", "ID", "Local", "Nome", "Preço"));
					for (Planta p : plantas) {
						System.out.println(p);
					}
					System.out.println("Encontrados " + plantas.size());
					System.out.println();
				} catch (BotanicaException e) {
					logger.log(Level.SEVERE, e.getMessage(), e);
				}

				break;
			case 6:

				while (id == -1) {
					try {
						System.out.print("Digite o ID da planta a ser removida: ");
						id = Integer.parseInt(sc.nextLine());
					} catch (NumberFormatException e) {
						System.err.println("Valor digitado não é um número, tente novamnete.");
					}
				}

				try {
					controle.delete(id);
					System.out.println("Planta removida com sucesso");
				} catch (BotanicaException e) {
					logger.log(Level.SEVERE, e.getMessage(), e);
				}
				break;
			case 7:
				System.out.println("Obrigado por utilziar o sistema, vonte logo.");
				break;
			default:
				System.err.println("Opção Inválida");
				break;
			}
		}

		sc.close();
	}
}
