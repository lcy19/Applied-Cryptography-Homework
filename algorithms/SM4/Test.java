import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.io.File;
import java.io.FileInputStream;

class Test{
    public static void main(String[] args){
        
        Scanner sc = new Scanner(System.in);
        System.out.print("Input the file you want to read: ");
        String fileName = sc.nextLine();
        System.out.print("Input the file you want to create: ");
        String newFile = sc.nextLine();
        SMS4 sm4 = new SMS4();
        byte[] key = {
            0x01, 0x23, 0x45, 0x67, (byte)0x89, (byte)0xab, (byte)0xcd, (byte)0xef, 
            (byte)0xfe, (byte)0xdc, (byte)0xba, (byte)0x98, 0x76, 0x54, 0x32, 0x10
        };

        FileInputStream inStream = null;
        FileOutputStream outStream = null;
        try{
            inStream = new FileInputStream(fileName);
            outStream = new FileOutputStream(new File(newFile));
            int n = 0;
            byte[] bb = new byte[1024];
            while((n = inStream.read(bb))!= -1){
                byte[] out = new byte[1024];
                // 1表示加密，0表示解密
                sm4.sms4_ecb(bb, bb.length, key, out, 0);
                outStream.write(out, 0, n);
            }
            inStream.close();
            outStream.close();
        } catch(FileNotFoundException e){
            System.out.println("Don't find the file " + fileName);
        } catch(IOException e){
            System.out.println("Problem opening the file");
        }
        
        System.out.println("Finish it");
    }
}

