import java.io.CharArrayReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;




public class main {
	
	
	
	
public static void main(String args[]) {
		
	System.out.println("开始：");
	//String script = "int age = 40";
	//String script = "intA  = 10";
	String script = "2+3*5";
	
	Lexer lexer = new Lexer();
	lexer.parseScript(script);
	lexer.dump();
	
}



}
