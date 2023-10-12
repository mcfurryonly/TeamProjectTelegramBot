package pro.sky.teamproject.model.enums;

public enum TypeRule {
    ACCESS_TO_TERRITORY("Пропуск на территорию приюта"),
    IN_TERRITORY("Пропуск на территорию приюта"),
    COMMUNICATION_WITH_ANIMALS("Пропуск на территорию приюта"),
    MEETING_WITH_ANIMALS("Пропуск на территорию приюта");

    private final String note;
    //Переменная где хранится инфо на русском языке, без нее значение негде хранить

    TypeRule(String note) {
        this.note = note;
    }

    public String getNote() {
        return note;
    }
}