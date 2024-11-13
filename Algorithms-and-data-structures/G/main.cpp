#include <iostream>
#include <unordered_map>
#include <string>
#include <sstream>
#include <vector>
#include <algorithm>

int main() {
    std::string str, costs;
    
    std::cin >> str;    
    std::cin.ignore(); 
    
    std::getline(std::cin, costs);
    std::istringstream iss(costs);

    std::unordered_map<char, int> alphabet;
    char letter = 'a';
    int num;
    while (iss >> num) {
        alphabet[letter] = num;
        ++letter;
    }

    std::vector<char> letters; 
    for (char letter : str) {
        letters.push_back(letter);
    }

    std::sort(letters.begin(), letters.end(), [&alphabet](char a, char b) {
        return alphabet[a] >= alphabet[b];
    });
    
    std::string left;
    std::string center;
    std::string right;
    

    for (size_t i = 0; i < letters.size(); ++i) {
        if (i + 1 < letters.size() && letters[i] == letters[i + 1]) {
            left += letters[i];
            ++i; 
        } else {
            center += letters[i];
        }
    }
    

    right = left;
    std::reverse(right.begin(), right.end());
    std::cout << left + center + right << std::endl;

    return 0;
}

