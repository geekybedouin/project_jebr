/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jebr;


import org.fife.ui.rsyntaxtextarea.Style;
import org.fife.ui.rsyntaxtextarea.TokenTypes;



import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.io.IOException;
import java.io.InputStream;
import org.xml.sax.Attributes;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

public class SyntaxScheme implements Cloneable, TokenTypes {

    private static class XmlParser extends DefaultHandler {

        private Font baseFont;
        private SyntaxScheme scheme;

        public XmlParser(Font baseFont) {
            //compiled code
            throw new RuntimeException("Compiled Code");
        }

        private static XMLReader createReader() throws IOException {
            //compiled code
            throw new RuntimeException("Compiled Code");
        }

        public static SyntaxScheme load(Font baseFont, InputStream in) throws IOException {
            //compiled code
            throw new RuntimeException("Compiled Code");
        }

        public void startElement(String uri, String localName, String qName, Attributes attrs) {
            //compiled code
            throw new RuntimeException("Compiled Code");
        }
    }
    public Style[] styles;
    private static final String VERSION = "*ver1";

    public SyntaxScheme(boolean useDefaults) {
        //compiled code
        throw new RuntimeException("Compiled Code");
    }

    public SyntaxScheme(Font baseFont) {
        //compiled code
        throw new RuntimeException("Compiled Code");
    }

    public SyntaxScheme(Font baseFont, boolean fontStyles) {
        //compiled code
        throw new RuntimeException("Compiled Code");
    }

    void changeBaseFont(Font old, Font font) {
        //compiled code
        throw new RuntimeException("Compiled Code");
    }

    public Object clone() {
        //compiled code
        throw new RuntimeException("Compiled Code");
    }

    public boolean equals(Object otherScheme) {
        //compiled code
        throw new RuntimeException("Compiled Code");
    }

    private static final String getHexString(Color c) {
        //compiled code
        throw new RuntimeException("Compiled Code");
    }

    public Style getStyle(int index) {
        //compiled code
        throw new RuntimeException("Compiled Code");
    }

    public int getStyleCount() {
        //compiled code
        throw new RuntimeException("Compiled Code");
    }

    public int hashCode() {
        //compiled code
        throw new RuntimeException("Compiled Code");
    }

    public static SyntaxScheme load(Font baseFont, InputStream in) throws IOException {
        //compiled code
        throw new RuntimeException("Compiled Code");
    }

    public static SyntaxScheme loadFromString(String string) {
        //compiled code
        throw new RuntimeException("Compiled Code");
    }

    void refreshFontMetrics(Graphics2D g2d) {
        //compiled code
        throw new RuntimeException("Compiled Code");
    }

    public void restoreDefaults(Font baseFont) {
        //compiled code
        throw new RuntimeException("Compiled Code");
    }

    public void restoreDefaults(Font baseFont, boolean fontStyles) {
        //compiled code
        throw new RuntimeException("Compiled Code");
    }

    public void setStyle(int type, Style style) {
        //compiled code
        throw new RuntimeException("Compiled Code");
    }

    private static final Color stringToColor(String s) {
        //compiled code
        throw new RuntimeException("Compiled Code");
    }

    public String toCommaSeparatedString() {
        //compiled code
        throw new RuntimeException("Compiled Code");
    }
}
