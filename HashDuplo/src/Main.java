import java.util.Random;

public class Main {
    public static void main(String[] args) {


        int[] tamanhosTabela = {10000, 100000, 1000000};
        int[] tamanhosConjunto = {100000, 1000000, 10000000};
        long seed = 1234564L;

        for (int tamanhoX : tamanhosTabela) {
            System.out.println("Tamanho da Tabela: " + tamanhoX);

            for (int tamanhoConjunto : tamanhosConjunto) {
                System.out.println("\nConjunto de dados: " + tamanhoConjunto);

                HashDuplo tabela = new HashDuplo(tamanhoX);

                Random random = new Random(seed);
                int[] dados = new int[tamanhoConjunto];
                for (int i = 0; i < tamanhoConjunto; i++) {
                    dados[i] = random.nextInt(999_999_999);
                }
                long inicioInsercao = System.nanoTime();

                for (int valor : dados) {
                    tabela.inserir(valor);
                }

                long fimInsercao = System.nanoTime();
                long tempoInsercao = fimInsercao - inicioInsercao;


                long inicioBusca = System.nanoTime();

                for (int valor : dados) {
                    tabela.buscar(valor);
                }

                long fimBusca = System.nanoTime();
                long tempoBusca = fimBusca - inicioBusca;


                System.out.println("Tempo de inserção: " + (tempoInsercao / 1_000_000_000.0) + " s");
                System.out.println("Tempo de busca: " + (tempoBusca / 1_000_000_000.0) + " s");
                System.out.println("Colisões: " + tabela.getColisoes());
            }
        }
    }
}
