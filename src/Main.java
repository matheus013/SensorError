import adjuster.Appraiser;
import adjuster.Training;

public class Main {
	public static void main(String[] args) {
		String[] nodes = { "11", "12" };
		Training training = new Training(nodes);
		training.train("11", "sensirion_temp");
		training.train("12", "sensirion_temp");
		Appraiser appraiser = training.getApp();

//		System.out.println(appraiser.coefficient);
	}
}
