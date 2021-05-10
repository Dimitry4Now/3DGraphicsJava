package EntitY;

import EntitY.Builder.BasicEntityBuilder;
import EntitY.Builder.ComplexEntityBuilder;
import Input.ClickType;
import Input.Mouse;
import Point.PointConverter;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class EntityManager {
    private List<IEntity> entities;
    private int initialX, initialY;
    private double mouseSensitivity = 1.2;

    public EntityManager(){
        this.entities = new ArrayList<IEntity>();
    }

    public void init(){
       // this.entities.add(BasicEntityBuilder.createDiamond( new Color(200, 40, 150),200, 0, 0, 0));
        this.entities.add(ComplexEntityBuilder.createRubicksCube(100, 0, 0, 0));
    }

    public void update(Mouse mouse){
        int x = mouse.getMouseX();
        int y = mouse.getMouseY();
        if(mouse.getMouseB() == ClickType.LeftClick){
            int xDif = x - initialX;
            int yDif = y - initialY;

            this.rotate(true, 0, -yDif/mouseSensitivity, -xDif/mouseSensitivity);
        }
        else if(mouse.getMouseB() == ClickType.RightClick){
            int xDif = x - initialX;

            this.rotate(true, xDif, 0, 0);
        }

        if(mouse.isScrollingUp()){
            PointConverter.zoomIn();
        }
        else if(mouse.isScrollingDown()){
            PointConverter.zoomOut();
        }

        mouse.resetScroll();

        initialX = x;
        initialY = y;
    }

    private void rotate(boolean CW, double xDegrees, double yDegrees, double zDegrees){
        for(IEntity ie: this.entities){
            ie.rotate(CW, xDegrees, yDegrees, zDegrees);
        }
    }

    public void render(Graphics g){
        for(IEntity ie: this.entities){
            ie.render(g);
        }
    }
}
