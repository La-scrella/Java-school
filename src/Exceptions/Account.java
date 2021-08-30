package Exceptions;

import javax.security.auth.login.AccountLockedException;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Account {
    private String card;
    private double balance;
    private int password;
    private LocalTime blockTime;

    public Account(String card, double balance, int password) {
        this.balance = balance;
        this.card = card;
        this.password = password;
    }

    public void writeOff(double money) {
        balance -= money;
    }

    public void topUp(double money) {
        balance += money;
    }

    public void setBlock(int seconds) {
        blockTime = LocalTime.now().plusSeconds(seconds);
    }

    public double getBalance() {
        return balance;
    }

    public String getCard() {
        return card;
    }

    public boolean isPassword(int password) throws AccountLockedException {
        return isUnLocked() && this.password == password;
    }

    public boolean isUnLocked() throws AccountLockedException {
        LocalTime now = LocalTime.now();
        if (blockTime != null && blockTime.isAfter(now)) {
            int h = blockTime.getHour() - now.getHour();
            int m = blockTime.getMinute() - now.getMinute();
            int s = blockTime.getSecond() - now.getSecond() +
                    60 * (m + 60 * h);
            throw new AccountLockedException("Аккаунт будет разблокирован через " +
                    /*((h > 0) ? (h + " часов ") : "") + ((m > 0) ? m + " минут " : "") + */(s + " секунд"));
        } else return true;
    }
}
