ORG   0x0
Buffer: WORD 0x0
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

    CMP #0xA
    BPL DoneNumbers ;CHECK '+ - *'

    SWAM CurrentNum
    ASL
    ASL
    ASL
    ASL
    ADD CurrentNum
    ST CurrentNum
    JUMP S1
LoadNumToSteck:
    CLA
    LD CurrentNum
    ST (MyStek)+
    CALL $OUT5
    CLA
    ST CurrentNum
    JUMP S1

DoneNumbers:
    ST Buffer
    LD MyStek
    DEC
    DEC
    ST MyStek
    LD (MyStek)
    PUSH
    CALL $CONVERT16
    ST (MyStek)+
    LD (MyStek)
    PUSH
    CALL $CONVERT16
    ST (MyStek)

    LD MyStek
    PUSH
    CALL $SUM
    CALL $OUT5

    JUMP S1




STOP: HLT

; func for out
BUF: WORD 0x0
FUG: WORD 0xF000
COUNTER: WORD 0x0
OUT5:
    IN 0xD
    AND #0x40
    BEQ OUT5
    LD #0x0
    OUT 0xC
    CLA
    PUSH
    PUSH
    ;STACK 
    ;   RET 2
    ;   Addr LOCAL 1
    ;   Num LOCAL 0
    LD MyStek
    DEC
    ST BUF
    LD (BUF)
    ST &0
    LD #0x4
    ST COUNTER

CheckOIn5:
    IN 0xD
    AND #0x40
    BEQ CheckOIn5 ;проверка готовности ву
    LD &0 ;загрузка и отделение бита
    SWAB
    AND #0xF0
    BEQ outer5
    ASR
    ASR
    ASR
    ASR
outer5:
    ADD #0x30
    OUT 0xC

    LD &0
    ASL
    ASL
    ASL
    ASL
    ST &0

    LOOP COUNTER
    JUMP CheckOIn5

    SWAP
    POP
    SWAP
    POP

    RET

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
    POP

    SWAP
    ST &1
    SWAP

    SWAP
    POP

    RET

;fonc for summ
ADDRESS: WORD 0x0
SUM:
    CLA
    PUSH 
    ;STACK 
    ;   adrs stack 2
    ;   RET 1
    ;   ANSWER 0
    LD &2
    DEC
    ST ADDRESS
    LD (ADDRESS)
    ADD &0
    ST &0

    LD &2
    ST ADDRESS
    LD (ADDRESS)
    ADD &0
    ST &0
    
    LD &2
    DEC
    ST ADDRESS
    CALL $CONVERTER10
    ST (ADDRESS)

    LD &2
    ST ADDRESS
    CLA
    SWAM (ADDRESS)
    

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
    CMP STEP22
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
    POP

    SWAP
    ST &1
    SWAP

    SWAP
    POP

    RET