package BaseClass;


import Utils.States;
import Utils.Vector2Float;
import main.GamePanel;

public class GameObject extends TrackedEntity  {

    public States state;
    public Vector2Float direction;

    protected boolean collidable;

    public GameObject(boolean Collidable) {

        direction = new Vector2Float(0,0);
        //if collidable, the object will be added to the list of collidable objects of the game panel
        collidable = Collidable;
        if (Collidable) {
              gp.NewObstacle(this);
        }

        this.state = States.LIVE;
    }

    //target an entity
    public void target(Vector2Float Pos) {
        direction = getPos().sub(Pos).normalize();
        //print direction x and y
    }

    public void destroy() {
        this.state = States.DESTROY;
    }



}

