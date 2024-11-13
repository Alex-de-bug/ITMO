#include <stdio.h>
#include <stdlib.h>

//создание стека
struct Node {
    int data;
    struct Node* next;
};


// Функция push добавляет новый элемент со значением data на вершину стека.
void push(struct Node** head, int data) {
    // Выделяем память для нового узла
    struct Node* newNode = (struct Node*) malloc(sizeof(struct Node));
    // Записываем значение в новый узел
    newNode->data = data;
    // Связываем новый узел с предыдущим узлом
    newNode->next = *head;
    // Новый узел становится вершиной стека
    *head = newNode;
}

//подпрограмма
int under_program() {
    int arg = head->arg; //вызов аргумента со стека

    int arr[2] = {0x0B67, 0x0084}; // создание массива с двумя элементами типа int
    int *const_num = arr; // создание указателя на начало массива

    if (arg < 0 || arg >= *const_num) {
        push(head, *const_num) //кладём элемент на вершину стека
        return 0;
    }

    int result = (arg << 2) + arg - *(const_num+1); //подсчёт результата
    push(head, result) //кладём элемент на вершину стека
    return 0;
}

//начало основной программы
struct Node* head = NULL; //создание стека пустого

int main_arr[3] = {0xZZZZ, 0xYYYY, 0xXXXX}; // создание массива с двумя элементами типа int
int *main_arg = main_arr; // создание указателя на начало массива
int ans = 0; //создание R


void (*program) (void); // определяем указатель на функцию
program = under_program;  // указатель указывает на функцию under_program

push(head, (*main_arg+0x0001)); //кладём на вершину стека первый элемент массива с учётом +1
program(); //вызов подпрограммы
int f_y_plus_one = head->f_y_plus_one; //вызов результата подпрограммы со стека
ans+= f_y_plus_one; //прибавление результата

push(head, (*(main_arg+1)-0x0001)); //кладём на вершину стека второй элемент массива с учётом -1
program(); //вызов подпрограммы
int f_y_plus_two = head->f_y_plus_two; //вызов результата подпрограммы со стека
ans+= f_y_plus_two; //прибавление результата

push(head, (*(main_arg+2))); //кладём на вершину стека третий элемент массива
program(); //вызов подпрограммы
int f_y_plus_three = head->f_y_plus_three; //вызов результата подпрограммы со стека
ans+= f_y_plus_three+0x0001; //прибавление результата

printf(ans);