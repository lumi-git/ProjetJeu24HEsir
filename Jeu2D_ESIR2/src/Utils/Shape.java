package Utils;

public interface Shape {

    public boolean intersects(Rect r);

    public boolean contains(float x, float y) ;

    public boolean contains(Rect r);
}
