package classes;

import java.util.*;

public class JogoDaVelha {
    public static void main(String[] args) {
        List<List<String>> matriz = new ArrayList<>();
        boolean daParaTerOutraRodada = true;
        int roud = 0;
        String jogador = defineJogador(roud);
        iniciarJogo(matriz);
        while (daParaTerOutraRodada) {
            imprimirMatriz(matriz);
            jogar(matriz, jogador);
            roud++;
            jogador = defineJogador(roud);
            daParaTerOutraRodada = gabarito(matriz);
            daParaTerOutraRodada = empatou(roud, daParaTerOutraRodada);
        }
    }

    public static void iniciarJogo(List<List<String>> matriz) {
        for (int i = 0; i < 3; i++) {
            String[] entrada = {" ", " ", " "};
            List<String> listaInterna = new ArrayList<>(Arrays.asList(entrada).subList(0, 3));
            matriz.add(listaInterna);
        }

    }

    public static void imprimirMatriz(List<List<String>> matriz) {
        for (List<String> strings : matriz) {
            System.out.println(strings);
        }

    }

    public static void jogar(List<List<String>> matriz, String jogador) {
        try {
            Scanner sc = new Scanner(System.in);
            boolean jogadaInvalida = true;
            while (jogadaInvalida) {
                System.out.println("Escolha a linha que voce quer joga entre 1 2 3 e deopis a coluna entre 1 2 3: coloque os 2 numeros separados por espaço ");
                int linha = sc.nextInt() - 1;
                int coluna = sc.nextInt() - 1;
                if (linha >= 3 || coluna >= 3 || linha < 0 || coluna < 0) {
                    System.out.println("linha ou coluna invalidos");
                } else if (!matriz.get(linha).get(coluna).equals(" ")) {
                    System.out.println("posição ja usada pelo jogador " + matriz.get(linha).get(coluna) + ":");
                } else {

                    matriz.get(linha).set(coluna, jogador);
                    jogadaInvalida = false;
                }
            }
        } catch (InputMismatchException e) {
            System.out.println("entrada invalida");
            jogar(matriz, jogador);
        }
    }

    public static String defineJogador(int roud) {
        if (roud % 2 == 0) {
            return "X";
        } else {
            return "O";
        }

    }

    public static boolean gabarito(List<List<String>> matriz) {
        //verifica linha
        for (int i = 0; i < matriz.size(); i++) {
            for (int j = 0; j < 1; j++) {
                if (!matriz.get(i).get(j).equals(" ") &&
                        matriz.get(i).get(j).equals(matriz.get(i).get(j + 1)) &&
                        matriz.get(i).get(j).equals(matriz.get(i).get(j + 2))) {
                    System.out.println("o vencedor do jogo é " + matriz.get(i).get(0));
                    imprimirMatriz(matriz);
                    return false;
                }
            }
        }
        //verifica coluna
        for (int i = 0; i < matriz.size(); i++) {
            for (int j = 0; j < 1; j++) {
                if (!matriz.get(j).get(i).equals(" ") &&
                        matriz.get(j).get(i).equals(matriz.get(j + 1).get(i)) &&
                        matriz.get(j).get(i).equals(matriz.get(j + 2).get(i))) {
                    System.out.println("o vencedor do jogo é " + matriz.get(0).get(i));
                    imprimirMatriz(matriz);
                    return false;
                }
            }
        }
        //verifica diagonal
        if (!matriz.get(0).get(0).equals(" ") &&
                matriz.get(0).get(0).equals(matriz.get(1).get(1)) &&
                matriz.get(1).get(1).equals(matriz.get(2).get(2))) {
            System.out.println("o vencedor do jogo é " + matriz.get(1).get(1));
            imprimirMatriz(matriz);
            return false;
        }
        if (!matriz.get(0).get(2).equals(" ") &&
                matriz.get(0).get(2).equals(matriz.get(1).get(1)) &&
                matriz.get(1).get(1).equals(matriz.get(2).get(0))) {
            System.out.println("o vencedor do jogo é " + matriz.get(1).get(1));
            imprimirMatriz(matriz);
            return false;
        }

        return true;
    }

    public static boolean empatou(Integer roud, boolean daParaTerOutraRodada) {
        if (!daParaTerOutraRodada) {
            return false;
        } else if (roud == 9) {
            System.out.println("O jogo deu velha !!!");
            return false;
        } else {
            return true;
        }
    }
}

