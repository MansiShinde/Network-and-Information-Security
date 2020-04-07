import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.math.BigInteger;
import java.net.ServerSocket;
import java.net.Socket;


public class DS_RSA_Bob {

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try {
			int port =8088;
			
			ServerSocket serverSocket = new ServerSocket(port);
			
			System.out.println("Waiting for Alice....");
			Socket server = serverSocket.accept();
			System.out.println("Connected to "+server.getRemoteSocketAddress());
			
//Accept data from client
			
			ObjectInputStream in = new ObjectInputStream(server.getInputStream());
			BigInteger n = (BigInteger) in.readObject(); 
			BigInteger e = (BigInteger) in.readObject();; 
			
			DataInputStream bytein = new DataInputStream(server.getInputStream());
			int option = bytein.readInt();
			int length = bytein.readInt(); // read length of incoming message
			 byte[] ciphertext = new byte[length];
			if(length>0) { 
			    bytein.readFully(ciphertext, 0, ciphertext.length); // read the message
			}
				
			Ciphertext ci = new Ciphertext(e,n);
			
			ci.separate_message_and_sign(ciphertext, option);
			
			String message = ci.getMessage();	
			
			System.out.println("Message is:"+message);
			
			byte ds_in_bytes[] = ci.getSignInBytes();
			
			 				
				System.out.println("\n\nDecrypting the digital Signature using Bob's secret key...");
				byte[] MD = ci.decrypt(ds_in_bytes);
				
				String MessageDigest1 = new String(MD);
				System.out.println("Message Digest after decryption(one got from Alice):"+MessageDigest1);
				
				messageDigest md = new messageDigest();
				String MessageDigest2 = md.createMessageDigest(message); 
				System.out.println("Message Digest of message got from ALice:"+MessageDigest2);
				
				if(md.verification(MessageDigest1, MessageDigest2))
					System.out.println("Message is Authenticated!!!");
				else
					System.out.println("Message is not Authenticated!!!");
				
			
		}catch(IOException e) {
			
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}	
	}
}
