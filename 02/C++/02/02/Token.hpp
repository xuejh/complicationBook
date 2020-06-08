//
//  Token.hpp
//  02
//
//  Created by XJH on 2020/6/8.
//  Copyright © 2020 XJH. All rights reserved.
//

#ifndef Token_hpp
#define Token_hpp

#include <stdio.h>
#include <iostream>
#include "TokenType.hpp"
using namespace std;

/**
* 一个简单的Token。
* 只有类型和文本值两个属性。
*/
class Token {
    
public:
    /**
        * Token的类型
        * @return TokenType
        */
    virtual TokenType::TokenType getType() = 0;
    /**
        * Token的文本值
        * @return string
        */
    virtual string getText() = 0;
};

#endif /* Token_hpp */
