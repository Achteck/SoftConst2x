package ui;

import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.Layer;
import org.openstreetmap.gui.jmapviewer.MapMarkerCircle;

import java.awt.*;

public class MapMarkerSimple extends MapMarkerCircle {
    public static final double defaultMarkerSize = 50.0;
    public static final Color defaultColor = Color.red;
    private Image image;

    public MapMarkerSimple(Layer layer, Coordinate coord, Color color, Image image) {
        super(layer, null, coord, defaultMarkerSize, STYLE.FIXED, getDefaultStyle());
        setColor(color);
        setBackColor(color);
        this.image = image;
    }

    @Override
    public void paint(Graphics g, Point position, int radius) {
        int size = radius * 2;
        if (g instanceof Graphics2D && this.getBackColor() != null) {
            Graphics2D g2 = (Graphics2D)g;
            Composite oldComposite = g2.getComposite();
            g2.setComposite(AlphaComposite.getInstance(3));
            g2.setPaint(this.getBackColor());
            g.fillOval(position.x - radius, position.y - radius, size, size);
            g.drawImage(this.image, position.x-(radius/2), position.y-(radius/2), radius, radius, null);
            g2.setComposite(oldComposite);
        }
        g.setColor(this.getColor());
        g.drawOval(position.x - radius, position.y - radius, size, size);
        if (this.getLayer() == null || this.getLayer().isVisibleTexts()) {
            this.paintText(g, position);
        }

    }
}
