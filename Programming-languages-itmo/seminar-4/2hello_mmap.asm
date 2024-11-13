%define O_RDONLY 0 
%define PROT_READ 0x1
%define MAP_PRIVATE 0x2
%define SYS_WRITE 1
%define SYS_OPEN 2
%define SYS_CLOSE 3
%define SYS_FSTAT 5
%define SYS_MMAP 9
%define SYS_MUNMAP 11
%define SYS_EXIT 60
%define FD_STDOUT 1
%define SIZE_OFFT 8
%define SIZEOF_STAT 144
%define OFFSET_ST_SIZE 48

section .data
    fname: db 'hello.txt', 0
    file_size: dq 0  ; To store file size

section .bss
    file_stat resb 144  ; Struct stat has 144 bytes

section .text
global _start

; use exit system call to shut down correctly
exit:
    mov  rax, SYS_EXIT
    xor  rdi, rdi
    syscall

; These functions are used to print a null terminated string
; rdi holds a string pointer
print_string:
    push rdi
    call string_length
    pop  rsi
    mov  rdx, rax 
    mov  rax, SYS_WRITE
    mov  rdi, FD_STDOUT
    syscall
    ret

string_length:
    xor  rax, rax
.loop:
    cmp  byte [rdi+rax], 0
    je   .end 
    inc  rax
    jmp .loop 
.end:
    ret

; This function is used to print a substring with given length
; rdi holds a string pointer
; rsi holds a substring length
print_substring:
    mov  rdx, rsi 
    mov  rsi, rdi
    mov  rax, SYS_WRITE
    mov  rdi, FD_STDOUT
    syscall
    ret

_start:
    ; Open the file
    mov  rax, SYS_OPEN
    mov  rdi, fname
    mov  rsi, O_RDONLY
    mov  rdx, 0
    syscall

    mov r14, rax
   
    ; Use fstat to get the file size
    mov  rax, 5        ; syscall number for sys_fstat
    mov  rdi, r14      ; File descriptor
    mov  rsi, file_stat  ; Pointer to the stat structure
    syscall

    ; Load the file size from the stat structure
    mov  rbx, [file_stat + 48]  ; st_size offset in struct stat
    mov  [file_size], rbx  ; Store file size

    ; Use mmap to map the file
    mov  rax, SYS_MMAP
    mov  rdi, 0           ; Let the system choose the address
    mov  rsi, [file_size]         ; Length (file size)
    mov  rdx, PROT_READ   ; Prot (PROT_READ)
    mov  r10, MAP_PRIVATE  ; Flags (MAP_PRIVATE)
    mov  r8, r14          ; File descriptor
    mov  r9, 0            ; Offset
    syscall

   ;Print mapped file with file size
    mov rdi, rax
    mov rsi, [file_size]
    call print_substring



; close file
    mov rax, SYS_CLOSE
    mov rdi, r14

    ; Cleanup and exit
    call exit

