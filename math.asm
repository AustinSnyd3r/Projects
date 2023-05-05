 segment .data

a        db     -3, 2, 4    ; array of three
b        dq     -8          ; variable
quot     dq     0           ; quotient
rem      dq     0           ; remainder

       segment .text
       global  main         ; Tell linker about main
     
main:
                                ; finds the product of top of equation
    movsx   rax, byte [a]       ; move first num of array a to rax. sign extended   
    imul    byte [a+1]          ; multiply second num
    imul    byte[a+2]           ; multiple third num                             
    mov     rdx, 0              ; clear out rdx, will hold remainder
    
    mov     rsi, qword [b]      ; move value of b to rsi register
    cqo                         ; quad-octo word, extends to rax(quotient):rdx(remainder) for division   
    idiv    rsi                 ; divide rax by rsi, (product(a)/b)
    
    neg     rax                 ; negate twice to set sign flag
    neg     rax                 ; if the value is negative
    
    cmovge  r8,  rax            ; we consider 0 positive, move quot to r8 if >= 0
    cmovl   r9,  rax            ; if rax (quot) < 0, mov to r9
   
    mov     [quot],   rax       ; move quotient to quot 
    mov     [rem],    rdx       ; move remainder to rem.
    
    xor     rax, rax            ; zero out all registers
    xor     rdx, rdx
    xor     rsi, rsi
    xor     r8, r8
    xor     r9, r9
    ret                         ;return
