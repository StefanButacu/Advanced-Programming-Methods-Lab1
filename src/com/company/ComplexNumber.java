package com.company;

public class ComplexNumber {
    public double re;
    public double im;


    ComplexNumber(double re, double im) {
        this.re = re;
        this.im = im;

    }

    public ComplexNumber() {
        this.re = 0;
        this.im = 0;
    }

    /**
     * adds to currentObject other ComplexNumber
     *
     * @param other - Complex number
     * @return referrence to current object after add operation
     */
    public ComplexNumber adunare(ComplexNumber other) {
        this.re += other.re;
        this.im += other.im;
        return this;
    }

    /**
     * Subtract from current number other ComplexNumber
     *
     * @param other
     * @return
     */
    public ComplexNumber scadere(ComplexNumber other) {
        this.re -= other.re;
        this.im -= other.im;
        return this;
    }

    /**
     * Multiply the current object with other ComplexNumber
     *
     * @param other
     * @return reference to current object
     */
    public ComplexNumber inmultire(ComplexNumber other) {
        // (a+bi) * (c+di) = ac + (a*d) i + (b*c) i - d*b;
        this.re = this.re * other.re - this.im * other.im;
        this.im = this.re * other.im + this.im * other.re;
        return this;
    }

    /**
     * @return a new ComplexNumber equal to complex conjugate of current object
     */
    public ComplexNumber conjugat() {
        return new ComplexNumber(this.re, this.im * (-1));

    }

    /**
     * @param other - ComplexNumber
     * @return - reference to current object after the multiplication
     */
    public ComplexNumber impartire(ComplexNumber other) {
        inmultire(other.conjugat());
        return this;
    }


    @Override
    public String toString() {
        return re + " + " + im + "*i";
    }
}
