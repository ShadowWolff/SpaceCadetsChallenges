package defaultPackage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import defaultPackage.InternetPageScanner;

public class IOInterface {

	private static String teacher_name;
	
	private static Scanner create_scanner() {
		
		Scanner scanner = new Scanner(System.in);
		return scanner;
		
	}
	
	private static String get_username() {
		Scanner scan = create_scanner();
		
		System.out.println("Enter username : ");
		String user_id = scan.nextLine();
		
		// check wheter the user_id is valid
		
		return user_id;
	}
	
	private static void transform_username_into_full_name() throws IOException{
		String name_and_rank = InternetPageScanner.return_name();
		teacher_name = name_and_rank.substring(name_and_rank.indexOf(' ')+1,name_and_rank.length());
		System.out.println(teacher_name);
	}
	
	private static void  get_related_people_for_user() throws IOException {
		ArrayList<Element> related_people = InternetPageScanner.return_related_prople();
		
		System.out.println( "The related people for " + teacher_name );
		
		// go through each of the tables
		for( int i = 0; i < related_people.size(); i++ ) {
			
			Element table = related_people.get(i);
			ArrayList<Element> rows = table.getElementsByTag("tr");
			
			// go through each row
			for(int j = 0; j < rows.size(); j++) {
				Element row = rows.get(j);
				String name = row.getElementsByTag("td").get(2).text();
				String function = row.getElementsByTag("td").get(6).text();
				System.out.println( name + " " + function );
			}
			
		}
		
	}
	
	public static void main(String[] args) throws IOException{
		
		String user = get_username();
		InternetPageScanner.set_user_profile(user);	
		
	    transform_username_into_full_name();
		get_related_people_for_user();
		
	}

}
