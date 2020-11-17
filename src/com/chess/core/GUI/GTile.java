package com.chess.core.GUI;

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
    private Color color;
    private Image pieceImage;

    public GTile(int position, Color color) {
        this.x = Converter.getColumnNumber(position);
        this.y = Converter.getRowNumber(position);
        this.color = color;
        this.setSize(50, 50);
        this.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                System.out.println(position);
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
        g.fillRect(0, 0, 50, 50);
        g.drawImage(pieceImage, 0, 0, 50, 50, this);
    }

    public void drawPiece(Image pieceImage) {
        this.pieceImage = pieceImage;
        this.repaint();
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

}
