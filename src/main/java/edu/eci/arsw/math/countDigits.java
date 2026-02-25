package edu.eci.arsw.math;

public class countDigits extends Thread{
    private int espera = 5000;
    private Object lock;
    private int a;
    private int cantidad;

    public countDigits(int a, int b,Object lock){
        this.a = a;
        this.cantidad= b;
        this.lock = lock;
    }

    public countDigits(){}

    @Override
    public void run(){
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
        PiDigits.getDigits2(a,cantidad);
    }

    public void setIntervalo(int a, int b){
        this.a = a;
        this.cantidad = b;
    }
}
