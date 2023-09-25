;Austin Snyder
;49720465
;I pledge that this submission is solely my work,
; and that I have neither given, nor received help from anyone.  
     
                   
             segment .data
a       db      7,5,2,3,6           ;array to sort
size    db      5                   ;size of array
loc     db      -1
value   db      7

       segment .text
       global  main                    ; Tell linker about main
   
main:
      
    xor     rax, rax
    xor     rbx, rbx
    xor     r8, r8
    xor     r9, r9    
    mov     eax, 1                      ;set up counter variable   

SortingAlgorithm:
     
for_loop:
                                     
    cmp   al, byte [size]             ;compare counter to array size
    jge    end_for                    ;jump to end of for if its done
    
    mov r8b, [a+eax]                  ;r8b will be temp, moves a[i] to temp
    mov ebx, eax                      ;set up a "j" = "i"
    dec ebx                           ;j = i-1 
    
while_loop:
    
    cmp  r8b, [a+ebx]                 ;compare temp to array[j]
    jge  end_while                    ;if equal, jump to end while_loop
    cmp  ebx, 0                       ;compare j to 0, j-0
    jl   end_while                    ;jump if j is less than 0
    
    mov   r9b, [a+ebx]                ;move array[j] to r9b
    mov  [a+ebx+1], r9b               ;move array[j] to array[j+1]
    
    add ebx, -1                       ;decrement j    
    jmp while_loop                    ;jump into while 

end_while:

    mov [a+ebx+1], r8b                ;move temp to array[j+1]
    inc al                            ;i++
    jmp for_loop                      ;jump for loop      
end_for:



Searching_Alg:
    
    xor r8, r8                      ;zero regs
    xor rax, rax                   
    mov al, -1                      ;set up counter. i = -1 to i++ at start of loop. starts i=0
    mov r8b, [value]                  ;move num to r8b
    
for_loop2:
    inc     al                      ;i++
    cmp     al, byte [size]         ;compare counter to size
    jge     end_for2                ;if its equal or counter bigger, quit
    
if:
    cmp     r8b, [a+eax]            ;compare num to current array position
    jnz     for_loop2               ;jump to for loop if it isnt zero
    
    mov    [loc], al                ;if it is zero move index to location
    
end_for2:

    xor     rax, rax                ;zero out registers
    xor     rbx, rbx
    xor     r9, r9
    xor     r8, r8
    
    ret                             ;return
    











