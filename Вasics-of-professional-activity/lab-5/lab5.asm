ORG   0x060
ADDR:  WORD  $STRING

START: CLA 
	OUT 2
	
S1: IN  3
  	AND  #0x40  
  	BEQ  S1
	LD (ADDR)
    SWAB
  	OUT 2
	SXTB
	CMP #0x0D
    BEQ STOP_POINT
  	
S2: IN  3
  	AND  #0x40  
  	BEQ  S2 
	LD (ADDR)+
  	OUT 2
	SXTB
	CMP #0x0D
    BEQ STOP_POINT
  	JUMP S1
	
STOP_POINT: HLT

ORG 0x5D8
STRING: 
	WORD 0xDC3F  
	WORD 0x710D




; ORG   0x060
; ADDR:  WORD  $STRING

; START: CLA 
; 	OUT 2
	
; S1: IN  3
;   	AND  #0x40  
;   	BEQ  S1
; 	LD (ADDR)
;     SWAB
;   	PUSH
;   	CALL $BREAK_POINT
;   	POP
;   	OUT 2
  	
; S2: IN  3
;   	AND  #0x40  
;   	BEQ  S2 
; 	LD (ADDR)+
;   	PUSH
;   	CALL $BREAK_POINT
;   	POP
;   	OUT 2
;   	JUMP S1
  
; BREAK_POINT:  LD &1
;      SXTB
;      CMP #0x0D
;      BEQ STOP_POINT
;      RET
     
; STOP_POINT: HLT

; ORG 0x5D8
; STRING: 
; 	WORD 0x1234  
; 	WORD 0x4321
; 	WORD 0x110D
