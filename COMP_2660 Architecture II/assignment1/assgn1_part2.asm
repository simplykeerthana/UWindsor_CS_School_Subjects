TITLE assingment 1 part 2   (assign1_part2.asm)

; Program Description: part 2 of assignment 1
;Author: Keerthana
;Creation Date: 10/7/2019
; Revisions:
; Date : 10/7/2019

INCLUDE Irvine32.inc

.data
bigEndian       BYTE            ?, ?, ?, ?
littleEndian    DWORD  12345678h       

.code
		main   PROC
			  mov al,[littleEndian]
			  mov BYTE PTR [bigEndian],al

			  mov al,[littleEndian + 1]
			  mov BYTE PTR [bigEndian+1],al

			  mov al,[littleEndian+2]
			  mov BYTE PTR [bigEndian+2],al

			  mov al,[littleEndian + 3]
			  mov BYTE PTR [bigEndian+3],al               
			  mov     bigEndian, ax                       
			  call WriteInt
		exit

		main   ENDP
END main