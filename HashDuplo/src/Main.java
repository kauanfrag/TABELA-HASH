import java.util.Random;
import java.io.PrintWriter;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        int[] tamanhosTabela = {10000, 100000, 1000000};
        int[] tamanhosConjunto = {100000, 1000000, 10000000};
        long seed = 1234564L;

        // Variáveis para status geral
        long totalTempoInsercao = 0;
        long totalTempoBusca = 0;
        long totalColisoes = 0;
        int totalExecutions = 0;

        for (int tamanhoX : tamanhosTabela) {
            for (int tamanhoConjunto : tamanhosConjunto) {

                HashDuplo tabela = new HashDuplo(tamanhoX);

                Random random = new Random(seed);
                int[] dados = new int[tamanhoConjunto];
                for (int i = 0; i < tamanhoConjunto; i++) {
                    dados[i] = random.nextInt(999_999_999);
                }

                String nomeArquivo = "hash_duplo" + tamanhoX + "_" + tamanhoConjunto + ".txt";
                PrintWriter pw = new PrintWriter(nomeArquivo);

                // Inserção
                long inicioInsercao = System.nanoTime();
                for (int valor : dados) {
                    int pos = valor % tamanhoX;
                    tabela.inserir(valor);
                    pw.println("* Inserido " + valor + " na posição " + pos);
                }
                long fimInsercao = System.nanoTime();
                long tempoInsercao = fimInsercao - inicioInsercao;

                // Busca
                long inicioBusca = System.nanoTime();
                for (int valor : dados) {
                    tabela.inserir(valor);
                }
                long fimBusca = System.nanoTime();
                long tempoBusca = fimBusca - inicioBusca;

                // Status individual no arquivo
                pw.println("\n===== STATUS =====");
                pw.println("Tempo de inserção: " + (tempoInsercao / 1_000_000_000.0) + " s");
                pw.println("Tempo de busca: " + (tempoBusca / 1_000_000_000.0) + " s");
                pw.println("Colisões: " + tabela.getColisoes());

                int[] gaps = tabela.calcularGaps();

                pw.println("Menor gap: " + gaps[0]);
                pw.println("Maior gap: " + gaps[1]);
                pw.println("Média de gap: " + gaps[2]);

                pw.close();

                System.out.println("Tabela: " + tamanhoX + ", Conjunto: " + tamanhoConjunto);
                System.out.println("Arquivo gerado: " + nomeArquivo + "\n");

                // Acumula para status geral
                totalTempoInsercao += tempoInsercao;
                totalTempoBusca += tempoBusca;
                totalColisoes += tabela.getColisoes();
                totalExecutions++;
            }
        }

        // Status geral
        System.out.println("Total de execuções: " + totalExecutions);
        System.out.println("Tempo total de inserção: " + (totalTempoInsercao / 1_000_000_000.0) + " s");
        System.out.println("Tempo total de busca: " + (totalTempoBusca / 1_000_000_000.0) + " s");
        System.out.println("Total de colisões: " + totalColisoes);
        System.out.println("Tempo médio de inserção: " + ((totalTempoInsercao / 1_000_000_000.0) / totalExecutions) + " s");
        System.out.println("Tempo médio de busca: " + ((totalTempoBusca / 1_000_000_000.0) / totalExecutions) + " s");
    }
}
