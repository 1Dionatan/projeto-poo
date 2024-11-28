package com.fag.service;

import java.time.LocalDateTime;
import java.util.UUID;

import com.fag.domain.dto.BankslipDTO;
import com.fag.domain.dto.LoginDTO;
import com.fag.domain.dto.UserAccountDTO;
import com.fag.domain.repositories.IBassRepository;
import com.fag.domain.repositories.IUserInterface;
import com.fag.domain.repositories.IUserRepository;
import com.fag.infra.celcoin.CelcoinBassRepository;

public class BankingService {

     private IUserInterface gui;
     private IUserRepository userDB;
     private IBassRepository bassRepo;

     private Integer account = 1;

    public BankingService(IUserInterface gui, IUserRepository userDB, CelcoinBassRepository celcoinRepo) {
        this.gui = gui;
        this.userDB = userDB;
        this.bassRepo = celcoinRepo;
    }


    public Integer showMenu(){
        return gui.showInitialScreenMenu();
    }

    public LoginDTO getLoginDTO(){
        return gui.getloginData();
    }

   
    public UserAccountDTO getRegisterUserDTO(){
        UserAccountDTO userAccountDTO = gui.getRegisterUser();
        String uuid = UUID.randomUUID().toString();

        userAccountDTO.setAccountNumber(account.toString());
        userAccountDTO.setCreatedAt(LocalDateTime.now());  

        userAccountDTO.setId(uuid);
        userAccountDTO.setAccountNumber(account.toString());
        userAccountDTO.setCreatedAt(LocalDateTime.now());

        account++;

        return userAccountDTO;
    }

    public void showExitMessage(){
        gui.showExitMessage();
    }

    public UserAccountDTO createUserAccountDTO(UserAccountDTO user){
        return user;
    }

    public void login(UserAccountDTO user) throws Exception{
        while(true){
        Integer opcao = gui.showHomeMenu(user.getName());

        switch (opcao) {

            case 1:
                String barcode = gui.getBarcode();

                String bankSlipData = bassRepo.consultarBoleto(barcode);

                gui.showBankslipData(bankSlipData);
                break;

            case 2:
                BankslipDTO bankslipDTO = gui.getPaymentBankslipInfo();

                String paymentReponse = bassRepo.pagarBoleto(bankslipDTO);

                gui.showBankslipData(paymentReponse);
                break;

            case 3:
                Double amount = gui.getPixData();

                String pixResponse = bassRepo.gerarQrCode(amount);

                gui.showBankslipData(pixResponse);
                break;

            case 4: 
                gui.showExitMessage();
                return;
            }
        }
    }

    public UserAccountDTO createUser(UserAccountDTO user){
        return userDB.createUser(user);
    }

    public UserAccountDTO findUser(LoginDTO loginDTO){

        UserAccountDTO user = userDB.findUserBy(loginDTO.getDocument());

        if(user == null){
            gui.showErrorMsg("Usuário não encontrado");

            return null;
        }

        if (!user.getPassword().equals(loginDTO.getPassword())){
             gui.showErrorMsg("Senha incorreta.");
            
            return null;
        }

        return user;
    } 

}
