//
//  TokenReader.hpp
//  02
//
//  Created by XJH on 2020/6/8.
//  Copyright © 2020 XJH. All rights reserved.
//

#ifndef TokenReader_hpp
#define TokenReader_hpp

#include <iostream>
#include <stdio.h>
#include "Token.hpp"

/**
* 一个Token流。由Lexer生成。Parser可以从中获取Token。
*/

class TokenReader {
    
public:
    /**
    * 返回Token流中下一个Token，并从流中取出。 如果流已经为空，返回null;
    */
    virtual Token read() = 0;
    /**
        * 返回Token流中下一个Token，但不从流中取出。 如果流已经为空，返回null;
        */
    virtual Token peek() = 0;
    /**
    * Token流回退一步。恢复原来的Token。
    */
    virtual void unread() = 0;
    /**
    * 获取Token流当前的读取位置。
    * @return
    */
    virtual int getPosition() = 0;
    /**
    * 设置Token流当前的读取位置
    * @param position
    */
    virtual void setPosition(int position) = 0;
};

#endif /* TokenReader_hpp */
