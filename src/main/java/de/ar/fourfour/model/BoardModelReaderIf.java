package de.ar.fourfour.model;

import de.ar.fourfour.FFException;

import java.io.IOException;

public interface BoardModelReaderIf {
    void readFile(String fileName, BoardModelIf bModel) throws IOException;

    void readModel(BoardModelIf bmodel, String modelName) throws IOException, FFException;

}
