ma 71
ma 71
mw 81F0044002  
ma F0
ma F0
mw 81F2024002 
mw 80E0101040 
mw 81F4014002 
mw 80E0101040 
mw 815C201040 
mw 80C4101040 

; Проверка на верное выполнение
ORG 	0x02D5
RESULT: WORD 0x0000 ;0x02D5
START: 
    EI ;0x02D6
    BODY:
        WORD	0xFF01 ;0x02D7
        HLT ;0x02D8
        LD #0x1 ;0x02D9
        ST RESULT ;0x02DA
        HLT ;0x02DB
END

; Проверка на неверное выполнение
ORG 	0x02D5
RESULT: WORD 0x0000 ;0x02D5
START: 
    DI ;0x02D6
    BODY:
        WORD	0xFF01 ;0x02D7
        HLT ;0x02D8
        LD #0x1 ;0x02D9
        ST RESULT ;0x02DA
        HLT ;0x02DB
END
