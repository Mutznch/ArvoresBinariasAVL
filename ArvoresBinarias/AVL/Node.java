package AVL;

public class Node {
    private Integer info;
    private Node esquerda;
    private Node direita;

    public Node(int info) {
        this.info = info;
    }

    public Node(int info, int ...valores) {
        this(info);

        for (int v: valores) {
            inserirElemento(v);
        }
    }

    public void inserirElemento(int valor) {
        if (info == null)
            info = valor;
        else if (valor < info) {
            if (esquerda != null)
                esquerda.inserirElemento(valor);
            else
                esquerda = new Node(valor);
        } else {
            if (direita != null)
                direita.inserirElemento(valor);
            else
                direita = new Node(valor);
        }
    }

    public Node buscaBinaria(int valor) {
        if (info == valor) {
            return this;
        }
        if (info > valor) {
            if (esquerda == null) return null;
            return esquerda.buscaBinaria(valor);
        }
        if (direita != null)
            return direita.buscaBinaria(valor);
        return null;
    }

    public void remover(int valor) {
        if (!buscaPreOrdem(valor)) return;

        if (valor == info) {
            if (direita == null && esquerda == null)
                info = null;
            else {
                info = acharSubstituto();
                if (direita != null) {
                    if (direita.getEsquerda() != null)
                        direita.remover(info);
                    else direita = null;
                }
                else {
                    if (esquerda.getDireita() != null)
                        esquerda.remover(info);
                    else esquerda = null;
                }
            }
        } else if (valor < info) {
            if (esquerda.getInfo() == valor &&
                    esquerda.getDireita() == null &&
                    esquerda.getEsquerda() == null
            ) esquerda = null;
            else esquerda.remover(valor);
        } else {
            if (direita.getInfo() == valor &&
                    direita.getDireita() == null &&
                    direita.getEsquerda() == null
            ) direita = null;
            else direita.remover(valor);
        }
    }

    private int acharSubstituto() {
        //Node prox = direita != null ? direita : esquerda;

        Node prox;
        if (direita != null)
            prox = direita;
        else
            prox = esquerda;

        int sub = info;
        while (prox != null) {
            sub = prox.getInfo();
            if (direita != null)
                prox = prox.getEsquerda();
            else
                prox = prox.getDireita();
        }
        return sub;
    }

    public int altura() {
        int esq, dir;

        if (esquerda == null) esq = -1;
        else esq = esquerda.altura();
        if (direita == null) dir = -1;
        else dir = direita.altura();

        if (esq > dir)
            return 1 + esq;
        return 1 + dir;
    }

    public int fatorDeBalanceamento() {
        int esq = esquerda != null ? esquerda.altura() : 0;
        int dir = direita != null ? direita.altura() : 0;

        return esq - dir;
    }

    public int getInfo() {
        return info;
    }

    public Node getEsquerda() {
        return esquerda;
    }

    public Node getDireita() {
        return direita;
    }

    public void setDireita(Node direita) {
        this.direita = direita;
    }

    public void setEsquerda(Node esquerda) {
        this.esquerda = esquerda;
    }

    public boolean buscaPreOrdem(int valor) {
        if (valor == info) return true;

        return (esquerda != null && esquerda.buscaPreOrdem(valor)) ||
                (direita != null && direita.buscaPreOrdem(valor));
    }

    public void removerMaiorValor() {
        if (direita == null)
            remover(info);
        if (direita.getDireita() == null)
            direita = null;
        else direita.removerMaiorValor();
    }

    public void removerMenorValor() {
        if (esquerda == null)
            remover(info);
        if (esquerda.getEsquerda() == null)
            esquerda = null;
        else esquerda.removerMenorValor();
    }

//        public int fatorDeBalanceamento() {
//        return altura(esquerda) - altura(direita);
//    }
//
//    public static int altura(Node no) {
//        if (no == null) return -1;
//        int esquerda = altura(no.getEsquerda());
//        int direita = altura(no.getDireita());
//        if (esquerda > direita) return 1 + esquerda;
//        return 1 + direita;
//    }
}
