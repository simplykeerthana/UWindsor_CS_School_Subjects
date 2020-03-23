TITLE Ass4-Q3 (Ass4-Q3.asm)

; team members
;Noah Campbell 104536111
;Keerthana Madhavan - 104995097
;Austin Malaquias - 104586622


INCLUDE Irvine32.inc

.data

	strPrompt BYTE "What do you want to do, Lovely? ", 0
	decimalArray BYTE 8 DUP(?), 0
	hexaDecArray BYTE 10 DUP(?), 0

	decimalPrompt BYTE "Enter a unsigned (positive) 32-bit decimal number: " ,0
	hexaDecPrompt BYTE "Enter a string to convert to binary: ",0
	exitString BYTE "Get Lost, you Sweetey Honey Bun",0
	endString BYTE "Thank you, Sweetey Honey Bun", 0

.code

main PROC

	mov edx, OFFSET strPrompt
	call WriteString ; ask the user what they want to do
	call crlf

	call ReadChar ; reads in W or w or R or r
	call WriteChar

	cmp al, 57h ; if it is W
	je DecToHex ; if it is W jmp to convert from decimal to hexadecimal
	cmp al, 77h ; if w
	je DecToHex
	cmp al, 52h ; if it is R jmp to convert from hexadecimal to binary
	je HexToBin
	cmp al, 72h ; if r
	je HexToBin
	jmp Exit_Msg


DecToHex:
	call crlf
	mov eax, 0 ; empty register
	mov edx, OFFSET decimalPrompt
	call WriteString
	call crlf
	call ReadDec ; read an decimal from the user
	
	mov ebx, eax
	call HexOutput
	jmp Goodbye_Msg
	
HexToBin:
	call crlf
	mov edx, OFFSET hexaDecPrompt
	call WriteString
	call HexInput
	jmp Goodbye_Msg
	

jmp exitProgram	
	
Exit_Msg: 
	mov edx, OFFSET exitString
	call WriteString
	call crlf
	jmp exitProgram


Goodbye_Msg:
	call crlf
	mov edx, 0
	mov edx, OFFSET endString
	call WriteString
	call crlf

exitProgram:
	call crlf
	call WaitMsg
	exit 
	main ENDP	

HexOutput PROC

			mov ecx, 8
			mov esi, OFFSET decimalArray

		Begin_Rotation:
			ROL ebx, 4 
			mov DL, BL
			AND DL, 0Fh ; 5 bits from the input
	
			cmp DL, 0Ah; checks the converted number
			jb less_ten ; if the number is less than 10 
			add DL, 37h 

			mov [esi], DL
			jmp next

			less_ten:
				add DL, 30h
				mov [esi], DL
				jmp next
		next: 
			ADD esi, TYPE decimalArray
		LOOP Begin_Rotation

		mov BYTE PTR[esi], 68h ; to add h in the end

		mov edx, OFFSET decimalArray
		call WriteString

		ret

HexOutput ENDP

HexInput PROC
		;mov edx, OFFSET hexaDecPrompt
		;call WriteString

		mov edx, OFFSET hexaDecArray
		mov ecx, sizeof hexaDecArray
		call ReadString
		xor eax, eax

		Read_Next:
			mov bl, [edx]
			cmp bl, 'h'
			je Finish_Input
			cmp bl, 'A'
			jb It_Number
			sub bl, 37h
			shl eax, 4
			OR al, bl
			inc edx
		jmp Read_Next

		It_Number: 
			sub bl, 30h ; convert the numbers 
			shl eax, 4
			OR al, bl
			inc edx
		jmp Read_Next

		Finish_Input:
			call WriteBin
			call crlf

		ret
HexInput ENDP

END main	

