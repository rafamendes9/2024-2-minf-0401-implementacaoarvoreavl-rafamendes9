package atividade;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class Testes {

    @Test
    public void basico01(){
        No no = new No(5);
        assertEquals(no.chave, 5);
        assertEquals(no.esquerda, null);
        assertEquals(no.direita, null);
        assertEquals(no.altura, 0);
    }

    @Test
    public void basico02(){
        AVL avl = new AVL();
        avl.put(50);

        assertEquals(avl.raiz.chave, 50);
        assertEquals(avl.raiz.esquerda, null);
        assertEquals(avl.raiz.direita, null);
        assertEquals(avl.raiz.altura, 0);
    }

    @Test
    public void basico03(){
        AVL avl = new AVL();
        avl.put(50);
        avl.put(60);
        avl.put(40);

        assertEquals(avl.raiz.chave, 50);
        assertEquals(avl.raiz.direita.chave, 60);
        assertEquals(avl.raiz.esquerda.chave, 40);
    }

    @Test
    public void basicoAlturasPositivas(){
        AVL avl = new AVL();
        avl.put(1);
        assertEquals(avl.calcularFatorBalanceamento(avl.raiz), 0);
        avl.put(2);
        assertEquals(avl.calcularFatorBalanceamento(avl.raiz), 1);
        assertEquals(avl.calcularFatorBalanceamento(avl.raiz.direita), 0);
    }

    @Test
    public void basicoAlturasNegativas(){
        AVL avl = new AVL();
        avl.put(2);
        assertEquals(avl.calcularFatorBalanceamento(avl.raiz), 0);
        avl.put(1);
        assertEquals(avl.calcularFatorBalanceamento(avl.raiz), -1);
        assertEquals(avl.calcularFatorBalanceamento(avl.raiz.esquerda), 0);
    }

    @Test
    public void basicoRemocao01(){
        AVL avl = new AVL();
        avl.put(50);
        avl.put(100);
        avl.put(25);

        avl.deletar(50);
        assertEquals(avl.raiz.chave, 25);
    }

    @Test
    public void basicoRemocao02(){
        AVL avl = new AVL();
        avl.put(50);
        avl.put(100);
        avl.put(25);
        avl.put(30);

        avl.deletar(50);
        assertEquals(avl.raiz.chave, 30);
    }

    @Test
    public void rotacaoLL(){
        AVL avl = new AVL();
        avl.put(30);
        assertEquals(avl.raiz.chave, 30);
        avl.put(20);
        assertEquals(avl.raiz.esquerda.chave, 20);
        avl.put(10);
        assertEquals(avl.raiz.chave, 20);
        assertEquals(avl.raiz.direita.chave, 30);
        assertEquals(avl.raiz.esquerda.chave, 10);
    }

    @Test
    public void rotacaoRR(){
        AVL avl = new AVL();
        avl.put(10);
        assertEquals(avl.raiz.chave, 10);
        avl.put(20);
        assertEquals(avl.raiz.direita.chave, 20);
        avl.put(30);
        assertEquals(avl.raiz.chave, 20);
        assertEquals(avl.raiz.esquerda.chave, 10);
        assertEquals(avl.raiz.direita.chave, 30);
    }

    @Test
    public void rotacaoLR(){
        AVL avl = new AVL();
        avl.put(30);
        assertEquals(avl.raiz.chave, 30);
        avl.put(10);
        assertEquals(avl.raiz.esquerda.chave, 10);
        avl.put(20);
        assertEquals(avl.raiz.chave, 20);
        assertEquals(avl.raiz.esquerda.chave, 10);
        assertEquals(avl.raiz.direita.chave, 30);
    }

    @Test
    public void rotacaoRL(){
        AVL avl = new AVL();
        avl.put(10);
        assertEquals(avl.raiz.chave, 10);
        avl.put(30);
        assertEquals(avl.raiz.direita.chave, 30);
        avl.put(20);
        assertEquals(avl.raiz.chave, 20);
        assertEquals(avl.raiz.esquerda.chave, 10);
        assertEquals(avl.raiz.direita.chave, 30);
    }

    @Test
    public void rotacoes(){ // definir mais testes para esse problema
        AVL avl = new AVL();
        avl.put(1);
        avl.put(2);
        avl.put(3);

        assertEquals(avl.raiz.chave, 2);
        assertEquals(avl.raiz.esquerda.chave, 1);
        assertEquals(avl.raiz.direita.chave, 3);
        
        avl.put(4);
        avl.put(5);

        No raizsubarvore = avl.raiz.direita;
        assertEquals(raizsubarvore.chave, 4);
        assertEquals(raizsubarvore.esquerda.chave, 3);
        assertEquals(raizsubarvore.direita.chave, 5);

        avl.put(6);
        assertEquals(avl.raiz.chave, 4);
        assertEquals(avl.raiz.esquerda.chave, 2);
        assertEquals(avl.raiz.direita.chave, 5);
        assertEquals(avl.raiz.direita.direita.chave, 6);
        assertEquals(avl.raiz.direita.direita.direita, null);
        assertEquals(avl.raiz.esquerda.direita.chave, 3);

        avl.put(7);
        raizsubarvore = avl.raiz.direita;
        assertEquals(raizsubarvore.chave, 6);
        assertEquals(raizsubarvore.esquerda.chave, 5);
        assertEquals(raizsubarvore.direita.chave, 7);

        avl.put(15);
        avl.put(14);
        raizsubarvore = avl.raiz.direita.direita;
        assertEquals(raizsubarvore.chave, 14);
        assertEquals(raizsubarvore.direita.chave, 15);
        assertEquals(raizsubarvore.esquerda.chave, 7);

        avl.put(13);
        raizsubarvore = avl.raiz.direita;
        assertEquals(raizsubarvore.chave, 7);
        assertEquals(raizsubarvore.esquerda.chave, 6);
        assertEquals(raizsubarvore.esquerda.esquerda.chave, 5);

        avl.put(12);
        avl.put(11);

        raizsubarvore = avl.raiz.direita.esquerda;
        assertEquals(raizsubarvore.chave, 12);
        assertEquals(raizsubarvore.esquerda.chave, 11);
        assertEquals(raizsubarvore.direita.chave, 13);
    }   
}
