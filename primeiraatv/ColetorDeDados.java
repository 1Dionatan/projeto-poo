package primeiraatv;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class ColetorDeDados {

    public static void editarArquivo(String nomeArquivo, Scanner scanner) {
        File arquivo = new File(nomeArquivo);
        if (arquivo.exists()) {
            System.out.println("Escolha uma opção:");
            System.out.println("1. Adicionar linha");
            System.out.println("2. Adicionar coluna");
            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    adicionarLinha(nomeArquivo, scanner);
                    break;

                case 2:
                    adicionarColuna(nomeArquivo, scanner);
                    break;

                default:
                    System.out.println("Opção inválida.");
                    break;
            }
        } else {
            System.out.println("Arquivo não encontrado.");
        }
    }

    private static void adicionarLinha(String nomeArquivo, Scanner scanner) {
        ArrayList<ArrayList<String>> dados = lerArquivo(nomeArquivo, scanner);
        if (dados != null) {
            System.out.println("Digite o número de colunas (deve ser igual ao existente):");
            int colunas = dados.get(0).size();
            ArrayList<String> novaLinha = new ArrayList<>();
            for (int i = 0; i < colunas; i++) {
                System.out.print("Digite o que deseja para nova linha, coluna " + (i + 1) + ": ");
                novaLinha.add(scanner.nextLine());
            }
            dados.add(novaLinha);
            salvarArquivo(nomeArquivo, dados);
        }
    }

    private static void adicionarColuna(String nomeArquivo, Scanner scanner) {
        ArrayList<ArrayList<String>> dados = lerArquivo(nomeArquivo, scanner);
        if (dados != null) {
            for (int i = 0; i < dados.size(); i++) {
                System.out.print("Digite o que deseja para linha " + (i + 1) + ", nova coluna: ");
                dados.get(i).add(scanner.nextLine());
            }
            salvarArquivo(nomeArquivo, dados);
        }
    }

    private static ArrayList<ArrayList<String>> lerArquivo(String nomeArquivo, Scanner scanner) {
        ArrayList<ArrayList<String>> dados = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(nomeArquivo))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                ArrayList<String> linhaDados = new ArrayList<>();
                for (String valor : linha.split(",")) {
                    linhaDados.add(valor);
                }
                dados.add(linhaDados);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dados;
    }

    private static void salvarArquivo(String nomeArquivo, ArrayList<ArrayList<String>> dados) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(nomeArquivo))) {
            for (ArrayList<String> linhaDados : dados) {
                pw.println(String.join(",", linhaDados));
            }
            System.out.println("Arquivo CSV atualizado com sucesso!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
