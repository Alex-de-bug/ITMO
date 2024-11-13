#include <iostream>
#include <queue>
#include <list>
#include <vector>

using namespace std;

struct Node {
    int cost;
    int marked;
    int x;
    int y;
    int predecessor;

    Node(int cost = -1, int marked = 0, int x = -1, int y = -1, int pred = -1) 
        : cost(cost), marked(marked), x(x), y(y), predecessor(pred) {}

    bool operator>(const Node& other) const {
        return this->cost > other.cost;
    }
};

using NodeQueue = priority_queue<Node, vector<Node>, greater<Node> >;

int main() {
    int N, M, start_x, start_y, finish_x, finish_y;
    cin >> N >> M >> start_x >> start_y >> finish_x >> finish_y;
    start_x--;
    start_y--;
    finish_x--;
    finish_y--;
    int size = N * M;

    vector<vector<pair<int, int> > > map(N, vector<pair<int, int> >(M));

    string tmp;
    for (int i = 0; i < N; i++) {
        cin >> tmp;
        for (int j = 0; j < M; j++) {
            if (tmp[j] == '.') {
                map[i][j].first = 1;
                map[i][j].second = 0;
            } else if (tmp[j] == 'W') {
                map[i][j].first = 2;
                map[i][j].second = 0;
            } else {
                map[i][j].first = 666;
                map[i][j].second = 0;
            }
        }
    }

    NodeQueue pq_sorted;
    vector<Node> visited(size);

    pq_sorted.push(Node(0, 1, start_x, start_y));
    visited[start_x * M + start_y] = Node(0, 1, start_x, start_y, -1);

    while (true) {
        if(pq_sorted.empty()) break;
        Node curr = pq_sorted.top();
        pq_sorted.pop();

        int curr_index = curr.x * M + curr.y;
        if (curr_index == finish_x * M + finish_y) break;

        
        int new_y = curr.y-1;
        if (new_y >= 0 && new_y < M && map[curr.x][new_y].first != 666 && !map[curr.x][new_y].second) {
            int new_cost = curr.cost + map[curr.x][new_y].first;
            int next_index = curr.x * M + new_y;
            if (visited[next_index].cost == -1 || visited[next_index].cost > new_cost) {
                pq_sorted.push(Node(new_cost, 1, curr.x, new_y, curr_index));
                visited[next_index] = Node(new_cost, 1, curr.x, new_y, curr_index);
                map[curr.x][new_y].second = 1;
            }
        }
        
        new_y = curr.y+1;
        if (new_y >= 0 && new_y < M && map[curr.x][new_y].first != 666 && !map[curr.x][new_y].second) {
            int new_cost = curr.cost + map[curr.x][new_y].first;
            int next_index = curr.x * M + new_y;
            if (visited[next_index].cost == -1 || visited[next_index].cost > new_cost) {
                pq_sorted.push(Node(new_cost, 1, curr.x, new_y, curr_index));
                visited[next_index] = Node(new_cost, 1, curr.x, new_y, curr_index);
                map[curr.x][new_y].second = 1;
            }
        }

        int new_x = curr.x-1;
        if (new_x >= 0 && new_x < N && map[new_x][curr.y].first != 666 && !map[new_x][curr.y].second) {
            int new_cost = curr.cost + map[new_x][curr.y].first;
            int next_index = new_x * M + curr.y;
            if (visited[next_index].cost == -1 || visited[next_index].cost > new_cost) {
                pq_sorted.push(Node(new_cost, 1, new_x, curr.y, curr_index));
                visited[next_index] = Node(new_cost, 1, new_x, curr.y, curr_index);
                map[new_x][curr.y].second = 1;
            }
        }

        new_x = curr.x+1;
        if (new_x >= 0 && new_x < N && map[new_x][curr.y].first != 666 && !map[new_x][curr.y].second) {
            int new_cost = curr.cost + map[new_x][curr.y].first;
            int next_index = new_x * M + curr.y;
            if (visited[next_index].cost == -1 || visited[next_index].cost > new_cost) {
                pq_sorted.push(Node(new_cost, 1, new_x, curr.y, curr_index));
                visited[next_index] = Node(new_cost, 1, new_x, curr.y, curr_index);
                map[new_x][curr.y].second = 1;
            }
        }

    }

    
    if (visited[finish_x * M + finish_y].cost == -1){
        cout << visited[finish_x * M + finish_y].cost << endl;
        return 0;
    } 

    string way;
    int curr = finish_x * M + finish_y;
    while (curr != -1 && visited[curr].predecessor != -1) {
        int pred = visited[curr].predecessor;
        int diff = curr - pred;
        if (diff == 1) way = 'E' + way;   
        else if (diff == -1) way = 'W' + way; 
        else if (diff == M) way = 'S' + way; 
        else if (diff == -M) way = 'N' + way;
        curr = pred;
    }
    
    cout << visited[finish_x * M + finish_y].cost << endl;
    cout << way;
    return 0;
}