/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp_poo;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Yan
 */
public class Mapa {

    private int linhas;
    private int colunas;
    private ArrayList<ArrayList<Sala>> sala = new ArrayList<ArrayList<Sala>>();
    private ArrayList<ArrayList<Integer>> portas = new ArrayList<ArrayList<Integer>>();
    private ArrayList<String> chavesMapa = new ArrayList<String>();
    private ArrayList<String> chaveCaminho = new ArrayList<String>();
    private ArrayList<Integer> caminhoPercorrido = new ArrayList<Integer>();
    private ArrayList<Troll> trolls = new ArrayList<Troll>();

    public Mapa() {
        this.linhas = 4;
        this.colunas = 5;

    }

    public void setarTroll() {
        Random gerador = new Random();
        int a = 0;
        while (a == 0) {
            a = gerador.nextInt(11);
        }
        for (int i = 0; i < a; i++) {
            Troll t = new Troll();
            t.setNumSala(gerador.nextInt(21));
            String s = new String();
            s = verificaNumSala(t.getNumSala());
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
            t.setSala1(Integer.parseInt(si));
            t.setSala2(Integer.parseInt(sj));
            this.trolls.add(t);
        }
    }

    public String verificaNumSala(int c) {
        int a = 0, b = 0;
        for (int i = 0; i < sala.size(); i++) {
            for (int j = 0; j < sala.get(i).size(); j++) {
                if (sala.get(i).get(j).getNum() == c) {
                    a = i;
                    b = j;
                    break;
                }
            }
        }
        String s = new String();
        s += a + "-" + b;
        return s;
    }

    public boolean verificaCiclo(int a) {
        for (int i = 0; i < caminhoPercorrido.size(); i++) {
            if (a == caminhoPercorrido.get(i)) {
                return true;
            }
        }
        return false;
    }

    public void resetaCaminho() {
        while (caminhoPercorrido.size() != 0) {
            caminhoPercorrido.remove(0);
        }
    }

    public boolean verificaListaChave(String s) {
        for (int i = 0; i < chaveCaminho.size(); i++) {
            if (chaveCaminho.get(i).equals(s)) {
                return true;
            }
        }
        return false;
    }

    public boolean verificaCaminho(int a, int ant, int c) {
        String s = new String();
        s = verificaNumSala(a);
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
        int ii, ij;
        ii = Integer.parseInt(si);
        ij = Integer.parseInt(sj);
        if (sala.get(ii).get(ij).getChave().size() != 0) {
            if (!verificaListaChave(sala.get(ii).get(ij).getChave().get(0))) {
                chaveCaminho.add(sala.get(ii).get(ij).getChave().get(0));
                resetaCaminho();
                if (verificaCaminho(1, 0, 0)) {
                    c = 1;
                    return true;
                }
            }
        }
        if (a == 4) {
            if (chaveCaminho.size() > 0) {
                if (chaveCaminho.get(chaveCaminho.size() - 1).equals("Saida")) {
                    c = 1;
                    return true;
                }
            }

            return false;
        }
        for (int i = 0; i < portas.get(a - 1).size(); i++) {

            if (portas.get(a - 1).get(i) == 2) {
                String s2 = new String();
                s2 = verificaNumSala(i + 1);
                String si2 = new String();
                String sj2 = new String();
                int aux1 = 0;
                for (int l = 0; l < s2.length(); l++) {
                    if (s2.charAt(l) == '-') {
                        aux1++;
                    } else if (aux1 == 0) {
                        si2 += s2.charAt(l);
                    } else {
                        sj2 += s2.charAt(l);
                    }
                }
                int ii2, ij2;
                ii2 = Integer.parseInt(si2);
                ij2 = Integer.parseInt(sj2);
                for (int b = 0; b < chaveCaminho.size(); b++) {
                    if (verificaChavePorta(a, i, chaveCaminho.get(b))) {
                        if (!verificaCiclo(i + 1)) {
                            if (sala.get(ii2).get(ij2).getNum() != ant) {
                                caminhoPercorrido.add(a);
                                if (verificaCaminho(i + 1, a, c)) {
                                    break;
                                }
                                if (caminhoPercorrido.size() > 0) {
                                    caminhoPercorrido.remove(caminhoPercorrido.size() - 1);
                                }
                                //caminhoPercorrido.remove(a);
                            }
                        }
                    }

                }
            }
            if (portas.get(a - 1).get(i) == 1) {
                String s2 = new String();
                s2 = verificaNumSala(i + 1);
                String si2 = new String();
                String sj2 = new String();
                int aux1 = 0;
                for (int l = 0; l < s2.length(); l++) {
                    if (s2.charAt(l) == '-') {
                        aux1++;
                    } else if (aux1 == 0) {
                        si2 += s2.charAt(l);
                    } else {
                        sj2 += s2.charAt(l);
                    }
                }
                int ii2, ij2;
                ii2 = Integer.parseInt(si2);
                ij2 = Integer.parseInt(sj2);
                if (!verificaCiclo(i + 1)) {
                    if (sala.get(ii2).get(ij2).getNum() != ant) {
                        caminhoPercorrido.add(a);
                        if (i + 1 <= 20 && i >= 0) {
                            if (verificaCaminho(i + 1, a, c)) {
                                c = 1;
                                break;
                            }
                        }
                        if (caminhoPercorrido.size() > 0) {
                            caminhoPercorrido.remove(caminhoPercorrido.size() - 1);
                        }
                        //caminhoPercorrido.remove(a);
                    }
                }
            }
        }
        if (c == 1) {
            return true;
        }
        return false;
    }

