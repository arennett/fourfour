package de.ar.fourfour;

import de.ar.fourfour.model.BoardModelIf;

import javax.swing.*;
import java.awt.*;

public interface BoardRendererIf {
    public void render (Graphics g, BoardModelIf bm);
}
