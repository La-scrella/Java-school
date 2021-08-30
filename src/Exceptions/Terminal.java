package Exceptions;

import javax.security.auth.login.AccountLockedException;
import javax.security.auth.login.AccountNotFoundException;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;

public class Terminal {
    private TerminalState state;
    private static final Server server = new Server(new HashSet<>(Arrays.asList(
            new Account("4268 2001", 3000, 1234),
            new Account("4268 2002", 50, 5678))));
    private static final Validator validator = new Validator();
    private int attempt = 0;
    private String card;

    public Terminal() {
        state = TerminalState.PASSWORDINPUT;
    }

    public void setCard(String card) {
        this.card = card;
    }

    private boolean checkCash(int cash) throws CashException {
        boolean a1 = cash > 0;
        boolean a2 = cash % 100 == 0;
        if (a1 && a2) {
            return true;
        } else {
            String s = "кратна 100";
            if (!a1) {
                s = "положительна" + (!a2? (" и " + s): "");
            }
            throw new CashException(s);
        }
    }

    public String process(String str) throws Validator.PinFormatException, Validator.PinNonReadyException, AccountNotFoundException, AccountLockedException, WrongPinCodeException, CashException, Server.OverMoneyException, Server.InsufficientFundsException, NoOperationException {
        switch (state) {
            case PASSWORDINPUT: {
                if (!validator.addingSymbol(str)) {
                    if (server.getAccess(card, validator.getPinCode())) {
                        state = TerminalState.ACCESSGRANTED;
                        return "Выберите операцию: balance, put xxx, take xxx";
                    } else {
                        validator.clear();
                        if (++attempt == 3) {
                            server.lock(card);
                            state = TerminalState.TIMEOUT;
                            attempt = 0;
                            return "Карта заблокирована";
                        }
                        throw new WrongPinCodeException("Пин-код не совпадает! Осталось " +
                                (3 - attempt) + " попытки до блокировки");
                    }
                }
                break;
            }
            case TIMEOUT: {
                if (server.isUnLocked(card)) {
                    state = TerminalState.PASSWORDINPUT;
                    return "Карта разблокирована. У Вас есть 3 попытки ввода.";
                }
                break;
            }
            case ACCESSGRANTED: {
                if (str.toLowerCase().equals("balance")) {
                    return String.valueOf(server.showBalance(card));
                }
                String[] strings = str.split(" ");
                if (strings.length == 2) {
                    String operation = strings[0].toLowerCase();
                    try {
                        switch (operation) {
                            case "put": {
                                int cash = Integer.parseInt(strings[1]);
                                if (checkCash(cash) && server.tryPutMoney(card, cash)) {
                                    return "Зачислено " + cash + ". Остаток " + server.showBalance(card);
                                }
                                break;
                            }
                            case "take": {
                                int cash = Integer.parseInt(strings[1]);
                                if (checkCash(cash) && server.tryGetMoney(card, cash)) {
                                    return "Снято " + cash + ". Остаток " + server.showBalance(card);
                                }
                                break;
                            }
                            default:
                                throw new NoOperationException();
                        }
                    } catch (NumberFormatException exception) {
                        throw new CashException("целым числом");
                    }
                } else {
                    throw new NoOperationException();
                }
            }
        }
        return "";
    }

    class WrongPinCodeException extends Exception {
        public WrongPinCodeException(String message) {
            super(message);
        }
    }

    class CashException extends Exception {
        private static final String string = "Сумма должна быть ";

        public CashException(String message) {
            super(message);
        }

        @Override
        public String getMessage() {
            return string + super.getMessage();
        }
    }

    class NoOperationException extends Exception {
        @Override
        public String getMessage() {
            return "Нет такой операции! Допустимые операции: balance, put xxx, take xxx.";
        }
    }

}
