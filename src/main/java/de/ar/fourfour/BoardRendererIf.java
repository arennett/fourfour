package de.ar.fourfour;

import de.ar.fourfour.model.BoardModelIf;

import java.awt.*;

public interface BoardRendererIf {
    void render (Graphics g, BoardModelIf bm);
}
