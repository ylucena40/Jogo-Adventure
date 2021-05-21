/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp_poo;

import java.util.ArrayList;

/**
 *
 * @author Yan
 */
public class Itens {
    private int ouro;
    private int diamante;
    private int pocao;
    private int machado;
    private ArrayList<String> chave = new ArrayList<String>();

    public int getOuro() {
        return ouro;
    }

    public void setOuro(int ouro) {
        this.ouro = ouro;
    }

    public int getDiamante() {
        return diamante;
    }

    public void setDiamante(int diamante) {
        this.diamante = diamante;
    }

    public int getPocao() {
        return pocao;
    }

    public void setPocao(int pocao) {
        this.pocao = pocao;
    }

    public int getMachado() {
        return machado;
    }

    public void setMachado(int machado) {
        this.machado = machado;
    }

    public ArrayList<String> getChave() {
        return chave;
    }

    public void setChave(ArrayList<String> chave) {
        this.chave = chave;
    }

   
}
