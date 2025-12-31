/*
PROG: hamming
LANG: C++
*/

#include <bits/stdc++.h>
using namespace std;

int n, b, d;
vector<int> path;

bool isValid(int x) {
    for (int y : path) {
        int diff = x ^ y;
        int cnt = 0;
        while (diff) {
            diff &= diff - 1; // count set bits
            cnt++;
        }
        if (cnt < d) return false;
    }
    return true;
}

void dfs(int start, int largest) {
    if ((int)path.size() == n) return;

    for (int i = start + 1; i < largest; i++) {
        if (isValid(i)) {
            path.push_back(i);
            dfs(i, largest);
            if ((int)path.size() == n) return;
        }
    }
}

int main() {
    freopen("hamming.in", "r", stdin);
    freopen("hamming.out", "w", stdout);

    cin >> n >> b >> d;
    int largest = 1 << b;
    path.push_back(0);
    dfs(0, largest);

    for (int i = 0; i < n; i++) {
        cout << path[i];
        if ((i + 1) % 10 == 0 || i == n - 1)
            cout << '\n';
        else
            cout << ' ';
    }
}
