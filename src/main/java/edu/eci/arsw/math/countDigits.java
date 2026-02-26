package edu.eci.arsw.math;

import java.io.ObjectStreamException;

import static edu.eci.arsw.math.Main.bytesToHex;
import static edu.eci.arsw.math.PiDigits.DigitsPerSum;
import static edu.eci.arsw.math.PiDigits.sum;

public class countDigits extends Thread{
    private int espera = 5000;
    private Object lock;
    private int a;
    private int cantidad;

    private byte[] digits;
    private byte[] miniDigits;

    public countDigits(int a, int b,Object lock){
        this.a = a;
        this.cantidad= b;
        this.lock = lock;
        this.miniDigits = new byte[b];
    }

    public countDigits(Object l){
        this.lock = l;
    }

    @Override
    public void run(){
        synchronized (lock){
            try{
                contar();
                this.sleep(espera);
                System.out.println(this.getDigits());
            }catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }


//        synchronized(lock){
//            while(true){
//                try {
//                    this.sleep(espera);
//                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
//                }
//            }
//        }
    }

    public void contar(){
        this.digits = PiDigits.getDigits2(a,cantidad);
        System.out.println(bytesToHex(this.digits));
    }

    public void contar2(){
        double sum = 0;

        for (int i = 0; i < cantidad; i++) {
            if (i % DigitsPerSum == 0) {
                sum = 4 * sum(1, a)
                        - 2 * sum(4, a)
                        - sum(5, a)
                        - sum(6, a);

                a += DigitsPerSum;
            }

            sum = 16 * (sum - Math.floor(sum));
            miniDigits[i] = (byte) sum;
        }
    }

    public byte[]  getMiniDigits(){
        return  this.miniDigits;
    }
    public void setIntervalo(int a, int b){
        this.a = a;
        this.cantidad = b;
    }
    public byte[] getDigits(){
        return  this.digits;
    }

    public int getA(){
        return  this.a;
    }

    public int getCantidad(){
        return  this.cantidad;
    }
}
