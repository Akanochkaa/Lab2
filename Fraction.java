public class Fraction {
    private int numerator;
    private int denominator;

    public Fraction(int numerator, int denominator) {
        if (denominator == 0) {
            throw new IllegalArgumentException("Знаменатель не может быть равен нулю!");
        }
        this.numerator = numerator;
        this.denominator = denominator;
    }


    private Fraction simplify() {
        int gcd = gcd(Math.abs(this.numerator), Math.abs(this.denominator));
        int newNum = this.numerator / gcd;
        int newDen = this.denominator / gcd;


        if (newDen < 0) {
            newNum = -newNum;
            newDen = -newDen;
        }
        return new Fraction(newNum, newDen);
    }

    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    public Fraction sum(Fraction other) {
        return new Fraction(
                this.numerator * other.denominator + other.numerator * this.denominator,
                this.denominator * other.denominator
        ).simplify();
    }

    public Fraction minus(Fraction other) {
        return new Fraction(
                this.numerator * other.denominator - other.numerator * this.denominator,
                this.denominator * other.denominator
        ).simplify();
    }

    public Fraction mult(Fraction other) {
        return new Fraction(
                this.numerator * other.numerator,
                this.denominator * other.denominator
        ).simplify();
    }

    public Fraction div(Fraction other) {
        if (other.numerator == 0) throw new ArithmeticException("Деление на ноль!");
        return new Fraction(
                this.numerator * other.denominator,
                this.denominator * other.numerator
        ).simplify();
    }

    public Fraction sum(int integer) { return this.sum(new Fraction(integer, 1)); }
    public Fraction minus(int integer) { return this.minus(new Fraction(integer, 1)); }
    public Fraction mult(int integer) { return this.mult(new Fraction(integer, 1)); }
    public Fraction div(int integer) { return this.div(new Fraction(integer, 1)); }

    @Override
    public String toString() {
        if (denominator == 1) {
            return String.valueOf(numerator);
        }
        return numerator + "/" + denominator;
    }
}