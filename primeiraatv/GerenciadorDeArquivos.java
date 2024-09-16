package primeiraatv;

import java.io.File;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.InputMismatchException;

public class GerenciadorDeArquivos {

    public static void criarArquivo(String nomeArquivo, Scanner scanner) {
        boolean entradaValida = false;
        int colunas = 0;
        while (!entradaValida) {
            System.out.println("Digite o número de colunas (mínimo 3):");
            try {
                colunas = scanner.nextInt();
                if (colunas < 3) {
                    System.out.println("Número de colunas deve ser pelo menos 3.");
                } else {
                    entradaValida = true;
                }
            } catch (InputMismatchException erro) {
                System.out.println("Entrada inválida. Digite um número inteiro.");
                scanner.next();
            }
        }

        entradaValida = false;
        int linhas = 0;
        while (!entradaValida) {
            System.out.println("Digite o número de linhas:");
            try {
                linhas = scanner.nextInt();
                entradaValida = true;
            } catch (InputMismatchException erro) {
                System.out.println("Entrada inválida. Digite um número inteiro.");
                scanner.next();
            }
        }

        ArrayList<ArrayList<String>> dados = new ArrayList<>();
        scanner.nextLine();
        for (int linha = 0; linha < linhas; linha++) {
            ArrayList<String> linhaDados = new ArrayList<>();
            for (int coluna = 0; coluna < colunas; coluna++) {
                System.out.print("Digite o valor para linha " + (linha + 1) + ", coluna " + (coluna + 1) + ": ");
                linhaDados.add(scanner.nextLine());
            }
            dados.add(linhaDados);
        }

        try (PrintWriter csvWriter = new PrintWriter(nomeArquivo)) {
            for (ArrayList<String> linhaDados : dados) {
                csvWriter.println(String.join(",", linhaDados));
            }
            System.out.println("Arquivo CSV criado com sucesso!");
        } catch (IOException erro) {
            erro.printStackTrace();
        }
    }

    public static void excluirArquivo(String nomeArquivo) {
        File arquivoExcluir = new File(nomeArquivo);
        if (arquivoExcluir.exists()) {
            if (arquivoExcluir.delete()) {
                System.out.println("Arquivo deletado com sucesso.");
            } else {
                System.out.println("Falha ao deletar o arquivo.");
            }
        } else {
            System.out.println("Arquivo não encontrado.");
        }
    }
}
