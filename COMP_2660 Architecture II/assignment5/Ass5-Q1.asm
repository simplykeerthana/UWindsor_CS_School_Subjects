TITLE	  Assignment 5 (Ass5-Q1.asm)  
;team Members
;Keerthana Madhavan - 104995097
; Taqi Farooqui - 104073892
; Program : Calculates the GCD of 5 given pair values recursively.

INCLUDE Irvine32.inc


.data   

prompt BYTE "GCD of the following given pairs are", 0
prompt1 BYTE "The GCD of pair (5, 20) is : ",0
prompt2 BYTE "The GCD of pair (24, 18) is : ",0
prompt3 BYTE "The GCD of pair (11, 7) is : ",0
prompt4 BYTE "The GCD of pair (432, 226) is : ",0
prompt5 BYTE "The GCD of pair (0, 0) is : ",0
  
.code
main PROC

	mov edx, OFFSET prompt
	Call WriteString
	Call crlf
	; the beginning of the stack
	;(5,20)
	mov edx, OFFSET prompt1
	Call WriteString

	push 5 
	push 20 
    call GCD
	mov eax, ebx
	call writedec
	call crlf

	mov edx, OFFSET prompt2
	Call WriteString

	;(24,18)
	push 24 
	push 18 
    call GCD
	mov eax, ebx
	call writedec
	call crlf

	mov edx, OFFSET prompt3
	Call WriteString

	;(11,7)
	push 11 
	push 7 
    call gcd
	mov eax, ebx
	call writedec
	call crlf

	mov edx, OFFSET prompt4
	Call WriteString

	;(432, 226)
	push 432 
	push 226 
    call GCD
	mov eax, ebx
	call writedec
	call crlf

	mov edx, OFFSET prompt5
	Call WriteString
	;(0,0)
	push 0 
	push 0 
    call GCD
	mov eax, ebx
	call writedec
	call crlf

    exit
main ENDP

; gcd function procedure
GCD PROC
		
    mov edx, 0 
	mov eax, DWORD ptr[esp+8]   ;dividend (5,20)--> 5 is the dividend
    mov ebx, DWORD ptr[esp+4]  ;divisor (5,20)--> 20 is the divisor
	cmp eax, ebx
	je QUIT
	jb SWAP		;swap if x is less than y or eax less than ebx
	jmp CONTINUE
SWAP: 
	xchg eax, ebx
	jmp continue

 CONTINUE:
    div ebx     ;eax/ebx
    cmp edx, 0  ;remainder in edx register
    je QUIT    ;if yes, then quit, base case
	mov eax, ebx
	mov ebx, edx
	push ebx
	push eax
    call gcd   ;if no zero the gcd procedure again, the recursive call
QUIT:
    ret 8      ;to clean up the stack by moving to its original empty location

gcd ENDP

END main