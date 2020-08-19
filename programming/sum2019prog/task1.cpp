#include <iostream>
#include <cstdlib>
#include <ctime>

using namespace std;

int main(){

    int METERS = 100;
    int min = 100;

    int N, x;
    cin >> N;
    for(int i = N; i > 0; i--)
    {
        cin >> x;
        METERS += x;
        if(METERS < min) min = METERS;
    }

    cout << "min is " << min;

    return 0;
}