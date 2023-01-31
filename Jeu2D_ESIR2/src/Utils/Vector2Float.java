package Utils;

//vector of float
public class Vector2Float {
    float m_x;
    float m_y;

    public Vector2Float(float x, float y) {
        this.m_x = x;
        this.m_y = y;

    }
    public String ToString()
    {
        return "("+m_x+","+m_y+")";
    }

    //setter et getters pour x et y

    public void setX(float x) {
        this.m_x = x;
    }

    public void setY(float y) {
        this.m_y = y;
    }

    public float getX() {
        return m_x;
    }

    public float getY() {
        return m_y;
    }


    //normalize
    public Vector2Float normalize()
    {

        float norme = norm();
        if (norme == 0)
            return new Vector2Float(0, 0);
        return new Vector2Float(m_x/norme,m_y/norme);
    }

    //norm of the vector
    public float norm()
    {
        return (float) Math.sqrt(Math.pow(m_x,2)+Math.pow(m_y,2));
    }


    //addition of two vectors
    public Vector2Float add(Vector2Float v)
    {
        return new Vector2Float(m_x+v.getX(),m_y+v.getY());
    }

    //substraction of two vectors
    public Vector2Float sub(Vector2Float v)
    {
        return new Vector2Float(m_x-v.getX(),m_y-v.getY());
    }


    public Vector2Float mul(float f)
    {
        return new Vector2Float(m_x*f,m_y*f);
    }

    public Vector2Float div(float f)
    {
        return new Vector2Float(m_x/f,m_y/f);
    }

    public float dot(Vector2Float v)
    {
        return m_x*v.getX()+m_y*v.getY();
    }

    public float cross(Vector2Float v)
    {
        return m_x*v.getY()-m_y*v.getX();
    }

    public Vector2Float rotate(float angle)
    {
        float x = (float) (m_x*Math.cos(angle)-m_y*Math.sin(angle));
        float y = (float) (m_x*Math.sin(angle)+m_y*Math.cos(angle));
        return new Vector2Float(x,y);
    }

    public Vector2Float rotate(Vector2Float center,float angle)
    {
        Vector2Float v = this.sub(center);
        v = v.rotate(angle);
        v = v.add(center);
        return v;
    }



    public float getAngle()
    {
        return (float) Math.atan2(m_y,m_x);
    }

    public void setZeros(){
        m_x = 0;
        m_y = 0;

    }

}
