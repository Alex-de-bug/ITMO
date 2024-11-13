#include <iostream>
#include <string>

void removeElements(char characters[][2], int& length, int index) {
    for (int i = index; i < length - 2; ++i) {
        characters[i][0] = characters[i + 2][0];
        characters[i][1] = characters[i + 2][1];
    }
    characters[length - 2][0] = (char) 0;
    characters[length - 2][1] = 0;
    characters[length - 1][0] = (char) 0;
    characters[length - 1][1] = 0;
    length -= 2;
}

int main() {
    std::string input_str;

    std::cin >> input_str;

    int length = input_str.length();
    int traps[input_str.length()/2];
    char characters[input_str.length()][2];
    int uppercase_count = 0; 
    int lowercase_count = 0; 

    for (int i = 0; i < input_str.length(); ++i) {
        characters[i][0] = input_str[i];
        if (isupper(input_str[i])) {
            uppercase_count++;
            characters[i][1] = uppercase_count;
        } else if (islower(input_str[i])) {
            lowercase_count++;
            characters[i][1] = lowercase_count;
        }
    }

    for (int i = 0; i < length; i++) {
        if ((isupper(characters[i][0]) && islower(characters[i+1][0]) ||
             islower(characters[i][0]) && isupper(characters[i+1][0])) &&
             tolower(characters[i][0]) == tolower(characters[i+1][0])) {
            if(isupper(characters[i][0])){
                traps[characters[i][1]-1] = characters[i+1][1]; 
            }
            else if(isupper(characters[i+1][0])){
                traps[characters[i+1][1]-1] = characters[i][1]; 
                }
            removeElements(characters, length, i);
            i -= 2;
        }
    }
    for (int i = 0; i < input_str.length(); ++i) {
        if (characters[i][0] != 0 || characters[i][1] != 0) {
            std::cout << "Impossible";
            return 0;
        }
    }
    std::cout << "Possible" << std::endl;
    for (int i = 0; i < input_str.length() / 2; ++i) {
        std::cout << traps[i] << " ";
    }
    return 0;
}
