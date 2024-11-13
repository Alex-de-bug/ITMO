#include <iostream>
#include <vector>

using namespace std;

int partition(vector<string>& arr, int low, int high) {
    string pivot = arr[high]; // Опорный элемент
    int i = low - 1; // Индекс элемента, который меньше или равен опорному элементу

    for (int j = low; j < high; j++) {
        string first_pair = arr[j] + pivot;
        string second_pair = pivot + arr[j];
        if (first_pair > second_pair) {
            i++;
            swap(arr[i], arr[j]);
        }
    }
    swap(arr[i + 1], arr[high]);
    return i + 1;
}

void quickSort(vector<string>& arr, int low, int high) {
    if (low < high) {
        int pi = partition(arr, low, high);

        quickSort(arr, low, pi - 1); // Сортировка левой части
        quickSort(arr, pi + 1, high); // Сортировка правой части
    }
}

int main() {
    vector<string> arr = {"10", "7", "8", "009", "1", "5"};
    quickSort(arr, 0, arr.size() - 1);
    for (string x : arr) {
        cout << x;
    }
    cout << endl;
    return 0;
}

