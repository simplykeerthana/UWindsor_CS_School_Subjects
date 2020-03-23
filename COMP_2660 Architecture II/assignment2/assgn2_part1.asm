TITLE assignment2 part 1   (assign2_part1.asm)
; Program Description: part 1 of assignment 2
;Author: Keerthana
;Creation Date: 10/15/2019
; Revisions:
; Date : 10/15/2019

INCLUDE Irvine32.inc
.data

BaseFib DWORD 1
Prompt BYTE  "Enter an integer that is greater than zero : ",0
resultFib BYTE "Fibonacci sequence With N = ",0
resultFib1 BYTE " is : ",0
N DWORD ?


.code
main PROC

     mov edx, OFFSET Prompt
     call WriteString
     call ReadInt ; reads a value greater than 0
     call crlf
     mov edx, OFFSET resultFib 
     call WriteString
     call WriteInt
     mov edx, OFFSET resultFib1
     call WriteString
     mov N, eax
     mov al, 30h
     call writeChar
     mov al, 20h
     call WriteChar

; the sequence to print the fibonacci, a loop
PRINTFIBSEQUENCE:
     push BaseFib
     call Fibonacci
     add esp, 4

     call WriteDec
     mov al, 20h
     call WriteChar
     inc BaseFib
	dec N
	cmp N, 1
	jae PRINTFIBSEQUENCE
     call Crlf
     call Crlf

exit
main ENDP

;Function Fibonacci, the alogorith recursive

Fibonacci PROC

     add ecx,1
     push ebp
     mov  ebp,esp
     sub  esp, 4         
     mov  eax,[ebp+8]
     cmp  eax, 2
     je   BASECASE
     cmp  eax,1
     je   BASECASE
     dec eax
     push eax
     call Fibonacci
     mov [ebp-4], eax    	
     dec DWORD ptr [esp]
     call Fibonacci
     add esp, 4
     add eax, [ebp-4]
     jmp RETURN

BASECASE:
    mov eax, 1          

RETURN:
    mov esp, ebp
    pop ebp

    ret                 
Fibonacci ENDP

END main