    public void setarPortas() {
        for (int i = 0; i < 20; i++) {
            ArrayList<Integer> aux = new ArrayList<Integer>();
            for (int j = 0; j < 20; j++) {
                aux.add(0);
            }
            this.portas.add(aux);
        }
        this.portas.get(0).set(1, 1);
        this.portas.get(1).set(0, 1);
        this.portas.get(0).set(5, 1);
        this.portas.get(5).set(0, 1);
        this.portas.get(1).set(2, 1);
        this.portas.get(2).set(1, 1);
        this.portas.get(2).set(7, 1);
        this.portas.get(7).set(2, 1);
        this.portas.get(3).set(4, 1);
        this.portas.get(4).set(3, 1);
        this.portas.get(4).set(9, 1);
        this.portas.get(9).set(4, 1);
        this.portas.get(5).set(6, 1);
        this.portas.get(6).set(5, 1);
        this.portas.get(6).set(11, 1);
        this.portas.get(11).set(6, 1);
        this.portas.get(7).set(12, 1);
        this.portas.get(12).set(7, 1);
        this.portas.get(8).set(9, 1);
        this.portas.get(9).set(8, 1);
        this.portas.get(9).set(14, 1);
        this.portas.get(14).set(9, 1);
        this.portas.get(10).set(15, 1);
        this.portas.get(15).set(10, 1);
        this.portas.get(10).set(11, 1);
        this.portas.get(11).set(10, 1);
        this.portas.get(11).set(12, 1);
        this.portas.get(12).set(11, 1);
        this.portas.get(13).set(14, 1);
        this.portas.get(14).set(13, 1);
        this.portas.get(13).set(18, 1);
        this.portas.get(18).set(13, 1);
        this.portas.get(14).set(19, 1);
        this.portas.get(19).set(14, 1);
        this.portas.get(15).set(16, 1);
        this.portas.get(16).set(15, 1);
        this.portas.get(16).set(17, 1);
        this.portas.get(17).set(16, 1);
        this.portas.get(17).set(18, 1);
        this.portas.get(18).set(17, 1);
    }

    public void trancaPorta() {
        Random gerador = new Random();
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                if (portas.get(i).get(j).equals(1)) {
                    if (gerador.nextInt(10000) < 2500) {
                        portas.get(i).set(j, 2);
                        portas.get(j).set(i, 2);
                        chavesMapa.add((i+1) + "-" + (j+1));
                    }
                }
            }
        }
        chavesMapa.add("Saida");
        if (portas.get(0).get(1) == 2 && portas.get(0).get(5) == 2) {
            if (gerador.nextInt(2) == 1) {
                portas.get(0).set(1, 1);
                portas.get(1).set(0, 1);
            } else {
                portas.get(0).set(5, 1);
                portas.get(5).set(0, 1);
            }

        }

    }

    public void setarItens() {
        int n = 16;
        for (int a = 0; a < linhas; a++) {
            ArrayList<Sala> s = new ArrayList<Sala>();
            for (int b = 0; b < colunas; b++) {
                Random gerador = new Random();
                Sala s1 = new Sala();
                s1.setDiamante(gerador.nextInt(6));
                s1.setMachado(gerador.nextInt(2));
                s1.setOuro(gerador.nextInt(11));
                s1.setPocao(gerador.nextInt(3));
                s1.setNum(n);
                n++;
                s.add(s1);
            }
            n -= 10;
            this.sala.add(s);
        }
    }

    public void distribuiChave() {
        for (int i = 0; i < chavesMapa.size(); i++) {
            Random gerador = new Random();
            int a = gerador.nextInt(21);
            for (int b = 0; b < sala.size(); b++) {
                for (int c = 0; c < sala.get(b).size(); c++) {
                    if (sala.get(b).get(c).getNum() == a) {
                        ArrayList<String> v = new ArrayList<String>();
                        this.sala.get(b).get(c).getChave().add(chavesMapa.get(i));
                    }
                }
            }
        }

    }

    public boolean verificaChavePorta(int i, int j, String s) {
        String si = new String();
        String sj = new String();
        int aux = 0;
        if (s.equals("Saida")) {
            return false;
        }
        for (int a = 0; a < s.length(); a++) {
            if (s.charAt(a) == '-') {
                aux++;
            } else if (aux == 0) {
                si += s.charAt(a);
            } else {
                sj += s.charAt(a);
            }
        }
        int ii, ij;
        ii = Integer.parseInt(si);
        ij = Integer.parseInt(sj);
        if (i == ii & ij == j) {
            return true;
        } else {
            return false;
        }

    }

    public ArrayList<String> getChaveCaminho() {
        return chaveCaminho;
    }

    public void setChaveCaminho(ArrayList<String> chaveCaminho) {
        this.chaveCaminho = chaveCaminho;
    }

    public ArrayList<ArrayList<Sala>> getSala() {
        return sala;
    }

    public void setSala(ArrayList<ArrayList<Sala>> sala) {
        this.sala = sala;
    }

    public ArrayList<ArrayList<Integer>> getPortas() {
        return portas;
    }

    public void setPortas(ArrayList<ArrayList<Integer>> portas) {
        this.portas = portas;
    }

    public ArrayList<String> getChavesMapa() {
        return chavesMapa;
    }

    public void setChavesMapa(ArrayList<String> chavesMapa) {
        this.chavesMapa = chavesMapa;
    }

    public ArrayList<Troll> getTrolls() {
        return trolls;
    }

    public void setTrolls(ArrayList<Troll> trolls) {
        this.trolls = trolls;
    }

    
}
