
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;

public class MainClass {
  public static void main(String[] args) throws Exception {
   
	 KeyPairGenerator kpg = KeyPairGenerator.getInstance("DSA");
    
	 kpg.initialize(512); // 512 is the keysize.
	  KeyPair keyPair = kpg.generateKeyPair();

    byte[] signature = performSigning("test", "SHA1withDSA", keyPair);
    performVerification("mansi", "SHA1withDSA", signature, keyPair.getPublic());
  }

  static byte[] performSigning(String s, String alg, KeyPair keyPair) throws Exception {
    Signature sign = Signature.getInstance(alg);
    PrivateKey privateKey = keyPair.getPrivate();
    PublicKey publicKey = keyPair.getPublic();
    sign.initSign(privateKey);
    sign.update(s.getBytes());
    return sign.sign();
  }

  static void performVerification(String s, String alg, byte[] signature, PublicKey publicKey)
      throws Exception {
    Signature sign = Signature.getInstance(alg);
    sign.initVerify(publicKey);
    sign.update(s.getBytes());
    System.out.println(sign.verify(signature));
  }
}