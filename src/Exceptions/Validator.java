package Exceptions;

import java.util.Scanner;

public class Validator {
    private String[] acceptedSymbols;
    private int position;
    private int formingPin;

    public Validator() {
        position = 0;
        formingPin = 0;
        acceptedSymbols = new String[4];
    }

    public void clear() {
        for (int i = 0; i < 4; i++) {
            acceptedSymbols[i] = null;
        }
        position = 0;
        formingPin = 0;
    }

    public boolean addingSymbol(String str) throws PinFormatException {
        if (position > 3) {
            return false;
        }
        if (str.length() > 1) {
            throw new PinFormatException("Вводите по 1 цифре");
        }
        int number;
        try {
            number = Integer.parseInt(str, 10);
        } catch (NumberFormatException ex) {
            throw new PinFormatException("Вводите только цифры");
        }
        acceptedSymbols[position++] = str;
        formingPin = formingPin * 10 + number;
        return position < 4;
    }

    public int getPinCode() throws PinNonReadyException {
        if (position == 4) {
            return formingPin;
        } else {
            throw new PinNonReadyException();
        }
    }

    class PinFormatException extends Exception {
        public PinFormatException(String message) {
            super(message);
        }
    }

    class PinNonReadyException extends Exception {

        @Override
        public String getMessage() {
            return "Пин-код не введён";
        }
    }
}
