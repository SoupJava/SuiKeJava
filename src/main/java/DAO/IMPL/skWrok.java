package DAO.IMPL;

public class skWrok {
    public static double KQwork(int a, int b, int c) {
        return Math.abs(8 * Math.sin((double) a / 5) + 8 + b - c / 10);
    }

    public static double TWwork(double a, int b, int c, double d, double e, int f) {
        return 100 * Math.exp(d * (1 + ((double) a - (e / (double) f)) / (e / (double) f))) * (0.6 * (double) b + 1)
                / ((0.5 * (double) c + 1) * (Math.sin((double) c / 5) + 1));
    }

}
