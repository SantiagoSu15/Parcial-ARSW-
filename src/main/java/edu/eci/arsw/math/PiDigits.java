package edu.eci.arsw.math;


import java.lang.reflect.Array;
import java.util.ArrayList;

///  <summary>
///  An implementation of the Bailey-Borwein-Plouffe formula for calculating hexadecimal
///  digits of pi.
///  https://en.wikipedia.org/wiki/Bailey%E2%80%93Borwein%E2%80%93Plouffe_formula
///  *** Translated from C# code: https://github.com/mmoroney/DigitsOfPi ***
///  </summary>
public class PiDigits {

    public static int DigitsPerSum = 8;
    private static double Epsilon = 1e-17;
    private static Object lock = new Object();
    private static ArrayList<countDigits> hilos = new ArrayList<>();
    public static void crearHilos(int num){
        for(int i = 0;i<num;i++){
            countDigits c = new countDigits(lock);
            hilos.add(c);
        }
    }

    public static void dividir(int inicio, int numhilos,int cantidad,ArrayList<countDigits> lista){
        int tamaño = cantidad/numhilos;
        int fin = tamaño;
        for(countDigits c : lista){
            c.setIntervalo(inicio,tamaño);
            inicio+=fin;
            fin+=tamaño+1;
        }
    }


    /**
     * Returns a range of hexadecimal digits of pi.
     * @param start The starting location of the range.
     * @param count The number of digits to return
     * @return An array containing the hexadecimal digits.
     */
    public static byte[] getDigits(int start, int count,int numHilos) throws InterruptedException {

        if (start < 0) {
            throw new RuntimeException("Invalid Interval");
        }

        if (count < 0) {
            throw new RuntimeException("Invalid Interval");
        }

        crearHilos(numHilos);
        dividir(start,hilos.size(),count,hilos);

        hilos.forEach(h-> h.start());

        for(countDigits h : hilos){
            h.join();
        }

        byte[] digits = new byte[count];

        ArrayList<byte[]> bList = new ArrayList<>();
        for(countDigits h2 : hilos){
            bList.add(h2.getDigits());
//            for(int i = h2.getA();i<h2.getCantidad();i++){
//                digits[i] =
//            }
        }




        return digits;
    }

    public static byte[] getDigits2(int start, int count)  {

        if (start < 0) {
            throw new RuntimeException("Invalid Interval");
        }

        if (count < 0) {
            throw new RuntimeException("Invalid Interval");
        }

        byte[] digits = new byte[count];
        double sum = 0;

        for (int i = 0; i < count; i++) {
            if (i % DigitsPerSum == 0) {
                sum = 4 * sum(1, start)
                        - 2 * sum(4, start)
                        - sum(5, start)
                        - sum(6, start);

                start += DigitsPerSum;
            }

            sum = 16 * (sum - Math.floor(sum));
            digits[i] = (byte) sum;
        }

        return digits;
    }


    public void despertar(){
        for(countDigits h : hilos){
            lock.notifyAll();
        }
    }



    /// <summary>
    /// Returns the sum of 16^(n - k)/(8 * k + m) from 0 to k.
    /// </summary>
    /// <param name="m"></param>
    /// <param name="n"></param>
    /// <returns></returns>
    public static double sum(int m, int n) {
        double sum = 0;
        int d = m;
        int power = n;

        while (true) {
            double term;

            if (power > 0) {
                term = (double) hexExponentModulo(power, d) / d;
            } else {
                term = Math.pow(16, power) / d;
                if (term < Epsilon) {
                    break;
                }
            }

            sum += term;
            power--;
            d += 8;
        }

        return sum;
    }

    /// <summary>
    /// Return 16^p mod m.
    /// </summary>
    /// <param name="p"></param>
    /// <param name="m"></param>
    /// <returns></returns>
    private static int hexExponentModulo(int p, int m) {
        int power = 1;
        while (power * 2 <= p) {
            power *= 2;
        }

        int result = 1;

        while (power > 0) {
            if (p >= power) {
                result *= 16;
                result %= m;
                p -= power;
            }

            power /= 2;

            if (power > 0) {
                result *= result;
                result %= m;
            }
        }

        return result;
    }

}
