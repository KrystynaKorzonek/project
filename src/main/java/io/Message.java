package io;

public enum Message {
    // komunikaty (co się stało)
    FAILED_ADD,
    IMPOSSIBLE_CREATE,
    CIRCUMCIRCLE_CHOSEN,
    IMPOSSIBLE_CIRCUMCIRCLE,
    ADDED,
    CHOSEN_F,
    DELETED,

    WRITTEN_TO_FILE,
    NOT_ADDED_ALREADY_EXISTS,

    //obsługa zaokrągleń
    CHOOSE_ROUND,
    ROUND_INCORRECT,
    ROUND_CHANGED,

    // komunikat typu "wpisz komendę z podanych..."
    CHOOSE_NUMBER_OF_SHAPE


}
