import java.awt.Image;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import sun.awt.image.InputStreamImageSource;

class SMS4Test{
    public static void main(String[] args){
        // test algorithm
        SMS4 sm4 = new SMS4();

        byte[] input = {
            0x01, 0x23, 0x45, 0x67, (byte)0x89, (byte)0xab, (byte)0xcd, (byte)0xef, 
            (byte)0xfe, (byte)0xdc, (byte)0xba, (byte)0x98, 0x76, 0x54, 0x32, 0x10
        };
        byte[] key = {
            0x01, 0x23, 0x45, 0x67, (byte)0x89, (byte)0xab, (byte)0xcd, (byte)0xef, 
            (byte)0xfe, (byte)0xdc, (byte)0xba, (byte)0x98, 0x76, 0x54, 0x32, 0x10
        };

        byte[] output = new byte[256];
        int[] rk = new int[32];
        // encryption
        sms4_ecb(byte[] in, int inLen, byte[] key, byte[] out, int CryptFlag)
        sm4.sms4_ecb(input, input.length, key, output, sm4.ENCRYPT);
        sm4.SMS4Crypt(input, output, rk);
        // change to hexadcimal, so divide 16
        for(int i = 0; i < output.length / 16; i++){
            System.out.printf("%04x ",output[i]);
        }
        System.out.println();
        // decryption
        sm4.SMS4KeyExt(key, rk, sm4.DECRYPT);
        byte[] plaintext = new byte[256];
        sm4.SMS4Crypt(output, plaintext, rk);
        for(int i = 0; i < plaintext.length / 16; i++){
            System.out.printf("%04x ", plaintext[i]);
        }

    }
}