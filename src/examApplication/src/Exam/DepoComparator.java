package examApplication.src.Exam;

import java.util.Comparator;

public class DepoComparator implements Comparator<DepoBase> {

	public int compare(DepoBase depo1, DepoBase depo2) {
		if (depo1.getInterest() > depo2.getInterest()) {
			return 1;
		}
		if (depo1.getInterest() < depo2.getInterest()) {
			return -1;
		}
		return 0;
	}

}