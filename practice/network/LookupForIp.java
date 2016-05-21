import java.net.*;

public class LookupForIp {
	public static void main(String[] args) {
		try {
			if (args.length == 0) {
				System.out.println("Call with one parameter that" +
					"specifies the host to lookup.");
			} else {
				InetAddress address = InetAddress.getByName(args[0]);
				System.out.println(address);
			}
		} catch (Exception e) {
			System.out.println("Could not find " + args[0]);
		}
	}
}