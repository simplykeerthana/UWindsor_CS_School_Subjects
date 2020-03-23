TITLE	Ass3.asm
;Noah Campbell 104536111
;Keerthana Madhavan - 104995097
;Austin Malaquias - 104586622

INCLUDE Irvine32.inc

.data
dispStr1 BYTE "What do you want to do now? > ", 0
dispStr2 BYTE "What is the size N of Vector? > ", 0
dispStr3 BYTE "What are the ", 0
dispStr4 BYTE " values in Vector? > ", 0
dispStr5 BYTE "Vector is ", 0
dispStr6 BYTE "Stack  is ", 0
dispStr7 BYTE "Size of Vector is N = ", 0
dispStr8 BYTE "Stack is empty", 0
dispStr9 BYTE "Stack is not empty", 0
dispStr10 BYTE " before ArrayToStack", 0
dispStr11 BYTE " after ArrayToStack", 0
dispStr12 BYTE " before StackToArray", 0
dispStr13 BYTE " after StackToArray", 0
dispStr14 BYTE " before StackReverse", 0
dispStr15 BYTE " after StackReverse", 0
dispStr16 BYTE "Error ", 2Dh, " Stack is empty: Cannot perform StackToArray", 0
dispStr17 BYTE "I am exiting... Thank you Honey... and Get lost...", 0
spcStr BYTE " ", 0
N BYTE ?
stkFlg BYTE 0
Vector DWORD 20 DUP(?)
tmpVec DWORD 20 DUP(?)
tmpStack DWORD 20 DUP(?)
buffer BYTE 100 DUP(?)
buffSz DWORD ?
tmpNum BYTE 100 DUP(?)

.code
main PROC

OPT:
	lea EDX, dispStr1	; display option to user
	call WriteString
	call ReadInt		; get option from user
	call crlf
	cmp EAX, 0			; user wants to do PROG0
	je PROG0
	cmp EAX, 1			; user wants to do PROG1
	je PROG1
	cmp EAX, 2			; user wants to do PROG2
	je PROG2
	cmp EAX, 3			; user wants to do PROG3
	je PROG3
	lea EDX, dispStr17
	call WriteString
	call crlf
	call WaitMsg
	jmp FIN				; any other input must mean exit

PROG0:					; Get Vector values
	lea EDX, dispStr2 
	call WriteString	; ask user for size
	call ReadInt
	mov N, AL			; save size to N
	call Crlf
	call Crlf
	lea EDX, dispStr3	; 'ask for elements'
	call WriteString
	call WriteDec		; N elements
	lea EDX, dispStr4
	call WriteString	; 'into vector'


;	lea EDX, buffer
;	mov ecx, SIZEOF buffer
;	call ReadString
;	mov buffSz, EAX
;	mov EAX, 0
;	mov ECX, buffSz
;	mov ESI, 0
;CHECKSPC:
;	mov AL, buffer[ESI]
;	cmp AL, ' '
;	JE NEXT
;	mov tmpNum[ESI], AL
;NEXT:
;	inc ESI
;	LOOP CHECKSPC


	movzx ECX, N		; loop input N times
	mov ESI, 0
 GET:
	call ReadDec
	mov Vector[ESI], eax ; store in Vector
	add ESI, 4
	LOOP GET
	call crlf

	lea EDX, dispStr7 ; dispaly for N
	call WriteString
	movzx EAX, N
	call WriteDec
	call crlf

	lea EDX, dispStr5 ; display string for vector
	call WriteString
	lea EDX, spcStr
	call WriteString
	movzx ECX, N
	mov ESI, 0
DISPVEC:
	mov EAX, Vector[ESI]
	call WriteDec
	call WriteString
	add ESI, 4
	LOOP DISPVEC
	call Crlf
	
	lea EDX, dispStr8
	call WriteString
	call Crlf

	call Crlf
	jmp OPT				; Go back to options

