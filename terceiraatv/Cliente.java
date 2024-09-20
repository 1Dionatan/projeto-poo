package terceiraatv;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 7777);
        Scanner scanner = new Scanner(System.in);

        PrintStream saida = new PrintStream(socket.getOutputStream());

        System.out.print("Digite seu nome: ");
        String nome = scanner.nextLine();
        saida.println(nome);

        ClienteThread clienteThread = new ClienteThread(socket);
        clienteThread.start();

        while (true) {
            String mensagem = scanner.nextLine();
            if (mensagem.equalsIgnoreCase("sair")) {
                saida.println(mensagem);
                socket.close();
                break;
            }
            saida.println(mensagem);
        }
    }
}
