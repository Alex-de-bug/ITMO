#include <iostream>
#include <unordered_map>
#include <string>
#include <sstream>
#include <vector>
#include <algorithm>
using namespace std;


int partition(vector<char>& arr, int low, int high, unordered_map<char, int>& alphabet) {
    char pivot = arr[high]; 
    int i = low - 1; 
    for (int j = low; j < high; j++) {
        if (alphabet[pivot] < alphabet[arr[j]] ) {
            i++;
            swap(arr[i], arr[j]);
        }
    }
    swap(arr[i + 1], arr[high]);
    return i + 1;
}

void quickSort(vector<char>& arr, int low, int high, unordered_map<char, int>& alphabet) {
    if (low < high) {
        int pi = partition(arr, low, high, alphabet);

        quickSort(arr, low, pi - 1, alphabet);
        quickSort(arr, pi + 1, high, alphabet);
    }
}

int main() {
    string str, costs;
    
    cin >> str;    
    //cin.ignore(); 
    
    //getline(std::cin, costs);
    //istringstream iss(costs);

    unordered_map<char, int> alphabet;

    //for (int32_t &weight : weights) cin >> weight;

    for (char letter = 'a'; letter < 'z'; ++letter) {
        std::cin >> alphabet[letter];
    }

    vector<char> letters; 
    for (char letter : str) {
        letters.push_back(letter);
    }
    quickSort(letters, 0, letters.size() - 1, alphabet);
    string left;
    string center;
    string right;

    for (int i = 0; i < letters.size() - 1; i++){
        if (letters[i] == letters[i + 1] && ((left.size()!=0 && left[left.size() - 1] != letters[i]) || (left.size()==0))){
            left+=letters[i];
            i++;
        } else center+=letters[i];
    }
    if (letters.size() > center.size() + 2*left.size()) center+=letters[letters.size() - 1];

    right = left;
    std::reverse(right.begin(), right.end());
    std::cout << left + center + right << std::endl;
    return 0;
}

