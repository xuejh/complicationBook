
public enum DfaState {
	Initial, //初始状态

     Id,  //标识符状态

     GT,// >
     GE,// >=
    IntLiteral, //数字字面量
    Int, Id_int1, Id_int2, Id_int3,//Int
    Assignment,//=
    Plus,//+
    Minus,//-
    Star,//*
    Slash,///
}
