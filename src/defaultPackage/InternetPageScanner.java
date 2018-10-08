package defaultPackage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import org.jsoup.*;
import org.jsoup.Connection.Method;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class InternetPageScanner {
	
	private static String link;
	private static String secure_link;
	private static Document doc;
	private static Map<String, String> login_cookies;
	
	private static String username = "app1u18";
	private static String password = "HereShouldBeThePasswordButSomeoneStoleIt";

	public static void set_user_profile( String username ) throws IOException {
		link = "https://www.ecs.soton.ac.uk/people/dem";// + username;
		secure_link = "http://www.secure.ecs.soton.ac.uk/people/dem";// + username;
		doc = Jsoup.connect(link).get();
	}
	
	public static String return_name() {
		return doc.select("h1").text();
	}
	
	private static void create_intranet_session() throws IOException {
    	// create post request
		Connection.Response res = Jsoup.connect("https://secure.ecs.soton.ac.uk/login/now/index.php")
        .data("ecslogin_username", username, "ecslogin_password", password)
        .method(Method.POST)
        .execute();	
		
		// return the login session cookies
		 login_cookies = res.cookies();
	}
	
	public static ArrayList<Element> return_related_prople() throws IOException {
		
		create_intranet_session();
		
		Document related_people_document = Jsoup.connect(secure_link + "/related_people")
			      .cookies(login_cookies)
			      .get();
		
		// get all the tables in that web page
		// then ignore the first 4, as they are in the header
		Elements all_tables = related_people_document.getElementsByTag("table");
		ArrayList<Element> tables = new ArrayList<Element>();
		
		for( int i = 3; i < all_tables.size(); i++ ) {
			tables.add( all_tables.get(i));
		}
		
		return tables;
	}
	
}