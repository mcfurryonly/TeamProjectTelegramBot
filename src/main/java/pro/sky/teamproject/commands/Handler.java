package pro.sky.teamproject.commands;

import com.pengrad.telegrambot.model.Update;

public interface Handler {


    /**
     * @param update contains user choose and commands like
     *               /infoDog
     *               /takeDog
     *               /reportDog
     *               /infoCat
     *               /takeCat
     *               /reportCat
     *               /volunteer
     */
    void handle(Update update);

}