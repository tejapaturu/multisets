import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class DataGenerator
{
	public static void main (String[] args)
	{
		int smallRange = 3;	//Small range of numbers
		int mediumRange = 5;	//Medium range of numbers
		int largeRange = 10;	//Large range of numbers
		Random random = new Random();	//Used to randomise the output.

		String smallRangeTest = "";	//String for test for small range
		String mediumRangeTest = "";	//String for test for medium range
		String largeRangeTest = "";	//String for test for large range

		String addTest = ""; //String for testing add function
		String removeOnceTest = ""; //String for testing removeOnce function
		String removeAllTest = ""; //String for testing removeAll function
		String searchTest = ""; //String for testing search function

		String[] commands = {"A ", "RO ", "RA ", "S "};	//Array of commands.

		int smallRangeNumbers = 30;	//Number of outputs for small range test
		int mediumRangeNumbers = 80;	//Number of outputs for medium range test
		int largeRangeNumbers = 210;	//Number of outputs for large range test

		//small range test.
		for(int i=0; i < smallRangeNumbers; i++)
		{
			if(i < smallRangeNumbers/3) // the first 1/3rd of the commands are just add.
			{
				smallRangeTest += commands[0] + random.nextInt(smallRange) + "\n";
				smallRangeTest += commands[0] + random.nextInt(smallRange) + "\n";
			}

			else if(i >= smallRangeNumbers/3 && i <= (2*smallRangeNumbers)/3) // the second 1/3rd of the code adds a node and removes the node.
			{
				smallRangeTest += commands[0] + random.nextInt(smallRange) + "\n";
				smallRangeTest += commands[1] + random.nextInt(smallRange) + "\n";
			}

			else // the last 1/3rd of the code removes one instance and removes all instances of an element.
			{
				smallRangeTest += commands[1] + random.nextInt(smallRange) + "\n";
				smallRangeTest += commands[2] + random.nextInt(smallRange) + "\n";
			}
		}

		//medium range test.
		for(int i=0; i < mediumRangeNumbers; i++)
		{
			if(i%2 == 0) // All even numbers add.
			{
				mediumRangeTest += commands[0] + random.nextInt(mediumRange) + "\n";
				mediumRangeTest+= commands[0] + random.nextInt(mediumRange) + "\n";
			}

			else // all odd ninstances of the code removes one instance and search for an element.
			{
				mediumRangeTest += commands[1] + random.nextInt(mediumRange) + "\n";
				mediumRangeTest += commands[3] + random.nextInt(mediumRange) + "\n";
			}
		}

		//large range test.
		for(int i=0; i < largeRangeNumbers; i++)
		{
			if(i < largeRangeNumbers/2) // the first 1/2 of the commands are just add.
			{
				largeRangeTest += commands[0] + random.nextInt(largeRange) + "\n";
				largeRangeTest+= commands[0] + random.nextInt(largeRange) + "\n";
			}

			else // the last 1/2 of the code removes one instance and removes all instances of an element and search for an element.
			{
				largeRangeTest += commands[1] + random.nextInt(largeRange) + "\n";
				largeRangeTest += commands[2] + random.nextInt(largeRange) + "\n";
				largeRangeTest += commands[3] + random.nextInt(largeRange) + "\n";
			}
		}

		//add test
		for(int i=0; i < mediumRangeNumbers; i++)
		{
			addTest += commands[0] + random.nextInt(mediumRange) + "\n";
		}

		//Remove once test
		for(int i=0; i < mediumRangeNumbers; i++)
		{
			if(i < mediumRangeNumbers/2) // The first half adds
			{
				removeOnceTest += commands[0] + random.nextInt(mediumRange) + "\n";
			}

			else // The second half removes Once.
			{
				removeOnceTest += commands[1] + random.nextInt(mediumRange) + "\n";
			}
		}

		//Remove All test
		for(int i=0; i < mediumRangeNumbers; i++)
		{
			if(i < mediumRangeNumbers/2) // The first half adds
			{
				removeAllTest += commands[0] + random.nextInt(mediumRange) + "\n";
			}

			else // The second half removes All.
			{
				removeAllTest += commands[2] + random.nextInt(mediumRange) + "\n";
			}
		}

		//Search test
		for(int i=0; i < mediumRangeNumbers; i++)
		{
			if(i < mediumRangeNumbers/2) // The first half adds
			{
				searchTest += commands[0] + random.nextInt(mediumRange) + "\n";
			}

			else // The second half search.
			{
				searchTest += commands[3] + random.nextInt(mediumRange) + "\n";
			}
		}

		// Check for any exceptions
		try
		{
			BufferedWriter writer1 = new BufferedWriter(new FileWriter("smallRangeTest.txt"));
			BufferedWriter writer2 = new BufferedWriter(new FileWriter("mediumRangeTest.txt"));
			BufferedWriter writer3 = new BufferedWriter(new FileWriter("largeRangeTest.txt"));
			BufferedWriter writer4 = new BufferedWriter(new FileWriter("addTest.txt"));
			BufferedWriter writer5 = new BufferedWriter(new FileWriter("removeOnceTest.txt"));
			BufferedWriter writer6 = new BufferedWriter(new FileWriter("removeAllTest.txt"));
			BufferedWriter writer7 = new BufferedWriter(new FileWriter("searchTest.txt"));

			writer1.write(smallRangeTest);	// Outputs small range test
			writer1.close();

			writer2.write(mediumRangeTest);	// Outputs medium range test
			writer2.close();

			writer3.write(largeRangeTest);	// Outputs large range test
			writer3.close();

			writer4.write(addTest);			// Outputs add  test
			writer4.close();

			writer5.write(removeOnceTest);	// Outputs remove once test
			writer5.close();

			writer6.write(removeAllTest);	// Outputs remove all test
			writer6.close();

			writer7.write(searchTest);		// Outputs search test
			writer7.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
