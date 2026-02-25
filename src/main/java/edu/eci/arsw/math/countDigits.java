package edu.eci.arsw.math;

public class countDigits extends Thread{
    private int espera = 5000;
    private Object lock;
    private int a;
    private int cantidad;

    private byte[] digits;

    public countDigits(int a, int b,Object lock){
        this.a = a;
        this.cantidad= b;
        this.lock = lock;
    }

    public countDigits(){}

    @Override
    public void run(){
        System.out.println("Inicio: " + this.a + " Mi tamaño: " + this.cantidad);
        contar();

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
    }

    public void setIntervalo(int a, int b){
        this.a = a;
        this.cantidad = b;
    }
    public byte[] getDigits(){
        return  this.digits;
    }
}
