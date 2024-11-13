ORG 0x0
V0: WORD $DEFAULT, 0x180 ;Вектор прерываний #0
V1: WORD $DEFAULT, 0x180 ;Вектор прерываний #1
V2: WORD $INT2, 0x180    ;Вектор прерываний #2 -- 0010
V3: WORD $DEFAULT, 0x180 ;Вектор прерываний #3
V4: WORD $DEFAULT, 0x180 ;Вектор прерываний #4
V5: WORD $DEFAULT, 0x180 ;Вектор прерываний #5
V6: WORD $DEFAULT, 0x180 ;Вектор прерываний #6
V7: WORD $DEFAULT, 0x180 ;Вектор прерываний #7
DEFAULT:    IRET    ;Возврат из прерываний

ORG 0x01F  
SYMB: WORD    0x30
X:  WORD    0xFFFF  ;Переменная X
TMP: WORD   0x0  ;Временная переменная
ADDRX: WORD $X  ;Адрес X
MIN: WORD   0x0  ;Нижняя граница значений X
MAX: WORD   0xA  ;Верхняя граница значений X
START:  DI ;загрузка начальных векторов прерываний
    LD #0xA ;разрешить прерывания и вектор 2
    OUT 0x1D ;(1000|0010=1010) в MR ВУ-9
MAIN:   
    EI
    LD X
    ST TMP
    INC
    PUSH
    LD TMP
    PUSH
    LD ADDRX
    PUSH
    CALL $CAS
INP1: 
    IN 0xD
	AND #0x40
	BEQ INP1
OUTPUT1:
    LD #0x0
	OUT 0xC 
    LD SYMB
    ADD X
	OUT 0xC

    LD X
    CMP #0x0
    BNE A
    JUMP IF_NULL
    ; CALL $DRAW0
    
    A: 
        LD X

        CMP #0x0
        BNE A_2
        JUMP IF_NULL
    A_2:
        CMP #0x1
        BNE B
        CALL $DRAW1
        JUMP MAIN
    B: 
        LD X
    
        CMP #0x0
        BNE B_2
        JUMP IF_NULL
    B_2:
        CMP #0x2
        BNE C
        CALL $DRAW2
        JUMP MAIN
    C: 
        LD X

        CMP #0x0
        BNE C_2
        JUMP IF_NULL
    C_2:
        CMP #0x3
        BNE D
        CALL $DRAW3
        JUMP MAIN
    D: 
        LD X

        CMP #0x0
        BNE D_2
        JUMP IF_NULL
    D_2:
        CMP #0x4
        BNE F
        CALL $DRAW4
        JUMP MAIN
    F: 
        LD X

        CMP #0x0
        BNE F_2
        JUMP IF_NULL
    F_2:
        CMP #0x5
        BNE E
        CALL $DRAW5
        JUMP MAIN
    E: 
        LD X

        CMP #0x0
        BNE E_2
        JUMP IF_NULL
    E_2:
        CMP #0x6
        BNE G
        CALL $DRAW6
        JUMP MAIN
    G: 
        LD X

        CMP #0x0
        BNE G_2
        JUMP IF_NULL
    G_2:
        CMP #0x7
        BNE H
        CALL $DRAW7
        JUMP MAIN
    H: 
        LD X

        CMP #0x0
        BNE H_2
        JUMP IF_NULL
    H_2:
        CMP #0x8
        BNE J
        CALL $DRAW8
        JUMP MAIN
    J: 
        LD X

        CMP #0x0
        BNE J_2
        JUMP IF_NULL
    J_2:
        CMP #0x9
        BNE MAIN
        CALL $DRAW9
        LD #0x0
        ST X
        JUMP MAIN

