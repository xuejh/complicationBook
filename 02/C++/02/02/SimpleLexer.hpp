//
//  SimpleLexer.hpp
//  02
//
//  Created by XJH on 2020/6/8.
//  Copyright © 2020 XJH. All rights reserved.
//

#ifndef SimpleLexer_hpp
#define SimpleLexer_hpp

#include <stdio.h>
#include <iostream>
#include <string>
#include <vector>
#include "Token.hpp"
#include "TokenType.hpp"
#include "TokenReader.hpp"

using namespace std;

/**
 * 有限状态机的各种状态。
 */
//struct  DfaState{
//    const int Initial = 0;
//    const int If = 1;
//    const int Id_if1 = 2;
//    const int Id_if2 = 3;
//    const int Id_else1 = 4;
//    const int Id_else2 = 5;
//    const int Id_else3 = 6;
//    const int Id_else4 = 7;
//    const int Int = 8;
//    const int Id_int1 = 9;
//    const int Id_int2 = 10;
//    const int Id_int3 = 11;
//    const int Id = 12;
//    const int GT = 13;
//    const int GE = 14;
//    const int Assignment = 15;
//    const int Plus = 16;
//    const int Minus = 17;
//    const int Star = 18;
//    const int Slash = 19;
//    const int SemiColon = 20;
//    const int LeftParen = 21;
//    const int RightParen = 22;
//    const int IntLiteral = 23;
//};

enum DfaState {
    Initial,

    If, Id_if1, Id_if2, Else, Id_else1, Id_else2, Id_else3, Id_else4, Int, Id_int1, Id_int2, Id_int3, Id, GT, GE,

    Assignment,

    Plus, Minus, Star, Slash,

    SemiColon,
    LeftParen,
    RightParen,

    IntLiteral
};

class  SimpleToken: Token{
private:
    TokenType::TokenType type;
    string text;
    
public:
    TokenType::TokenType getType() ;
    void setType(TokenType::TokenType mtype) ;
    string getText() ;
    void setText(string str) ;
};


class  SimpleTokenReader: TokenReader{
private:
    vector<Token> tokens;
    int pos = 0;
    
    
public:
    SimpleTokenReader(vector<Token> mtokens);
    SimpleTokenReader() = default;
    Token read() override;
    Token peek() override;
    void unread() override;
    int getPosition() override;
    void setPosition(int position) override;
};


/**
* 一个简单的手写的词法分析器。
* 能够为后面的简单计算器、简单脚本语言产生Token。
*/
class SimpleLexer {
private:
    string tokenText;
    vector<SimpleToken> tokens;
    SimpleToken token;
    bool isAlpha(int ch);
    bool isDigit(int ch);
    bool isBlank(int ch);
    /**
        * 有限状态机进入初始状态。
        * 这个初始状态其实并不做停留，它马上进入其他状态。
        * 开始解析的时候，进入初始状态；某个Token解析完毕，也进入初始状态，在这里把Token记下来，然后建立一个新的Token。
        * @param ch
        * @return
        */
    DfaState initToken(int ch);
    
    
    
public:
    /**
    * 解析字符串，形成Token。
    * 这是一个有限状态自动机，在不同的状态中迁移。
    * @param code
    * @return
    */
    SimpleTokenReader tokenize(string code);
    
};


#endif /* SimpleLexer_hpp */
