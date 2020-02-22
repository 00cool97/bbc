package bf.orangemoney.web;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;

public class Formatage {
	
	public static String method(long bigDecimal) {
		DecimalFormatSymbols symbols = new DecimalFormatSymbols();
	     symbols.setDecimalSeparator(',');
	     symbols.setGroupingSeparator(' ');
	     
	     NumberFormat goodNumberFormat1 = new DecimalFormat("#,##0.00#", symbols);
	    goodNumberFormat1.setMaximumFractionDigits(0);		 ;
	     return goodNumberFormat1.format(bigDecimal);
	}

}
