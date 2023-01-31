package Utils;


//usefull class used in collition object
public class pair<T, U> {
    public T first;
    public U second;

    public pair(T first, U second) {
        this.first = first;
        this.second = second;
    }

    public T getFirst() {
        return first;
    }

    public U getSecond() {
        return second;
    }

    public void setFirst(T first) {
        this.first = first;
    }

    public void setSecond(U second) {
        this.second = second;
    }

    public String toString() {
        return "(" + first + ", " + second + ")";
    }

    public boolean equals(Object o) {
        if (o instanceof pair) {
            pair<?, ?> p = (pair<?, ?>) o;
            return first.equals(p.first) && second.equals(p.second);
        }
        return false;
    }



}
