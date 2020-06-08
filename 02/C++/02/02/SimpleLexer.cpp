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

string SimpleToken::getText()
{
    return text;
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