ADDRDRAW: WORD 0x0
IF_NULL:
    EI
    LD $X
    ST $TMP
    INC
    PUSH
    LD $TMP
    PUSH
    LD $ADDRX
    PUSH
    CALL $CAS
    
    BODY:
        INP1_2: 
            IN 0xD
            AND #0x40
            BEQ INP1_2
        OUTPUT1_2:
            LD #0x0
            OUT 0xC 
            LD $SYMB
            ADD $X
            OUT 0xC
        LD $X
        CMP #0x0
        BNE PREDICT
        JUMP MAIN
    PREDICT:
        LD $X
        CMP #0x1
        BNE B_1
        CALL $DRAW1
        JUMP BODY
    B_1: 
        LD $X
        CMP #0x2
        BNE C_1
        CALL $DRAW2
        JUMP BODY
    C_1: 
        LD $X
        CMP #0x3
        BNE D_1
        CALL $DRAW3
        JUMP BODY
    D_1: 
        LD $X
        CMP #0x4
        BNE F_1
        CALL $DRAW4
        JUMP BODY
    F_1: 
        LD $X
        CMP #0x5
        BNE E_1
        CALL $DRAW5
        JUMP BODY
    E_1: 
        LD $X
        CMP #0x6
        BNE G_1
        CALL $DRAW6
        JUMP BODY
    G_1: 
        LD $X
        CMP #0x7
        BNE H_1
        CALL $DRAW7
        JUMP BODY
    H_1: 
        LD $X
        CMP #0x8
        BNE J_1
        CALL $DRAW8
        JUMP BODY
    J_1: 
        CALL $DRAW9
        JUMP BODY

        

    
INT2:
    PUSH
    
    IN 0x1C
    ST $X
    
    POP
    IRET

; CAS(ADDRX; EXPECT; NEW)
DEREF: WORD 0x0000
CAS:
    PUSHF; Кладёт флаг прерываний
    DI
    LD &2
    ST DEREF
    LD (DEREF)
    CMP &3
    BNE FAIL
    SUCCES:
        LD &4
        ST (DEREF)
        LD #0x1
        JUMP EXIT
    FAIL:
        CLA
    EXIT:
        POPF
        SWAP
        ST &3
        SWAP
        SWAP 
        POP
        SWAP 
        POP
        SWAP 
        POP
        NOP
        RET

ORG 0x300
DRAWER0: WORD $DRAW0 ; ADDR 300
DRAWER1: WORD $DRAW1
DRAWER2: WORD $DRAW2
DRAWER3: WORD $DRAW3
DRAWER4: WORD $DRAW4
DRAWER5: WORD $DRAW5
DRAWER6: WORD $DRAW6
DRAWER7: WORD $DRAW7
DRAWER8: WORD $DRAW8 
DRAWER9: WORD $DRAW9

NULL: WORD 0x18
ONE: WORD 0x1
TWO: WORD 0x2
FOUR: WORD 0x4
EIGHT: WORD 0x8
TEN: WORD 0x10
TWENTY: WORD 0x20
FOURTY: WORD 0x40
EIGHTY: WORD 0x80


DRAW0:
    LD NULL
    OUT 0x10
    OUT 0x10
    OUT 0x10
    OUT 0x10
    OUT 0x10
    OUT 0x10
    RET

DRAW1:
    LD $TEN
    OUT 0x10
    LD $EIGHT
    OUT 0x10
    LD $TEN
    OUT 0x10
    LD $EIGHT
    OUT 0x10
    LD $TEN
    OUT 0x10
    LD $EIGHT
    OUT 0x10
    RET
DRAW2:
    LD $TEN
    OUT 0x10
    LD $TWENTY
    OUT 0x10
    LD $TWENTY
    OUT 0x10
    LD $TEN
    OUT 0x10
    LD $EIGHT
    OUT 0x10
    LD $FOUR
    OUT 0x10
    LD $FOUR
    OUT 0x10
    LD $EIGHT
    OUT 0x10
    RET
DRAW3:
    LD $TEN
    OUT 0x10
    LD $TWENTY
    OUT 0x10
    LD $TWENTY
    OUT 0x10
    LD $TWENTY
    OUT 0x10
    LD $TEN
    OUT 0x10
    LD $EIGHT
    OUT 0x10
    LD $FOUR
    OUT 0x10
    LD $FOUR
    OUT 0x10
    LD $FOUR
    OUT 0x10
    LD $EIGHT
    OUT 0x10
    RET
