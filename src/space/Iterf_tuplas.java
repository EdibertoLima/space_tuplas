/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package space;

import java.util.ArrayList;
import net.jini.space.JavaSpace;

import java.rmi.*;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import net.jini.core.entry.UnusableEntryException;
import net.jini.core.lease.Lease;
import net.jini.core.transaction.TransactionException;

/**
 *
 * @author ediberto
 */
public class Iterf_tuplas {

    /**
     * @param args the command line arguments
     */
    public String Add_Amb(JavaSpace space) {
        ListAmbs lambientes = new ListAmbs();

        String nome = "";
        try {
            ListAmbs lambients = (ListAmbs) space.take(lambientes, null, 60);
            if (lambients == null) {
                space.write(lambientes, null, Lease.FOREVER);
                lambients = (ListAmbs) space.take(lambientes, null, 60);
            }
            if (lambients.ListNames == null) {

                lambients.ListNames = new ArrayList<>();
                nome = lambients.getName();
                lambients.ListNames.add(nome);
                space.write(lambients, null, Lease.FOREVER);
            } else {
                nome = lambients.getName();
                lambients.ListNames.add(nome);
                space.write(lambients, null, Lease.FOREVER);
            }
            Ambs ambiente = new Ambs();
            ambiente.name = nome;
            space.write(ambiente, null, Lease.FOREVER);

        } catch (UnusableEntryException | TransactionException | InterruptedException | RemoteException e) {
        }

        return nome;
    }

    public void AddUserAmb(JavaSpace space, String nome, String amb) {
        Ambs ambiente = new Ambs();
        ambiente.name = amb;

        try {

            Ambs ambientes = (Ambs) space.read(ambiente, null, 60 * 100);
            if (ambientes != null) {
                Ambs ambiente1 = (Ambs) space.take(ambiente, null, 60 * 100);
                if (ambiente1.user == null) {
                    ambiente1.user = new ArrayList<>();
                }
                ambiente1.user.add(nome);
                space.write(ambiente1, null, Lease.FOREVER);

                user usuario = new user();
                usuario.name = nome;
                user usuario1 = (user) space.take(usuario, null, 60 * 100);
                usuario1.ambi = amb;
                System.out.println("Usuario " + nome + " adicionado no ambiente " + amb);
                space.write(usuario1, null, Lease.FOREVER);
            } else {
                System.out.println("Ambiente nao ancontrado");
            }
        } catch (UnusableEntryException | TransactionException | InterruptedException | RemoteException e) {
        }

    }

    public void AddDispAmb(JavaSpace space, String nome, String amb) {
        Ambs ambiente = new Ambs();
        ambiente.name = amb;
        try {
            Ambs ambientes = (Ambs) space.read(ambiente, null, 60 * 100);
            if (ambientes != null) {
                Ambs ambiente1 = (Ambs) space.take(ambiente, null, 60 * 100);
                if (ambiente1.disp == null) {
                    ambiente1.disp = new ArrayList<>();
                }
                ambiente1.disp.add(nome);
                space.write(ambiente1, null, Lease.FOREVER);
                dispo usuario = new dispo();
                usuario.name = nome;
                dispo usuario1 = (dispo) space.take(usuario, null, 60 * 100);
                usuario1.ambi = amb;
                System.out.println("Dispositivo " + nome + " adicionado no ambiente " + amb);
                space.write(usuario1, null, Lease.FOREVER);
            } else {
                System.out.println("Ambiente nao encontrado");
            }
        } catch (UnusableEntryException | TransactionException | InterruptedException | RemoteException e) {
        }

    }

    public void AddDisp(JavaSpace space, String nome) {
        try {
            dispo disp = new dispo();
            disp.name = nome;
            space.write(disp, null, Lease.FOREVER);
            System.out.println("Dipositivo " + nome + " adicionado");
        } catch (TransactionException | RemoteException e) {
        }

    }

    public void AddUser(JavaSpace space, String nome) {
        try {
            user us = new user();
            us.name = nome;
            space.write(us, null, Lease.FOREVER);
            System.out.println("Usuario " + nome + " adicionado");
        } catch (TransactionException | RemoteException e) {
        }
    }

