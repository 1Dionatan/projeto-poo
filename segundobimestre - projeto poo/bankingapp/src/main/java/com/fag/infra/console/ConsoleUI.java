package com.fag.infra.console;

import java.util.Scanner;

import com.fag.domain.dto.BankslipDTO;
import com.fag.domain.dto.LoginDTO;
import com.fag.domain.dto.UserAccountDTO;
import com.fag.domain.repositories.IUserInterface;

public class ConsoleUI implements IUserInterface {

    private Scanner inputScanner = new Scanner(System.in);

    @Override
    public Integer showInitialScreenMenu() {
        System.out.println("Bem vindo ao Bank D1Z!");
        System.out.println("(1) Realizar login");
        System.out.println("(2) Criar conta");
        System.out.println("(3) Sair");
        
        return inputScanner.nextInt();
    }

    @Override
    public Integer showHomeMenu(String userName) {
        System.out.println("Olá " + userName + "! O que deseja fazer hoje?");
        System.out.println("(1) Consultar boleto");
        System.out.println("(2) Realizar pagamento boleto");
        System.out.println("(3) Gerar QR Code PIX");
        System.out.println("(4) Sair");
        
        return inputScanner.nextInt();
    }

   
    @Override
    public LoginDTO getloginData() {
        LoginDTO data = new LoginDTO();
        inputScanner.nextLine();


        System.out.println("Informe seu documento: ");
        String document = inputScanner.nextLine();
        
        System.out.println("Informe sua senha: ");
        String password = inputScanner.nextLine();

        data.setDocument(document);
        data.setPassword(password);

        return data;
    }

    @Override
    public UserAccountDTO getRegisterUser() {
       UserAccountDTO userData = new UserAccountDTO();
       inputScanner.nextLine();


       System.out.println("Informe seu documento: ");
       String document = inputScanner.nextLine();

       System.out.println("Informe seu nome: ");
       String name = inputScanner.nextLine();
       
       System.out.println("Informe seu email: ");
       String email = inputScanner.nextLine();
       System.out.println("Informe sua senha: ");
       String password = inputScanner.nextLine();

       userData.setDocument(document);
       userData.setPassword(password);
       userData.setName(name);
       userData.setEmail(email);
       
       return userData;
    }

    @Override
    public void showErrorMsg(String msg) {
        System.out.println("ERRO: " + msg);
    }

    @Override
    public void showExitMessage() {

        System.out.println("Obrigado por estar utilizando nossas aplicações");
       
    }

    @Override
        public String getBarcode() {
        System.out.println("Insira o código de barras:");
        inputScanner.nextLine();
        String barcode = inputScanner.nextLine();

        return barcode;
    }

    @Override
    public BankslipDTO getPaymentBankslipInfo() {
        BankslipDTO bankslipDTO = new BankslipDTO();
        
        System.out.println("Insira o código de barras:");
        String barcode = inputScanner.nextLine();

        System.out.println("Insira o identificador de pagamento:");
        String id = inputScanner.nextLine();

        bankslipDTO.setBarcode(barcode);
        bankslipDTO.setTransactionId(id);

        return bankslipDTO;
    }

    @Override
    public void showBankslipData(String data) {
        System.out.println("Dados do boleto: " + data);
    }

    @Override
    public Double getPixData() {
        System.out.println("Insira valor do PIX:");
        Double amount = inputScanner.nextDouble();

        return amount;
    }

    @Override
    public void showPixData(String data) {
    System.out.println("Dados do PIX: " + data);
    }
   
}
