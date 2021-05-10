package EntitY;

import Shapes.MyPolygon;
import Shapes.Tetrahedron;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Entity implements IEntity{
    private List<Tetrahedron> tetrahedrons;
    private MyPolygon[] polygons;

    public Entity(List<Tetrahedron> tetrahedrons){
        this.tetrahedrons = tetrahedrons;
        List<MyPolygon> tempList = new ArrayList<MyPolygon>();
        for(Tetrahedron tetra:this.tetrahedrons){
            tempList.addAll(Arrays.asList(tetra.getPolygons()));
        }
        this.polygons = new MyPolygon[tempList.size()];
        this.polygons = tempList.toArray(this.polygons);
        this.sortPolygons();
    }

    private void sortPolygons(){
        MyPolygon.sortPolygons(this.polygons);
    }

    @Override
    public void render(Graphics g) {
        for(MyPolygon poly : this.polygons){
            poly.render(g);
        }
    }

    @Override
    public void rotate(boolean CW, double xDegrees, double yDegrees, double zDegrees) {
        for(Tetrahedron t:this.tetrahedrons){
            t.rotate(CW, xDegrees, yDegrees, zDegrees);
        }
        MyPolygon.sortPolygons(this.polygons);
    }
}
