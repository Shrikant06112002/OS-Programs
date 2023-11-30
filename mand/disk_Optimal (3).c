#include <stdio.h>
#include <stdbool.h>

bool search(int key, int fr[], int frSize) {
    for (int i = 0; i < frSize; i++) {
        if (fr[i] == key) {
            return true;
        }
    }
    return false;
}

int predict(int pg[], int pgSize, int fr[], int frSize, int index) {
    int res = -1, farthest = index;
    for (int i = 0; i < frSize; i++) {
        int j;
        for (j = index; j < pgSize; j++) {
            if (fr[i] == pg[j]) {
                if (j > farthest) {
                    farthest = j;
                    res = i;
                }
                break;
            }
        }
        if (j == pgSize) {
            return i;
        }
    }
    return (res == -1) ? 0 : res;
}

void optimalPage(int pg[], int pn, int fn) {
    int fr[fn];
    int hit = 0;
    for (int i = 0; i < fn; i++) {
        fr[i] = -1;
    }
    for (int i = 0; i < pn; i++) {
        // Page found in a frame: HIT
        if (search(pg[i], fr, fn)) {
            hit++;
            continue;
        }
        // Page not found in a frame: MISS
        // If there is space available in frames.
        if (fn > i) {
            fr[i] = pg[i];
        } 
        else {
            int j = predict(pg, pn, fr, fn, i + 1);
            fr[j] = pg[i];
        }
    }
    printf("No. of hits = %d\n", hit);
    printf("No. of misses = %d\n", pn - hit);
}

int main() {
    int pg[] = { 7, 0, 1, 2, 0, 3, 0, 4, 2, 3, 0, 3, 2 };
    int pn = sizeof(pg) / sizeof(pg[0]);
    int fn = 4;
    optimalPage(pg, pn, fn);
    return 0;
}
