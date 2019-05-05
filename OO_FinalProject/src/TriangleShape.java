import java.awt.geom.Path2D;
import java.awt.geom.Point2D;

public class TriangleShape extends Path2D.Double {

        public TriangleShape(Point2D... points) {
            moveTo(points[0].getX(), points[0].getY());
            for(int i=0; i<points.length;i++) {
            	lineTo(points[i].getX(), points[i].getY());
            }
            closePath();
        }
        

    }