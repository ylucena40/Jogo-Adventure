/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp_poo;

/**
 *
 * @author Yan
 */
public class Troll {

    private int sala1;
    private int sala2;
    private int numSala;
    private boolean procurarSala;
    private boolean estadoMachado;

    public boolean verificaMachdo(Mapa a) {
        if (a.getSala().get(sala1).get(sala2).getMachado() > 0) {
            this.estadoMachado = true;
            return true;
        }
        return false;
    }

    public boolean atiraMachado() {
        if (this.estadoMachado) {
            this.estadoMachado = false;
            return true;
        }
        return false;
    }

    public boolean verificaUsuarioSala(Jogador a) {
        if (a.getNumSala() == this.numSala) {
            return true;
        }
        return false;
    }

    public boolean procuraSala(Mapa a) {
        if (a.getSala().get(sala1).get(sala2).getDiamante() + a.getSala().get(sala1).get(sala2).getOuro() >= 5) {
            this.procurarSala = false;
            return false;
        }
        return true;
    }

    public void trocaSala(Mapa a) {
        if (a.getPortas().get(numSala - 1) == null) {
            return;
        }
        for (int i = 0; i < a.getPortas().get(numSala - 1).size(); i++) {
            if (a.getPortas().get(numSala - 1).get(i) == 1) {
                this.numSala = i + 1;
                String s = new String();
                s = a.verificaNumSala(i + 1);
                String si = new String();
                String sj = new String();
                int aux = 0;
                for (int l = 0; l < s.length(); l++) {
                    if (s.charAt(l) == '-') {
                        aux++;
                    } else if (aux == 0) {
                        si += s.charAt(l);
                    } else {
                        sj += s.charAt(l);
                    }
                }
                this.sala1 = Integer.parseInt(si);
                this.sala2 = Integer.parseInt(sj);
                break;
            }
        }

    }

    public Troll() {
        this.estadoMachado = false;
        this.procurarSala = true;
    }

    public int getSala1() {
        return sala1;
    }

    public void setSala1(int sala1) {
        this.sala1 = sala1;
    }

    public int getSala2() {
        return sala2;
    }

    public void setSala2(int sala2) {
        this.sala2 = sala2;
    }

    public int getNumSala() {
        return numSala;
    }

    public void setNumSala(int numSala) {
        this.numSala = numSala;
    }

    public boolean isProcurarSala() {
        return procurarSala;
    }

    public void setProcurarSala(boolean procurarSala) {
        this.procurarSala = procurarSala;
    }

    public boolean isEstadoMachado() {
        return estadoMachado;
    }

    public void setEstadoMachado(boolean estadoMachado) {
        this.estadoMachado = estadoMachado;
    }

}
