
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import org.jgap.*;
import org.jgap.gp.*;
import org.jgap.gp.function.*;
import org.jgap.gp.function.RandomGenerator;
import org.jgap.gp.function.statistics.Skewness;
import org.jgap.gp.impl.*;
import org.jgap.gp.terminal.*;

public class RegressionGenoType extends GPProblem {

	public Variable vx;
	private Variable CT;
	private Variable USz;
	private Variable UShp;
	private Variable MA;
	private Variable SESz;
	private Variable BN;
	private Variable BC;
	private Variable NN;
	private Variable M;
	private Variable bx;

	public RegressionGenoType(GPConfiguration a_conf) throws InvalidConfigurationException {
		super(a_conf);
	}

	public void setVariable() throws InvalidConfigurationException {
		this.CT = Variable.create(getGPConfiguration(), "CT", CommandGene.FloatClass);
		this.USz = Variable.create(getGPConfiguration(), "USz", CommandGene.FloatClass);
		this.UShp = Variable.create(getGPConfiguration(), "UShp", CommandGene.FloatClass);
		this.MA = Variable.create(getGPConfiguration(), "MA", CommandGene.FloatClass);
		this.SESz = Variable.create(getGPConfiguration(), "SESz", CommandGene.FloatClass);
		this.BN = Variable.create(getGPConfiguration(), "BN", CommandGene.FloatClass);
		this.BC = Variable.create(getGPConfiguration(), "BC", CommandGene.FloatClass);
		this.NN = Variable.create(getGPConfiguration(), "NN", CommandGene.FloatClass);
		this.M = Variable.create(getGPConfiguration(), "M", CommandGene.FloatClass);


		// this.bx = Variable.create(getGPConfiguration(), "B",
		// CommandGene.FloatClass);
		if (vx == null) {
			System.out.println("not valid");

		}

	}

	public Variable getVariableCT() {

		return this.CT;
	}

	public Variable getVariableUSz() {

		return this.USz;
	}

	public Variable getVariableUShp() {

		return this.UShp;
	}

	public Variable getVariableMA() {

		return this.MA;
	}

	public Variable getVariableBN() {

		return this.BN;
	}

	public Variable getVariableSESz() {

		return this.SESz;
	}


	public Variable getVariableBC() {

		return this.BC;
	}

	public Variable getVariableNN() {

		return this.NN;
	}
	public Variable getVariableM() {

		return this.M;
	}







	public GPGenotype create() throws InvalidConfigurationException {

		GPConfiguration a_conf = getGPConfiguration();

		Class[] types = { CommandGene.FloatClass };
		Class[][] argTypes = { {} };

		// TODO Auto-generated method stub

		CommandGene[][] nodeSets = { { vx,
				// bx,
			    new Multiply(a_conf, CommandGene.FloatClass),
		        new Multiply3(a_conf, CommandGene.FloatClass),
		        new Divide(a_conf, CommandGene.FloatClass),
		       // new Sine(a_conf, CommandGene.FloatClass),
		        //new Cosine(a_conf, CommandGene.FloatClass),
		       // new Tangent(a_conf, CommandGene.FloatClass),
		      //  new Exp(a_conf, CommandGene.FloatClass),
		       // new RandomGenerator(a_conf, CommandGene.FloatClass),
		        new Subtract(a_conf, CommandGene.FloatClass),
		        new Subtract(a_conf, CommandGene.FloatClass),
		        new Add(a_conf, CommandGene.FloatClass),
		        new Add3(a_conf, CommandGene.FloatClass),
		        new Add4(a_conf, CommandGene.FloatClass),
		      //  new Pow(a_conf, CommandGene.FloatClass),
		        new Log(a_conf, CommandGene.FloatClass),
		        //new Increment(a_conf, CommandGene.FloatClass),
		      //  new Round(a_conf, CommandGene.FloatClass),
		        new Abs(a_conf, CommandGene.FloatClass),
		       // new Mean(a_conf, CommandGene.LongClass, 2, 0, 10),
		       // new ArcCosine(a_conf, CommandGene.FloatClass),
		       // new ArcSine(a_conf, CommandGene.FloatClass),
		       // new ArcTangent(a_conf, CommandGene.FloatClass),
				// Use terminal with possible value from 2.0 to 10.0
				// decimal
				new Terminal(a_conf, CommandGene.FloatClass, 0.0d, 0.5d, false),
				} };
		if (vx == null) {
			System.out.println("vx is empty");

		} else {
			System.out.println("vx is not empty");
		}
		return GPGenotype.randomInitialGenotype(a_conf, types, argTypes, nodeSets, 100, true);
	}

}
