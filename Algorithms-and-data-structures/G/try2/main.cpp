#include <iostream>
#include <vector>
#include <cstdint>

using namespace std;

struct Br
{
    int cost;
    int count;
};

int findMaxCostBrIndex(const vector<Br> &korob_p)
{
    int maxCost = -1;
    int maxCostIndex = 0;

    for (size_t i = 0; i < korob_p.size(); ++i)
    {
        if (korob_p[i].count > 1 && korob_p[i].cost > maxCost)
        {
            maxCost = korob_p[i].cost;
            maxCostIndex = i;
        }
    }

    return maxCostIndex;
}

int main()
{
    string stroka, costs_p;
    cin >> stroka;

    vector<Br> korob_p(26); //'a'+i

    for (int i = 0; i < 26; i++)
    {
        cin >> korob_p[i].cost;
    }

    for (int i = 0; i < stroka.size(); i++)
    {
        korob_p[stroka[i] - 'a'].count++;
    }

    int size_ps = stroka.size();
    string left_pochka;
    string center_pochka;

    while (size_ps > 0)
    {
        size_ps--;
        int max_l_p = findMaxCostBrIndex(korob_p);
        while (korob_p[max_l_p].count > 1)
        {
            left_pochka += 'a' + max_l_p;
            korob_p[max_l_p].count -= 2;
            while (korob_p[max_l_p].count > 0)
            {
                center_pochka += 'a' + max_l_p;
                korob_p[max_l_p].count--;
            }
        }
        if (korob_p[max_l_p].count == 1)
        {
            center_pochka += 'a' + max_l_p;
            korob_p[max_l_p].count--;
        }
    }

    for (int i = 0; i < 26; i++)
    {
        if (korob_p[i].count == 1)
        {
            center_pochka += 'a' + i;
            korob_p[i].count--;
        }
    }

    cout << left_pochka << center_pochka;
    for (int i = left_pochka.size() - 1; i >= 0; i--)
    {
        cout << left_pochka[i];
    }

    return 0;
}
