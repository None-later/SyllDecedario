/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package contalletres;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;

/**
 *
 * @author jesus
 */
public class Print implements Printable {

    private MainForm mf;

    public Print(MainForm form) {
        this.mf = form;
    }

    @Override
    public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
        Graphics2D g2d = (Graphics2D) graphics;

        //--- Translate the origin to 0,0 for the top left corner
        //g2d.translate(pageFormat.getImageableX(), pageFormat.getImageableY());
        //--- Set the drawing color to black
        g2d.setPaint(Color.black);

        //--- Draw a border arround the page using a 12 point border
        //g2d.setStroke(new BasicStroke(12));
        //Rectangle2D.Double border = new Rectangle2D.Double(0, 0, pageFormat.getImageableWidth(), pageFormat.getImageableHeight());
        //g2d.draw(border);
        //--- Print the text one inch from the top and laft margins
        g2d.drawString("This the content page", 72, 72);

        //--- Validate the page
        return (PAGE_EXISTS);

    }

    private String buildString() {
        return mf.getPalabras() + mf.getLetras() + mf.getIniciales() + mf.getSilabas();
    }

}
