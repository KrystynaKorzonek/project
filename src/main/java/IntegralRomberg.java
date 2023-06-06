import java.util.function.Function;

public class IntegralRomberg {
    private double a;
    private double b;
    private Function<Double, Double> f;
    private double result;

    public IntegralRomberg(double a, double b, Function<Double, Double> f, int n) {
        this.a = a;
        this.b = b;
        this.f = f;

        double[][] romberg = new double[n][n];

        for (int i = 0; i < n; i++) {
            romberg[i][0] = trap(a, b, f);
        }
        for (int i = 1; i < n; i++) {
            for (int j = 1; j <= i; j++) {
                romberg[i][j] = (Math.pow(4, j) * romberg[i][j - 1] - romberg[i - 1][j - 1]) / (Math.pow(4, j) - 1);
            }
        }
        this.result = romberg[n - 1][n - 1];
    }

    public static double trap(double a, double b, Function<Double, Double> f) {
        int n = 1000;
        double h = (b - a) / n;
        double sum = (f.apply(a) + f.apply(b)) / 2;
        for (int i = 1; i < n; i++) {
            sum += f.apply(a + i * h);
        }
        return sum * h;

    }

    public double getIntegral() {
        return result;
    }

}
