package segundaatv;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.Scanner;


public class Conversor {
    public static class ConversorNumerosRomanos {

        private static final int[] valores = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        private static final String[] simbolos = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

        public static String toRoman(int numero) {
            StringBuilder resultado = new StringBuilder();
            for (int i = 0; i < valores.length; i++) {
                while (numero >= valores[i]) {
                    resultado.append(simbolos[i]);
                    numero -= valores[i];
                }
            }
            return resultado.toString();
        }
    }

    public static class ConversorMoedas {

        private static final String[] moedas = {"USD", "EUR", "JPY", "GBP", "BRL"};
        private static final String[] nomesMoedas = {"Dólar Americano", "Euro", "Iene Japonês", "Libra Esterlina", "Real Brasileiro"};
        private static final double[] taxas = {1.0, 0.85, 110.0, 0.75, 5.4};

        public static int findCurrencyIndex(String currency) {
            for (int i = 0; i < moedas.length; i++) {
                if (moedas[i].equalsIgnoreCase(currency)) {
                    return i;
                }
            }
            throw new IllegalArgumentException("Moeda não encontrada: " + currency);
        }

        public static double convert(double amount, String fromCurrency, String toCurrency) {
            int fromIndex = findCurrencyIndex(fromCurrency);
            int toIndex = findCurrencyIndex(toCurrency);

            double amountInUSD = amount / taxas[fromIndex];
            return amountInUSD * taxas[toIndex];
        }

        public static String getCurrencyFullName(String currency) {
            int index = findCurrencyIndex(currency);
            return moedas[index] + " (" + nomesMoedas[index] + ")";
        }

        public static void displayCurrencyOptions() {
            System.out.println("Moedas disponíveis para conversão:");
            for (int i = 0; i < moedas.length; i++) {
                System.out.println(moedas[i] + " - " + nomesMoedas[i]);
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean continuar = true;

        while (continuar) {
            System.out.println("Escolha uma funcionalidade:");
            System.out.println("1. Converter número inteiro para números romanos");
            System.out.println("2. Converter moedas");
            System.out.println("3. Sair");

            int opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    System.out.print("Digite um número para converter para números romanos (1-3999): ");
                    int numero = scanner.nextInt();

                    if (numero < 1 || numero > 3999) {
                        System.out.println("Número fora do intervalo permitido.");
                    } else {
                        String romano = ConversorNumerosRomanos.toRoman(numero);
                        System.out.println("O número " + numero + " em números romanos é: " + romano);
                    }
                    break;

                case 2:
                    System.out.println("Escolha a moeda de origem e destino para conversão.");
                    ConversorMoedas.displayCurrencyOptions();

                    System.out.print("Digite o valor a ser convertido: ");
                    double valor = scanner.nextDouble();

                    System.out.print("Digite a moeda de origem (ex: USD, EUR, JPY, GBP, BRL): ");
                    String moedaOrigem = scanner.next();

                    System.out.print("Digite a moeda de destino (ex: USD, EUR, JPY, GBP, BRL): ");
                    String moedaDestino = scanner.next();

                    try {
                        double valorConvertido = ConversorMoedas.convert(valor, moedaOrigem, moedaDestino);

                        Locale brasil = new Locale("pt", "BR");
                        NumberFormat formatoMoeda = NumberFormat.getCurrencyInstance(brasil);

                        String moedaOrigemNome = ConversorMoedas.getCurrencyFullName(moedaOrigem);
                        String moedaDestinoNome = ConversorMoedas.getCurrencyFullName(moedaDestino);

                        System.out.println(formatoMoeda.format(valor) + " " + moedaOrigemNome + " é equivalente a " + formatoMoeda.format(valorConvertido) + " " + moedaDestinoNome);
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 3:
                    System.out.println("Saindo...");
                    continuar = false;
                    break;

                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
        }
        scanner.close();
    }
}
