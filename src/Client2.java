public class Client2 {
    public static void main(String[] args) {
        Client client = new Client("127.0.0.1", 6789);
        client.connetti();
        client.comunica();
    }
}