PROG1:					; Fill stack from Vector
	mov stkFlg, 1
	movzx ECX, N
	mov ESI, 0
	lea EDX, dispStr5
	call WriteString
	lea EDX, spcStr
FILL:
	mov EAX, Vector[ESI]
	call WriteDec
	call WriteString
	mov tmpStack[ESI], EAX
	PUSH EAX			; Push Vector value into stack
	ADD ESI, 4
	LOOP FILL
	lea EDX, dispStr10
	call WriteString
	call crlf
	
	lea EDX, dispStr6
	call WriteString
	lea EDX, spcStr
	mov ESI, 0
	movzx ECX, N
SETESI:
	add ESI, 4
	LOOP SETESI
	movzx ECX, N
DISPTMP:
	mov EAX, tmpStack[ESI-4]
	call WriteDec
	call WriteString
	SUB ESI, 4
	LOOP DISPTMP
	lea EDX, dispStr11
	call WriteString
	call crlf
	lea EDX, dispStr5
	call WriteString
	lea EDX, spcStr
	movzx ECX, N
THISDISP:
	mov EAX, 0
	call WriteDec
	call WriteString
	LOOP THISDISP
	lea EDX, dispStr11
	call WriteString
	call crlf
	lea EDX, dispStr9
	call WriteString
	call crlf
	call crlf
	jmp opt
	

PROG2:
	cmp stkFlg, 0
	jne NMT
	lea EDX, dispStr16
	call WriteString
	call crlf
	call crlf
	jmp OPT
NMT:
	movzx ECX, N
	mov ESI, 0
POPTOVAR:
	POP eax
	mov tmpVec[ESI], EAX
	add ESI, 4
	LOOP POPTOVAR
	movzx ECX, N
	mov ESI, 0
	lea EDX, dispStr6
	call WriteString
	lea EDX, spcStr
DISPTMP2:
	mov EAX, tmpVec[ESI]
	call WriteDec
	call WriteString
	add ESI, 4
	LOOP DISPTMP2
	lea EDX, dispStr12
	call WriteString
	call crlf
	lea EDX, dispStr5 ; display string for vector
	call WriteString
	lea EDX, spcStr
	call WriteString
	movzx ECX, N
	mov ESI, 0
DISPVEC3:
	mov EAX, Vector[ESI]
	call WriteDec
	call WriteString
	add ESI, 4
	LOOP DISPVEC3
	lea EDX, dispStr13
	call WriteString
	call Crlf
	call crlf
	mov stkFlg, 0
	jmp OPT

PROG3:
	mov stkFlg, 0
	mov ESI, 0
	movzx ECX, N
INSERT:
	mov EAX, Vector[ESI]
	PUSH EAX			; Push Vector value into stack
	ADD ESI, 4
	LOOP INSERT

	lea EDX, dispStr5
	call WriteString
	mov ESI, 0
	movzx ECX, N
	lea EDX, spcStr
DISPVEC2:
	mov EAX, Vector[ESI]
	call WriteDec
	call WriteString
	add ESI, 4
	LOOP DISPVEC2
	lea EDX, dispStr14
	call WriteString
	call crlf
	lea EDX, dispStr9
	call WriteString
	call crlf
	mov ESI, 0
	movzx ECX, N
REVERSE:
	POP EAX
	mov tmpVec[ESI], EAX
	add ESI, 4
	LOOP REVERSE
	mov ESI, 0
	movzx ECX, N
	lea EDX, dispStr5
	call WriteString
	lea EDX, spcStr
DISPREV:
	mov EAX, tmpVEC[ESI]
	call WriteDec
	call WriteString
	add ESI, 4
	LOOP DISPREV
	lea EDX, dispStr15
	call WriteString
	call crlf
	lea EDX, dispStr8
	call WriteString
	call crlf
	call crlf
	jmp OPT
	call crlf


FIN:   
	exit

main ENDP
END main