import java.io.CharArrayReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;



public class Lexer {
	
	private StringBuffer tokenText = null; //临时存储tokenText
	private List<Token> tokens = null; //Token数组
	private Token token = null; //临时token
	
	//初始化有限自动机
	private DfaState initToken(char ch) {
	    if (tokenText.length() > 0) {
	        token.text = tokenText.toString();
	        tokens.add(token);

	        tokenText = new StringBuffer();
	        token = new Token();
	    }

	    DfaState newState = DfaState.Initial;
	    if (isAlpha(ch)) {              //第一个字符是字母
	    	
	    	if(ch == 'i') {
	    		newState = DfaState.Id_int1; //进入Id状态
	    	}else {
	    		newState = DfaState.Id; //进入Id状态
	    	}
	    	
	    	
	        token.type = TokenType.Identifier;
	        tokenText.append(ch);
	        
	    } else if (isDigit(ch)) {       //第一个字符是数字
	        newState = DfaState.IntLiteral;
	        token.type = TokenType.IntLiteral;
	        tokenText.append(ch);
	    } else if (ch == '>') {         //第一个字符是>
	        newState = DfaState.GT;
	        token.type = TokenType.GT;
	        tokenText.append(ch);
	    }else if (ch == '=') {
            newState = DfaState.Assignment;
            token.type = TokenType.Assignment;
            tokenText.append(ch);
        }else {
	        newState = DfaState.Initial; // skip all unknown patterns
	    }
	    return newState;
	}


	//解析脚本
	public  void parseScript(String code) {
		
		//初始化数据
		tokens = new ArrayList<Token>();
	    CharArrayReader reader = new CharArrayReader(code.toCharArray());
	    tokenText = new StringBuffer();
	    token = new Token();
	    int ich = 0;
	    char ch = 0;
	    
	    DfaState state = DfaState.Initial;
	    try {
	    	while ((ich = reader.read()) != -1) {
	    		ch = (char) ich;
	    		switch (state) {
	    		case Initial:{
	    			state = initToken(ch);          //重新确定后续状态
	                break;
	    		}
	    		case Id:
	                if (isAlpha(ch) || isDigit(ch)) {
	                    tokenText.append(ch);       //保持标识符状态
	                } else {
	                    state = initToken(ch);      //退出标识符状态，并保存Token
	                }
	                break;
	    		case Id_int1:
	    			if(ch == 'n') {
	    				state = DfaState.Id_int2;
	    				tokenText.append(ch);
	    			}else if (isAlpha(ch) || isDigit(ch)) {
	    				state = DfaState.Id;
	                    tokenText.append(ch);       //保持标识符状态
	                } else {
	                    state = initToken(ch);      //退出标识符状态，并保存Token
	                }
	                break;
	    		case Id_int2:
	    			if(ch == 't') {
	    				state = DfaState.Id_int3;
	    				tokenText.append(ch);
	    			}else if (isAlpha(ch) || isDigit(ch)) {
	    				state = DfaState.Id;
	                    tokenText.append(ch);       //保持标识符状态
	                } else {
	                    state = initToken(ch);      //退出标识符状态，并保存Token
	                }
	                break;
	    		case Id_int3:
	    			if(isBlank(ch)) {
	    				token.type = TokenType.Int;
	    				state = initToken(ch);
	    			}else if (isAlpha(ch) || isDigit(ch)) {
	    				state = DfaState.Id;
	                    tokenText.append(ch);       //保持标识符状态
	                } else {
	                    state = initToken(ch);      //退出标识符状态，并保存Token
	                }
	                break;
	    		case GT:
	                if (ch == '=') {
	                    token.type = TokenType.GE;  //转换成GE
	                    state = DfaState.GE;
	                    tokenText.append(ch);
	                } else {
	                    state = initToken(ch);      //退出GT状态，并保存Token
	                }
	                break;
	    		case GE:
	    		case Assignment:
	    			state = initToken(ch);      //退出GE状态，并保存Token
	    			break;
	    		case IntLiteral:
	                if (isDigit(ch)) {
	                    tokenText.append(ch);       //继续保持在数字字面量状态
	                } else {
	                    state = initToken(ch);      //退出当前状态，并保存Token
	                }
	                break;
	    		}
	    		  
	        }
	    	
	    	// 把最后一个token送进去
            if (tokenText.length() > 0) {
                initToken(ch);
            }
	    }catch (IOException e) {
	        e.printStackTrace();
	    }
	    
	}

	//是否是字母
	public  boolean isAlpha(int ch) {
	    return ch >= 'a' && ch <= 'z' || ch >= 'A' && ch <= 'Z';
	}

	//是否是数字
	public  boolean isDigit(int ch) {
	    return ch >= '0' && ch <= '9';
	}

	//是否是空白字符
	public  boolean isBlank(int ch) {
	    return ch == ' ' || ch == '\t' || ch == '\n';
	}

	/**
	 * 打印所有的Token
	 * @param tokenReader
	 */
	public  void dump(){
	    Token token = null;
	    int pos = 0;
	    while (pos < tokens.size()){
	    	token= tokens.get(pos);
	        System.out.println(token.type+"\t"+token.text);
	        pos ++;
	    }
	}
}
