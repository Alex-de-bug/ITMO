#include <iostream>
#include <queue>
#include <set>
#include <list>
#include <vector>

using namespace std;

struct Node {
    int cost;
    int marked;
    int x;
    int y;

    Node(int cost, int marked, int x, int y)
        : cost(cost), marked(marked), x(x), y(y) {}

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
    int size = N*M;

    vector<vector<pair<int, int> > > map(N, vector<pair<int, int> >(M)); //first - cost, second - marked

    string tmp;
    for (int i = 0; i < N; i++) {
        cin >> tmp;
        for (int j = 0; j < M; j++) {
            if(tmp[j] == '.'){
                map[i][j].first = 1;
                map[i][j].second = 0;
            }else if(tmp[j] == 'W'){
                map[i][j].first = 2;
                map[i][j].second = 0;
            }else{
                map[i][j].first = 666;
                map[i][j].second = 1;
            }
        }
    }

    NodeQueue pq_sorted; //for sort rel
    queue<pair<int, int> > queue_curr; //main queue with full way (x,y)
    int cost = 0 ; //main cost
    int curr_x = start_x;
    int curr_y = start_y;
    map[curr_x][curr_y].second = 1;
    
    while(true){
        if(curr_y-1 >=0)// up
        {
            if (map[curr_x][curr_y-1].second == 0)
            {
                int x = curr_x;
                int y = curr_y-1;
                pq_sorted.push(Node(map[x][y].first, map[x][y].second, x, y));
            }            
        }
        if(curr_y+1 < M)// down
        {
            if (map[curr_x][curr_y+1].second == 0)
            {
                int x = curr_x;
                int y = curr_y+1;
                pq_sorted.push(Node(map[x][y].first, map[x][y].second, x, y));
            } 
        }
        if(curr_x-1 >=0)// left
        {
            if (map[curr_x-1][curr_y].second == 0)
            {
                int x = curr_x-1;
                int y = curr_y;
                pq_sorted.push(Node(map[x][y].first, map[x][y].second, x, y));
            } 
        }
        if(curr_x+1 < N)// right
        {
            if (map[curr_x+1][curr_y].second == 0)
            {
                int x = curr_x+1;
                int y = curr_y;
                pq_sorted.push(Node(map[x][y].first, map[x][y].second, x, y));
            } 
        }

        if(!pq_sorted.empty()){
            queue_curr.push(make_pair(curr_x,curr_y));
            Node new_pos = pq_sorted.top();
            curr_x = new_pos.x;
            curr_y = new_pos.y;
            map[curr_x][curr_y].second = 1;
            cost += map[curr_x][curr_y].first;
        }
        else if(!queue_curr.empty()){
            cost -= map[curr_x][curr_y].first;
            
            curr_x = queue_curr.front().first;
            curr_y = queue_curr.front().second;
            queue_curr.pop();
        }else{
            cout << "-1";
            return 0;
        }
        while(!pq_sorted.empty()) pq_sorted.pop();
        cout << "x: " << curr_x << "y: " << curr_y << " cost: " << cost << endl;
        if(curr_x == finish_x && curr_y == finish_y) break;
    }
    cout << cost << endl;

    // for (int i = 0; i < N; i++) {
    //     for (int j = 0; j < M; j++) {
    //         cout << map[i][j].first << " ";
    //     }
    //     cout << endl;
    // }
    

    return 0;
}