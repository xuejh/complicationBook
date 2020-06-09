//
//  SimpleLexer.cpp
//  02
//
//  Created by XJH on 2020/6/8.
//  Copyright © 2020 XJH. All rights reserved.
//

#include "SimpleLexer.hpp"

TokenType::TokenType SimpleToken::getType()
{
    return type;
}

void SimpleToken::setType(TokenType::TokenType mtype)
{
    type = mtype;
}

string SimpleToken::getText()
{
    return text;
}

void SimpleToken::setText(string str)
{
    text = str;
}


bool SimpleLexer::isAlpha(int ch)
{
    return ch >= 'a' && ch <= 'z' || ch >= 'A' && ch <= 'Z';
}

bool SimpleLexer::isDigit(int ch)
{
    return ch >= '0' && ch <= '9';
}

bool SimpleLexer::isBlank(int ch)
{
    return ch == ' ' || ch == '\t' || ch == '\n';
}

DfaState SimpleLexer::initToken(int ch)
{
    char s1[2] = {(char)ch, 0};
    if (tokenText.length() > 0) {
        token.setText(tokenText);
        tokens.push_back(token);
        
        tokenText = string();
        token =  SimpleToken();
    }
    
    DfaState newState = Initial;
    if (isAlpha(ch)) {              //第一个字符是字母
        if (ch == 'i') {
            newState = Id_int1;
        } else {
            newState = Id; //进入Id状态
        }
        token.setType(TokenType::Identifier);
        tokenText.append(s1);
    } else if (isDigit(ch)) {       //第一个字符是数字
        newState = IntLiteral;
        token.setType(TokenType::IntLiteral);
        tokenText.append(s1);
    } else if (ch == '>') {         //第一个字符是>
        newState = GT;
        token.setType(TokenType::GT);
        tokenText.append(s1);
    } else if (ch == '+') {
        newState = Plus;
        token.setType(TokenType::Plus);
        tokenText.append(s1);
    } else if (ch == '-') {
        newState = Minus;
        token.setType(TokenType::Minus);
        tokenText.append(s1);
    } else if (ch == '*') {
        newState = Star;
        token.setType(TokenType::Star);
        tokenText.append(s1);
    } else if (ch == '/') {
        newState = Slash;
        token.setType(TokenType::Slash);
        tokenText.append(s1);
    } else if (ch == ';') {
        newState = SemiColon;
        token.setType(TokenType::SemiColon);
        tokenText.append(s1);
    } else if (ch == '(') {
        newState = LeftParen;
        token.setType(TokenType::LeftParen);
        tokenText.append(s1);
    } else if (ch == ')') {
        newState = RightParen;
        token.setType(TokenType::RightParen);
        tokenText.append(s1);
    } else if (ch == '=') {
        newState = Assignment;
        token.setType(TokenType::Assignment);
        tokenText.append(s1);
    } else {
        newState = Initial; // skip all unknown patterns
    }
    return newState;
}

SimpleTokenReader SimpleLexer::tokenize(string code)
{
    tokens.clear();
    tokenText = "";
    token = SimpleToken();
    int ich = 0;
    char ch = 0;
    DfaState state = Initial;
    try {
        for(int i = 0; i < code.size(); i++)   {
            char s1[2] = {(char)i, 0};
            ch = (char)i;
            switch (state) {
            case Initial:
                state = initToken(ch);          //重新确定后续状态
                break;
            case Id:
                if (isAlpha(ch) || isDigit(ch)) {
                    tokenText.append(s1);       //保持标识符状态
                } else {
                    state = initToken(ch);      //退出标识符状态，并保存Token
                }
                break;
            case GT:
                if (ch == '=') {
                    token.setType(TokenType::GE); //转换成GE
                    state = GE;
                    tokenText.append(s1);
                } else {
                    state = initToken(ch);      //退出GT状态，并保存Token
                }
                break;
            case GE:
            case Assignment:
            case Plus:
            case Minus:
            case Star:
            case Slash:
            case SemiColon:
            case LeftParen:
            case RightParen:
                state = initToken(ch);          //退出当前状态，并保存Token
                break;
            case IntLiteral:
                if (isDigit(ch)) {
                    tokenText.append(s1);       //继续保持在数字字面量状态
                } else {
                    state = initToken(ch);      //退出当前状态，并保存Token
                }
                break;
            case Id_int1:
                if (ch == 'n') {
                    state = Id_int2;
                    tokenText.append(s1);
                }
                else if (isDigit(ch) || isAlpha(ch)){
                    state = Id;    //切换回Id状态
                    tokenText.append(s1);
                }
                else {
                    state = initToken(ch);
                }
                break;
            case Id_int2:
                if (ch == 't') {
                    state = Id_int3;
                    tokenText.append(s1);
                }
                else if (isDigit(ch) || isAlpha(ch)){
                    state = Id;    //切换回id状态
                    tokenText.append(s1);
                }
                else {
                    state = initToken(ch);
                }
                break;
            case Id_int3:
                if (isBlank(ch)) {
                    token.setType(TokenType::Int);
                    state = initToken(ch);
                }
                else{
                    state = Id;    //切换回Id状态
                    tokenText.append(s1);
                }
                break;
            default:
                    break;
            }

        }
        // 把最后一个token送进去
        if (tokenText.length() > 0) {
            initToken(ch);
        }
    } catch (std::exception e) {
        return SimpleTokenReader();
    }
    return SimpleTokenReader();
}


SimpleTokenReader::SimpleTokenReader(vector<Token> mtokens)
{
    tokens = mtokens;
}

Token SimpleTokenReader::read()
{
    if (pos < tokens.size()) {
        return tokens[pos++];
    }
    return Token();
}

Token SimpleTokenReader::peek()
{
    if (pos < tokens.size()) {
        return tokens[pos];
    }
    return Token();
}

void SimpleTokenReader::unread()
{
    if (pos > 0) {
        pos--;
    }
}

int SimpleTokenReader::getPosition()
{
    return pos;
}

void SimpleTokenReader::setPosition(int position)
{
    if (position >=0 && position < tokens.size()){
        pos = position;
    }
}
