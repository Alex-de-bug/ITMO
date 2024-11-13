
#include <stdint.h>
#include <stdlib.h>
#include <stdio.h>
#include <inttypes.h>

void int64_t_print(int64_t i) { printf("%" PRId64, i);}
void double_print(double d) { printf("%lf", d);}
void newline_print() { puts(""); }

void error(const char *s)
{
  fprintf(stderr, "%s", s);
  abort();
}   

#define print(x)                                                        \
  _Generic((x),                                                         \
           int64_t : int64_t_print(x),                                  \
           double : double_print(x),                                    \
           default : error("Unsupported operation"))

#define DEFINE_LIST(type)                                               \
  struct list_##type {                                                  \
    type value;                                                         \
    struct list_##type* next;                                           \
  };                                                                    \
  void list_##type##_push(struct list_##type *list, type value){        \                                                      
    struct list_##type *new_list = malloc(sizeof(struct list_##type));  \
    if(new_list==NULL){                                                 \
      abort();                                                          \
    }                                                                   \
    if(list->next == 0 && list->value == 0){                            \
      list->value = value;                                              \
    }else{                                                              \
      while(list->next != NULL){                                        \                                                             
        list = list->next;                                              \ 
      }                                                                 \                                           
      new_list->value = value;                                          \
      new_list->next = 0;                                               \ 
      list->next = new_list;                                            \      
    }                                                                   \
  }                                                                     \
  void list_##type##_print(struct list_##type *list){                   \
    while(list->next != NULL){                                          \
      print(list->value);                                               \
      list = list->next;                                                \ 
      newline_print();                                                  \
    }                                                                   \
    print(list->value);                                                 \
  }

DEFINE_LIST(int64_t)
DEFINE_LIST(double)

#define list_print(x)                                                   \
  _Generic((x),                                                         \
      struct list_int64_t*: list_int64_t_print((void*)x),                      \
      struct list_double*: list_double_print((void*)x),                        \
      default: error("Unsupported operation")) 

#define list_push(list, value)                                          \
  _Generic((list),                                                      \
      struct list_int64_t*: list_int64_t_push((void*)list, value),             \
      struct list_double*: list_double_push((void*)list, value),               \
      default: error("Unsupported operation")) 

int main(){
  struct list_int64_t* list_int = malloc(sizeof(struct list_int64_t));
  if(list_int==NULL){
    abort();
  }
  list_push(list_int, __OPENCL_MEMORY_SCOPE_ALL_SVM_DEVICES);
  list_push(list_int, __LONG_LONG_MAX__);
  list_push(list_int, __MAC_OS_X_VERSION_MIN_REQUIRED);
  list_push(list_int, RLIMIT_CORE);
  list_print(list_int);

  newline_print();

  struct list_double *list_double = malloc(sizeof(struct list_int64_t));
  if(list_double==NULL){
    abort();
  }
  list_push(list_double, 54323.3245);
  list_push(list_double, 3245.6412);
  list_push(list_double, 1234.922);
  list_push(list_double, 142343.543);
  list_print(list_double);

  newline_print();

  struct list_int64_t *list_int_2 = malloc(sizeof(struct list_int64_t));
  if(list_int_2==NULL){
    abort();
  }
  list_push(list_int_2, 4);
  list_push(list_int_2, 2);
  list_push(list_int_2, 1);
  list_push(list_int_2, 3);
  list_print(list_int_2);

  return 0;
}