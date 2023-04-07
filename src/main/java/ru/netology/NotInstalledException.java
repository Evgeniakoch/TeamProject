package ru.netology;

public class NotInstalledException extends RuntimeException {
    public NotInstalledException(Game game) {
        super("Игра" + game + "не установлена");
    }
}
