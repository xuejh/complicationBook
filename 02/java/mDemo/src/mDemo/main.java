package mDemo;



public class main {
	
	public static void main(String args[]) {
		
		SimpleLexer lexer = new SimpleLexer();

        String script = "int age = 45;";
        System.out.println("parse :" + script);
        SimpleTokenReader tokenReader = lexer.tokenize(script);
        Operation.dump(tokenReader);

        //测试inta的解析
        script = "inta age = 45;";
        System.out.println("\nparse :" + script);
        tokenReader = lexer.tokenize(script);
        Operation.dump(tokenReader);

        //测试in的解析
        script = "in age = 45;";
        System.out.println("\nparse :" + script);
        tokenReader = lexer.tokenize(script);
        Operation.dump(tokenReader);

        //测试>=的解析
        script = "age >= 45;";
        System.out.println("\nparse :" + script);
        tokenReader = lexer.tokenize(script);
        Operation.dump(tokenReader);

        //测试>的解析
        script = "age > 45;";
        System.out.println("\nparse :" + script);
        tokenReader = lexer.tokenize(script);
        Operation.dump(tokenReader);
	}
}
