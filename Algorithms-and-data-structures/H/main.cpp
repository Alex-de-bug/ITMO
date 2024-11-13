#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

int main()
{

    int N, K;
    cin >> N >> K;
    int sum = 0;
    vector<int> products(N);
    for (int i = 0; i < N; ++i)
    {
        cin >> products[i];
        sum += products[i];
    }

    sort(products.begin(), products.end(), greater<int>());
    for (int i = K - 1; i < N; i += K)
    {
        sum -= products[i];
    }
    cout << sum;
    return 0;
}
