import java.io.*;
import java.net.*;

class Client {
    String serverName = "[Nome Server]";
    int serverPort = -1;
    Socket mySocket;
    BufferedReader keyboard;
    String userString;
    String stringFromServer;
    DataOutputStream outToServer;
    BufferedReader inFormServer;

    //Costruttore
    public Client(String serverName, int serverPort) {
        this.serverName = serverName;
        this.serverPort = serverPort;
    }

    //metodi
    public Socket connetti() {
        System.out.println("2 CLIENT partito in esecuzione ...");
        try {
            keyboard = new BufferedReader(new InputStreamReader(System.in));

            mySocket = new Socket(serverName, serverPort);

            outToServer = new DataOutputStream(mySocket.getOutputStream());
            inFormServer = new BufferedReader(new InputStreamReader(mySocket.getInputStream()));
        } catch (UnknownHostException e) {
            System.out.println("Host Sconosciuto");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Errore durante la connessione!");
            System.exit(1);
        }
        return mySocket;
    }

    public void comunica() {
        for (; ; ) {
            try {
                System.out.println("4 ... utente inserisci una stringa da trasmetere al server:");
                userString = keyboard.readLine();

                System.out.println("5 ... invio la stringa al server e attendo ...");
                outToServer.writeBytes(userString + '\n');

                stringFromServer = inFormServer.readLine();
                System.out.println("7 ... risposta dal server:\n" + stringFromServer);

                if (userString.equals("FINE")) {
                    System.out.println("8 CLIENT: termina elaborazione e chiude connessione");
                    mySocket.close();
                    break;
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
                System.out.println("Errore durante a comuniczione col server!");
                System.exit(1);
            }
        }
    }

    //Main
    public static void main(String[] args) {
        Client client = new Client("127.0.0.1", 6789);
        client.connetti();
        client.comunica();
    }
}