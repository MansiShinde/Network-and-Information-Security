import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.Signature;


public class DSA_Alice {

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try {
			
			System.out.println("\n******* ALICE *******");
			
			String serverName = "localhost";
			int port = 8088;

			BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
			
			System.out.println("Enter the Message to Send:");
			String Message = input.readLine();
			
			
			KeyPairGenerator kpg = KeyPairGenerator.getInstance("DSA");
		    kpg.initialize(512); // 512 is the keysize.
			KeyPair keyPair = kpg.generateKeyPair();
			
		    
		    byte[] signature = performSigning(Message, "DSA", keyPair);
		    
		    System.out.println("Creating Ciphertext.....");
		    
		    ByteArrayOutputStream concatenate = new ByteArrayOutputStream();
		      concatenate.write(Message.getBytes());
		      concatenate.write("$".getBytes()); 
		      concatenate.write(signature);
		      
		      byte[] ciphertext = concatenate.toByteArray();
		      
		      System.out.println("Which case to choose?\n 1. Verification Success case \n. 2.Verification Failed Case");
		      int option = Integer.parseInt(input.readLine());
		      
		  	System.out.println("\n\nConnecting to "+serverName + "on port "+port);

			Socket client = new Socket(serverName, port);
			
			System.out.println("\nJust connected to "+client.getRemoteSocketAddress());
			
			OutputStream outToServer = client.getOutputStream();
			
			ObjectOutputStream out = new ObjectOutputStream(outToServer); 
			DataOutputStream byteout = new DataOutputStream(outToServer);
			
			out.writeObject(keyPair);
			byteout.writeInt(option);
			byteout.writeInt(ciphertext.length);
			byteout.write(ciphertext);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	

 static byte[] performSigning(String MessageDigest, String DSA_alg, KeyPair keyPair) throws Exception {
	    Signature sign = Signature.getInstance(DSA_alg);
	    PrivateKey privateKey = keyPair.getPrivate();
	    sign.initSign(privateKey);
	    sign.update(MessageDigest.getBytes());
	    return sign.sign();
	  }
 
}
