#include <iostream>
#include <cmath>
#include <string>
using namespace std;

struct elem{
    int set;
    struct elem *next;
    struct elem *head;
    struct elem *tail;
    int count;
    int xp;
    int klanXP;
};

elem s[300001];

void init(int n){
    for(int i = 0; i < n; i++){
        s[i].set = i;
        s[i].head = nullptr;
        s[i].head = &s[i];
        s[i].tail = &s[i];
        s[i].count = 1;
        s[i].klanXP = 0;
    }
}

int find(elem& x){
    return x.head->set;
}

void union_set(elem& x, elem& y){
    elem* px1 = x.head;
    elem* py1 = y.head;

    if (px1 == py1) {
        return;
    }

    if (px1->count > py1->count){
        elem* tmp = px1;
        px1 = py1;
        py1 = tmp;
    }
    py1->tail->next = px1->head;
    py1->tail = px1->tail;
    py1->count += px1->count;

    elem* i = px1->head;

    int delta = px1->klanXP - py1->klanXP;

    while (i != nullptr){
        i->head = py1;
        i->xp += delta;
        i = i->next;
    }
}

void add_set(elem& x, int v){
    x.head->klanXP += v;
}

int getXP(elem& x){
    return x.head->klanXP + x.xp;
}

int main(){
    int n;
    int m;

    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    cin>>n>>m;
    string oper;

    init(n);

    for(int i = 0; i < m; i++){
        cin>>oper;
        if (oper=="add"){
            int x, v;
            cin>>x>>v;
            x--;
            add_set(s[x], v);
        }
        if (oper=="join"){
            int x, y;
            cin>>x>>y;
            x--;
            y--;
            union_set(s[x], s[y]);
        }
        if (oper=="get"){
            int x;
            cin>>x;
            x--;
            cout<<getXP(s[x])<<endl;
        }
    }

}