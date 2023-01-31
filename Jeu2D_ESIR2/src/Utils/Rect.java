package Utils;

//permite simple colision detection
public class Rect implements Shape{
    //Rectancle class
    public float x,y,width,height;
    public Rect(float x, float y, float width, float height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public boolean intersects(Rect r) {
        return this.x < r.x + r.width &&
                this.x + this.width > r.x &&
                this.y < r.y + r.height &&
                this.y + this.height > r.y;
    }

    public boolean contains(float x, float y) {
        return this.x < x &&
                this.x + this.width > x &&
                this.y < y &&
                this.y + this.height > y;
    }

    public boolean contains(Rect r) {
        return this.x < r.x &&
                this.x + this.width > r.x + r.width &&
                this.y < r.y &&
                this.y + this.height > r.y + r.height;
    }

    public Rect copy(){
        return new Rect(x,y,width,height);

    }


}

