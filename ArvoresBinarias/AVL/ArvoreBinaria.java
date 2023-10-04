package AVL;

public class ArvoreBinaria {
    private Node raiz;

    public ArvoreBinaria(int info) {
        raiz = new Node(info);
    }

    public ArvoreBinaria(int info, int ...valores) {
        this(info);

        for (int v: valores) {
            inserirElemento(v);
        }
    }

    public void inserirElemento(int valor) {
        raiz.inserirElemento(valor);
    }

    public Node buscaBinaria(int valor) {
        return raiz.buscaBinaria(valor);
    }


    public void remover(int valor) {
        raiz.remover(valor);
    }

    public void imprime(Node no, String prefix, boolean isLeft) {
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
        imprime(raiz, "", true);
    }

    public void removerMaiorValor() {
        raiz.removerMaiorValor();
    }

    public void removerMenorValor() {
        raiz.removerMenorValor();
    }

    public boolean buscaPreOrdem(int valor) {
        return raiz.buscaPreOrdem(valor);
    }
}
