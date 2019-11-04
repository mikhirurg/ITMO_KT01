#include <iostream>
#include <cmath>
#include <string>
using namespace std;

int p[300001];
int size_set[300001];
int max_set[300001];
int min_set[300001];

int find(int x){
    if (p[x]!=x){
        p[x] = find(p[x]);
    }
    return p[x];
}

void union_set(int x, int y){
    x = find(x);
    y = find(y);
    if (x==y) return;
    if (size_set[x]>size_set[y]){
        swap(x,y);
    }
    p[x] = y;
    size_set[y] += size_set[x];
    max_set[y] = fmax(max_set[x], max_set[y]);
    min_set[y] = fmin(min_set[x], min_set[y]);
}

int get_size(int x){
    x = find(x);
    return size_set[x];
}
int get_max(int x){
    x = find(x);
    return max_set[x];
}
int get_min(int x){
    x = find(x);
    return min_set[x];
}

int main(){
    int n;
    cin>>n;
    for (int i = 0; i < n; i++){
        p[i] = i;
        max_set[i] = i;
        min_set[i] = i;
        size_set[i] = 1;
    }
    string oper;
    while (cin>>oper){
        if (oper=="union"){
            int a, b;
            cin>>a>>b;
            a--;
            b--;
            union_set(a,b);
        }
        if (oper=="get"){
            int c;
            cin>>c;
            c--;
            cout<<(get_min(c)+1)<<" "<<(get_max(c)+1)<<" "<<get_size(c)<<endl;
        }
    }
    return 0;
}