    public void ListUsers(JavaSpace space, String amb) {
        Ambs ambiente = new Ambs();
        ambiente.name = amb;
        try {
            Ambs ambiente1 = (Ambs) space.read(ambiente, null, 60 * 1000);
            List<String> listUser = ambiente1.user;
            System.out.println("Lista de usuarios");
            for (int i = 0; i < listUser.size(); i++) {
                System.out.println(listUser.get(i));
            }
        } catch (UnusableEntryException | TransactionException | InterruptedException | RemoteException e) {
        }
    }

    public void ListDip(JavaSpace space, String amb) {
        Ambs ambiente = new Ambs();
        ambiente.name = amb;
        try {
            Ambs ambiente1 = (Ambs) space.read(ambiente, null, 60 * 1000);
            List<String> listUser = ambiente1.disp;
            System.out.println("Lista de Dispositivos");
            for (int i = 0; i < listUser.size(); i++) {
                System.out.println(listUser.get(i));
            }
        } catch (UnusableEntryException | TransactionException | InterruptedException | RemoteException e) {
        }
    }

    public void DeleteAmb(JavaSpace space, String amb) {
        Ambs ambiente = new Ambs();
        ambiente.name = amb;
        try {
            Ambs ambiente1 = (Ambs) space.take(ambiente, null, 60);
            if ((ambiente1.disp == null || ambiente1.disp.isEmpty()) && (ambiente1.user == null) || ambiente1.user.isEmpty()) {

                System.out.println("Ambiente " + amb + " deletado");
            } else {
                System.out.println("O Ambiente não está fazio");
                space.write(ambiente1, null, Lease.FOREVER);
            }
        } catch (UnusableEntryException | TransactionException | InterruptedException | RemoteException e) {
        }
    }

    public void TrocarAmbUser(JavaSpace space, String nome, String amb) {
        try {
            user us = new user();
            us.name = nome;
            user users = (user) space.take(us, null, 60 * 1000);
            Ambs amb1 = new Ambs();
            amb1.name = users.ambi;
            Ambs amb2 = (Ambs) space.take(amb1, null, 60 * 1000);
            int id = amb2.getIdUser(nome);
            amb2.user.remove(id);
            users.ambi = amb;
            space.write(users, null, Lease.FOREVER);
            space.write(amb2, null, Lease.FOREVER);
            amb1.name = amb;
            Ambs amb3 = (Ambs) space.take(amb1, null, 60 * 1000);
            if (amb3.user == null) {
                amb3.user = new ArrayList<>();
            }
            amb3.user.add(nome);
            space.write(amb3, null, Lease.FOREVER);
            System.out.println("O usuario " + nome + " foi movido para " + amb);
        } catch (UnusableEntryException | TransactionException | InterruptedException | RemoteException e) {

        }
    }

    public void TrocarAmbDisp(JavaSpace space, String nome, String amb) {
        try {
            dispo us = new dispo();
            us.name = nome;
            dispo dispos = (dispo) space.take(us, null, 60);
            Ambs amb1 = new Ambs();
            amb1.name = dispos.ambi;
            Ambs amb2 = (Ambs) space.take(amb1, null, 60);
            int id = amb2.getIdDisp(nome);
            amb2.disp.remove(id);
            dispos.ambi = amb;
            space.write(dispos, null, Lease.FOREVER);
            space.write(amb2, null, Lease.FOREVER);
            amb1.name = amb;
            Ambs amb3 = (Ambs) space.take(amb1, null, 60);
            if (amb3.disp == null) {
                amb3.disp = new ArrayList<>();
            }
            amb3.disp.add(nome);
            space.write(amb3, null, Lease.FOREVER);
            System.out.println("O dispositivo " + nome + " foi movido para " + amb);
        } catch (UnusableEntryException | TransactionException | InterruptedException | RemoteException e) {

        }
    }

    public void chat(JavaSpace space, String user1, String user2) {
        try {
            user users = new user();
            users.name = user1;
            user us1 = (user) space.read(users, null, 60);
            users.name = user2;
            user us2 = (user) space.read(users, null, 60);
            if (us1.ambi.equals(us2.ambi)) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        new MessegerChat(space, user1, user2);
                    }
                }).start();

            }else{System.out.println("Os usuarios nao estao no mesmo ambiente");}
        } catch (UnusableEntryException | TransactionException | InterruptedException | RemoteException e) {
        }
    }
}
