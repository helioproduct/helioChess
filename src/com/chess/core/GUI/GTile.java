package com.chess.core.GUI;

import com.chess.core.game.Game;
import com.chess.core.service.Converter;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

public class GTile extends JComponent {

    private final int x, y;
    private Color color, legalColor;
    private Image pieceImage;
    private boolean targeted;

    public GTile(int position, Color color, Color legalColor, Game game) {
        this.x = Converter.getColumnNumber(position);
        this.y = Converter.getRowNumber(position);
        this.color = color;
        this.legalColor = legalColor;
        this.setSize(50, 50);
        this.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                game.handleClick(position);
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(color);
        if (targeted) {
            g.setColor(legalColor);
        }
        g.fillRect(0, 0, 50, 50);
        g.drawImage(pieceImage, 0, 0, 50, 50, this);
    }

    public void drawPiece(Image pieceImage) {
        this.pieceImage = pieceImage;
        this.repaint();
    }

    public void removePiece() {
        this.pieceImage = null;
        this.repaint();
    }

    public void makeTargeted() {
        this.targeted = true;
    }

    public void makeOrdinary() {
        this.targeted = false;
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(50, 50);
    }

    public int getXCoordinate() {
        return x;
    }

    public int getYCoordinate() {
        return y;
    }

    public Image getPieceImage() {
        return pieceImage;
    }
}
