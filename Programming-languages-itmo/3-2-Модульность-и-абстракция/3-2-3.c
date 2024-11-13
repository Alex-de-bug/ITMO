struct list {
  item value;
  struct list *next;
};

struct stack_int {
  struct list *items;
};

static struct list* c(item i, struct list *next) {
  struct list *result = malloc(sizeof(struct list));
  *result = (struct list){i, next};
  return result;
}

static struct stack_int stack_int_default() {
  return (struct stack_int){.items = NULL};
}

struct stack_int* stack_int_create() {
  struct stack_int *const result = malloc(sizeof(struct stack_int));
  *result = stack_int_default();
  return result;
}

static void list_free( struct list* l ) {
  while (l) {
    struct list *cur = l;
    l = l->next;
    free(cur);
    }
}

void stack_int_destroy(struct stack_int *s) {
    list_free( s -> items );
    free( s );
}

bool stack_int_empty(struct stack_int const *s) { 
    if(s -> items == NULL){
        return true;
    }
    return false;
}

bool stack_int_full(struct stack_int const *s) { 
    struct stack_int* new = stack_int_create();
    if(new != NULL){
        stack_int_destroy(new);
        return false;
    }
    return true;
}

bool stack_int_push(struct stack_int *s, item i) {
    if(!stack_int_full(s)){
        s->items = c(i, s->items);
        return true;
    }
    return false;
}

struct maybe_item stack_int_pop(struct stack_int *s) {
    if (stack_int_empty(s)) {
      return none_int;
    };
    const struct maybe_item ans = some_int((s -> items -> value).value);
    struct list* old = s -> items;
    s -> items = s -> items -> next;
    free(old);
    return ans;
}

static void stack_int_foreach(struct stack_int const *s, void (f)(item)) {
  for (struct list *l = s->items; l; l = l->next)
    f(l->value);
}

static void print_int64_cr(item i) { printf("%" STACK_ITEM_PRI "\n", i.value); }

// Заметьте, что и здесь мы смогли оставить функцию "как есть"
// просто переписав foreach, через который она реализована
// Это показывает, что обход разных структур данных, свертки (fold)
// и другие популярные функции высшего порядка на самом деле изолируют кусочек
// логики, отличающие работу с одними и теми же данными, структурироваными по-разному.
// Строгое обоснование этого дают теория категорий и алгебраические типы данных.
void stack_int_print(struct stack_int const *s) {
  stack_int_foreach( s, print_int64_cr );
}
