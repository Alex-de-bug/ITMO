ASM=nasm
ASMFLAGS=-f elf64
LD=ld


%.o: %.asm
	$(ASM) $(ASMFLAGS) $@ $<

libi.o: lib.inc lib.o
	$(ASM) $(ASMFLAGS) -o $@ $<

main.o: main.asm libi.o
	$(ASM) $(ASMFLAGS) -o $@ $<

main: lib.o libi.o main.o
	$(LD) -o $@ $^

