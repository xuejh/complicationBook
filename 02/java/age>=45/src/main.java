import java.io.CharArrayReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;




public class main {
	
	
	
	
public static void main(String args[]) {
		
	System.out.println("\n开始：");
	String script = "age >= 45";
	
	Lexer lexer = new Lexer();
	lexer.parseScript(script);
	lexer.dump();
}



}
