package atividade;
 
public class No {
    int chave;
    int altura;
    No esquerda;
    No direita;
 
    public No(int chave) {
        this.chave = chave;
        this.altura = 0;  // A altura inicial do nó é 0.
    }
}