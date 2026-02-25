/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.math;

import java.util.Arrays;

/**
 *
 * @author hcadavid
 */
public class Main {

    public static void main(String a[]) throws InterruptedException {
        System.out.println(bytesToHex(PiDigits.getDigits(0, 10,3)));
        //System.out.println(bytesToHex(PiDigits.getDigits(1, 100,3)));
        //System.out.println(bytesToHex(PiDigits.getDigits(1, 1000000,3)));

//        243F6A8885
//        43F6A8885A308D313198A2E03707344A4093822299F31D0082EFA98EC4E6C89452821E638D01377BE5466CF34E90C6CC0AC2
        System.out.println(bytesToHex(PiDigits.getDigits2(0, 10)));
        System.out.println(bytesToHex(PiDigits.getDigits2(1, 100)));


    }

    private final static char[] hexArray = "0123456789ABCDEF".toCharArray();

    public static String bytesToHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        StringBuilder sb=new StringBuilder();
        for (int i=0;i<hexChars.length;i=i+2){
            //sb.append(hexChars[i]);
            sb.append(hexChars[i+1]);            
        }
        return sb.toString();
    }

}
