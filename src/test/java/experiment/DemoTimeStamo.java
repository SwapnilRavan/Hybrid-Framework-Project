package experiment;

import java.util.Date;

public class DemoTimeStamo {

	public static void main(String[] args) {
		Date date=new Date();
		System.out.println(date.toString().replace(" ","_").replace(":", "_").replace("-", "_"));
		//use Date class FOR CHANGE DATE AND TIME WISE
	}

}
