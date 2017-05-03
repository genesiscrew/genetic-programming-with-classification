import java.util.*;
import org.jgap.*;
import org.jgap.gp.*;
import org.jgap.gp.function.*;
import org.jgap.gp.impl.*;
import org.jgap.gp.terminal.*;

public class FitnessFunction extends GPFitnessFunction {
	Float[] x1;
	Float[] x2;
	Float[] x3;
	Float[] x4;
	Float[] x5;
	Float[] x6;
	Float[] x7;
	Float[] x8;
	Float[] x9;
	Float[] classTypes;
	Float[] y;
	Variable vx;
	Variable bx;
	private Variable CT;
	private Variable USz;
	private Variable UShp;
	private Variable MA;
	private Variable SESz;
	private Variable BN;
	private Variable BC;
	private Variable NN;
	private Variable M;

	public FitnessFunction(Float[][] parameters, Variable CT, Variable USz, Variable UShp, Variable MA, Variable SESz,
			Variable BN, Variable BC, Variable NN, Variable M) {
		// TODO Auto-generated constructor stub
		this.x1 = parameters[0];
		this.x2 = parameters[1];
		this.x3 = parameters[2];
		this.x4 = parameters[3];
		this.x5 = parameters[4];
		this.x6 = parameters[5];
		this.x7 = parameters[6];
		this.x8 = parameters[7];
		this.x9 = parameters[8];
		this.classTypes = parameters[9];
		this.y = y;
		this.CT = CT;
		this.USz = USz;
		this.UShp = UShp;
		this.MA = MA;
		this.SESz = SESz;
		this.BN = BN;
		this.BC = BC;
		this.NN = NN;
		this.M = M;
		// this.bx = bx;

		System.out.println(" i am here");
	}

	@Override
	protected double evaluate(IGPProgram arg0) {
		// TODO Auto-generated method stub
		return computeRawFitness(arg0);
	}

	private double computeRawFitness(IGPProgram arg0) {
		// TODO Auto-generated method stub

		if (vx == null) {
			System.out.println("shouldnt be here");
		}
		double error = 0.0f;
		double hits1 = 0.0f;
		double hits2 = 0.0f;
		Object[] noargs = new Object[0];

		for (int i = 0; i < 352; i++) {
			CT.set(x1[i]);
			USz.set(x2[i]);
			UShp.set(x3[i]);
			MA.set(x4[i]);
			SESz.set(x5[i]);
			BN.set(x6[i]);
			BC.set(x7[i]);
			NN.set(x8[i]);
			M.set(x9[i]);
			Random r = new Random();
			Float b = r.nextFloat();
			// bx.set(b);
			try {

				double result = arg0.execute_float(0, noargs);

				// if result is more than 1 and class is 1 OR result is less
				// than or equal to zero
				if ((result >= 1 && classTypes[i] == 1)) {
					hits1 = hits1 + 1.0f;
				}
				if ( (result <= 0 && classTypes[i] == 0)) {
					hits2 = hits2 + 1.0f;
				}
				/*
				 * error += Math.abs(result - y[i]);
				 *
				 * if (Double.isInfinite(error)) { return Double.MAX_VALUE; }
				 */

			}

			catch (ArithmeticException ex) {
				// This should not happen, some illegal operation was executed.
				// ------------------------------------------------------------
				// System.out.println("x = " + x[i].floatValue());
				System.out.println(arg0);
				throw ex;
			}

		}

		Double accuracy = ((hits1/236.0f)+(hits2/116.0f))/2.0f;

		if (error < 0.001) {
			error = 0.0d;
		}

		return error;
	}

}
