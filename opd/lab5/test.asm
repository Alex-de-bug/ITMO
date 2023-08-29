ORG   0x0
MyStek: WORD 0x401
CurrentNum: WORD 0x0
START: CLA

S1:
    IN 0x1D
    AND #0x40
    BEQ  S1

    IN 0x1C

    CMP #0xE
    BEQ LoadNumToSteck ;CHECK '.'

    CMP #0xD
    BEQ Mulling ;CHECK '*'

    CMP #0xB
    BEQ SUMM ;CHECK '+'

    CMP #0xA
    BEQ Subber ;CHECK '-'

    SWAM CurrentNum
    ASL
    ASL
    ASL
    ASL
    ADD CurrentNum
    ST CurrentNum
    JUMP S1


STOP: 
    HLT
LoadNumToSteck:
    CLA
    LD CurrentNum
    PUSH
    CALL $OUT5
    LD CurrentNum
    PUSH
    CALL $CONVERT16
    POP
    ST (MyStek)+
    CLA
    ST CurrentNum
    JUMP S1
SUMM:
    CLA
    LD MyStek
    DEC
    DEC
    ST MyStek
    LD (MyStek)+
    ST CurrentNum
    LD (MyStek)+
    ADD CurrentNum  
    ST CurrentNum  
    LD MyStek
    DEC
    DEC
    ST MyStek
    LD CurrentNum
    ST (MyStek)+
    CLA
    ST (MyStek)
    LD CurrentNum
    PUSH
    CALL $CONVERTER10
    CALL $OUT5
    CLA
    ST CurrentNum
    JUMP S1
Subber:
    CLA
    LD MyStek
    DEC
    DEC
    ST MyStek
    LD (MyStek)+
    ST CurrentNum
    LD (MyStek)+
    SUB CurrentNum  
    ST CurrentNum  
    LD MyStek
    DEC
    DEC
    ST MyStek
    LD CurrentNum
    ST (MyStek)+
    CLA
    ST (MyStek)
    LD CurrentNum
    PUSH
    CALL $CONVERTER10
    CALL $OUT5
    CLA
    ST CurrentNum
    JUMP S1

Mulling:
    CLA
    LD MyStek
    DEC
    DEC
    ST MyStek
    LD (MyStek)+
    PUSH
    LD (MyStek)+
    PUSH
    CALL $MUL 
    ST CurrentNum  
    LD MyStek
    DEC
    DEC
    ST MyStek
    LD CurrentNum
    ST (MyStek)+
    CLA
    ST (MyStek)
    LD CurrentNum
    PUSH
    CALL $CONVERTER10
    CALL $OUT5
    CLA
    ST CurrentNum
    JUMP S1

    


; func convert 10 to 16 
STEP1: WORD 0x1000
STEP11: WORD 0x3E8
STEP2: WORD 0x100
CONVERT16:
    CLA
    PUSH 
    ;STACK 
    ;   Number 10 2
    ;   RET 1
    ;   ANSWER 16 0
def1:
    CLA
    LD &2
    CMP STEP1
    BMI def2
    SUB STEP1
    ST &2
    LD STEP11
    ADD &0
    ST &0
    JUMP def1
def2:
    CLA
    LD &2
    CMP STEP2
    BMI def3
    SUB STEP2
    ST &2
    LD #0x64
    ADD &0
    ST &0
    JUMP def2

def3:
    CLA
    LD &2
    CMP #0x10
    BMI def4
    SUB #0x10
    ST &2
    LD #0xA
    ADD &0
    ST &0
    JUMP def3

def4:
    CLA
    LD &2
    CMP #0x1
    BMI EXIT
    SUB #0x1
    ST &2
    LD #0x1
    ADD &0
    ST &0
    JUMP def4

EXIT:
    
    LD &0
    ST &2
    SWAP
    POP

    RET

; FUNC Вывода в ву5 числа
E: WORD 0xF000
COUNTER: WORD 0x0
OUT5:
    IN 0xD
    AND #0x40
    BEQ OUT5
    LD #0x0
    OUT 0xC
    LD #0x4
    ST COUNTER
    ;STACK 
    ;   number10 
    ;   RET 
    CheckOIn5:
        IN 0xD
        AND #0x40
        BEQ CheckOIn5 ;проверка готовности ву
    LD &1
    AND E
    SWAB
    ASR
    ASR
    ASR
    ASR
    ADD #0x30
    OUT 0xC
    LD &1
    ASL
    ASL
    ASL
    ASL
    ST &1
    LOOP COUNTER
    JUMP CheckOIn5


    SWAP 
    ST &1
    SWAP

    SWAP
    POP

    RET

; func convert 16 to 10
STEP12: WORD 0x3E8
STEP112: WORD 0x1000
STEP22: WORD 0x100
CONVERTER10:
    CLA
    PUSH 
    ;STACK 
    ;   Number 16 2
    ;   RET 1
    ;   ANSWER 10 0
def12:
    CLA
    LD &2
    CMP STEP12
    BMI def22
    SUB STEP12
    ST &2
    LD STEP112
    ADD &0
    ST &0
    JUMP def12
def22:
    CLA
    LD &2
    CMP #0x64
    BMI def32
    SUB #0x64
    ST &2
    LD STEP22
    ADD &0
    ST &0
    JUMP def22

def32:
    CLA
    LD &2
    CMP #0xA
    BMI def42
    SUB #0xA
    ST &2
    LD #0x10
    ADD &0
    ST &0
    JUMP def32

def42:
    CLA
    LD &2
    CMP #0x1
    BMI EXIT2
    SUB #0x1
    ST &2
    LD #0x1
    ADD &0
    ST &0
    JUMP def42

EXIT2:

    LD &0
    ST &2
    SWAP
    POP

    RET    

; Function A * B -> AC
MUL:
    CLA
    PUSH
    ; STACK
    ; A 3
    ; B 2
    ; RET 1
    ; answer 0
    BEGIN: 
        LD &2
        AND #1
        ; If last bit of B is 0,
        ;then skipping sum and going to shifts
        BEQ SKIP_ADD
        LD &0
        ADD &3
        ST &0
    SKIP_ADD:
        ; SHIFT A LEFT BY 1 BIT
        LD &3
        ASL
        ST &3
        ; SHIFT B RIGHT BY 2 BIT
        LD &2
        ASR
        ST &2
        ; COMPARE B TO 0. If it isn't 0, BACK TO BEGIN
        BNE BEGIN
    
    POP

    SWAP
    ST &2
    SWAP

    SWAP
    POP

    SWAP
    POP
    RET

