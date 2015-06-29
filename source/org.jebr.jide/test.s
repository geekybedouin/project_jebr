x:
 addi $sp,$sp,-64 
sw $t0, 60($sp) 
sw $t1, 56($sp) 
sw $t2,52($sp)
sw $t3, 48($sp)
sw $t5, 40($sp)
sw $t6, 36($sp)
sw $t7, 32($sp)
sw $s0, 28($sp)
sw $s1, 24($sp)
sw $s2, 20($sp)
sw $s3, 16($sp)
sw $s4, 12($sp)
sw $s5, 8($sp)
sw $s6, 4($sp)
sw $s7, 0($sp)


lw $s7, 0($sp)
lw $s6, 4($sp)
lw $s5, 8($sp)
lw $s4, 12($sp)
lw $s3, 16($sp)
lw $s2, 20($sp)
lw $s1, 24($sp)
lw $s0, 28($sp)
lw $t7, 32($sp)
lw $t6, 36($sp)
lw $t5, 40($sp)
lw $t4, 44($sp)
lw $t3, 48($sp)
lw $t2,52($sp)
lw $t1, 56($sp) 
lw $t0, 60($sp) 
 addi $sp,$sp,64 
jr $ra

.data

c:  .word   1,2,3,4

.text
.globl main
main:

