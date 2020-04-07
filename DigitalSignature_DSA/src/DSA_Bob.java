import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.KeyPair;
import java.security.PublicKey;
import java.security.Signature;




public class DSA_Bob {

	@SuppressWarnings("resource")
	public static void main(String[] args)  {
		// TODO Auto-generated method stub

		try {
			
			int port =8088;
			
			ServerSocket serverSocket = new ServerSocket(port);
			
			System.out.println("Waiting for Alice....");
			Socket server = serverSocket.accept();
			System.out.println("Connected to "+server.getRemoteSocketAddress());
			
//Accepting data from Alice
			
			ObjectInputStream in = new ObjectInputStream(server.getInputStream());
			KeyPair keyPair = (KeyPair) in.readObject();
			
			DataInputStream bytein = new DataInputStream(server.getInputStream());
			int option = bytein.readInt();
			int length = bytein.readInt(); // read length of incoming message
			 byte[] ciphertext = new byte[length];
			if(length>0) { 
			    bytein.readFully(ciphertext, 0, ciphertext.length); // read the message
			}
			
			Ciphertext ci = new Ciphertext();
			
			ci.separate_message_and_sign(ciphertext, option);
			
			String message = ci.getMessage();	
			
			System.out.println("Message is:"+message);
			
			byte ds_in_bytes[] = ci.getSignInBytes();
					
			if(performVerification(message, "DSA", ds_in_bytes,keyPair.getPublic()))
				System.out.println("Message is authenticated!!!");
			else
				System.out.println("Message is not authenticated. Its not send by Alice!!");
			
		}catch(IOException e) {
			
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	static boolean performVerification(String s, String alg, byte[] signature, PublicKey publicKey) throws Exception {
		    Signature sign = Signature.getInstance(alg);
		    sign.initVerify(publicKey);
		    sign.update(s.getBytes());
		   if(sign.verify(signature))
			   return true;
		   return false;
		  }
	
}
