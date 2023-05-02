package client;

import controller.facade.ExpedidFacadeImpl;
import controller.facade.IExpedidFacade;

import java.util.Scanner;

;

public class Expedid {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        IExpedidFacade expedid = ExpedidFacadeImpl.getInstance();
        String command;

        while (true) {
            command = scanner.nextLine();
            expedid.enter(command);
        }
    }
}
