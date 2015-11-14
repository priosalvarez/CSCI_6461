package co.com.csci.util;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class FileReaderUtil {

	public static void main(String[] args) {
		FileReaderUtil obj = new FileReaderUtil();
		System.out.println(obj.getFile("TextProgramTwo.txt"));		
	}

	public static String getFile(String fileName) {

		StringBuilder result = new StringBuilder("");

		//Get file from resources folder
		File file = new File(fileName);

		try (Scanner scanner = new Scanner(file)) {

			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				result.append(line).append("\n");
			}

			scanner.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

		return result.toString();

	}

}
