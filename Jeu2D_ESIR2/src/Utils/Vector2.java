package Utils;

//vector of ints
public class Vector2 {
    int m_x;
    int m_y;

    public Vector2(int x, int y) {
        this.m_x = x;
        this.m_y = y;

    }
    public String ToString()
    {
        return "("+m_x+","+m_y+")";
    }

    //setter et getters pour x et y

    public void setX(int x) {
        this.m_x = x;
    }

    public void setY(int y) {
        this.m_y = y;
    }

    public int getX() {
        return m_x;
    }

    public int getY() {
        return m_y;
    }


    //normalize
    public Vector2 normalize()
    {
        double norme = norm();
        return new Vector2((int)(m_x/norme*100),(int)(m_y/norme*100));
    }

    //norm of the vector
    public double norm()
    {
        return Math.sqrt(Math.pow(m_x,2)+Math.pow(m_y,2));
    }


    //addition of two vectors
    public Vector2 add(Vector2 v)
    {
        return new Vector2(m_x+v.getX(),m_y+v.getY());
    }

    //substraction of two vectors
    public Vector2 sub(Vector2 v)
    {
        return new Vector2(m_x-v.getX(),m_y-v.getY());
    }




}
