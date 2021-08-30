package Exceptions;

import javax.security.auth.login.AccountLockedException;
import javax.security.auth.login.AccountNotFoundException;
import java.util.Scanner;

public class Task4 {
    public static void main(String[] args) {
        Terminal terminal = new Terminal();
        terminal.setCard("4268 2001");
        Scanner scanner = new Scanner(System.in);
        String string;
        System.out.println("Введите пин-код:");
        do {
            string = scanner.nextLine();
            try {
                String out = terminal.process(string);
                if (!out.equals("")) {
                    System.out.println(out);
                }
            } catch (Validator.PinFormatException | Validator.PinNonReadyException
                    | AccountNotFoundException | AccountLockedException
                    | Terminal.WrongPinCodeException | Terminal.CashException
                    | Server.OverMoneyException | Server.InsufficientFundsException
                    | Terminal.NoOperationException exception) {
                System.out.println(exception.getMessage());
            }
        } while (!string.toLowerCase().equals("exit"));
    }
}
