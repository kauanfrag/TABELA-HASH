class HashDuplo {
    private Registro[] tabela;
    private int tamanho;
    private int colisoes = 0;
    private int numElementos = 0;

    public HashDuplo(int tamanho) {
        this.tamanho = tamanho;
        this.tabela = new Registro[tamanho];
    }

    public int getColisoes() {
        return colisoes;
    }

    int hashDuplo(int chave, int i) {
        int h1 = chave % tamanho;
        {
            if (h1 < 0) h1 += tamanho;      // garante que h1 seja positivo
        }

        int h2 = 1 + (chave % (tamanho - 1));
        if (h2 < 0) {
            h2 += (tamanho - 1); // garante que h2 seja positivo
        }

        int indice = (h1 + i * h2) % tamanho;
        if (indice < 0) {
            indice += tamanho;
        }// garante índice positivo final

        return indice;
    }


    public void inserir(int chave) {
        if ((float) numElementos / tamanho >= 0.7) {
            rehashExpandir();
        }

        int i = 0;
        int pos;

        while (i < tamanho) {
            pos = hashDuplo(chave, i);

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
            pos = hashDuplo(chave, i);

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
