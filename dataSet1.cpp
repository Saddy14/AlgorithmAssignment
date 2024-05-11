#include <iostream>
#include <fstream> //for CSV files

using std::cout, std::endl, std::ofstream; //don't use using namespace std VERY bad practice

int main() {

    // cout << "Chicken Nuggets";
    ofstream file; //output file stream object

    //set seed to Ern Qi id
    srand(1211101398);

    file.open("Set1.csv"); //Create A File for writing 

    cout << "Set 1 : 100 Numbers\n";
    for (int i = 1; i <= 100; i++)
    {
        int random = rand();
        file << random << endl;
    }

    file.close(); //close the file

    file.open("Set2.csv");

    cout << "Set 2 : 1000 Numbers\n";
    for (int i = 1; i <= 1000; i++)
    {
        int random = rand();
        file << random << endl;
    }

    file.close();

    file.open("Set3.csv");

    cout << "Set 3 : 10000 Numbers\n";
    for (int i = 1; i <= 10000; i++)
    {
        int random = rand();
        file << random << endl;
    }

    file.close();

    file.open("Set4.csv");

    cout << "Set 4 : 100000 Numbers\n";
    for (int i = 1; i <= 100000; i++)
    {
        int random = rand();
        file << random << endl;
    }

    file.close();

    file.open("Set5.csv");

    cout << "Set 5 : 500000 Numbers\n";
    for (int i = 1; i <= 500000; i++)
    {
        int random = rand();
        file << random << endl;
    }

    file.close();

    file.open("Set6.csv");

    cout << "Set 6 : 1000000 Numbers\n";
    for (int i = 1; i <= 1000000; i++)
    {
        int random = rand();
        file << random << endl;
    }

    file.close();
    
    // std::string freePizza[] = {"Pizza1", "Pizza2", "Pizza3"};

    // std::string *pFree = freePizza;

    // cout << "\n" << *pFree;
    
    return 0;
}