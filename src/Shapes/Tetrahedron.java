package Shapes;

import java.awt.*;

public class Tetrahedron {
    private MyPolygon[] polygons;
    private Color color;

    public Tetrahedron(Color color,boolean decayColor, MyPolygon... polygons){
        this.color = color;
        this.polygons = polygons;
        if(decayColor){
            setRandomPolygonColor();
        }
        else{
            this.setPolygonColor();
        }
        this.sortPolygons();
    }



    public Tetrahedron(MyPolygon... polygons){
        this.color = Color.white;
        this.polygons = polygons;
        this.sortPolygons();
    }

    public void render(Graphics g){
        for(MyPolygon polygon:this.polygons){
            polygon.render(g);
        }
    }

    public void rotate(boolean CW, double xDegrees, double yDegrees, double zDegrees){
        for(MyPolygon p:polygons){
            p.rotate(CW, xDegrees, yDegrees, zDegrees);
        }
        this.sortPolygons();
    }

    private void sortPolygons(){
        MyPolygon.sortPolygons(this.polygons);
    }

    private void setPolygonColor(){
        for(MyPolygon polygon:this.polygons){
            polygon.setColor(color);
        }
    }

    private void setRandomPolygonColor(){
        double decayFactor = 0.95;
        for(MyPolygon polygon:this.polygons){
            polygon.setColor(this.color);
            int R = (int) (this.color.getRed() * decayFactor);
            int G = (int) (this.color.getGreen() * decayFactor);
            int B = (int) (this.color.getBlue() * decayFactor);
            this.color = new Color(R, G, B);
        }
    }

    public MyPolygon[] getPolygons(){
        return this.polygons;
    }
}
