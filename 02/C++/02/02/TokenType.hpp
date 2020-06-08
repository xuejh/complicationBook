//
//  TokenType.hpp
//  02
//
//  Created by XJH on 2020/6/8.
//  Copyright © 2020 XJH. All rights reserved.
//

#ifndef TokenType_hpp
#define TokenType_hpp

#include <stdio.h>

//struct TokenType
//{
//
//const int Plus = 0; // +
//
//const int Minus =1; // -
//
//const int Star = 2; // *
//
//const int Slash = 3; // /
//
//const int GE = 4; // >=
//
//const int GT = 5; // >
//
//const int EQ =6; // ==
//
//const int LE = 7; // <=
//
//const int LT = 8; // <
//
//const int SemiColon = 9;  // ;
//
//const int LeftParen = 10; // (
//
//const int RightParen =11; // )
//
//const int Assignment = 12; // =
//
//const int If = 13;
//
//const int Else = 14;
//
//const int Int = 15;
//
//const int Identifier = 16; //标识符
//
//const int IntLiteral = 17; //整型字面量
//
//const int StringLiteral = 18; //字符串字面量
//};

namespace TokenType{
    enum TokenType{
        Plus,   // +
        Minus,  // -
        Star,   // *
        Slash,  // /

        GE,     // >=
        GT,     // >
        EQ,     // ==
        LE,     // <=
        LT,     // <

        SemiColon, // ;
        LeftParen, // (
        RightParen,// )

        Assignment,// =

        If,
        Else,

        Int,

        Identifier,     //标识符

        IntLiteral,     //整型字面量
        StringLiteral   //字符串字面量
    };
}



#endif /* TokenType_hpp */
