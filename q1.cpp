#include <iostream>
#include <fstream> //for CSV files

using std::cout, std::endl;

void dataSet1();

int main()
{
    // cout << "Chicken Nuggets";

    //set seed to Ern Qi id
    srand(1211101398);

    cout << "Set 1 : 100 Numbers\n";
    for (int i = 1; i <= 100; i++)
    {
        int random = rand();
        // cout << i << ": " << random << "\n";
        cout << random << " ";
    }

    cout << endl;

    cout << "Set 2 : 1000\n";
    for (int i = 1; i <= 1000; i++)
    {
        int random = rand();
        // cout << i << ": " << random << "\n";
        cout << random << " ";
    }

    cout << endl;

    cout << "Set 3 : 10000\n";
    for (int i = 1; i <= 10000; i++)
    {
        int random = rand();
        // cout << i << ": " << random << "\n";
        cout << random << " ";
    }

    cout << endl;

    cout << "Set 4 : 100000\n";
    for (int i = 1; i <= 1000; i++)
    {
        int random = rand();
        // cout << i << ": " << random << "\n";
        cout << random << " ";
    }

    cout << endl;

    cout << "Set 5 : 500000\n";
    for (int i = 1; i <= 500000; i++)
    {
        int random = rand();
        // cout << i << ": " << random << "\n";
        cout << random << " ";
    }

    cout << endl;

    cout << "Set 6 : 1000000\n";
    for (int i = 1; i <= 1000000; i++)
    {
        int random = rand();
        // cout << i << ": " << random << "\n";
        cout << random << " ";
    }
    
    
    return 0;
}

void dataSet1() {


}