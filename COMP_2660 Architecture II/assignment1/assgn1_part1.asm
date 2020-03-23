TITLE assignment1 part 1      (assign1_part1.asm)

; Program Description: part 1 of assignment 1
;Author: Keerthana
;Creation Date: 10/7/2019
; Revisions:
; Date : 10/7/2019

INCLUDE Irvine32.inc

.data
	variableA SDWORD -543210
	variableB SDWORD -10
	variableC SDWORD ?
	variableD SDWORD ?
	variableZ SDWORD ?
	promptC BYTE "What is the value of C? ",0
	promptD BYTE "What is the value of D? ",0
	strEquation BYTE "Z=(A-B)-(C-D)",0
	strInSpace BYTE "   ;   ",0  ;three spaces
	

.code
main PROC
	mov edx,OFFSET promptC	
	call WriteString
	call ReadInt					
	mov variableC,eax
	call WriteInt
	call crlf

	mov edx,OFFSET promptD		
	call WriteString
	call ReadInt					
	mov variableD,eax
	call WriteInt
	call crlf

	mov edx,OFFSET strEquation		
	call WriteString
	call crlf

	mov eax,variableA						
	call WriteInt
	mov edx,OFFSET strInSpace
	call WriteString
	mov eax,variableB
	call WriteInt
	call WriteString
	mov eax,variableC
	call WriteInt
	call WriteString
	mov eax,variableD
	call WriteInt

	call crlf
	call crlf

	mov eax,variableA						
	sub eax,variableB
	mov variableZ,eax
	mov eax,variableC
	sub eax,variableD
	sub variableZ,eax
	
	mov eax,variableZ						
	call WriteInt
	call crlf
	call WriteBin					
	call crlf
	call WriteHex					

	exit
main ENDP
END main 