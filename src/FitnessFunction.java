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

	private Double computeRawFitness(IGPProgram arg0) {
		// TODO Auto-generated method stub

		double error = 0.0f;
		float hits1 = 0.0f;
		float hits2 = 0.0f;
		float hits3 = 0.0f;
		Object[] noargs = new Object[0];

		for (int i = 0; i < 349; i++) {
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
				//System.out.println(x1[i] + " " + x2[i] + " " + x3[i] + " " + x4[i] + " " + x5[i] + " " + x6[i] + " "  + x7[i] +  " " + x8[i] + " " + x9[i] );

				double result = arg0.execute_float(0, noargs);

				int roundedresult = (int) Math.round(result);

				// if result is more than 1 and class is 1 OR result is less

				// than or equal to zero
				//System.out.println(classTypes[10]);
				//System.out.println(classTypes[i]);

				if ((result <= 0 && classTypes[i] == 2.0f)) {
					hits1+=1;

				}


				else if (result > 0 && classTypes[i] == 4.0f) {
					hits2+=1;

				}

			}

			catch (ArithmeticException ex) {
				// This should not happen, some illegal operation was executed.
				// ------------------------------------------------------------
				// System.out.println("x = " + x[i].floatValue());
				System.out.println(arg0);
				throw ex;
			}

		}

		// precision and recall rate
		// float precision = hits1 / (hits1+ hits2);
		// float recall = hits1/ (hits1 + hits3);

		// use 0 if it's NaN
		// precision = Float.isNaN(precision) ? 0 : precision;
		// recall = Float.isNaN(recall) ? 0 : recall;

		// return 100 * (2.0f * precision * recall) / (precision + recall);

		Float accuracy = ((hits1/229.0f)+(hits2/120.0f))/2.0f;
		error = Math.abs(1.0f - accuracy);

		if (error < 0.001) {
			error = 0.0d;
		}
		//System.out.println("accuracy is: "+ hits1);
		//System.out.println("accuracy is: "+ hits2);


		return error;
	}

}
