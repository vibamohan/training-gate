/*
PROG: subset
LANG: C++
*/

#include <bits/stdc++.h>
using namespace std;

int main() {
    freopen("subset.in", "r", stdin);
    freopen("subset.out", "w", stdout);
    long long N; std::cin >> N;
    long long S = N * (N + 1) / 2;

    if (S % 2 != 0) {
        cout << 0 << '\n';
        return 0;
    }

    long long half = S / 2;
    vector<unsigned long long> dp(half + 1, 0ULL);

    dp[0] = 1ULL;
    for (long long i = 1; i <= N; ++i) {
        for (long long j = half; j >= i; --j) {
            dp[j] += dp[j - i];
        }
    }

    cout << (dp[half] / 2ULL) << '\n';
    return 0;
}