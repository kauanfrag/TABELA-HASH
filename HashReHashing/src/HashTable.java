class HashTable {
    private Registro[] tabela;
    private int tamanho;
    private int colisoes = 0;
    private int numElementos = 0;

    public HashTable(int tamanho) {
        this.tamanho = tamanho;
        this.tabela = new Registro[tamanho];
    }

    public int getColisoes() {
        return colisoes;
    }

    int hashQuadratico(int chave, int i) {
        int h = chave % tamanho;
        return (h + 1 * i + 3 * i * i) % tamanho;
    }

    public void inserir(int chave) {
        if ((float) numElementos / tamanho >= 0.7) {
            rehashExpandir();
        }

        int i = 0;
        int pos;

        while (i < tamanho) {
            pos = hashQuadratico(chave, i);

            if (tabela[pos] == null) {
                tabela[pos] = new Registro(chave);
                numElementos++;
                System.out.println("* Inserido " + chave + " na posição " + pos);
                return;
            } else {
                colisoes++;
            }
            i++;
        }

    }

    public boolean buscar(int chave) {
        int i = 0;
        int pos;

        while (i < tamanho) {
            pos = hashQuadratico(chave, i);

            if (tabela[pos] == null){
                return false;
            }
            if (tabela[pos].chave == chave){
                return true;
            }

            i++;
        }

        return false;
    }

    private void rehashExpandir() {
        Registro[] antiga = tabela;
        tamanho = tamanho * 2;
        tabela = new Registro[tamanho];
        colisoes = 0;
        numElementos = 0;

        for (Registro r : antiga) {
            if (r != null) {
                inserir(r.chave);
            }
        }
    }
}
