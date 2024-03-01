package Utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

public class TerminalProcessMain {
	private static boolean isWindows = System.getProperty("os.name").toLowerCase().startsWith("windows");

	public static void main(String[] args) throws Exception {
		File location = new File("C:\\Users\\thrah\\NewWorkSpace\\SauceDemoProject");

		// runCommand(location, "ping localhost"); // for Mac(Linux based OS) users list
		// files
		runCommand(location, "mvn test");
		runCommand(location, "allure serve allure-results");
		// runCommand(location, "dir"); // For Windows users list files
	}

	public static void runCommand(File whereToRun, String command) throws Exception {
		System.out.println("Running in: " + whereToRun);
		System.out.println("Command: " + command);

		ProcessBuilder builder = new ProcessBuilder();
		builder.directory(whereToRun);

		if (isWindows) {
			builder.command("cmd.exe", "/c", command);
		} else {
			builder.command("sh", "-c", command);
		}

		Process process = builder.start();

		OutputStream outputStream = process.getOutputStream();
		InputStream inputStream = process.getInputStream();
		InputStream errorStream = process.getErrorStream();

		printStream(inputStream);
		printStream(errorStream);

		boolean isFinished = process.waitFor(30, TimeUnit.SECONDS);
		outputStream.flush();
		outputStream.close();

		if (!isFinished) {
			process.destroyForcibly();
		}
	}

	private static void printStream(InputStream inputStream) throws IOException {
		try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream))) {
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				System.out.println(line);
			}

		}
	}
}
