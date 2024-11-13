#include <iostream>
#include <queue>

using namespace std;

int main()
{
    int count;
    cin >> count;

    string type;
    int goblin;
    queue<int> first;
    deque<int> second;
    for (int i = 0; i < count; i++)
    {
        cin >> type;
        if (type == "-")
        {
            if (!first.empty())
            {
                cout << first.front() << endl;
                first.pop();
            }
        }
        else
        {
            cin >> goblin;
            if (type == "+")
            {
                second.push_back(goblin);
            }
            else
            {
                second.push_front(goblin);
            }
        }
        while (first.size() > second.size() + 1)
        {
            second.push_front(first.back());
            first.pop();
        }
        while (second.size() > first.size())
        {
            first.push(second.front());
            second.pop_front();
        }
    }
    return 0;
}