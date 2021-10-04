package com.company;

public class ComplexNumber {
    private double re;
    private double im;

    public double getRe() {
        return re;
    }

    public void setRe(double re) {
        this.re = re;
    }

    public double getIm() {
        return im;
    }

    public void setIm(double im) {
        this.im = im;
    }

    /**
     * Creates an object with these 2 attributes
     * @param re double
     * @param im double
     */
    ComplexNumber(double re, double im) {
        this.re = re;
        this.im = im;

    }

    /**
     * Default constructor,
     * real part = 0,
     * imaginary part = 0
     */
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
     * @param other - Complex number
     * @return reference to the current object
     */
    public ComplexNumber scadere(ComplexNumber other) {
        this.re -= other.re;
        this.im -= other.im;
        return this;
    }

    /**
     * Multiply the current object with other ComplexNumber
     *
     * @param other - Complex number
     * @return reference to current object
     */
    public ComplexNumber inmultire(ComplexNumber other) {
        // (a+bi) * (c+di) = ac + (a*d) i + (b*c) i - d*b = ( ac - bd) + (ad +bc)*i;
        double a = this.re;
        double b = this.im;
        double c = other.re;
        double d = other.im;
        this.re = a * c - b*d;
        this.im = a * d + b * c;
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
    public ComplexNumber impartire(ComplexNumber other) throws Exception {
        if( other.re == 0 && other.im == 0)
            throw new Exception("Division by 0");
        double a = this.re;
        double b = this.im;
        double c = other.re;
        double d = other.im;
        this.re = (a * c + b * d)/(c * c + d* d);
        this.im = (c * b - a * d)/(c * c + d * d);
        return this;
    }

    /**
     *
     * @return ComplexNumber string = real part + imaginary part * i (imaginary unit)
     */
    @Override
    public String toString() {

        if(im > 0)
            return re + " + " + im + "*i";
        else
            return re +" "+ im + " *i";
    }
}
