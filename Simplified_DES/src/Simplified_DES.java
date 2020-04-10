import java.util.*;

public class Simplified_DES {

    public static int P10[] = {3,5,2,7,4,10,1,9,8,6};
    public static int P8[] = {6,3,7,4,8,5,10,9};
    public static int IP[] = {2,6,3,1,4,8,5,7};
    public static int IPI[] = {4,1,3,5,7,2,8,6};
    public static int EP[] = {4,1,2,3,2,3,4,1};
    public static int P4[] = {2,4,3,1};
    public static int S0[][]= {{1,0,3,2},{3,2,1,0},{0,2,1,3},{3,1,3,2}};
    public static int S1[][]= {{0,1,2,3},{2,0,1,3},{3,0,1,0},{2,1,0,3}};

    public static void main(String[] args) {
        // TODO Auto-generated method stub

        Scanner scan= new Scanner(System.in);
        int option = 0;

        do {

            System.out.println("Enter the message to be encrypted:");
            String message = scan.next();

            System.out.println("Enter the key:");
            String key = scan.next();

            System.out.println("\n ------------ KEY GENERATION----------\n");
            KeyGeneration(key, message);

            System.out.println("\n\nDo you want to continue?(Yes=1, NO=0)");
            option = scan.nextInt();
        }while(option!=0);
        
        scan.close();
    }
    
    //Performing Permutation Operation

    public static String permute(String str, int P[]) 
    {
        HashMap<Integer, Character> hash = new HashMap<>();
        StringBuilder strB = new StringBuilder();

        for(int i=0;i<str.length();i++)
        {
            hash.put(i+1, str.charAt(i));
        }

        for(int i=0;i<P.length;i++)
        {
            if(hash.containsKey(P[i]))
            {
                strB.append(hash.get(P[i]));
            }
        }

        return strB.toString();
    }
    
    
    //Key Generation function

    public static void KeyGeneration(String key, String message)
    {

        String str = permute(key,P10);

        System.out.println("Key after P10 operation:"+str+"\n");

        String strLS = LeftShifts(str, 1);

        System.out.println("Key after LS1(1 left shift):"+strLS);

        String str2 = permute(strLS,P8);

        System.out.println("Key after P8 operation, Key 1:"+str2+"\n");

        String strKey2 = LeftShifts(strLS, 2);

        System.out.println("Key after LS2(2 left shifts):"+strKey2);

        String str3 = permute(strKey2, P8);
        System.out.println("Key after P8 operation, Key 2:"+str3);

        System.out.println("\n ---------------ENCRYPTION ------------\n");
        Encryption(str2,str3,message);
    }

    public static String func1(String key, String message)
    {
        String sL1 = message.substring(0, (message.length())/2);
        String sR1= message.substring((message.length())/2);

        String strep = permute(sR1, EP);
        System.out.println("Output after Expanded permutation:"+strep);

        StringBuilder strep1 = new StringBuilder();

        for(int i=0;i<strep.length();i++)
        {
            strep1.append(Character.getNumericValue(strep.charAt(i)) ^ Character.getNumericValue(key.charAt(i)));
        }
        System.out.println("Output after K1 XOR EP:"+strep1.toString());


        String  sL = strep1.toString().substring(0, (strep1.toString().length())/2);
        String  sR = strep1.toString().substring((strep1.toString().length())/2);

        int no = S0[RowColCal(sL.charAt(0), sL.charAt(3))][RowColCal(sL.charAt(1), sL.charAt(2))];
        System.out.println("no:"+no);
        String s1 = Integer.toBinaryString(no);
        if(no == 1) s1 = "01";
        else if(no == 0) s1 = "00";
        System.out.println("s1:"+s1);

        no =   S1[RowColCal(sR.charAt(0), sR.charAt(3))][RowColCal(sR.charAt(1), sR.charAt(2))];
        System.out.println("no:"+no);
        String s2 = Integer.toBinaryString(no);
        if(no == 1) s2 = "01";
        else if(no == 0) s2 = "00";
        System.out.println("s2:"+s2);

        String sbox = s1 + s2 ;
        System.out.println("sbox output:"+sbox);

        String strP4 = permute(sbox,P4);

        System.out.println("AFter operation P4:" + strP4);

        StringBuilder strFun1 = new StringBuilder();

        for(int i=0;i<strP4.length();i++)
        {
            strFun1.append(Character.getNumericValue(strP4.charAt(i)) ^ Character.getNumericValue(sL1.charAt(i)));
        }

        strFun1.append(sR1);

        return strFun1.toString();
    }


    public static void Encryption(String key1, String key2,String message)
    {
        String str = permute(message, IP);
        System.out.println("Output after initial permutation (IP):"+str+"\n");

        String strFun1 = func1(key1, str);

        System.out.println("Output after func1:"+strFun1);

        String strFun2 = func1(key2, strFun1.substring(str.length()/2, str.length()) + strFun1.substring(0,str.length()/2));

        String ciphertext = permute(strFun2, IPI);
        System.out.println("The ciperText is:"+ ciphertext );

        System.out.println("\n ---------------DECRYPTION------------\n");
        Decryption(key1, key2, ciphertext);
    }

    public static void Decryption(String key1, String key2, String ciphertext)
    {
        String str = permute(ciphertext, IP);
        System.out.println("Text after initial permutation(IP):"+str);

        String strFun1 = func1(key2, str);

        System.out.println("Message after func1:"+strFun1);

        String strFun2 = func1(key1, strFun1.substring(str.length()/2, str.length()) + strFun1.substring(0,str.length()/2) );

        System.out.println("The plainText is :"+permute(strFun2, IPI));
    }


    public static int RowColCal(char a1,char a2)
    {
        int no=0;
        if(a1 == '1')
        {
            if(a2 == '1')   no = 3;
            else no = 2;
        }
        else if(a1 == '0')
        {
            if(a2=='1') no=1;
            else no=0;
        }

        return no;
    }

//To perform Left Shifts during Key Generation and Encryption, Decryption
    public static String LeftShifts(String str, int shift)
    {
        String s1 = str.substring(0,(str.length())/2);

        String s2 = str.substring((str.length())/2);

        String st1 = s1.substring(shift) + s1.substring(0, shift);
        System.out.println(st1);

        String st2 = s2.substring(shift) + s2.substring(0, shift);
        System.out.println(st2);

        String strLS = st1+st2;
        return strLS;
    }
}
