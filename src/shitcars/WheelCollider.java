/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shitcars;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Circle;

/**
 *
 * @author carls
 */
public class WheelCollider extends Entity {

    private Ranger ranger;
    private Circle wheel;
    private float radius;
    private boolean grounded;
    
    public WheelCollider(float x, float y, float radius, float length,Entity parent) {
        super(x, y);

        ranger = new Ranger(0, 0, 100);
        ranger.parent = this;
        ranger.setLength(length);
        ranger.ignoreEntity(this);
        ranger.ignoreEntity(parent);
        grounded = false;
        this.radius = radius;
        wheel = new Circle(x + 100, y, radius);
    }

    public void update() {
        if (enabled) {
            super.update();
            wheel.setLocation(x - radius, (float)(y + ranger.getDistance()-(radius*2)));
            ranger.update();
            if(ranger.isHit()){
                grounded = true;
            }else{
                grounded = false;
            }
        }
    }

    public void fixedUpdate() {
        if (enabled) {
            ranger.fixedUpdate();
        }
    }

    public void render(Graphics g) {
        wheel.setLocation(x + Map.viewX - radius, y + Map.viewY + ranger.getDistance() - radius*2);
        g.draw(wheel);
        ranger.render(g);
    }

    public float distance() {
            if(grounded){
            return ranger.getLength() - ranger.getDistance();
            }else{
                return 0;
            }
    }

    /**
     * @return the ranger
     */
    public Ranger getRanger() {
        return ranger;
    }

    /**
     * @return the grounded
     */
    public boolean isGrounded() {
        return grounded;
    }

}
