TITLE assignment1 part 2    (assign2_part2.asm)
; Program Description: part 2 of assignment 2
;Author: Keerthana
;Creation Date: 10/15/2019
; Revisions: 10/17/19
; Date : 10/15/2019

INCLUDE Irvine32.inc

.data
MAXLENGTH = 129
prompt BYTE "Enter a string of at most 128 characters: ",0
prompt2 BYTE " ",0
prompt3 BYTE "Here it is in LOWERCASE adn UPPERCASE Flipped and in reverse order: ",0
prompt4 BYTE "There are ", 0
prompt5 BYTE " upper-case letters after conversion. ", 0
prompt6 BYTE " characters in the string "
myStr BYTE MAXLENGTH+1 DUP(?) ;duplicates of the original string and stors the input from the user. 
myStr2 BYTE MAXLENGTH+1 DUP(?) ;duplicates of the original string and stores the reversed string as it is gets case converted
strSize DWORD ?
numCharac DWORD 0 ; holds count of the number of characters in the string
upprSize DWORD 0 ;holds count of the numbers of characters converted to Uppercase.

.code
main PROC

        ;show prompt for asking input from the user
		   mov  edx,OFFSET prompt  
		   call WriteString
		   call crlf
        
        ;Read string from the user
		   mov edx, OFFSET myStr
		   mov ecx, (SIZEOF myStr) - 1
		   call ReadString 
		   mov strSize, eax

	   ;show the prompt before printing the reverse output
		   mov  edx,OFFSET prompt3 
		   call WriteString
		   call crlf

	   ;print string in reverse order
		;Set counter to string size
			mov ecx,strSize
			mov esi,0

			
	;loop to check if uppercase or lowercase and then covert it and stores it in the reverse string data variable mystr2
        L1:     

        ;check to see if character is uppercase
		   movzx eax,myStr[ecx-1]
		   cmp eax,'A'                             ;if(eax >= 'A'), then uppercase
		   jge is_capital                  ;if uppercase, goto is_capital

	   ;checks to see if the character is lowercase
		cmp eax, 'a'                      
		jge is_lower
		jmp store_char                  ;else store the string
	
	 

          is_capital:
                cmp eax,'Z'                             ;if(eax <= 'Z'), then uppercase
                jle inc_lower                   ;then the character is between 'A' and 'Z' so uppercase, jump to inc_lower  
        
	    is_lower:
                cmp eax,'z'                             ;if(eax <= 'z'), then lowercase
                jle inc_upper                   ;then the character is between 'a' and 'z' so uppercase, jump to inc_uppercase
           


         inc_lower:
                ;convert to lower case
                mov al,myStr[ecx-1]
                add al,20h ;converts to lower case 
                mov myStr[ecx-1],al  

			 jmp skip
	    inc_upper:
                ;convert to upper case
                mov al,myStr[ecx-1]
                sub al,20h ;converts to upper case 
                mov myStr[ecx-1],al  
			 inc upprSize

			 
         skip:

         store_char:
			 inc numCharac
                movzx eax,myStr[ecx-1]
                mov myStr2[esi],al      ; store string in variable myStr2 in reverse order      
                inc esi
			

        Loop L1

	   mov ecx, upprSize
	   mov esi,0



     ;Display the reversed string
          mov edx,OFFSET myStr2
          call Writestring
          call Crlf
		call crlf
		
	;Display to print the number of UPPERCASE Characters in the result string
		mov edx, OFFSET prompt4
		call WriteString
		mov eax, upprSize
		call WriteInt
		mov edx, OFFSET prompt5
		call WriteString
		call crlf

	;Display to print the number of Character in the string
		
		mov edx, OFFSET prompt4
		call WriteString
		mov eax, numCharac
		call WriteInt
		mov edx, OFFSET prompt6
		call WriteString
		call crlf
		call crlf

        exit
main ENDP
END main
