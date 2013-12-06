// Lê uma linha do teclado
// Envia o pacote (linha digitada) ao servidor
// Lê um pacote (linha)do servidor e mostra no vídeo

import java.io.*; // classes para input e output streams e
import java.net.*;// DatagramaSocket,InetAddress,DatagramaPacket

class UDPCliente
{
   public static void main(String args[]) throws Exception
   {
      // obtem endereço IP do servidor com o DNS
      InetAddress ipServidor = InetAddress.getByName("127.0.0.1");
      int portaServidor = 10000;

      // cria o stream do teclado
      BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));

      //Vetores de bytes para envio e recepção de mensagens
      byte[] msgEnviar = new byte[1024];
      byte[] msgRecebida = new byte[1024];

      // declara socket cliente
      DatagramSocket clientSocket = new DatagramSocket();

      //Criação de objtos necessários para envio e recepção de mensagens
      DatagramPacket enviarPacote;
      DatagramPacket receberPacote;
      String textoDigitado = " ";
      String msgRecebidaServidor;

      while (!textoDigitado.equals("fim"))
      {
         // lê uma linha do teclado
         System.out.print("Digite a mensagemm (fim = encerrar): ");
         textoDigitado = teclado.readLine();
         msgEnviar = textoDigitado.getBytes();

         // cria pacote com o dado, o endereço do servidor e porta do servidor
         enviarPacote = new DatagramPacket(msgEnviar, msgEnviar.length, ipServidor, portaServidor);

         //envia o pacote
         clientSocket.send(enviarPacote);

         // declara o pacote a ser recebido
         receberPacote = new DatagramPacket(msgRecebida, msgRecebida.length);

         // recebe pacote do servidor
         clientSocket.receive(receberPacote);

         // separa somente o dado recebido
         msgRecebidaServidor = new String(receberPacote.getData());
         // mostra no vídeo
         System.out.println("Recebida de " + receberPacote.getAddress().getLocalHost() + ":" + portaServidor + ": " + msgRecebidaServidor);
      }
      // fecha o cliente
      clientSocket.close();
   }
}
