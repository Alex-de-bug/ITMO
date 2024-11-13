#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

struct Beer {
    char letter;
    int cost;
};

bool compare(const Beer& a, const Beer& b) {
    return a.cost > b.cost;
}

int main() {
    string stroka;
    cin >> stroka;

    vector<Beer> korob_piva(26);

    // Вводим веса для каждой буквы алфавита
    for (int i = 0; i < 26; i++) {
        korob_piva[i].letter = 'a' + i;
        cin >> korob_piva[i].cost;
    }

    // Сортируем буквы по убыванию веса
    sort(korob_piva.begin(), korob_piva.end(), compare);

    vector<int> counts(26, 0);
    for (char c : stroka) {
        counts[c - 'a']++;
    }

    string result(stroka.size(), ' ');
    int start = 0;
    for (int i = 0; i < 26; i++) {
        char letter = korob_piva[i].letter;
        int count = counts[letter - 'a'];
        for (int j = 0; j < count; j++) {
            result[start] = letter;
            start += 2; // Располагаем символы с максимальным весом на четных позициях
            if (start >= stroka.size()) {
                start = 1; // Если достигли конца строки, начинаем заполнять нечетные позиции
            }
        }
    }

    cout << result << endl;

    return 0;
}

