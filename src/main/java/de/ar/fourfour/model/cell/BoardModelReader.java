package de.ar.fourfour.model.cell;

import de.ar.fourfour.FFException;
import de.ar.fourfour.model.BoardModelIf;
import de.ar.fourfour.model.BoardModelReaderIf;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import static de.ar.fourfour.ConstIf.*;
import static de.ar.fourfour.ConstIf.BOARDMAP_FILENAME;

public class BoardModelReader implements BoardModelReaderIf {

    private static final Logger logger = LoggerFactory.getLogger(BoardModelReader.class);

    @java.lang.Override
    public void readFile(String fileName, BoardModelIf bModel) throws IOException, FFException {
        FileReader filereader;
        filereader = new FileReader(fileName);
        BufferedReader bufferedReader = new BufferedReader(filereader);
        logger.debug("reading...");
        int row=0;
        while (bufferedReader.ready()){
            String line = bufferedReader.readLine();
            String[] strs = line.split(" ");
            int col = 0;
            for (String str:strs){
                bModel.setCell(row,col,Cell.create(str,row,col,bModel));
                col++;
            }
            row++;
        }

        bModel.allCellsRead();

    }

    @java.lang.Override
    public void readModel(BoardModelIf bmodel, String modelName) throws IOException, FFException {
        readFile(BOARDMAP_FILENAME+modelName+"_"+ROW_SIZE+".txt",bmodel);
    }
}
