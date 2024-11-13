#include <iostream>
#include <vector>
#include <cstdint>

using namespace std;

struct Beer {
    int cost;
    int count;
};

int findMaxCostBeerIndex(const vector<Beer>& korob_piva) {
    int maxCost = -1;
    int maxCostIndex = 0;

    for (size_t i = 0; i < korob_piva.size(); ++i) {
        if (korob_piva[i].count>1 && korob_piva[i].cost > maxCost) {
            maxCost = korob_piva[i].cost;
            maxCostIndex = i;
        }
    }

    return maxCostIndex;
}

int main() {
    string stroka, costs_piva;
    cin >> stroka;

    vector<Beer> korob_piva(26); //'a'+i 

    for (int i = 0; i < 26; i++) {
        cin >> korob_piva[i].cost;
    }
    
    for (int i = 0; i < stroka.size(); i++){
        korob_piva[stroka[i]-'a'].count++;
    }   
    
    int size_podpivas = stroka.size();
    string left_pochka;
    string center_pochka;

while(size_podpivas>0){
        size_podpivas--;
        int max_liter_piva = findMaxCostBeerIndex(korob_piva);
        while(korob_piva[max_liter_piva].count > 1){
            left_pochka += 'a' + max_liter_piva;
            korob_piva[max_liter_piva].count-=2;
            while(korob_piva[max_liter_piva].count > 0){
                center_pochka += 'a' + max_liter_piva;
                korob_piva[max_liter_piva].count--;
            }
        }
        if(korob_piva[max_liter_piva].count == 1){
            center_pochka += 'a' + max_liter_piva;
            korob_piva[max_liter_piva].count--;
        }
    }
    
    for(int i = 0; i < 26; i++){
        if(korob_piva[i].count == 1){
            center_pochka += 'a' + i;
            korob_piva[i].count--;
        }
    }
    
    cout << left_pochka << center_pochka;
    for( int i = left_pochka.size()-1; i>=0; i--){
        cout << left_pochka[i];
    }

    return 0;
}


