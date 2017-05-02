import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import org.jgap.gp.GPProblem;
import org.jgap.gp.impl.DeltaGPFitnessEvaluator;
import org.jgap.gp.impl.FitnessProportionateSelection;
import org.jgap.gp.impl.GPConfiguration;
import org.jgap.gp.impl.GPGenotype;
import org.jgap.gp.impl.TournamentSelector;
import org.jgap.gp.terminal.Variable;
import org.jgap.impl.WeightedRouletteSelector;

public class Regression {
	static Float[] x = new Float[20];
	static Float[] y = new Float[20];
	static Float[][] parameters = new Float[9][699];
	public static Variable vx;

	public static void main(String[] args) throws Exception {
		LoadData();
		GPConfiguration config = new GPConfiguration();
		config.setGPFitnessEvaluator(new DeltaGPFitnessEvaluator());
		config.setMaxInitDepth(8);
		config.setMaxCrossoverDepth(4);
		config.setPopulationSize(1000);
		config.setSelectionMethod(new TournamentSelector(2));

		RegressionGenoType gp = new RegressionGenoType(config);
		gp.setVariable();
		config.setFitnessFunction(new FitnessFunction(parameters, gp.getVariableCT(), gp.getVariableUSz(),
				gp.getVariableUShp(), gp.getVariableMA(), gp.getVariableSESz(), gp.getVariableBN(), gp.getVariableBC(),
				gp.getVariableNN(), gp.getVariableM()));

		GPGenotype gptype = gp.create();
		gptype.setVerboseOutput(true);
		gptype.evolve(1200);
		gptype.outputSolution(gptype.getAllTimeBest());
		// gp.showTree(gptype.getAllTimeBest(), "mathproblem_best.png");

	}

	private static void LoadData() throws FileNotFoundException {

		File file1 = new File("breast-cancer-wisconsin.data");
		int count = 0;
		Scanner input1 = new Scanner(file1);
		int missing = -1;
		while (input1.hasNext()) {
			String line = input1.nextLine();
			List<String> data = Arrays.asList(line.split(","));
			if (data.contains("?")) {
				missing = data.indexOf("?");
			}
			for (int i = 0; i < 9; i++) {
				if (missing == i) {
					parameters[missing][count] = -1.0f;
				} else {

					parameters[i][count] = Float.valueOf(data.get(i+1));
				}
				System.out.println(parameters[i][count]);

			}
            
			count++;
		}

	}

}
