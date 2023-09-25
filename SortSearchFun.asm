;Austin Snyder
;49720465
;I pledge that this submission is solely my work, and that I have neither given, nor received help from anyone.

       segment .data
a               db       7,5,2,3,6          ;array31
size            db       5                  ;arraysize
val             dq       0                  ;value to search for
scanf_format    db    "%ld",0               ;scanning input format
printf_found    db    "Value %ld found in location %ld",0x0a,0    ;found format
printf_notfound db    "Value %ld not found in array",0x0a, 0      ;not found format

 
        ; in rcx, rdx, r8, r9   
       segment .text
       global  main                       ;Tell linker about main 
       global  sort                       ;Tell linker about sort
       global  search                     ;Tell linker about search
       extern  scanf                      ;external scanf, printf
       extern  printf
main:
       push    rbp                        ;preserve rbp register
       mov     rbp,  rsp                  ;make equal
       frame   0, 0, 3                    ;0 in main, 0 local, space for max 3 params out because of scan or printf_found case 
       sub     rsp,  frame_size           ;subtract size to allocate memory for local vars
       
       call    sort                       ;call sort function
       
       lea     rcx,  [scanf_format]       ;move adress of format to rcx for scanf
       lea     rdx,  [val]                ;load adress of val to rdx for scanf
       call    scanf                      ;call scanf, value from terminal -> val             
       
       mov     rcx,  [val]
       call    search                     ;if found: RAX = INDEX else: RAX = -1
       
       cmp      rax, -1                   ;compare returned index of search to -1
       je       equal                     ;if its equal jump to equal
       
       lea      rcx, [printf_found]       ;load format for printf
       mov      rdx, [val]                ;load the searched for value to rdx for printf
       mov      r8,  rax                  ;move the index to r8 for printf
       call     printf                    ;call printf
       jmp      out_main
       
equal:
       lea      rcx,  [printf_notfound]   ;load format param to rcx
       mov      rdx,  [val]               ;load val to rdx
       call     printf                    ;call printf

out_main:       
       leave                              ;unwind
       ret                                ;return
       
       
SortingAlgorithm:                      ;START OF THE SORTING FUNCTION!

sort:
     
       push    rbp                     ;preserve rbp
       mov     rbp, rsp
       frame   0, 0, 0                 ;nothing going in, nothing local, nothing going out.
                                       ;if need use of rcx, rdx, r8, r9, or rax make locals to preserve on stack                                      
       sub     rsp, frame_size         
    
       xor rax, rax                    ;Rax/eax/al is counter
       xor r10, r10                    ;r10 will hold temp
       xor r11, r11                    
       xor rbx, rbx                    ;will hold j
         
for_loop:
    
       cmp al, byte [size]                 ;compare counter to array size
       jge end_for                         ;jumpto end if done
    
       mov     r10b, [a+eax]               ;move array[i] to temp, r10b
       mov     ebx, eax                    ;move i to j
       dec     ebx                         ;j=i-1. 
    
while_loop:
    
       cmp     r10b, [a+ebx]               ;compare temp to a[j]
       jge     end_while                   ;jump greater or equal endwhile
       cmp     ebx, 0                      ;compare j to 0... j-0
       jl      end_while                   ;jump if j < 0
    
       mov    r11b, [a+ebx]                ;move a[j] to r11b
       mov    [a+ebx+1], r11b              ;move a[j] to a[j+1]
    
       add ebx, -1                         ;decrement
       jmp while_loop
    
    
end_while:
    
       mov [a+ebx+1], r10b                ;move temp to a[j+1]
       inc al                             ;increment counter i
       jmp for_loop                       ;jump to start of for loop
    
end_for:                                                           
   
       leave                              ;unwind
       ret                                ;return


SearchingAlgorithm:                       ;START OF THE SEARCHING FUNCTION in:value, out:index

search:
       num      equ     local1            ;make local variable num
       push     rbp                       ;push base pointer to stack to save
       mov      rbp, rsp                  ;even stack pointer and base pointer
       frame    1, 1, 1                   ;value comes in, 1 local,and index goes out 1,1,1
       sub      rsp, frame_size           ;make room for variables
       
       mov      rax, -1                   ;set RAX = -1 from start in case value not found
       mov      [rbp+num], rcx            ;preserve original rcx on stack
       
       xor      r10, r10                  ;zero out r10 for counter use
       mov      r10, -1                   ;set up counter i=-1 for i++ at start of loop.
       
for_loop2:
       inc      r10                       ;i++
       cmp      r10b, byte [size]         ;cmp counter to size
       jge      end_for2                  ;if its equal or counter bigger end
    
      
if:
       cmp      cl, byte [a+r10]          ;compare value in cl (lower rcx) to a[i]
       jnz      for_loop2                 ;if comparison is not equal jmp back up
       mov      rax, r10                  ;if it is equal move index to rax.
          
end_for2:
     
       leave                        ;unwind
       ret                          ;return
                                                                                                                             
                                                                                                                      
      
                               
      
