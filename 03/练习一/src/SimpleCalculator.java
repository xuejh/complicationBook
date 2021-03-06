import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SimpleCalculator {
	
	public static void main(String[] args) {
        SimpleCalculator calculator = new SimpleCalculator();

        //测试变量声明语句的解析
        String script = "int a = b+3;";
        System.out.println("解析变量声明语句: " + script);
        SimpleLexer lexer = new SimpleLexer();
        TokenReader tokens = lexer.tokenize(script);
        try {
            SimpleASTNode node = calculator.intDeclare(tokens);
            calculator.dumpAST(node,"");
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }

        
    }
	
	
	 private SimpleASTNode intDeclare(TokenReader tokens) throws Exception {
	        SimpleASTNode node = null;
	        Token token = tokens.peek();    //预读
	        if (token != null && token.getType() == TokenType.Int) {   //匹配Int
	            token = tokens.read();      //消耗掉int
	            if (tokens.peek().getType() == TokenType.Identifier) { //匹配标识符
	                token = tokens.read();  //消耗掉标识符
	                //创建当前节点，并把变量名记到AST节点的文本值中，这里新建一个变量子节点也是可以的
	                node = new SimpleASTNode(ASTNodeType.IntDeclaration, token.getText());
	                token = tokens.peek();  //预读
	                if (token != null && token.getType() == TokenType.Assignment) {
	                    tokens.read();      //消耗掉等号
	                    SimpleASTNode child = additive(tokens);  //匹配一个表达式
	                    if (child == null) {
	                        throw new Exception("invalide variable initialization, expecting an expression");
	                    }
	                    else{
	                        node.addChild(child);
	                    }
	                }
	            } else {
	                throw new Exception("variable name expected");
	            }

	            if (node != null) {
	                token = tokens.peek();
	                if (token != null && token.getType() == TokenType.SemiColon) {
	                    tokens.read();
	                } else {
	                    throw new Exception("invalid statement, expecting semicolon");
	                }
	            }
	        }
	        return node;
	    }
	 
	 
	 /**
	     * 语法解析：加法表达式
	     * @return
	     * @throws Exception
	     */
	    private SimpleASTNode additive(TokenReader tokens) throws Exception {
	    	SimpleASTNode child1 = primary(tokens);
	        SimpleASTNode node = child1;

	        Token token = tokens.peek();
	        if (child1 != null && token != null) {
	            if (token.getType() == TokenType.Plus) {
	                token = tokens.read();
	                SimpleASTNode child2 = primary(tokens);
	                if (child2 != null) {
	                    node = new SimpleASTNode(ASTNodeType.Additive, token.getText());
	                    node.addChild(child1);
	                    node.addChild(child2);
	                } else {
	                    throw new Exception("invalid multiplicative expression, expecting the right part.");
	                }
	            }
	        }
	        return node;
	    }
	    
	 /**
	     * 语法解析：基础表达式
	     * @return
	     * @throws Exception
	     */
	    private SimpleASTNode primary(TokenReader tokens) throws Exception {
	        SimpleASTNode node = null;
	        Token token = tokens.peek();
	        if (token != null) {
	            if (token.getType() == TokenType.IntLiteral) {
	                token = tokens.read();
	                node = new SimpleASTNode(ASTNodeType.IntLiteral, token.getText());
	            } else if (token.getType() == TokenType.Identifier) {
	                token = tokens.read();
	                node = new SimpleASTNode(ASTNodeType.Identifier, token.getText());
	            } 
	        }
	        return node;  //这个方法也做了AST的简化，就是不用构造一个primary节点，直接返回子节点。因为它只有一个子节点。
	    }
	    
	 /**
     * 一个简单的AST节点的实现。
     * 属性包括：类型、文本值、父节点、子节点。
     */
    private class SimpleASTNode implements ASTNode {
        SimpleASTNode parent = null;
        List<ASTNode> children = new ArrayList<ASTNode>();
        List<ASTNode> readonlyChildren = Collections.unmodifiableList(children);
        ASTNodeType nodeType = null;
        String text = null;


        public SimpleASTNode(ASTNodeType nodeType, String text) {
            this.nodeType = nodeType;
            this.text = text;
        }

        @Override
        public ASTNode getParent() {
            return parent;
        }

        @Override
        public List<ASTNode> getChildren() {
            return readonlyChildren;
        }

        @Override
        public ASTNodeType getType() {
            return nodeType;
        }

        @Override
        public String getText() {
            return text;
        }

        public void addChild(SimpleASTNode child) {
            children.add(child);
            child.parent = this;
        }

    }
    
    private void dumpAST(ASTNode node, String indent) {
        System.out.println(indent + node.getType() + " " + node.getText());
        for (ASTNode child : node.getChildren()) {
            dumpAST(child, indent + "\t");
        }
    }
}
