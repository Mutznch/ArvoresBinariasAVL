package AVL;

public class ArvoreBinariaAVL {
    private Node raiz;

    public ArvoreBinariaAVL(int info) {
        raiz = new Node(info);
    }

    public ArvoreBinariaAVL(int info, int ...valores) {
        this(info);

        for (int v: valores) {
            inserirElemento(v);
        }
    }

    public void inserirElemento(int valor) {
        raiz.inserirElemento(valor);
        while (raiz.fatorDeBalanceamento() >= 2 || raiz.fatorDeBalanceamento() <= -2)
            balancear();
    }

    public boolean buscaPreOrdem(int valor) {
        return raiz.buscaPreOrdem(valor);
    }

    public Node buscaBinaria(int valor) {
        return raiz.buscaBinaria(valor);
    }

    public void remover(int valor) {
        raiz.remover(valor);
        while (raiz.fatorDeBalanceamento() >= 2 || raiz.fatorDeBalanceamento() <= -2)
            balancear();
    }

    private void balancear() {
        int fbRaiz = raiz.fatorDeBalanceamento();
        Integer fbFilhoEsq = raiz.getEsquerda() != null ? raiz.getEsquerda().fatorDeBalanceamento() : null;
        Integer fbFilhoDir = raiz.getDireita() != null ? raiz.getDireita().fatorDeBalanceamento() : null;

        if (fbRaiz >= 2) {
            if (fbFilhoDir != null && fbFilhoDir == -1)
                rotacionarFilhoEsquerda(1);
            else if (fbFilhoEsq != null && fbFilhoEsq == -1)
                rotacionarFilhoEsquerda(0);
            rotacaoDireita();
        }
        else if (fbRaiz <= -2) {
            if (fbFilhoDir != null && fbFilhoDir == 1)
                rotacionarFilhoDireita(1);
            else if (fbFilhoEsq != null && fbFilhoEsq == 1)
                rotacionarFilhoDireita(0);
            rotacaoEsquerda();
        }
    }

    private void rotacaoDireita() {
        Node novaRaiz = raiz.getEsquerda();
        Node temp = novaRaiz.getDireita();
        novaRaiz.setDireita(raiz);
        raiz.setEsquerda(temp);
        raiz = novaRaiz;
    }

    private void rotacaoEsquerda() {
        Node novaRaiz = raiz.getDireita();
        Node temp = novaRaiz.getEsquerda();
        novaRaiz.setEsquerda(raiz);
        raiz.setDireita(temp);
        raiz = novaRaiz;
    }

    private void rotacionarFilhoDireita(int n) {
        if (n == 1) {
            Node novaRaiz = raiz.getDireita().getEsquerda();
            Node temp = novaRaiz.getDireita();
            novaRaiz.setDireita(raiz.getDireita());
            raiz.getDireita().setEsquerda(temp);
            raiz.setDireita(novaRaiz);
        } else {
            Node novaRaiz = raiz.getEsquerda().getEsquerda();
            Node temp = novaRaiz.getDireita();
            novaRaiz.setDireita(raiz.getEsquerda());
            raiz.getEsquerda().setEsquerda(temp);
            raiz.setEsquerda(novaRaiz);
        }
    }

    private void rotacionarFilhoEsquerda(int n) {
        if (n == 1) {
            Node novaRaiz = raiz.getDireita().getDireita();
            Node temp = novaRaiz.getEsquerda();
            novaRaiz.setEsquerda(raiz.getDireita());
            raiz.getDireita().setDireita(temp);
            raiz.setDireita(novaRaiz);
        } else {
            Node novaRaiz = raiz.getEsquerda().getDireita();
            Node temp = novaRaiz.getEsquerda();
            novaRaiz.setEsquerda(raiz.getEsquerda());
            raiz.getEsquerda().setDireita(temp);
            raiz.setEsquerda(novaRaiz);
        }
    }

    private void imprime(Node no, String prefix, boolean isLeft) {
        if (no != null) {
            System.out.println(prefix + (isLeft ? "├── " : "└── ") + no.getInfo()); // Calcula os novos prefixos para os nós esquerdo e direito
            String newPrefix = prefix + (isLeft ? "│ " : " "); // Chama recursivamente para os filhos esquerdo e direito
            imprime(no.getDireita(), newPrefix, true);
            imprime(no.getEsquerda(), newPrefix, false);
        } else {
            System.out.println(prefix + (isLeft ? "├── " : "└── ") + "Vazio");
        }
    }

    public void imprime() {
        imprime(raiz, "", false);
    }

    public int fatorDeBalanceamento() {
        return raiz.fatorDeBalanceamento();
    }
}
