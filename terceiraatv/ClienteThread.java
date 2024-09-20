package terceiraatv;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ClienteThread extends Thread {
    private Socket socket;

    public ClienteThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String mensagem;
            while ((mensagem = input.readLine()) != null) {
                System.out.println(mensagem);
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler do servidor: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
