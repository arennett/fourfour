package de.ar.fourfour.model;

import de.ar.fourfour.FFException;

import java.io.IOException;

public interface BoardModelReaderIf {
    void readFile(String fileName, BoardModelIf bModel) throws IOException, FFException;

    void readModel(BoardModelIf bmodel, String modelName) throws IOException, FFException;

}
