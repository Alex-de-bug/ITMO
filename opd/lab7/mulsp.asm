ma e0
ma e0
mw 0180009008
mw 0030001001
mw 0180009408
mw 0001002001
mw 0002009000
mw 0002209402
mw 80E8011020
mw 0020209021
mw 0020180020
mw 80E5081002
mw 0000809001
mw 80EE801010
mw 0001008501
mw 0020009021
mw 80F1081040
mw 0001002610
mw 0020009021
mw 0088009208
mw 0201E09020
mw 80C4101040

E0 0180009008	RESERVED	SP → AR; MEM(AR) → DR
E1 0030001001			LTOL(DR) → AC, BR
E2 0180009408			SP + 1 → AR; MEM(AR) → DR
E3 0001002001			LTOH(DR) → DR
E4 0002009000			0 → CR
E5 0002209402			CR + 1 → CR, C
E6 80E8011020			if BR(0) = 0 then GOTO E8
E7 0020209021			BR + DR → BR, C
E8 0020180020			ROR(BR) → BR
E9 80E5081002			if CR(3) = 0 then GOTO E5
EA 0000809001			DR → N, Z
EB 80EE801010			if AC(7) = 0 then GOTO EE
EC 0001008501			HTOH(~DR + 1) → DR
ED 0020009021			BR + DR → BR
EE 80F1081040			if PS(N) = 0 then GOTO F1
EF 0001002610			LTOH(~AC + 1) → DR
F0 0020009021			BR + DR → BR
F1 0088009208			~0 + SP → SP, AR
F2 0201E09020			BR → DR, N, Z, V, C; DR → MEM(AR)
F3 80C4101040			GOTO INT @ C4

ORG     0x050
ARG1:   WORD    0x00F3
ARG2:   WORD    0x0042
CHECK1: WORD    0x0
FINAL:  WORD    0x0
RES1:   WORD    0xFCA6
TESTRES1: WORD 0x0

ORG 0x058
START:  CLA     
        CALL  TEST1          
        LD      #0x1    
        AND   CHECK1
        ST      FINAL  
STOP:   HLT     
 
TEST1:  LD  ARG1
        PUSH    
        LD   ARG2
        PUSH    
        CLA
        WORD    0x0F30 ; MULSP     
        POP
        ST TESTRES1
        CMP RES1
        BEQ DONE1
ERROR1: POP
        POP
        CLA
        RET
DONE1: POP
        POP 
        LD #0x1
        ST CHECK1
        CLA 
        RET 
