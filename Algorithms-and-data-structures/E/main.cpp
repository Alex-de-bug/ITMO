#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

int main()
{
    int N, K;
    cin >> N >> K;

    vector<int> stalls(N);
    for (int i = 0; i < N; ++i)
    {
        cin >> stalls[i];
    }

    int left = 1;
    int right = stalls.back() - stalls.front();
    int result;

    for (;;)
    {
        if (left > right)
        {
            cout << result << endl;
            return 0;
        }
        int mid = left + (right - left) / 2;
        int sit = 1;
        int last_sit = 0;
        for (int i = 1; i < stalls.size(); ++i)
        {
            if (stalls[i] - stalls[last_sit] >= mid)
            {
                sit++;
                last_sit = i;
                if (sit == K)
                {
                    result = mid;
                    left = mid + 1;
                    break;
                };
            }
        }
        if (sit != K)
        {
            right = mid - 1;
        }
    }
}
