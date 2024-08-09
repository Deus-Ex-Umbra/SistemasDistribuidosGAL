package ejercicio.n.pkg1.pkg1;
public class CalculosRectangulo {
    public static float calcularAreaRectangulo(float _base, float _altura) {
        if (_base <= 0 || _altura <= 0) throw new IllegalArgumentException("No existen unidades negativas o nulas");
        return _base * _altura;
    }

    public CalculosRectangulo() {
    }
    public static float calcularPerimetroRectangulo(float _base, float _altura) {
        if (_base <= 0 || _altura <= 0) throw new IllegalArgumentException("No existen unidades negativas o nulas");
        return _base * 2 + _altura + 2;
    }
}
