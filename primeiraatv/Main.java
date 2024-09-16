package primeiraatv;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String nomeArquivo;
        boolean continuar = true;

        while (continuar) {
            System.out.println("Menu:");
            System.out.println("1. Criar novo arquivo CSV");
            System.out.println("2. Excluir arquivo CSV existente");
            System.out.println("3. Editar arquivo CSV existente");
            System.out.println("4. Sair");

            int opcao = 0;
            boolean opcaoValida = false;

            while (!opcaoValida) {
                System.out.print("Escolha uma opção: ");
                try {
                    opcao = scanner.nextInt();
                    scanner.nextLine();
                    if (opcao >= 1 && opcao <= 4) {
                        opcaoValida = true;
                    } else {
                        System.out.println("Opção inválida. Escolha uma opção entre 1 e 4.");
                    }
                } catch (InputMismatchException erro) {
                    System.out.println("Entrada inválida. Digite uma das opções que aparecem na tela.");
                    scanner.next();
                }
            }
            switch (opcao) {
                case 1:
                    while (true) {
                        System.out.print("Digite o nome do arquivo CSV (deve terminar com .csv): ");
                        nomeArquivo = scanner.nextLine();
                        if (nomeArquivo.endsWith(".csv")) {
                            GerenciadorDeArquivos.criarArquivo(nomeArquivo, scanner);
                            break;
                        } else {
                            System.out.println("Nome do arquivo deve terminar com .csv. Tente novamente.");
                        }
                    }
                    break;

                case 2:
                    while (true) {
                        System.out.print("Digite o nome do arquivo CSV que deseja excluir (deve terminar com .csv): ");
                        nomeArquivo = scanner.nextLine();
                        if (nomeArquivo.endsWith(".csv")) {
                            GerenciadorDeArquivos.excluirArquivo(nomeArquivo);
                            break;
                        } else {
                            System.out.println("Nome do arquivo deve terminar com .csv. Tente novamente.");
                        }
                    }
                    break;

                case 3:
                    while (true) {
                        System.out.print("Digite o nome do arquivo CSV para editar (deve terminar com .csv): ");
                        nomeArquivo = scanner.nextLine();
                        if (nomeArquivo.endsWith(".csv")) {
                            ColetorDeDados.editarArquivo(nomeArquivo, scanner);
                            break;
                        } else {
                            System.out.println("Nome do arquivo deve terminar com .csv. Tente novamente.");
                        }
                    }
                    break;

                case 4:
                    continuar = false;
                    System.out.println("Saindo...");
                    break;
            }
        }
        scanner.close();
    }
}
