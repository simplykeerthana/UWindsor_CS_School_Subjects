TITLE	 Assignment 5 (Ass5-Q2) 
;team Members
;Keerthana Madhavan - 104995097
; Taqi Farooqui - 104073892
; Program: Calculates the fibonacci sequence recursively based on the user value N input

include Irvine32.inc

.data
N DWORD ?
base DWORD 1 
prompt1 BYTE  "Enter an integer that is above 0: ",0
prompt2 BYTE "Fibonacci sequence with N =  ",0
space BYTE " ",0
zeroVal BYTE "0 ",0

.code

main PROC

    mov edx, OFFSET prompt1 ;ask user to input an valid intefer
    call WriteString
    call ReadInt	        
	call crlf
	mov edx, OFFSET prompt2 
    call WriteString
	mov N, eax
	mov edx, OFFSET zeroVal ; zero is the first value of all fibonacci sequence
	call writestring

Print:
	push base         ; calculate the nth fib
    call fib            ; calculate fib (eax)
    add esp, 4          ; clean up the stack

    call WriteDec
	mov edx, OFFSET space  ; space for the next value
    call WriteString	
	inc base;
	dec N
	cmp N, 1
	jae Print

	call crlf ; new line after the fibonacci sequence


    exit
main ENDP

; Fibonacci Procedure
fib PROC 

    add ecx,1
    push ebp
    mov  ebp,esp
    sub  esp, 4         
    mov  eax,[ebp+8]    ; get N

    cmp  eax, 2          ; base case if (n == 2)
    je   BASECASE
    cmp  eax,1          ; base case if(n == 1)
    je   BASECASE

    dec eax				;fib(n-1) + fib(n-2);
    push eax            ; fib(n-1)
    call fib ; recursive call
    mov [ebp-4], eax    
	
    dec dword ptr [esp] ;  stack points (n-2), same as sub 4 ptr[esp]
    call fib
    add esp, 4          ; clean up stack
    
	add eax, [ebp-4]    ; store the final result into eax
    jmp FINISH

BASECASE:
    mov eax, 1			; the result will be 1 for the base case

FINISH:
    mov esp, ebp         
    pop ebp             

    ret                 
fib ENDP

END main