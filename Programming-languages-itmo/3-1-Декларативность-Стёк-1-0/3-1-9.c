/*

struct array_int {
  int64_t* data;
  size_t size;
};

struct stack {
  size_t count;
  struct array_int data;
};

*/

void interpret(struct vm_state* state, ins_interpreter * const  (actions)[]) {
  for (; state->ip ;) {
    const union ins* ins = state->ip;

    const struct ins_descr* ins_descr = instructions + ins->opcode;
      
      /* mnemonic argtype affects_ip stack_min stack_delta
[BC_PUSH] = { "push", IAT_I64 , false , 0 , 1 },
[BC_IADD] = { "iadd", IAT_NOARG, false , 2 , -1 },
[BC_POP] = { "pop", IAT_NOARG, false , 1 , -1 },
*/


    if(((int64_t)((state -> data_stack).count) + (int64_t)(ins_descr -> stack_delta)) > 5){
        printf("Stack overflow\n");
        return;
    }
    if( ( (int64_t)((state -> data_stack).count) - (int64_t)(ins_descr -> stack_min) ) < 0 ){
        printf("Stack underflow\n");
        return;
    }
    //printf("%zu %zu \n", (state -> data_stack).count, ( (int64_t)((state -> data_stack).count) - (int64_t)(ins_descr -> stack_min) ));

    actions[ins->opcode](state);

    if (!ins_descr->affects_ip) { state->ip = state->ip + 1; }
  }
}
