;Austin Snyder
;49720465
;I pledge that this submission is solely my work, 
;and that I have neither given, nor received help from anyone.             
        
          segment .data
a       db      1100b   ;multiplicand
b       db      0110b   ;multiplier
size    db      7      ;size of multiplier
result  dw      0      ;result

       segment .text
       global  main                   
       
main:                           ;Program does 6*12. Answer: 72.
    xor     rax, rax            ;zero out regs
    xor     r8, r8              
    xor     r9, r9
    xor     r10, r10            
    xor     rcx, rcx            ;cl register
      
    mov     cl, [size]          ;move size to cl
    movzx   r9,  byte [b]        
    add     cl, 1               ;add 1 so loop can remove 1 from start
    
loop_Start:  
   movzx   r8,  byte [a]        ;move ORIGINAL muliplicand each time    
   add cl, -1                   ;decrements loop counter/position
   bt   r9, rcx                 ;tests bit of r9 at bit position 2
   setc r10b                    ;moves carry flag to lower part r10   
   cmp  r10b, 1                 ;r10b-1, set flags
   jz   calculation             ;jump if r10b - 1 set zero, meaning 1 in position cl
   
   add  cl, 0                   ;add to cl to set flags, checking if it is >= 0
   jg   loop_Start              ;jump if > 0
   je   exit_loop               ;jump if equal to 0
   
calculation:                 
   shl  r8, cl                  ;shift multiplicand by current size
   add  rax, r8                 ;add result and the shifted value
   jge  loop_Start              ;jump if cl, or the size, is greater than or equal to 0

exit_loop:
    mov     [result], rax       ;moves the result from RAX to RESULT
    
    xor     rax, rax            ;zero out regs
    xor     r8, r8              
    xor     r9, r9
    xor     r10, r10
    xor     rcx, rcx            
    ret                         ;return
