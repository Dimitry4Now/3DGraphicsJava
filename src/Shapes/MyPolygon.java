package Shapes;

import Point.MyPoint;
import Point.PointConverter;

import java.awt.*;
import java.util.*;
import java.util.List;

public class MyPolygon {
    private Color color;
    private MyPoint[] points;

    public MyPolygon(Color color, MyPoint... points){
        this.color = color;
        this.points = new MyPoint[points.length];
        for(int i = 0; i <this.points.length; i++) {
           MyPoint p = points[i];
           this.points[i] = new MyPoint(p.x, p.y, p.z);
        }
    }

    public MyPolygon(MyPoint... points){
        this.color = Color.GREEN;
        this.points = new MyPoint[points.length];
        for(int i = 0; i <this.points.length; i++) {
            MyPoint p = points[i];
            this.points[i] = new MyPoint(p.x, p.y, p.z);
        }
    }

    public void render(Graphics g){
        Polygon polygon = new Polygon();
        for (MyPoint point : points) {
            Point p = PointConverter.convertPoint(point);
            polygon.addPoint(p.x, p.y);
        }
        g.setColor(this.color);
        g.fillPolygon(polygon);
    }

    public void rotate(boolean CW, double xDegrees, double yDegrees, double zDegrees){
        for(MyPoint p:points){
            PointConverter.rotateAxisX(p, CW, xDegrees);
            PointConverter.rotateAxisY(p, CW, yDegrees);
            PointConverter.rotateAxisZ(p, CW, zDegrees);
        }
    }

    public double getAverageX(){
        double sum = 0;
        for(MyPoint p:this.points){
            sum += p.x;
        }
        return sum / this.points.length;
    }

    public static MyPolygon[] sortPolygons(MyPolygon[] polygons){
        List<MyPolygon> polygonList = new ArrayList<MyPolygon>();

        for(MyPolygon polygon:polygons){
            polygonList.add(polygon);
        }

        Collections.sort(polygonList, new Comparator<MyPolygon>() {
            @Override
            public int compare(MyPolygon p1, MyPolygon p2) {
                double p1AverageX = p1.getAverageX();
                double p2AverageX = p2.getAverageX();
                double diff = p2AverageX - p1AverageX;
                if(diff == 0){
                    return 0;
                }
                return diff < 0 ? 1 : -1;
            }
        });

        for(int i = 0; i < polygons.length; i++){
            polygons[i] = polygonList.get(i);
        }
        return polygons;
    }

    public void setColor(Color color){
        this.color = color;
    }
}
