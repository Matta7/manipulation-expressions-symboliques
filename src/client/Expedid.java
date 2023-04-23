package client;

import facade.ExpedidFacadeImpl;
import facade.IExpedidFacade;

import java.util.Objects;
import java.util.Scanner;

public class Expedid {
    public static void main(String[] args) {
        IExpedidFacade expedid = ExpedidFacadeImpl.getInstance();
        Scanner scanner = new Scanner(System.in);
        String command;

        while (true) {
            System.out.print("> ");
            command = scanner.nextLine();
            String result = expedid.enter(command);
            if (Objects.equals(result, "quit")) {
                break;
            } else {
                System.out.println(result);
            }
        }
    }
}
