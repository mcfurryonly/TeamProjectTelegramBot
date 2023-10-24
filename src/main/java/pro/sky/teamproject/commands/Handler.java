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

//    //Получить имя посетителя и потом сравнить с тем именем пользователя
//    @Query(value = "SELECT * FROM visitor WHERE name like :name", nativeQuery = true)
//    List<Visitor> isNewUser(@Param("name") String name);

    //Add user to database
///    @Modifying
///    @Query(value = "INSERT INTO visitor (name) VALUES (:name)", nativeQuery = true)
//    void addUser(@Param("name") String name);

}

