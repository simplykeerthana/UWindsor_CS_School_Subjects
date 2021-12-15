#include <fstream>
#include <iostream>
#include <sstream>
#include <string>
#include <vector>
#include <queue>
#include <string>
#include <vector>
#include <queue>
#include <ctime>
#include <chrono>

using namespace std;

vector<vector<int>> convert(vector<vector<int>>  a){
    vector<vector<int>>  adjList(a.size());
    for (int i = 0; i < a.size(); i++)
    {
         
        for (int j = 0; j < a[i].size(); j++)
        {
            if (a[i][j] == 1)
            {
                adjList[i].push_back(j);
            }
        }
    }
    return adjList;
}

bool possibleBipartition(vector<vector<int>> edges) 
{
    int N = edges.size();
    vector<vector<int>> adj(N + 1); // adjacency matrix
    vector<int> color(N + 1, 0); 
    vector<bool> explored(N + 1, false); 

    for (auto &edge: edges) {
        adj[edge[0]].push_back(edge[1]);
        adj[edge[1]].push_back(edge[0]);
    }
 
    queue<int> q;        
    for (int i = 1; i <= N; ++i) {
        if (!explored[i]) {
            color[i] = 1;
            q.push(i);
            
            while (!q.empty()) {
                int u = q.front();
                q.pop();
                if (explored[u]) continue;
                
                explored[u] = true;
                for (auto v: adj[u]) {
                    if (color[v] == color[u])
                        return false;

                    color[v] = -color[u];                        
                    q.push(v);
                }
            }
        }
    }
    
    return true;
}
int main(int argc, char** argv){

    string line;

    vector<vector<int>>MatrixL;
    //vector<int>MatrixL;

    //input filename
    string file;

   // cout << "Enter file name: ";

    file = argv[1];
    
    //Number of lines
    int i= 0;

    ifstream coeff(file); //open file
    if(coeff.is_open()){
        while (getline(coeff, line)) {
            MatrixL.push_back(vector<int>());

            // Break down the row into column values
            stringstream split(line);
            int value;

            for(int x=0; x<line.size(); x++){
                MatrixL.back().push_back((int)line[x] - (int)'0');
            }
        }
    }else{
        cout << "Unable to open file. Pls try again!";
    }
    //Write out the content of MatrixL
    for (int i = 0; i < MatrixL.size(); i++) {
        for (int j = 0; j < MatrixL[i].size(); j++)
            cout << MatrixL[i][j] << ' ';

        cout << '\n';
    }
    
   
    vector<vector<int>>AdjacencyL = convert(MatrixL);
    // //Write out the content of AdjacencyL
    // for (int x = 0; x < AdjacencyL.size(); x++) {
    //     for (int y = 0; y < AdjacencyL[y].size(); y++)
    //         cout << AdjacencyL[x][y] << ' ';

    //    // cout << '\n';
    // }
     auto start = std::chrono::steady_clock::now();  

    if(possibleBipartition(AdjacencyL))
        cout<<"Yes, The given graph is Bipartite.\n";
    else
        cout<<"No, The given graph is not Bipartite.\n";

    auto end = std::chrono::steady_clock::now();
    auto elapsed = std::chrono::duration_cast<std::chrono::milliseconds>(end - start);
     cout << "The time it took " << elapsed.count() << " ms." << std::endl; //print out the second
    return 0;
}