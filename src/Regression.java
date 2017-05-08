import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import org.jgap.gp.GPProblem;
import org.jgap.gp.IGPProgram;
import org.jgap.gp.impl.DeltaGPFitnessEvaluator;
import org.jgap.gp.impl.FitnessProportionateSelection;
import org.jgap.gp.impl.GPConfiguration;
import org.jgap.gp.impl.GPGenotype;
import org.jgap.gp.impl.TournamentSelector;
import org.jgap.gp.terminal.Variable;
import org.jgap.impl.WeightedRouletteSelector;


public class Regression {
	static Float[][] parameters = new Float[10][349];
	public static Variable vx;
	private static Float[][] testSamples = new Float[10][350];

	public static void main(String[] args) throws Exception {
		LoadData();
		LoadData2();
		GPConfiguration config = new GPConfiguration();
		config.setGPFitnessEvaluator(new DeltaGPFitnessEvaluator());
		config.setMaxInitDepth(4);
		config.setMaxCrossoverDepth(8);
		config.setPopulationSize(800);
		// config.setSelectionMethod(new TournamentSelector(2));

		RegressionGenoType gp = new RegressionGenoType(config);
		gp.setVariable();
		config.setFitnessFunction(new FitnessFunction(parameters, gp.getVariableCT(), gp.getVariableUSz(),
				gp.getVariableUShp(), gp.getVariableMA(), gp.getVariableSESz(), gp.getVariableBN(), gp.getVariableBC(),
				gp.getVariableNN(), gp.getVariableM()));

		GPGenotype gptype = gp.create();
		gptype.setVerboseOutput(true);
		gptype.evolve(200);
		gptype.outputSolution(gptype.getAllTimeBest());
		IGPProgram best = gptype.getFittestProgramComputed();
		IGPProgram bestProgramme = gptype.getAllTimeBest();

		 float accuracyTraining = checkAccuracy(bestProgramme);
		System.err.println("================================== BEST ACCURACY IS: " + accuracyTraining);
		System.out.println("size of function is" + best.size());
		System.out.println(best.toStringNorm(1));
		// gp.showTree(gptype.getAllTimeBest(), "mathproblem_best.png");

	}


	private static void LoadData() throws FileNotFoundException {

		File file1 = new File("training_data_set.txt");
		int count = 0;
		Scanner input1 = new Scanner(file1);

		while (input1.hasNext()) {

			String line = input1.nextLine();
			List<String> data = new ArrayList<String>();
			data.addAll(Arrays.asList(line.split(",")));
			data.remove(0);

			for (int i = 0; i < 10; i++) {


					parameters[i][count] = Float.valueOf(data.get(i));



			}

			count++;

		}

		//List<Float[]> list = Arrays.asList(parameters);
		//Collections.shuffle(list);
		//parameters = list.stream().toArray(Float[][]::new);

	}


	private static void LoadData2() throws FileNotFoundException {

		File file1 = new File("test_data_set.txt");
		int count = 0;
		Scanner input1 = new Scanner(file1);

		while (input1.hasNext()) {

			String line = input1.nextLine();
			List<String> data = new ArrayList<String>();
			data.addAll(Arrays.asList(line.split(",")));
			data.remove(0);

			for (int i = 0; i < 9; i++) {

				if (i < 8) {
					testSamples[i][count] = Float.valueOf(data.get(i));

				} else {

					Float classType = ((Float.valueOf(data.get(i)) == 2) ? 1.0f : 0.0f);
					testSamples[i][count]
							= classType;
				}

			}

			count++;

		}

		List<Float[]> list = Arrays.asList(testSamples);
		Collections.shuffle(list);
		testSamples = list.stream().toArray(Float[][]::new);

	}

	private static float checkAccuracy(IGPProgram gpProgram) {
        // 9 variables
        Variable feature1 = gpProgram.getGPConfiguration().getVariable("CT");
        Variable feature2 = gpProgram.getGPConfiguration().getVariable("USz");
        Variable feature3 = gpProgram.getGPConfiguration().getVariable("UShp");
        Variable feature4 = gpProgram.getGPConfiguration().getVariable("MA");
        Variable feature5 = gpProgram.getGPConfiguration().getVariable("SESz");
        Variable feature6 = gpProgram.getGPConfiguration().getVariable("BN");
        Variable feature7 = gpProgram.getGPConfiguration().getVariable("BC");
        Variable feature8 = gpProgram.getGPConfiguration().getVariable("NN");
        Variable feature9 = gpProgram.getGPConfiguration().getVariable("M");

        // accuracy is measured by the percentage of correctly classified instances
        int totalNum = 140;
        float numCorrect = 0.0f;

        // check the performance against every instance from training set
        for (int i = 0; i < 140; i++) {
            feature1.set( (float) testSamples[0][i]);
            feature2.set( (float) testSamples[1][i]);
            feature3.set((float) testSamples[2][i]);
            feature4.set((float) testSamples[3][i]);
            feature5.set((float) testSamples[4][i]);
            feature6.set((float) testSamples[5][i]);
            feature7.set((float) testSamples[6][i]);
            feature8.set((float) testSamples[7][i]);
            feature9.set((float) testSamples[8][i]);

            double result = gpProgram.execute_float(0, null);

            // count how many correctly classified instances
            if ((result >= 0 &&  testSamples[9][i] == 0 )
                    || (result < 0 &&  testSamples[9][i] == 1)) {
                numCorrect++;
            }
        }

        return numCorrect / totalNum;
    }

}
