package ejercicio.n.pkg1;

public class Diploma {
    private String fullname;
    private String career;
    private String date;
    private String message;

    public Diploma(String fullname, String career, String date, String message) {
        this.fullname = fullname;
        this.career = career;
        this.date = date;
        this.message = message;
    }

    @Override
    public String toString() {
        return "Diploma{" + "fullname=" + fullname + ", career=" + career + ", date=" + date + ", message=" + message + '}';
    }

    public String getMessage() {
        return message;
    }
}
