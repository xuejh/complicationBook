package mDemo;



public class Operation {
	
	//是否是字母
    public static boolean isAlpha(int ch) {
        return ch >= 'a' && ch <= 'z' || ch >= 'A' && ch <= 'Z';
    }

    //是否是数字
    public static boolean isDigit(int ch) {
        return ch >= '0' && ch <= '9';
    }

    //是否是空白字符
    public static boolean isBlank(int ch) {
        return ch == ' ' || ch == '\t' || ch == '\n';
    }
    
    /**
     * 打印所有的Token
     * @param tokenReader
     */
    public static void dump(SimpleTokenReader tokenReader){
        System.out.println("text\ttype");
        SimpleToken token = null;
        while ((token= tokenReader.read())!=null){
            System.out.println(token.text+"\t\t"+token.type);
        }
    }
    
}