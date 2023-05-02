package client;

import controller.facade.ExpedidFacadeImpl;
import controller.facade.IExpedidFacade;

import java.util.Scanner;

;

public class Expedid {
    public static void main(String[] args) {
        IExpedidFacade expedid = ExpedidFacadeImpl.getInstance();
        Scanner scanner = new Scanner(System.in);
        String command;

        while (true) {
            System.out.print("> ");
            command = scanner.nextLine();
            String result = expedid.enter(command);

            System.out.println(result);
        }
    }
}
