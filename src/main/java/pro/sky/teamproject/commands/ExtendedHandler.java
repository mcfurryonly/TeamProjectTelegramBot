package pro.sky.teamproject.commands;

import com.pengrad.telegrambot.model.Update;

public interface ExtendedHandler {
    void handle(Update update, String userChoice);

    boolean isSuitable(Update update);
}
