lib.o: lib.asm
	nasm -f elf64 -o lib.o lib.asm

libi.o: lib.inc
	nasm -f elf64 -o libi.o lib.inc

main.o: main.asm
	nasm -f elf64 -o main.o main.asm

main: lib.o libi.o main.o
	ld -o main lib.o libi.o main.o

