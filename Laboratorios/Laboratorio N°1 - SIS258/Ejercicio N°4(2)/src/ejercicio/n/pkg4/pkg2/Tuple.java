package ejercicio.n.pkg4.pkg2;
public class Tuple<First, Second> {
    private final First first;
    private final Second second;
    public Tuple(First _first, Second _second) {
        this.first = _first;
        this.second = _second;
    }
    public First getFirst() {
        return this.first;
    }
    public Second getSecond() {
        return this.second;
    }
    @Override
    public String toString() {
        return "(" + this.first + ", " + this.second + ")";
    }
}