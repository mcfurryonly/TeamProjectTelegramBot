package pro.sky.teamproject.model.enums;

public enum TypeRecommendation {
    ANIMAL_TRANSPORTATION(""),
    BIG_HOUSE(""),
    SMALL_HOUSE(""),
    HOUSE_FOR_DISABLED(""),
    ADVISE_OF_CYNOLOGIST(""),
    CYNOLOGISTS(""),
    SAFETY_PRECAUTIONS("");

    private final String note;

    TypeRecommendation(String note) {
        this.note = note;
    }

    public String getNote() {
        return note;
    }
}

