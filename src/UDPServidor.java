// Recebe um pacote de algum cliente
// Separa o dado, o endereco IP e a portaCliente deste cliente
// Transforma em maiuscula
// Envia ao cliente, usando o endereco IP e a portaCliente recebidos
// Volta ao inicio

import java.net.*;

class UDPServidor
{
   static DatagramSocket serverSocket;

   public static void main(String args[]) throws Exception
   {
      // cria socket do servidor com a portaCliente 10000
      try
      {
         serverSocket = new DatagramSocket(10000);
      } catch (Exception e)
      {
         System.out.println("Porta sendo utilizada por outro processo");
         System.exit(0);
      }
      System.out.println("Aguardando recepcao de mensagens na porta 10000");
      while (true)
      {
         //Criar vetores de bytes para envio e recepção de mensagens
         byte[] msgRecebida = new byte[1024];
         byte[] msgEnviar = new byte[1024];

         // declara o pacote a ser recebido
         DatagramPacket receberDatagrama = new DatagramPacket(msgRecebida, msgRecebida.length);

         // recebe o pacote do cliente
         serverSocket.receive(receberDatagrama);

         // pega os dados, o endereco IP e a portaCliente do cliente
         // para poder mandar a msg de volta
         String mensagem = new String(receberDatagrama.getData());
         //InetAddress ipCliente = receberDatagrama.getAddress().getLocalHost();
         InetAddress ipCliente = receberDatagrama.getAddress();
         int portaCliente = receberDatagrama.getPort();

         System.out.println("Cliente " + ipCliente + ":" + portaCliente + " enviou: " + mensagem);

         // transforma em maiusculas
         String capitalizedSentence = mensagem.toUpperCase();
         msgEnviar = capitalizedSentence.getBytes();

         // monta o pacote com ender�o IP e portaCliente
         DatagramPacket sendPacket = new DatagramPacket(msgEnviar, msgEnviar.length, ipCliente, portaCliente);

         // envia ao cliente
         serverSocket.send(sendPacket);
      } 
   }
}
