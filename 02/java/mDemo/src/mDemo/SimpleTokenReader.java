package mDemo;

import java.util.List;

public class SimpleTokenReader {
	List<SimpleToken> tokens = null;
    int pos = 0;

    public SimpleTokenReader(List<SimpleToken> tokens) {
        this.tokens = tokens;
    }

    
    public SimpleToken read() {
        if (pos < tokens.size()) {
            return tokens.get(pos++);
        }
        return null;
    }

    
    public SimpleToken peek() {
        if (pos < tokens.size()) {
            return tokens.get(pos);
        }
        return null;
    }

    
    public void unread() {
        if (pos > 0) {
            pos--;
        }
    }

    
    public int getPosition() {
        return pos;
    }

    
    public void setPosition(int position) {
        if (position >=0 && position < tokens.size()){
            pos = position;
        }
    }
}
