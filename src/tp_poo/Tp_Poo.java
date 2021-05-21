/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp_poo;

import java.util.Scanner;

/**
 *
 * @author Yan
 */
public class Tp_Poo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Mapa map = new Mapa();
        map.setarPortas();
        map.setarItens();
        map.trancaPorta();
        map.distribuiChave();
        while (!map.verificaCaminho(1, 1, 0)) {
            map = new Mapa();
            map.setarPortas();
            map.setarItens();
            map.trancaPorta();
            map.distribuiChave();
        }
        map.setarTroll();
        Jogador usuario = Jogador.getInstace();
        int i = 3;
        int morte = 0;
        while (true) {

            for (int a = 0; a < map.getTrolls().size(); a++) {
                if (map.getTrolls().get(a).procuraSala(map)) {
                    map.getTrolls().get(a).trocaSala(map);
                }
            }
            for (int a = 0; a < map.getTrolls().size(); a++) {
                if (map.getTrolls().get(a).getNumSala() == usuario.getNumSala()) {
                    if (map.getTrolls().get(a).verificaMachdo(map)) {
                        if (map.getTrolls().get(a).atiraMachado()) {
                            System.out.println("Um Troll te atacou, voce quer se defender?\n1.Sim\n2.Nao");
                            Scanner resp = new Scanner(System.in);
                            int re = -2;
                            while (re == -2) {
                                try {
                                    re = resp.nextInt();
                                } catch (Exception ErroInt) {
                                    System.out.println("Digite novamente");
                                }
                            }
                            if (re == 1) {
                                if (usuario.verificaPocao()) {
                                    System.out.println("Voce se defendeu com sucesso do Troll\nDeseja atacar o Troll?\n1.Sim\n2.Nao");
                                    usuario.setPocao(usuario.getPocao() - 1);
                                    re = -2;
                                    while (re == -2) {
                                        try {
                                            re = resp.nextInt();
                                        } catch (Exception ErroInt) {
                                            System.out.println("Digite novamente");
                                        }
                                    }
                                    if (re == 1) {
                                        if (usuario.verificaMachado()) {
                                            System.out.println("Voce atacou com sucesso o Troll");
                                            map.getTrolls().remove(a);
                                        } else {
                                            System.out.println("Voce nao tem machado, mude de sala");
                                        }
                                    } else {
                                        System.out.println("Voce nao atacou o Troll, mude de sala");
                                    }
                                } else {
                                    System.out.println("Voce nao tem pocao, por isso morreu,o jogo acabou");
                                    morte = 1;
                                    break;
                                }
                            } else {
                                System.out.println("Voce nao se defendeu, por isso morreu, o jogo acabou.");
                                morte = 1;
                                break;
                            }
                        }
                    }
                }
            }
            if (morte == 1) {
                break;
            }

            System.out.println("Esolha:\n1.Mudar de Sala.\n2.Adicionar Item da Sala.\n3.Remover Item da Mochila\n4.Mostrar Quantidade Ouro/Diamante");
            Scanner s = new Scanner(System.in);
            i = -2;
            while (i == -2) {
                try {
                    i = s.nextInt();
                } catch (Exception ErroInt) {
                    System.out.println("Digite novamente");
                }
            }

            if (i == 0) {
                break;
            }
            if (i == 1) {
                if (usuario.mudarDeSala(map) == 100) {
                    break;
                }
            }
            if (i == 2) {
                usuario.adicionaItem(map);
            }
            if (i == 3) {
                usuario.removerItem(map);
            }
            if (i == 4) {
                usuario.mostrarOuroDiamante();
            }

        }

    }

}
