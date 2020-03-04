package com.mike.poo;

public class Complejo {

	private double real, imaginaria;

	public Complejo() {
		this.real = 0.0;
		this.imaginaria = 0.0;
	}

	public Complejo(double real, double imaginaria) {
		this.real = real;
		this.imaginaria = imaginaria;
	}

	public Complejo sumar(Complejo num2) {
		Complejo resultado = new Complejo();

		resultado.real = (this.real + num2.real);
		resultado.imaginaria = (this.imaginaria + num2.imaginaria);

		return resultado;
	}

	public Complejo restar(Complejo num2) {
		Complejo resultado = new Complejo();

		resultado.real = (this.real - num2.real);
		resultado.imaginaria = (this.imaginaria - num2.imaginaria);

		return resultado;
	}

	public Complejo multiplicar(Complejo num2) {
		Complejo resultado = new Complejo();

		resultado.real = ((this.real * num2.real) - (this.imaginaria * num2.imaginaria));
		resultado.imaginaria = ((this.real * num2.imaginaria) + (this.imaginaria * num2.real));

		return resultado;
	}

	public Complejo multiplicar(double num2) {
		Complejo resultado = new Complejo();

		resultado.real = (this.real * num2);
		resultado.imaginaria = (this.imaginaria * num2);

		return resultado;
	}

	public Complejo dividir(Complejo num2) {
		Complejo resultado = new Complejo();

		resultado.real = ((this.real * num2.real + this.imaginaria * num2.imaginaria)
				/ ((Math.pow(num2.real, 2)) + (Math.pow(num2.imaginaria, 2))));
		resultado.imaginaria = ((this.imaginaria * num2.real) - (this.real * num2.imaginaria))
				/ ((Math.pow(num2.real, 2) + Math.pow(num2.imaginaria, 2)));

		return resultado;
	}

	public double getReal() {
		return real;
	}

	public void setReal(double real) {
		this.real = real;
	}

	public double getImaginaria() {
		return imaginaria;
	}

	public void setImaginaria(double imaginaria) {
		this.imaginaria = imaginaria;
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Complejo other = (Complejo) obj;
		if (Double.doubleToLongBits(imaginaria) != Double.doubleToLongBits(other.imaginaria))
			return false;
		if (Double.doubleToLongBits(real) != Double.doubleToLongBits(other.real))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "(" + this.real + ", " + this.imaginaria + ")";
	}

}
