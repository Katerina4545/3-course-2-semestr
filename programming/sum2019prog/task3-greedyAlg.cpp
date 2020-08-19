#include <iostream>
#include <string>

using namespace std; 

//// 1 - клетка синяя, 2 - клетка входит в прямоугольник, 0 - клетка свободна 

class MaxArea{
    public:
    int way;    //1 - вправо, 2 - вниз, 3 - змейка
    int maxArea;
    int x, y;

    MaxArea(){
        way = 0;
        maxArea = 0;
        x = -1;
        y = -1;
    }
};

MaxArea max;
int count = 0;
int n, m;
int** square;

void print(){
    for(int i=0; i<n; i++)
    {
        for(int j=0; j<m; j++)
        {
            cout << square[i][j] << ' ';
        }
    cout << endl;
    }
}

void work();

int main() { 

////считывание данных, запись в массив
    cin >> n >> m;
    square = new int*[m];
    for(int i=0; i<m; i++)
    {
        square[i] = new int[n];
    }

    for(int i=0; i<n; i++)
    {
        for(int j=0; j<m; j++)
        {
            cin >> square[i][j];
        }
    }

////вызов рекурсивной функции - обход квадрата, пока все клетки не 2 или не 1
    work();



    return 0;
}

void changeMax(int areaNow, int x, int y, int way, int maxX, int maxY)
{
    if(areaNow > max.maxArea)
    {
        max.maxArea = areaNow;
        max.x = x;
        max.y = y;
        max.way = 1;

        for(int i = y; i<maxY; i++)
        {
            for(int j=x; j<maxX; j++)
            {
                square[i][j] = 2;
            }
        }
    }  
}

bool allIsPass = 0;
void work()
{
    if (allIsPass) return;

////обход вправо
    int areaNow = 0;
    int x, y;
    for(int i=0; i<n; i++)
    {
        for(int j=0; j<m; j++)
        {
            if(areaNow == 0) {y=i; x=j;}
            if(square[i][j] != 1 && square[i][j] != 2)
            {
                areaNow++;
            }
            else { changeMax(areaNow, x, y, 1, areaNow+x, 1+y); areaNow = 0; }
        }
        changeMax(areaNow, x, y, 1, areaNow+x, 1+y);
    }
    print();
    return;


////обход влево
    areaNow = 0;
    for(int j=0; j<n; j++)
    {
        for(int i=0; i<m; i++)
        {
            if(areaNow == 0) {y=i; x=j;}
            if(square[i][j] != 1 && square[i][j] != 2)
            {
                areaNow++;
            }
            else { changeMax(areaNow, x, y, 1, 1, areaNow); areaNow = 0; }
        }
        changeMax(areaNow, x, y, 1, 1, areaNow);
    }
}