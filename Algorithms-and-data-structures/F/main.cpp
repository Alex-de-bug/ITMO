#include <iostream>
#include <vector>

using namespace std;

int partition(vector<string> &arr, int low, int high)
{
    string op = arr[high];
    int i = low - 1;
    for (int j = low; j < high; j++)
    {
        string first_pair = arr[j] + op;
        string second_pair = op + arr[j];
        if (first_pair > second_pair)
        {
            i++;
            swap(arr[i], arr[j]);
        }
    }
    swap(arr[i + 1], arr[high]);
    return i + 1;
}

void qSort(vector<string> &arr, int low, int high)
{
    if (low < high)
    {
        int pi = partition(arr, low, high);

        qSort(arr, low, pi - 1);  // Сортировка левой части
        qSort(arr, pi + 1, high); // Сортировка правой части
    }
}

int main()
{
    vector<string> particles;
    string inp;
    while (cin >> inp)
    {
        particles.push_back(inp);
    }
    // particles = {"10", "07", "8", "9", "0001", "05"};
    qSort(particles, 0, particles.size() - 1);

    for (string x : particles)
    {
        cout << x;
    }
    cout << endl;
    return 0;
}
