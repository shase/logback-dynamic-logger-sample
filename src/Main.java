import java.util.stream.Stream;
import ch.qos.logback.classic.Logger;

public class Main {
	
	public static void main(String...args) {
		// ここは外部からhost名が入ってくるのをイメージしてみてください。
		String[] path = {"/tmp/host1/app-%d{yyyyMMdd}.log","/tmp/host2/app-%d{yyyyMMdd}.log","/tmp/host3/app-%d{yyyyMMdd}.log"};
	
		Stream.of(path)
			.forEach(p -> {
				System.out.println(p);
				Logger logger = new GeneralLoggerFactory().getLogger("sample", p);
				logger.info("dynamic path");
			});
	}

}
