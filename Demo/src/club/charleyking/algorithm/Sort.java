package club.charleyking.algorithm;

public class Sort {
	public static void bubbleSort(int[] arr) {
		int length = arr.length;
		for (int j = 0; j < length; j++) {
			for (int i = 0; i < length - j - 1; i ++) {
				if (arr[i] >arr[i+1]) {
					int temp = arr[i+1];
					arr[i+1] = arr[i];
					arr[i] = temp;
				}
			}
		}
	}
	public static void swap() {
	}
}
