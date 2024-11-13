%macro __words 1-*
%1: 
%rotate 1
%rep %0-2
db %1, ", "
%rotate 1
%endrep
db %1, 0
%2_end:
%endmacro

global _start

section .data

__words lol, "hello", "another", "world"

section .text
_start:
    mov     rax, 1
    mov     rdi, 1
    mov     rsi, lol
    mov     rdx, lol_end-lol
    syscall

    mov     rax, 60
    xor     rdi, rdi
    syscall
