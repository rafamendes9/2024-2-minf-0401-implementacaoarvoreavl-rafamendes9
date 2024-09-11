package atividade;
 
public class AVL {
    No raiz;
 
    // Função para obter a altura de um nó
    private int altura(No n) {
        if (n == null) return -1; // Para combinar com o teste, a altura de um nó nulo deve ser -1.
        return n.altura;
    }
 
    // Função para calcular o fator de balanceamento de um nó
    public int calcularFatorBalanceamento(No n) {
        if (n == null) return 0;
        return altura(n.direita) - altura(n.esquerda);
    }
 
    // Função para realizar uma rotação à direita
    private No rotacaoDireita(No y) {
        No x = y.esquerda;
        No T2 = x.direita;
 
        x.direita = y;
        y.esquerda = T2;
 
        y.altura = Math.max(altura(y.esquerda), altura(y.direita)) + 1;
        x.altura = Math.max(altura(x.esquerda), altura(x.direita)) + 1;
 
        return x;
    }
 
    // Função para realizar uma rotação à esquerda
    private No rotacaoEsquerda(No x) {
        No y = x.direita;
        No T2 = y.esquerda;
 
        y.esquerda = x;
        x.direita = T2;
 
        x.altura = Math.max(altura(x.esquerda), altura(x.direita)) + 1;
        y.altura = Math.max(altura(y.esquerda), altura(y.direita)) + 1;
 
        return y;
    }
 
    // Função para encontrar o nó de maior valor na subárvore
    private No maiorValor(No no) {
        No atual = no;
        while (atual.direita != null)
            atual = atual.direita;
        return atual;
    }
 
    // Função para encontrar o nó de menor valor na subárvore
    private No menorValor(No no) {
        No atual = no;
        while (atual.esquerda != null)
            atual = atual.esquerda;
        return atual;
    }
    // Função de inserção
    public void put(int chave) {
        raiz = inserirNo(raiz, chave);
    }
 
    private No inserirNo(No no, int chave) {
        if (no == null)
            return new No(chave);
 
        if (chave < no.chave)
            no.esquerda = inserirNo(no.esquerda, chave);
        else if (chave > no.chave)
            no.direita = inserirNo(no.direita, chave);
        else
            return no;
 
        no.altura = Math.max(altura(no.esquerda), altura(no.direita)) + 1;
 
        int balanceamento = calcularFatorBalanceamento(no);
 
        // Rotação à direita (LL)
        if (balanceamento < -1 && chave < no.esquerda.chave)
            return rotacaoDireita(no);
 
        // Rotação à esquerda (RR)
        if (balanceamento > 1 && chave > no.direita.chave)
            return rotacaoEsquerda(no);
 
        // Rotação à esquerda-direita (LR)
        if (balanceamento < -1 && chave > no.esquerda.chave) {
            no.esquerda = rotacaoEsquerda(no.esquerda);
            return rotacaoDireita(no);
        }
 
        // Rotação à direita-esquerda (RL)
        if (balanceamento > 1 && chave < no.direita.chave) {
            no.direita = rotacaoDireita(no.direita);
            return rotacaoEsquerda(no);
        }
 
        return no;
    }
 
    // Função de remoção
    public void deletar(int chave) {
        raiz = removerNo(raiz, chave);
    }
 
    private No removerNo(No no, int chave) {
        if (no == null)
            return no;
 
        if (chave < no.chave)
            no.esquerda = removerNo(no.esquerda, chave);
        else if (chave > no.chave)
            no.direita = removerNo(no.direita, chave);
        else {
            if ((no.esquerda == null) || (no.direita == null)) {
                No temp = no.esquerda != null ? no.esquerda : no.direita;
                if (temp == null) {
                    no = null;
                } else
                    no = temp;
            } else {
                No temp = maiorValor(no.esquerda);
                no.chave = temp.chave;
                no.esquerda = removerNo(no.esquerda, temp.chave);
            }
        }
 
        if (no == null)
            return no;
 
        no.altura = Math.max(altura(no.esquerda), altura(no.direita)) + 1;
 
        int balanceamento = calcularFatorBalanceamento(no);
 
        // Rotação à direita (LL)
        if (balanceamento < -1 && calcularFatorBalanceamento(no.esquerda) <= 0)
            return rotacaoDireita(no);
 
        // Rotação à esquerda (RR)
        if (balanceamento > 1 && calcularFatorBalanceamento(no.direita) >= 0)
            return rotacaoEsquerda(no);
 
        // Rotação à esquerda-direita (LR)
        if (balanceamento < -1 && calcularFatorBalanceamento(no.esquerda) > 0) {
            no.esquerda = rotacaoEsquerda(no.esquerda);
            return rotacaoDireita(no);
        }
 
        // Rotação à direita-esquerda (RL)
        if (balanceamento > 1 && calcularFatorBalanceamento(no.direita) < 0) {
            no.direita = rotacaoDireita(no.direita);
            return rotacaoEsquerda(no);
        }
 
        return no;
    }
}