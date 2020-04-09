#Digital Signature using RSA Algorithm

In this program, Alice depicts the sender who sends message to Bob who depicts receiver on a Web Socket. The program involves following steps:

Steps happening on Sender's (Alice) side:

1. Alice converts the message which she wants to send to Bob into Message Digest using MD5 algorithm
2. Alice performs RSA setup and generates public key (e) and private key (d)
3. Message Digest is encrypted using private key of Alice and hence Digital Signature is generated
4. This Digital Signature is appended to the original message t create Ciphertext
5. This Ciphertext along with public key of Alice is send accross the Socket.

Steps happening on Receiver's (Bob) side:

1. Bob receives ciphertext and the public key from Alice via socket
2. Ciphertext is separated into message and Digital Signature
3. Digital Signature is decrypted using public key of Alice and Message Digest say MD1 is obtained
4. Message that Bob received in Ciphertext is converted to Message Digest say MD2 by applying MD5 Algorithm 
5. Both MD1 and MD2 are checked.
	5.1 If both are same, message is authenticated. ALice is an authenticated sender
	5.2 If both are not same, message is not authenticated. ALice is not an authenticated sender.
	

