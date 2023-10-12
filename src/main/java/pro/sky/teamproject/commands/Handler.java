package pro.sky.teamproject.commands;

import com.pengrad.telegrambot.model.Update;

public interface Handler {

    void handle(Update update);

    boolean isSuitable(Update update);





}