DRAW4:
    LD $TEN
    OUT 0x10
    LD $TWENTY
    OUT 0x10
    LD $TWENTY
    OUT 0x10
    LD $TWENTY
    OUT 0x10
    LD $TWENTY
    OUT 0x10
    LD $TEN
    OUT 0x10
    LD $EIGHT
    OUT 0x10
    LD $FOUR
    OUT 0x10
    LD $FOUR
    OUT 0x10
    LD $FOUR
    OUT 0x10
    LD $FOUR
    OUT 0x10
    LD $EIGHT
    OUT 0x10
    RET
DRAW5:
    LD $TEN
    OUT 0x10
    LD $TWENTY
    OUT 0x10
    LD $FOURTY
    OUT 0x10
    LD $FOURTY
    OUT 0x10
    LD $TWENTY
    OUT 0x10
    LD $TEN
    OUT 0x10
    LD $EIGHT
    OUT 0x10
    LD $FOUR
    OUT 0x10
    LD $TWO
    OUT 0x10
    LD $TWO
    OUT 0x10
    LD $FOUR
    OUT 0x10
    LD $EIGHT
    OUT 0x10
    RET
DRAW6:
    LD $TEN
    OUT 0x10
    LD $TWENTY
    OUT 0x10
    LD $FOURTY
    OUT 0x10
    LD $FOURTY
    OUT 0x10
    LD $FOURTY
    OUT 0x10
    LD $TWENTY
    OUT 0x10
    LD $TEN
    OUT 0x10
    LD $EIGHT
    OUT 0x10
    LD $FOUR
    OUT 0x10
    LD $TWO
    OUT 0x10
    LD $TWO
    OUT 0x10
    LD $TWO
    OUT 0x10
    LD $FOUR
    OUT 0x10
    LD $EIGHT
    OUT 0x10
    RET
DRAW7:
    LD $TEN
    OUT 0x10
    LD $TWENTY
    OUT 0x10
    LD $FOURTY
    OUT 0x10
    LD $FOURTY
    OUT 0x10
    LD $FOURTY
    OUT 0x10
    LD $FOURTY
    OUT 0x10
    LD $TWENTY
    OUT 0x10
    LD $TEN
    OUT 0x10
    LD $EIGHT
    OUT 0x10
    LD $FOUR
    OUT 0x10
    LD $TWO
    OUT 0x10
    LD $TWO
    OUT 0x10
    LD $TWO
    OUT 0x10
    LD $TWO
    OUT 0x10
    LD $FOUR
    OUT 0x10
    LD $EIGHT
    OUT 0x10
    RET
DRAW8:
    LD $TEN
    OUT 0x10
    LD $TWENTY
    OUT 0x10
    LD $FOURTY
    OUT 0x10
    LD $EIGHTY
    OUT 0x10
    LD $EIGHTY
    OUT 0x10
    LD $EIGHTY
    OUT 0x10
    LD $FOURTY
    OUT 0x10
    LD $TWENTY
    OUT 0x10
    LD $TEN
    OUT 0x10
    LD $EIGHT
    OUT 0x10
    LD $FOUR
    OUT 0x10
    LD $TWO
    OUT 0x10
    LD $ONE
    OUT 0x10
    LD $ONE
    OUT 0x10
    LD $ONE
    OUT 0x10
    LD $TWO
    OUT 0x10
    LD $FOUR
    OUT 0x10
    LD $EIGHT
    OUT 0x10
    RET
DRAW9:
    LD $TEN
    OUT 0x10
    LD $TWENTY
    OUT 0x10
    LD $FOURTY
    OUT 0x10
    LD $EIGHTY
    OUT 0x10
    LD $EIGHTY
    OUT 0x10
    LD $EIGHTY
    OUT 0x10
    LD $EIGHTY
    OUT 0x10
    LD $FOURTY
    OUT 0x10
    LD $TWENTY
    OUT 0x10
    LD $TEN
    OUT 0x10
    LD $EIGHT
    OUT 0x10
    LD $FOUR
    OUT 0x10
    LD $TWO
    OUT 0x10
    LD $ONE
    OUT 0x10
    LD $ONE
    OUT 0x10
    LD $ONE
    OUT 0x10
    LD $ONE
    OUT 0x10
    LD $TWO
    OUT 0x10
    LD $FOUR
    OUT 0x10
    LD $EIGHT
    OUT 0x10
    RET