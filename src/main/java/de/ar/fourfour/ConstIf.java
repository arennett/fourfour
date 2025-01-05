package de.ar.fourfour;

public interface ConstIf {
    String BOARDMAP_FILENAME = "src/main/resources/maps/boardmap_";
    int BOARD_LENGTH = 800;
    int ROW_SIZE = 15;
    int MAX_ROW_IDX = ROW_SIZE - 1;
    int CELL_WIDTH = BOARD_LENGTH / ROW_SIZE;

    //public static  int BOARD_OFFSET = BOARD_LENGTH / 30;
    int CELL_MARGIN = CELL_WIDTH / 20;

    int WIN_GROUP_SIZE = 4;
}
