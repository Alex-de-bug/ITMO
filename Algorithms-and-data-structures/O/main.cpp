#include <iostream>
#include <vector>
#include <stack>
using namespace std;

vector<vector<int> > graph;
int colors[101] = {0};

bool dfs(int start){
    stack<pair<int, int> > s;
    pair<int, int> tmp;
    tmp.first = start;
    tmp.second = 1;
    s.push(tmp);

    while (!s.empty()) {
        pair<int, int> top = s.top(); s.pop();
        int v = top.first;
        int color = top.second;

        if (colors[v] == 0) {
            colors[v] = color;
        }

        for (int next : graph[v]) {
            if (colors[next] == 0) { 
                tmp.first = next;
                if(color == 1){
                    tmp.second = 2;
                }else{
                    tmp.second = 1;
                }
                s.push(tmp);
            } else if (colors[next] == color) {
                return false;
            }
        }
    }
    return true;
}

int main() {
    int N, M, x1, x2;
    cin >> N >> M;
    graph.resize(N+1);  

    for (int i = 0; i < M; i++){
        cin >> x1 >> x2;
        graph[x1].push_back(x2);
        graph[x2].push_back(x1);
    }

    bool error = true;
    for (int i = 0; i < N; i++){
        if (colors[i] == 0 && !dfs(i)) {  
            error = false;
            cout << "NO";
            return 0; 
        }
    }

    if (error) cout << "YES"; 
    return 0;
}
