package terceiraatv;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Servidor {
    private static Map<String, PrintStream> clientes = new ConcurrentHashMap<>();

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(7777);
        System.out.println("Servidor iniciado. Aguardando conexões...");

        while (true) {
            Socket socket = serverSocket.accept();
            ClienteHandler clienteHandler = new ClienteHandler(socket);
            clienteHandler.start();
        }
    }

    private static class ClienteHandler extends Thread {
        private Socket socket;
        private PrintStream output;
        private String nome;

        public ClienteHandler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try {
                BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                output = new PrintStream(socket.getOutputStream());

                nome = input.readLine();
                clientes.put(nome, output);
                System.out.println(nome + " conectado.");

                String mensagem;
                while ((mensagem = input.readLine()) != null) {
                    if (mensagem.equalsIgnoreCase("sair")) {
                        System.out.println(nome + " saiu.");
                        break;
                    }
                    System.out.println(nome + ": " + mensagem);
                    enviarParaTodos(nome + ": " + mensagem);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                clientes.remove(nome);
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        private void enviarParaTodos(String mensagem) {
            for (PrintStream clienteOutput : clientes.values()) {
                clienteOutput.println(mensagem);
            }
        }
    }
}
