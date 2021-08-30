package Exceptions;

import javax.security.auth.login.AccountLockedException;
import javax.security.auth.login.AccountNotFoundException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;

public class Server {
    private HashMap<String, Account> accounts;
    private static final int blockTime = 10;

    public Server(HashSet<Account> accounts) {
        this.accounts = new HashMap<>();
        accounts.forEach(account -> this.accounts.put(account.getCard(), account));
    }

    public double showBalance(String card) throws AccountNotFoundException {
        try {
            return accounts.get(card).getBalance();
        } catch (NullPointerException exception) {
            throw new AccountNotFoundException("Счёт не найден.");
        }
    }

    public boolean tryGetMoney(String card, double money) throws InsufficientFundsException, AccountNotFoundException {
        Account account = accounts.get(card);
        if (account == null) {
            throw new AccountNotFoundException("Счёт не найден.");
        }
        if (account.getBalance() - money < 0) {
            throw new InsufficientFundsException();
        }
        account.writeOff(money);
        return true;
    }

    public boolean tryPutMoney(String card, double money) throws OverMoneyException, AccountNotFoundException {
        Account account = accounts.get(card);
        if (account == null) {
            throw new AccountNotFoundException("Счёт не найден.");
        }
        if (Double.MAX_VALUE - money < account.getBalance()) {
            throw new OverMoneyException();
        }
        account.topUp(money);
        return true;
    }

    public boolean getAccess(String card, int password) throws AccountNotFoundException, AccountLockedException {
        try {
            return accounts.get(card).isPassword(password);
        } catch (NullPointerException exception) {
            throw new AccountNotFoundException("Счёт не найден.");
        }
    }

    public void lock(String card) throws AccountNotFoundException {
        try {
            accounts.get(card).setBlock(blockTime);
        } catch (NullPointerException exception) {
            throw new AccountNotFoundException("Счёт не найден.");
        }
    }

    public boolean isUnLocked(String card) throws AccountLockedException, AccountNotFoundException {
        try {
            return accounts.get(card).isUnLocked();
        } catch (NullPointerException exception) {
            throw new AccountNotFoundException("Счёт не найден.");
        }
    }

    class InsufficientFundsException extends Exception {

        @Override
        public String getMessage() {
            return "Недостаточно средств.";
        }
    }

    class OverMoneyException extends Exception {

        @Override
        public String getMessage() {
            return "Слишком крупная сумма.";
        }
    }
}
