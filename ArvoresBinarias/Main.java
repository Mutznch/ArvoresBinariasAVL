import AVL.ArvoreBinaria;
import AVL.ArvoreBinariaAVL;

import java.util.Random;

import static java.lang.System.currentTimeMillis;

public class Main {
    static Random random = new Random(50135);
    public static void main(String[] args) {

        long inicio = currentTimeMillis();

        //ArvoreBinaria ab = new ArvoreBinaria(0);
        ArvoreBinariaAVL ab = new ArvoreBinariaAVL(0);

        for (int i = 1; i < 20000; i++) {
            ab.inserirElemento(random.nextInt());
        }

        System.out.println(ab.buscaBinaria(726228774));
        ab.remover(726228774);
        System.out.println(ab.buscaBinaria(726228774));


        //System.out.println(ab.fatorDeBalanceamento());
        //ab.imprime();

        System.out.println("\n\n" + (currentTimeMillis() - inicio));
    }
}
