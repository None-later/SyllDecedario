package librerias.jerarquicos;

public class NodeABC<String> {

    public NodeABC<String> esq, dret;
    private String e;
    private int talla;

    public NodeABC(String e, NodeABC es, NodeABC dr) {
        this.e = e;
        esq = es;
        dret = dr;
        talla = 0;
    }

    public NodeABC(String e) {
        this(e, null, null);
    }

    public String getValor() {
        return this.e;
    }

    public void increaseTalla() {
        ++this.talla;
    }

    public int getTalla() {
        return this.talla;
    }
}
