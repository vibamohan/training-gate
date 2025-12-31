/*
PROG: sort3
LANG: C++
*/

#include <bits/stdc++.h>
using namespace std;

int f[1005];
int s[3];

int qqsort(int n) {
    int l = 0, mid = s[0], r = s[0] + s[1];
    int ans = 0;

    while (true) {
        int i, j;

        for (i = l; i < s[0]; i++) {
            if (f[i] != 1) break;
            l++;
        }
        if (l == s[0]) break;

        if (f[i] == 2) {
            bool flag = false, ff = true;
            for (j = mid; j < s[0] + s[1]; j++) {
                if (f[j] != 2 && ff) mid = j, ff = false;
                if (f[j] == 1) {
                    swap(f[j], f[i]);
                    ans++;
                    flag = true;
                    break;
                }
            }
            if (!flag)
                for (j = mid; j < s[0] + s[1]; j++)
                    if (f[j] == 3) {
                        swap(f[j], f[i]);
                        ans++;
                        break;
                    }
        } else {
            bool sss = false, ss = true;
            for (j = r; j < n; j++) {
                if (f[j] != 3 && ss) {
                    r = j;
                    ss = false;
                }
                if (f[j] == 1) {
                    swap(f[j], f[i]);
                    ans++;
                    sss = true;
                    break;
                }
            }
            if (!sss)
                for (j = r; j < n; j++)
                    if (f[j] == 2) {
                        swap(f[j], f[i]);
                        ans++;
                        break;
                    }
        }
    }

    for (int i = s[0]; i < s[0] + s[1]; i++)
        if (f[i] == 3) ans++;

    return ans;
}

int main() {
    int n;
    freopen("sort3.in", "r", stdin);
    freopen("sort3.out", "w", stdout);

    cin >> n;
    s[0] = s[1] = s[2] = 0;
    for (int i = 0; i < n; i++) {
        cin >> f[i];
        s[f[i] - 1]++;
    }

    cout << qqsort(n) << "\n";
}
