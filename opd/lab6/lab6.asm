ORG 0x000 
V0: WORD $DEFAULT, 0x180 ;Вектор прерываний #0
V1: WORD $DEFAULT, 0x180 ;Вектор прерываний #1
V2: WORD $INT2, 0x180    ;Вектор прерываний #2 -- 0010
V3: WORD $INT3, 0x180    ;Вектор прерываний #3 -- 0011
V4: WORD $DEFAULT, 0x180 ;Вектор прерываний #4
V5: WORD $DEFAULT, 0x180 ;Вектор прерываний #5
V6: WORD $DEFAULT, 0x180 ;Вектор прерываний #6
V7: WORD $DEFAULT, 0x180 ;Вектор прерываний #7
DEFAULT:    IRET    ;Возврат из прерываний

ORG 0x01F  
X:  WORD    0x0000  ;Переменная X
TMP: WORD   0x0000  ;Временная переменная
ADDRX: WORD $X  ;Адрес X
MIN: WORD   0xFFD6  ;Нижняя граница значений X
MAX: WORD   0x002A  ;Верхняя граница значений X
START:  DI ;загрузка начальных векторов прерываний
    LD #0xA ;разрешить прерывания и вектор 2
    OUT 5 ;(1000|0010=1010) в MR КВУ-2
    LD #0xB ;разрешить прерывания и вектор 3
    OUT 7 ;(1000|0011=1011) в MR КВУ-3
MAIN:   
    EI
    LD X
    ST TMP
    INC
    CALL $CHECK_BORDERS
    PUSH
    LD TMP
    PUSH
    LD ADDRX
    PUSH
    CALL $CAS
    JUMP MAIN
    
INT2: ;обработка прерывания на ВУ-2
    PUSH
    LD X
    NOP
    IN 4
    AND #0x000F
    AND X
    
    PUSH
    LD TMP
    PUSH
    LD ADDRX
    PUSH
    CALL $CAS
    NOP
    POP
    IRET

INT3: ;обработка прерывания на ВУ-3
    PUSH
    LD X

    NOP

    ASL
    ADD X
    
    NEG

    OUT 6
    POP
    IRET

CHECK_BORDERS:
    CHECK_MIN:  
        CMP MIN 
        BPL CHECK_MAX   
        JUMP LD_MIN
    CHECK_MAX:  
        CMP MAX
        BMI RETURN
    LD_MIN: 
        LD MIN
RETURN: RET

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
